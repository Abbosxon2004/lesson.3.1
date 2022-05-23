package uz.pdp.online.lesson_3_1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.lesson_3_1.Entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
