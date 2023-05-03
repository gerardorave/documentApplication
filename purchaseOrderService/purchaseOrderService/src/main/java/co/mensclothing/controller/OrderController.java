package co.mensclothing.controller;

import co.mensclothing.controller.constant.ControllerConstants;
import co.mensclothing.dto.request.OrderRequest;
import co.mensclothing.dto.response.OrderSaleResponse;
import co.mensclothing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping(ControllerConstants.ORDER_ROOT+"/order")
//@Api(value = "OrderControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

  //  @ApiOperation("getOrderById")
    @GetMapping("/{id}")
    public ResponseEntity<OrderSaleResponse> getOrderById(@PathVariable("id") Long id) {

        return new ResponseEntity(orderService.findById(id), HttpStatus.OK);
    }

   // @ApiOperation("saveOrder")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<OrderSaleResponse> saveOrder(@RequestBody @Valid OrderRequest request) {
        OrderSaleResponse response = orderService.save(request);
        return Mono.justOrEmpty(response);
    }

    @PostMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<OrderSaleResponse> getOrderList(@RequestBody List<Long> orderIdsList) {
        List<OrderSaleResponse> response = orderService.getOrders(orderIdsList);
        return Flux.fromIterable(response);
    }

}
