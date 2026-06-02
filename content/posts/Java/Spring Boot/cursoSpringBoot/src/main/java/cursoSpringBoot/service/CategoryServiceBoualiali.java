package cursoSpringBoot.service;

import cursoSpringBoot.domain.Category;
import cursoSpringBoot.domain.CategoryRecordDto;
import cursoSpringBoot.domain.CategoryRepository;
import cursoSpringBoot.domain.CategoryResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceBoualiali {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceBoualiali(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    // Crea un Category a partir del DTO y devuelve una Category sin usar el ResponseCategoryDto
    public Category create(
            CategoryRecordDto categoryRecordDto
    ){
        Category category =  categoryMapper.toCategory(categoryRecordDto);
        return categoryRepository.save(category);
    }

    public CategoryResponseDto createCategoryResponseDto(CategoryRecordDto categoryRecordDto) {
        var category = categoryMapper.toCategory(categoryRecordDto);
        var categorySave = categoryRepository.save(category);

        return categoryMapper.toCategoryResponseDto(categorySave);
    }

    public List<CategoryResponseDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryResponseDto)
                .collect(Collectors.toList());
    }
}
