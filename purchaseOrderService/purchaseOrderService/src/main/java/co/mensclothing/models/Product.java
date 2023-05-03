package co.mensclothing.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="PRODUCT")
@Getter
@Setter
@JsonIgnoreProperties({"order"})
public class Product implements Serializable {

    @Id
    @SequenceGenerator(name="product_id_seq-gen",sequenceName="product_id_seq", initialValue=10, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="product_id_seq-gen")
    @Column(name = "ID")
    private Long id;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="DESCRIPTION", nullable = false)
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch =
            FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<OrderProduct> orderProducts;

}