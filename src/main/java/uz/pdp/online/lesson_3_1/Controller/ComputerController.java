package uz.pdp.online.lesson_3_1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_3_1.Entity.Computer;
import uz.pdp.online.lesson_3_1.Entity.Product;
import uz.pdp.online.lesson_3_1.Repository.ComputerRepository;
import uz.pdp.online.lesson_3_1.Repository.ProductRepository;
import uz.pdp.online.lesson_3_1.payloadDto.ApiResponse;
import uz.pdp.online.lesson_3_1.payloadDto.ComputerDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/computer")
public class ComputerController {
    @Autowired
    ComputerRepository computerRepository;
    @Autowired
    ProductRepository productRepository;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping
    public ResponseEntity addComputer(@RequestBody ComputerDto computerDto) {
        Optional<Product> optionalProduct = productRepository.findById(computerDto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product not found", false));

        Computer computer = new Computer();
        computer.setMotherboard(computerDto.getMotherboard());
        computer.setRAM(computerDto.getRAM());
        computer.setCPU(computerDto.getCPU());
        computer.setSSD(computerDto.getSSD());
        computer.setVideoCard(computerDto.getVideoCard());
        computer.setHardDisk(computerDto.getHardDisk());
        computer.setProduct(optionalProduct.get());
        computerRepository.save(computer);
        return ResponseEntity.ok(new ApiResponse("Computer added", true));
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id, @RequestBody ComputerDto computerDto) {
        Optional<Computer> optionalComputer = computerRepository.findById(id);
        if (!optionalComputer.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Computer id not found", false));
        Optional<Product> optionalProduct = productRepository.findById(computerDto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product not found", false));

        Computer computer = optionalComputer.get();
        computer.setMotherboard(computerDto.getMotherboard());
        computer.setRAM(computerDto.getRAM());
        computer.setCPU(computerDto.getCPU());
        computer.setSSD(computerDto.getSSD());
        computer.setVideoCard(computerDto.getVideoCard());
        computer.setHardDisk(computerDto.getHardDisk());
        computer.setProduct(optionalProduct.get());
        computerRepository.save(computer);
        return ResponseEntity.ok(new ApiResponse("Computer edited", true));
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public ResponseEntity get() {
        List<Computer> computerList = computerRepository.findAll();
        return ResponseEntity.ok(computerList);
    }

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        Optional<Computer> optionalComputer = computerRepository.findById(id);
        if (!optionalComputer.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Computer id not found", false));
        return ResponseEntity.ok(optionalComputer.get());
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            computerRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Computer deleted", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Computer id not found", false));
        }
    }
}
