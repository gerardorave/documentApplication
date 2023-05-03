package co.mensclothing.dto.response;

import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSaleResponse implements Serializable {

    private Long id;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private TypeOfOrderResponse typeOfOrder;

    private SalePointResponse salePoint;

    private List<OrderProductResponse> orderProducts;

}