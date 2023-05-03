package co.mensclothing.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "ORDER_SALE")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true, value={"hibernateLazyInitializer", "handler"})
public class OrderSale {

    @Id
    @SequenceGenerator(name="order_sale_id_seq-gen",sequenceName="order_sale_id_seq", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="order_sale_id_seq-gen")
    private Long id;


    @Column(name = "START_DATE", nullable = false)
    private ZonedDateTime startDate;

    @Column(name = "END_DATE")
    private ZonedDateTime endDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_OF_ORDER_ID", referencedColumnName = "ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TypeOfOrder typeOfOrder;


    @ManyToOne(cascade = CascadeType.MERGE, fetch =
            FetchType.LAZY)
    @JoinColumn(name="SALE_POINT_ID", referencedColumnName = "ID", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private SalePoint salePoint;

    @OneToMany(mappedBy = "orderSale", cascade = CascadeType.ALL, fetch =
            FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<OrderProduct> orderProducts;
}