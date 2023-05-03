package co.mensclothing.service;

import co.mensclothing.dto.request.OrderRequest;
import co.mensclothing.dto.response.OrderSaleConsolidateResponse;
import co.mensclothing.dto.response.OrderSaleResponse;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderSaleResponse save(OrderRequest orderRequest);


    List<OrderSaleResponse> getOrders(List<Long> idOrders);
    Optional<OrderSaleResponse> findById(Long id);
    List<OrderSaleConsolidateResponse> getOrderConsolidateData(List<OrderSaleResponse>  responseList);

}
