package co.mensclothing.dto.response;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfOrderResponse implements Serializable {

    private Long id;

    private String type;

}
