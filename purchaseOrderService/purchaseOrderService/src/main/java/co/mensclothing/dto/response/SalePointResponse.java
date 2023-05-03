package co.mensclothing.dto.response;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalePointResponse implements Serializable {

    private Long id;

    private String name;

    private String address;

    private String city;

    private String country;

    private CommercialPartnerResponse commercialPartner;

}
