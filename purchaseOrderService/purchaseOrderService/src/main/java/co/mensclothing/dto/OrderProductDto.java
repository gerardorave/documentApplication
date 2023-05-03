package co.mensclothing.dto;

import co.mensclothing.dto.response.ProductResponse;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDto implements Serializable {

    private Long id;

    private Long orderSaleId;

    private ProductResponse product;

    private Integer quantity;
}