package co.mensclothing.dto.response;

import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommercialPartnerResponse implements Serializable {


    private Long id;

    private String name;

    private String description;
}
