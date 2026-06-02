package cursoSpringBoot.service;

import cursoSpringBoot.domain.Category;
import cursoSpringBoot.domain.CategoryRecordDto;
import cursoSpringBoot.domain.CategoryResponseDto;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    // Convertir el Dto en la entidad Category
    public Category toCategory(CategoryRecordDto categoryRecordDto) {
        return new Category(
                categoryRecordDto.nombre(),
                categoryRecordDto.description()
        );
    }

    // Convertir la entidad Category a CategoryRecordDto
    public CategoryRecordDto toCategoryDto (Category category) {
        return new CategoryRecordDto(
                category.getNombre(),
                category.getDescription(),
                category.getProducts()
        );
    }

    public CategoryResponseDto toCategoryResponseDto(Category category) {
        return new CategoryResponseDto(
                category.getNombre(),
                category.getDescription()
        );
    }
}
