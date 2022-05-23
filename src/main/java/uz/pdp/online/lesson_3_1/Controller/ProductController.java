package uz.pdp.online.lesson_3_1.Controller;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_3_1.Entity.Category;
import uz.pdp.online.lesson_3_1.Entity.Product;
import uz.pdp.online.lesson_3_1.Repository.CategoryRepository;
import uz.pdp.online.lesson_3_1.Repository.ProductRepository;
import uz.pdp.online.lesson_3_1.payloadDto.ApiResponse;
import uz.pdp.online.lesson_3_1.payloadDto.ProductDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping
    public ResponseEntity addProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Category not found", false));
        }
        Product product = new Product();
        product.setCategory(optionalCategory.get());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        productRepository.save(product);

        return ResponseEntity.ok(new ApiResponse("Product added", true));
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public ResponseEntity getProducts() {
        List<Product> productList = productRepository.findAll();
        return ResponseEntity.ok(productList);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product not found", false));
        return ResponseEntity.ok(optionalProduct.get());
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity editProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product not found", false));
        Product product = optionalProduct.get();

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Category not found", false));
        }

        product.setCategory(optionalCategory.get());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        productRepository.save(product);
        return ResponseEntity.ok(new ApiResponse("Prodeuct edited", true));
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        try {
            productRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Product deleted", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product id not found", false));
        }
    }
}
