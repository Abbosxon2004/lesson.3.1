package uz.pdp.online.lesson_3_1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_3_1.Entity.Laptop;
import uz.pdp.online.lesson_3_1.Entity.Printer;
import uz.pdp.online.lesson_3_1.Entity.Product;
import uz.pdp.online.lesson_3_1.Repository.PrinterRepository;
import uz.pdp.online.lesson_3_1.Repository.ProductRepository;
import uz.pdp.online.lesson_3_1.payloadDto.ApiResponse;
import uz.pdp.online.lesson_3_1.payloadDto.LaptopDto;
import uz.pdp.online.lesson_3_1.payloadDto.PrinterDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/printer")
public class PrinterController {
    @Autowired
    PrinterRepository printerRepository;

    @Autowired
    ProductRepository productRepository;

    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping
    public ResponseEntity add(@RequestBody PrinterDto printerDto) {
        Optional<Product> optionalProduct = productRepository.findById(printerDto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product not found", false));

        Printer printer = new Printer();
        printer.setBrand(printerDto.getBrand());
        printer.setColorfull(printerDto.isColorfull());
        printer.setTwoSidedPrinting(printerDto.getTwoSidedPrinting());
        printer.setDeviceFunction(printer.getDeviceFunction());
        printer.setMaxFormat(printerDto.getMaxFormat());
        printer.setNonColorPrintingSpeed(printerDto.getNonColorPrintingSpeed());
        printer.setColorPrintingSpeed(printerDto.getColorPrintingSpeed());
        printer.setPrintingTechnology(printerDto.getPrintingTechnology());
        printer.setConnectivity(printerDto.getConnectivity());
        printer.setProduct(optionalProduct.get());
        printerRepository.save(printer);
        return ResponseEntity.ok(new ApiResponse("Printer added", true));
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id, @RequestBody PrinterDto printerDto) {
        Optional<Printer> optionalPrinter = printerRepository.findById(id);
        if (!optionalPrinter.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Printer id not found", false));
        Optional<Product> optionalProduct = productRepository.findById(printerDto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Product not found", false));

        Printer printer = optionalPrinter.get();
        printer.setBrand(printerDto.getBrand());
        printer.setColorfull(printerDto.isColorfull());
        printer.setTwoSidedPrinting(printerDto.getTwoSidedPrinting());
        printer.setDeviceFunction(printer.getDeviceFunction());
        printer.setMaxFormat(printerDto.getMaxFormat());
        printer.setNonColorPrintingSpeed(printerDto.getNonColorPrintingSpeed());
        printer.setColorPrintingSpeed(printerDto.getColorPrintingSpeed());
        printer.setPrintingTechnology(printerDto.getPrintingTechnology());
        printer.setConnectivity(printerDto.getConnectivity());
        printer.setProduct(optionalProduct.get());
        printerRepository.save(printer);
        return ResponseEntity.ok(new ApiResponse("Printer edited", true));
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public ResponseEntity get() {
        List<Printer> printers = printerRepository.findAll();
        return ResponseEntity.ok(printers);
    }


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        Optional<Printer> optionalPrinter = printerRepository.findById(id);
        if (!optionalPrinter.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Printer id not found", false));
        return ResponseEntity.ok(optionalPrinter.get());
    }


    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            printerRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Printer deleted", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Printer id not found", false));
        }
    }

}
