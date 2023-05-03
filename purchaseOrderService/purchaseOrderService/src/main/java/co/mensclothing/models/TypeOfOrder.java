package co.mensclothing.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="TYPE_OF_ORDER")
@Getter
@Setter
@JsonIgnoreProperties({"order"})
public class TypeOfOrder implements Serializable {

    @Id
    @SequenceGenerator(name="type_of_order_id_seq-gen",sequenceName="type_of_order_id_seq", initialValue=3, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="type_of_order_id_seq-gen")
    @Column(name = "ID")
    private Long id;

    @Column(name="TYPE", nullable = false)
    private String type;

    @OneToMany(mappedBy = "typeOfOrder", cascade = CascadeType.ALL, fetch =
            FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<OrderSale> orderSales;

}