package co.mensclothing.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="SALE_POINT")
@Getter
@Setter
@JsonIgnoreProperties({"commercialPartner"})
public class SalePoint implements Serializable {

    @Id
    @SequenceGenerator(name="sale_point_id_seq-gen",sequenceName="sale_point_id_seq", initialValue=9, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="sale_point_id_seq-gen")
    @Column(name = "ID")
    private Long id;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="ADDRESS", nullable = false)
    private String address;

    @Column(name="CITY", nullable = false)
    private String city;

    @Column(name="COUNTRY", nullable = false)
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMERCIAL_PARTNER_ID", referencedColumnName = "ID", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CommercialPartner commercialPartner;

    @OneToMany(mappedBy = "salePoint", cascade = CascadeType.ALL, fetch =
            FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<OrderSale> orderSales;

}