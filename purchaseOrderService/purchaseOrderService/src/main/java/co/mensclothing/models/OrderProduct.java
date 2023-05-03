package co.mensclothing.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ORDER_PRODUCT")
@Getter
@Setter
public class OrderProduct {

    @Id
    @SequenceGenerator(name="order_product_id_seq-gen",sequenceName="order_product_id_seq", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="order_product_id_seq-gen")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_sale_id", referencedColumnName = "ID")
    private OrderSale orderSale;

    @ManyToOne(optional= false, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "ID")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}