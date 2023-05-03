package co.mensclothing.dto;

import co.mensclothing.dto.response.SalePointResponse;
import co.mensclothing.dto.response.TypeOfOrderResponse;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSaleDto implements Serializable {

    private Long id;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private TypeOfOrderResponse typeOfOrder;
    private SalePointResponse salePoint;

}
