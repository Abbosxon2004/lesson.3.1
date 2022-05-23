package uz.pdp.online.lesson_3_1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_3_1.Entity.Computer;
import uz.pdp.online.lesson_3_1.Entity.Laptop;
import uz.pdp.online.lesson_3_1.Entity.Product;
import uz.pdp.online.lesson_3_1.Repository.LaptopRepository;
import uz.pdp.online.lesson_3_1.Repository.ProductRepository;
import uz.pdp.online.lesson_3_1.payloadDto.ApiResponse;
import uz.pdp.online.lesson_3_1.payloadDto.LaptopDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/laptop")
public class LaptopConotroller {
    @Autowired
    LaptopRepository laptopRepository;
    @Autowired
    ProductRepository productRepository;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping
    public ResponseEntity add(@RequestBody LaptopDto laptopDto) {
        Optional<Product> optionalProduct = productRepository.findById(laptopDto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product not found", false));

        Laptop laptop = new Laptop();
        laptop.setBrand(laptopDto.getBrand());
        laptop.setRAM(laptopDto.getRAM());
        laptop.setCPU(laptopDto.getCPU());
        laptop.setSSD(laptopDto.getSSD());
        laptop.setVideoCard(laptopDto.getVideoCard());
        laptop.setHardDisk(laptopDto.getHardDisk());
        laptop.setProduct(optionalProduct.get());
        laptopRepository.save(laptop);
        return ResponseEntity.ok(new ApiResponse("Laptop added", true));
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id, @RequestBody LaptopDto laptopDto) {
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        if (!optionalLaptop.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Laptop id not found", false));
        Optional<Product> optionalProduct = productRepository.findById(laptopDto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product not found", false));

        Laptop laptop = optionalLaptop.get();
        laptop.setBrand(laptopDto.getBrand());
        laptop.setRAM(laptopDto.getRAM());
        laptop.setCPU(laptopDto.getCPU());
        laptop.setSSD(laptopDto.getSSD());
        laptop.setVideoCard(laptopDto.getVideoCard());
        laptop.setHardDisk(laptopDto.getHardDisk());
        laptop.setProduct(optionalProduct.get());
        laptopRepository.save(laptop);
        return ResponseEntity.ok(new ApiResponse("Laptop edited", true));
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public ResponseEntity get() {
        List<Laptop> laptops = laptopRepository.findAll();
        return ResponseEntity.ok(laptops);
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        if (!optionalLaptop.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Laptop id not found", false));
        return ResponseEntity.ok(optionalLaptop.get());
    }


    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            laptopRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Laptop deleted", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Laptop id not found", false));
        }
    }
}
