package uz.pdp.online.lesson_3_1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_3_1.Entity.Computer;
import uz.pdp.online.lesson_3_1.Entity.Display;
import uz.pdp.online.lesson_3_1.Entity.Product;
import uz.pdp.online.lesson_3_1.Repository.DisplayRepository;
import uz.pdp.online.lesson_3_1.Repository.ProductRepository;
import uz.pdp.online.lesson_3_1.payloadDto.ApiResponse;
import uz.pdp.online.lesson_3_1.payloadDto.ComputerDto;
import uz.pdp.online.lesson_3_1.payloadDto.DisplayDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/display")
public class DisplayController {
    @Autowired
    DisplayRepository displayRepository;
    @Autowired
    ProductRepository productRepository;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping
    public ResponseEntity add(@RequestBody DisplayDto displayDto) {
        Optional<Product> optionalProduct = productRepository.findById(displayDto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product not found", false));

        Display display=new Display();
        display.setBrand(displayDto.getBrand());
        display.setCurvedScreen(displayDto.isCurvedScreen());
        display.setDiagonalScreen(displayDto.getDiagonalScreen());
        display.setScreenRefreshRate(displayDto.getScreenRefreshRate());
        display.setVideoType(displayDto.getVideoType());
        display.setResponseTime(displayDto.getResponseTime());
        display.setProduct(optionalProduct.get());
        displayRepository.save(display);
        return ResponseEntity.ok(new ApiResponse("Display added", true));
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id, @RequestBody DisplayDto displayDto) {
        Optional<Display> optionalDisplay = displayRepository.findById(id);
        if (!optionalDisplay.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Display id not found", false));
        Optional<Product> optionalProduct = productRepository.findById(displayDto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product not found", false));

        Display display = optionalDisplay.get();
        display.setBrand(displayDto.getBrand());
        display.setCurvedScreen(displayDto.isCurvedScreen());
        display.setDiagonalScreen(displayDto.getDiagonalScreen());
        display.setScreenRefreshRate(displayDto.getScreenRefreshRate());
        display.setVideoType(displayDto.getVideoType());
        display.setResponseTime(displayDto.getResponseTime());
        display.setProduct(optionalProduct.get());
        displayRepository.save(display);
        return ResponseEntity.ok(new ApiResponse("Display edited", true));
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public ResponseEntity get() {
        List<Display> displayList = displayRepository.findAll();
        return ResponseEntity.ok(displayList);
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        Optional<Display> optionalDisplay = displayRepository.findById(id);
        if (!optionalDisplay.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Display id not found", false));
        return ResponseEntity.ok(optionalDisplay.get());
    }


    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            displayRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Display deleted", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Display id not found", false));
        }
    }
}
