package co.mensclothing.repository.jpa;

import co.mensclothing.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    @Query(value = "Select * from order_product op where op.order_sale_id = :orderId", nativeQuery = true)
    List<OrderProduct> getOrderProductsByOrderId(@Param("orderId") Long orderId);
}