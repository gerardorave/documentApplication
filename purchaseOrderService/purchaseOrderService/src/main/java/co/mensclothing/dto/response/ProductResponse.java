package co.mensclothing.dto.response;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse implements Serializable {


    private Long id;
    private String name;
    private String description;

}
