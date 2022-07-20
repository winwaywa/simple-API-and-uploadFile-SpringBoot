package learnSpringBoot.database;

import learnSpringBoot.model.Product;
import learnSpringBoot.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// Conect database with mysql using JPA
// -d :pull image và tạo container mysql-learn-spring-boot
// -rm : Khi run tắt thì xoá luôn, lần sau tạo lại
// -e : eviroment variable
//-p: map port; 3309: cổng trên pc, 3306: cổng trên container hub
//--volumn: thư mục trên pc sẽ được map vs thư mục trên container.
/*
    docker run -d --rm --name mysql-learn-spring-boot
    -e MYSQL_ROOT_PASSWORD=1jwdjp
    -e MYSQL_USER = hiep
    -e MYSQL_PASSWORD = 1jwdjp
    -e MYSQL_DATABASE = test_db
    -p 3309:3306
    --volumn mysql-learn-spring-boot-volumn:/var/lib/mysql
    mysql:latest

   // Run:
   docker run --name mysql-learn-spring-boot -d -p 3309:3306 -v mysql-learn-spring-boot-volumn:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=1jwdjp -e MYSQL_DATABASE=test_db mysql:latest
   mysql -h localhost -P 3309 --protocol=tcp -u root -p // connect db
 */

// Test trước ở đây ngon rồi mới connect database
@Configuration
public class Database {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Product productA = new Product("MacBook Pro 15", 2020,2200.0, "");
//                Product productB = new Product("iPad Air Green", 2021,599.0,"");
//                logger.info("insert data: "+productRepository.save(productA));
//                logger.info("insert data: "+productRepository.save(productB));
            }
        };
    }
}
