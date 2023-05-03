package co.mensclothing.service.impl;

import co.mensclothing.dto.OrderSaleDto;
import co.mensclothing.dto.OrderProductDto;
import co.mensclothing.dto.request.OrderRequest;
import co.mensclothing.dto.request.ProductsWithQuantitiesRequest;
import co.mensclothing.dto.response.*;
import co.mensclothing.error.ErrorMessages;
import co.mensclothing.error.GenericServiceException;
import co.mensclothing.models.OrderSale;
import co.mensclothing.models.OrderProduct;
import co.mensclothing.repository.jpa.*;
import co.mensclothing.service.OrderService;
import co.mensclothing.util.ZoneDateTimeUtils;
import io.vavr.collection.Seq;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderSaleRepository orderSaleRepository;
    private OrderProductRepository orderProductRepository;

    private ProductRepository productRepository;

    private TypeOfOrderRepository typeOfOrderRepository;

    private SalePointRepository salePointRepository;

    private ModelMapper mapper;


    @Autowired
    public OrderServiceImpl(OrderSaleRepository orderSaleRepository,
                            OrderProductRepository orderProductRepository,
                            ProductRepository productRepository,
                            TypeOfOrderRepository typeOfOrderRepository,
                            SalePointRepository salePointRepository,
                            ModelMapper mapper

    ) {
        this.orderSaleRepository = orderSaleRepository;
        this.mapper = mapper;
        this.salePointRepository = salePointRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
        this.typeOfOrderRepository = typeOfOrderRepository;
    }

    private void throwAndLogValidationError(Seq<String> errors) {
        log.error(ErrorMessages.ERROR_ORDER);
        errors.forEach(log::error);
        throw new GenericServiceException(ErrorMessages.ERROR_ORDER + errors.get(0));
    }

    @Override
    @Transactional
    public OrderSaleResponse save(OrderRequest orderRequest) {
        try {
            ZonedDateTime startDate = ZoneDateTimeUtils.stringToZonedDatetime(orderRequest.getStartDate());
            ZonedDateTime endDate = orderRequest.getEndDate() != null ? ZoneDateTimeUtils.stringToZonedDatetime(orderRequest.getStartDate()) : null;
            OrderSaleDto orderSaleDto = OrderSaleDto.builder()
                    .startDate(startDate)
                    .typeOfOrder(mapper.map(typeOfOrderRepository.findById(orderRequest.getTypeOfOrderId()).get(), TypeOfOrderResponse.class))
                    .salePoint(mapper.map(salePointRepository.findById(orderRequest.getSalePointId()).get(), SalePointResponse.class)).build();
            if(endDate != null)
                orderSaleDto.setEndDate(endDate);
            OrderSale order = mapper.map(orderSaleDto, OrderSale.class);
            order = orderSaleRepository.save(order);
            List<OrderProductResponse> orderProductsResponse = new ArrayList<>();
            for (ProductsWithQuantitiesRequest productsWithQuantities : orderRequest.getProductsAndQuantities()) {
                OrderProductDto orderProductDto = OrderProductDto.builder()
                        .orderSaleId(order.getId())
                        .product(mapper.map(productRepository.findById(productsWithQuantities.getIdProduct()).get(), ProductResponse.class))
                        .quantity(productsWithQuantities.getQuantity()).build();
                OrderProduct orderProduct = orderProductRepository.save(mapper.map(orderProductDto, OrderProduct.class));
                orderProductsResponse.add(mapper.map(
                        orderProduct,
                        OrderProductResponse.class
                ));
            }
            OrderSaleResponse response = mapper.map(order, OrderSaleResponse.class);
            response.setOrderProducts(orderProductsResponse);
            return response;

        } catch (Exception e) {
            Seq<String> errors = io.vavr.collection.List.of(e.getMessage(), e.getStackTrace().toString());
            throwAndLogValidationError(errors);
        }
        return null;
    }

    @Override
    public Optional<OrderSaleResponse> findById(Long id) {
        Optional<OrderSale> order = orderSaleRepository.findById(id);
        return mapper.map(order, new TypeToken<Optional<OrderSaleResponse>>() {
        }.getType());
    }

   public  List<OrderSaleResponse> getOrders(List<Long> idOrders) {
        List<OrderSale> orderSaleList= new ArrayList<>();
        for(Long id: idOrders){
            OrderSale orderSale = orderSaleRepository.findById(id).get();
            List<OrderProduct> orderProductList = orderProductRepository.getOrderProductsByOrderId(id);
            orderSale.setOrderProducts(orderProductList);
            orderSaleList.add(orderSale);
        }
         return mapper.map(orderSaleList, new TypeToken<List<OrderSaleResponse>>() {
         }.getType());
   }

   @Override
   public List<OrderSaleConsolidateResponse> getOrderConsolidateData(List<OrderSaleResponse>  responseList) {
        List<OrderSaleConsolidateResponse> OrderSaleConsolidateResponseList = new ArrayList<>();
        for(OrderSaleResponse response: responseList){
            OrderSaleConsolidateResponseList.add(
                    OrderSaleConsolidateResponse.builder()
                            .commercialPartner(response.getSalePoint().getCommercialPartner().getName())
                            .salesPointName(response.getSalePoint().getName())
                            .orderId(response.getId().toString())
                            .typeOfOrder(response.getTypeOfOrder().getType())
                            .startDate(response.getStartDate().toString())
                            .salesPointAddress(response.getSalePoint().getAddress())
                            .salesPointCity(response.getSalePoint().getCity())
                            .salesPointCountry(response.getSalePoint().getCountry()).build()
            );
        }

        return OrderSaleConsolidateResponseList;

   }

}
