package uz.pdp.online.lesson_3_1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_3_1.Entity.Monoblock;
import uz.pdp.online.lesson_3_1.Entity.Product;
import uz.pdp.online.lesson_3_1.Repository.MonoblockRepository;
import uz.pdp.online.lesson_3_1.Repository.ProductRepository;
import uz.pdp.online.lesson_3_1.payloadDto.ApiResponse;
import uz.pdp.online.lesson_3_1.payloadDto.MonoblockDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/monoblock")
public class MonoblockController {
    @Autowired
    MonoblockRepository monoblockRepository;
    @Autowired
    ProductRepository productRepository;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping
    public ResponseEntity add(@RequestBody MonoblockDto monoblockDto) {
        Optional<Product> optionalProduct = productRepository.findById(monoblockDto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product not found", false));

        Monoblock monoblock = new Monoblock();
        monoblock.setBrand(monoblockDto.getBrand());
        monoblock.setRAM(monoblockDto.getRAM());
        monoblock.setCPU(monoblockDto.getCPU());
        monoblock.setSSD(monoblockDto.getSSD());
        monoblock.setScreenSize(monoblockDto.getScreenSize());
        monoblock.setHardDisk(monoblockDto.getHardDisk());
        monoblock.setProduct(optionalProduct.get());
        monoblockRepository.save(monoblock);
        return ResponseEntity.ok(new ApiResponse("Monoblock added", true));
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id, @RequestBody MonoblockDto monoblockDto) {
        Optional<Monoblock> optionalMonoblock = monoblockRepository.findById(id);
        if (!optionalMonoblock.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Monoblock id not found", false));
        Optional<Product> optionalProduct = productRepository.findById(monoblockDto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product not found", false));

        Monoblock monoblock = optionalMonoblock.get();
        monoblock.setBrand(monoblockDto.getBrand());
        monoblock.setRAM(monoblockDto.getRAM());
        monoblock.setCPU(monoblockDto.getCPU());
        monoblock.setSSD(monoblockDto.getSSD());
        monoblock.setScreenSize(monoblockDto.getScreenSize());
        monoblock.setHardDisk(monoblockDto.getHardDisk());
        monoblock.setProduct(optionalProduct.get());
        monoblockRepository.save(monoblock);
        return ResponseEntity.ok(new ApiResponse("Monoblock edited", true));
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public ResponseEntity get() {
        List<Monoblock> monoblockList = monoblockRepository.findAll();
        return ResponseEntity.ok(monoblockList);
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        Optional<Monoblock> optionalMonoblock = monoblockRepository.findById(id);
        if (!optionalMonoblock.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Monoblock id not found", false));
        return ResponseEntity.ok(optionalMonoblock.get());
    }


    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            monoblockRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Monoblock deleted", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Monoblock id not found", false));
        }
    }

}
