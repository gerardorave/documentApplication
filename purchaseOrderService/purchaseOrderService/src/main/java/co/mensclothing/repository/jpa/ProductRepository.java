package co.mensclothing.repository.jpa;

import co.mensclothing.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

}