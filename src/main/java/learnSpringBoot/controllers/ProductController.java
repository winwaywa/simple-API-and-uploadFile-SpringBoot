package learnSpringBoot.controllers;

import learnSpringBoot.model.Product;
import learnSpringBoot.model.ResponseObject;
import learnSpringBoot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //báo cho thằng java spring biết rằng class này làm controller
@RequestMapping(path="/api/v1/products") // định tuyến routing
public class ProductController {
    // DI = Dependency Injection
    @Autowired // Đối tượng repository sẽ được tạo ra chỉ một lần ngay khi app run.
    private ProductRepository repository;


    // get all
    @GetMapping("")
    // http://localhost:8080/api/v1/products
    List<Product> getAllProducts(){
         return repository.findAll();
    }

    // get one
    @GetMapping("/{id}")
    // nên return {status,message,data}
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<Product> foundProduct = repository.findById(id);
        if(foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Querry product successfully!", foundProduct)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Can't find product with id"+id, "")
            );
        }
    }

    // insert
    @PostMapping("")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
        List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
        if(foundProducts.size()>0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("ok","products name already taken!","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","insert product successfully!",repository.save(newProduct))
        );
    }

    //update, upsert = update if exist, otherwise insert new
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
        Product updatedProduct = repository.findById(id).map(product -> {
            product.setProductName(newProduct.getProductName());
            product.setPrice(newProduct.getPrice());
            product.setYear(newProduct.getYear());
            product.setUrl(newProduct.getUrl());
            return repository.save(product);
        }).orElseGet(()->{
            newProduct.setId(id);
            return repository.save((newProduct ));
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","updated product successfully!",updatedProduct)
        );
    }

    // Delete
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        boolean exist = repository.existsById(id);
        if(exist){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Deleted product successfully",""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed","Can't find product with id"+id,"")
        );

    }
}
