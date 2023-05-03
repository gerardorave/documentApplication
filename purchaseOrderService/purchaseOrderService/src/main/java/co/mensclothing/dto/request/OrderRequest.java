package co.mensclothing.dto.request;

import co.mensclothing.models.TypeOfOrder;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotNull(message = "the order customer must have an start date")
    private String startDate;

    private String endDate;

    @NotNull(message = "the type of request id  can't be null")
    private Long typeOfOrderId;


    @NotNull(message = "the sale point id can't be null")
    private Long salePointId;

    @NotNull(message = "the order customer must have at least 1 product associated")
    @NotEmpty(message = "the order has include at least 1 product")
    @Valid
    private List<ProductsWithQuantitiesRequest> productsAndQuantities;

}