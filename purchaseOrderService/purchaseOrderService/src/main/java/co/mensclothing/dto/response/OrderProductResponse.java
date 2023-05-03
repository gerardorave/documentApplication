package co.mensclothing.dto.response;


import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResponse implements Serializable {

    private Long id;

    private ProductResponse product;

    private Integer quantity;
}
