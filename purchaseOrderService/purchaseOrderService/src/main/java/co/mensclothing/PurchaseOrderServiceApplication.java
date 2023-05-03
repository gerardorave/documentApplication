package co.mensclothing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class PurchaseOrderServiceApplication {

    public static void main(String[]args) {
        SpringApplication.run(PurchaseOrderServiceApplication.class, args);
    }
}
