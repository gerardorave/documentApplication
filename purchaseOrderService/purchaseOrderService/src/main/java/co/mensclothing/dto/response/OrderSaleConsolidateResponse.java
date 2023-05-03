package co.mensclothing.dto.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSaleConsolidateResponse implements Serializable {
    private String commercialPartner;
    private String orderId;
    private String startDate;
    private String endDate;
    private String typeOfOrder;
    private String salesPointName;
    private String salesPointAddress;
    private String salesPointCity;
    private String salesPointCountry;
}
