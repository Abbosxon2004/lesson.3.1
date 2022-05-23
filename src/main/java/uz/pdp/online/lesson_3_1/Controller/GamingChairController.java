package uz.pdp.online.lesson_3_1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_3_1.Entity.GamingChair;
import uz.pdp.online.lesson_3_1.Entity.Printer;
import uz.pdp.online.lesson_3_1.Entity.Product;
import uz.pdp.online.lesson_3_1.Repository.GamingChairRepository;
import uz.pdp.online.lesson_3_1.Repository.ProductRepository;
import uz.pdp.online.lesson_3_1.payloadDto.ApiResponse;
import uz.pdp.online.lesson_3_1.payloadDto.GamingChairDto;
import uz.pdp.online.lesson_3_1.payloadDto.PrinterDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/gaming-chair")
public class GamingChairController {
    @Autowired
    GamingChairRepository gamingChairRepository;
    @Autowired
    ProductRepository productRepository;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping
    public ResponseEntity add(@RequestBody GamingChairDto gamingChairDto) {
        Optional<Product> optionalProduct = productRepository.findById(gamingChairDto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product not found", false));

        GamingChair gamingChair = new GamingChair();
        gamingChair.setBrand(gamingChairDto.getBrand());
        gamingChair.setColor(gamingChairDto.getColor());
        gamingChair.setWeightLimit(gamingChairDto.getWeightLimit());
        gamingChair.setHeadrest(gamingChairDto.isHeadrest());
        gamingChair.setProduct(optionalProduct.get());
        gamingChairRepository.save(gamingChair);
        return ResponseEntity.ok(new ApiResponse("Gaming chair added", true));
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id, @RequestBody GamingChairDto gamingChairDto) {
        Optional<GamingChair> optionalGamingChair = gamingChairRepository.findById(id);
        if (!optionalGamingChair.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Gaming chair id not found", false));
        Optional<Product> optionalProduct = productRepository.findById(gamingChairDto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product not found", false));

        GamingChair gamingChair = optionalGamingChair.get();
        gamingChair.setBrand(gamingChairDto.getBrand());
        gamingChair.setColor(gamingChairDto.getColor());
        gamingChair.setWeightLimit(gamingChairDto.getWeightLimit());
        gamingChair.setHeadrest(gamingChairDto.isHeadrest());
        gamingChair.setProduct(optionalProduct.get());
        gamingChairRepository.save(gamingChair);

        return ResponseEntity.ok(new ApiResponse("Gaming chair edited", true));
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public ResponseEntity get() {
        List<GamingChair> gamingChairs = gamingChairRepository.findAll();
        return ResponseEntity.ok(gamingChairs);
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        Optional<GamingChair> optionalGamingChair = gamingChairRepository.findById(id);
        if (!optionalGamingChair.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Gaming chair id not found", false));
        return ResponseEntity.ok(optionalGamingChair.get());
    }


    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            gamingChairRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Gaming chair deleted", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Gaming chair id not found", false));
        }
    }

}
