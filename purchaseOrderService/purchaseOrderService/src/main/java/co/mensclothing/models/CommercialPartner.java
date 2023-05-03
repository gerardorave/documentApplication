package co.mensclothing.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="COMMERCIAL_PARTNER")
@Getter
@Setter
@JsonIgnoreProperties({"salePoints"})
public class CommercialPartner implements Serializable {

    @Id
    @SequenceGenerator(name="commercial_partner_id_seq-gen",sequenceName="commercial_partner_id_seq", initialValue=4, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="commercial_partner_id_seq-gen")
    @Column(name = "ID")
    private Long id;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="DESCRIPTION", nullable = false)
    private String description;

    @OneToMany(mappedBy = "commercialPartner", cascade = CascadeType.ALL, fetch =
            FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<SalePoint> salePoints;

}