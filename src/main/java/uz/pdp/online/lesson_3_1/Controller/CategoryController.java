package uz.pdp.online.lesson_3_1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_3_1.Entity.Category;
import uz.pdp.online.lesson_3_1.Repository.CategoryRepository;
import uz.pdp.online.lesson_3_1.payloadDto.ApiResponse;
import uz.pdp.online.lesson_3_1.payloadDto.CategoryDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity addCategory(@RequestBody CategoryDto categoryDto){
        if (categoryDto.getCategoryId()!=null){
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryId());
            if (!optionalCategory.isPresent())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Category id not found",false));
            Category category=new Category();
            category.setName(categoryDto.getName());
            category.setCategory(optionalCategory.get());
            categoryRepository.save(category);
            return ResponseEntity.ok(new ApiResponse("Category added",true));
        }
        Category category=new Category();
        category.setName(category.getName());
        categoryRepository.save(category);
        return ResponseEntity.ok(new ApiResponse("Category added",true));
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity getCategories(){
        List<Category> all = categoryRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity getCategoryById(@PathVariable Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent())
            return ResponseEntity.ok(optionalCategory.get());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Category id not found",false));
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity editCategory(@PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Category id not found",false));
        }
        Category category = optionalCategory.get();

        if (categoryDto.getCategoryId()!=null){
            optionalCategory = categoryRepository.findById(categoryDto.getCategoryId());
            if (!optionalCategory.isPresent())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Category id not found",false));
            category.setName(categoryDto.getName());
            category.setCategory(optionalCategory.get());
            categoryRepository.save(category);
            return ResponseEntity.ok(new ApiResponse("Category edited",true));
        }

        category.setName(category.getName());
        category.setCategory(null);
        categoryRepository.save(category);
        return ResponseEntity.ok(new ApiResponse("Category edited",true));
    }

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        try {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Category deleted.",true));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Category id not found",false));
        }
    }
}
