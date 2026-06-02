package cursoSpringBoot.controller;

import cursoSpringBoot.domain.Category;
import cursoSpringBoot.domain.CategoryRecordDto;
import cursoSpringBoot.domain.CategoryRepository;
import cursoSpringBoot.domain.CategoryResponseDto;
import cursoSpringBoot.service.CategoryServiceBoualiali;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    // Es una mala práctica, se debe hacer la logica en el servicio de la entidad.
    // Solo se deja para ver su uso.
    private final CategoryRepository categoryRepository;

    private final CategoryServiceBoualiali categoryServiceBoualiali;

    public CategoryController(CategoryRepository categoryRepository, CategoryServiceBoualiali categoryServiceBoualiali) {
        this.categoryRepository = categoryRepository;
        this.categoryServiceBoualiali = categoryServiceBoualiali;
    }


    // Inserta la categoria en la bd.
    // Se deja para ver su uso, es una mala practica. Debe ir en el servicio.
    @PostMapping("/db/categories")
    public Category create(
            @RequestBody Category category
    ){
        return categoryRepository.save(category);
    }

    @PostMapping("/db/dto/categories")
    public CategoryResponseDto createCategory(@RequestBody CategoryRecordDto categoryRecordDto) {
        return categoryServiceBoualiali.createCategoryResponseDto(categoryRecordDto);
    }

    // Se deja para ver su uso, es una mala practica. Debe ir en el servicio.
    @GetMapping("/db/categories")
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @GetMapping("/db/dto/categories")
    public List<CategoryResponseDto> allCategory() {
        return categoryServiceBoualiali.findAll();
    }
}
