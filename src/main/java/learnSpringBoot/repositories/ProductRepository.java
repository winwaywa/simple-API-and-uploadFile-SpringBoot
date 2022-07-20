package learnSpringBoot.repositories;

import learnSpringBoot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Nơi lưu trữ data
public interface ProductRepository extends JpaRepository<Product, Long> { //<Entity type, type của khoá chính>

    List<Product> findByProductName(String productName); // đúng format là nó tự hiểu, khỏi cần ghi đè body
}
