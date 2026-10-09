package it.aulab.theaulabchronicle.services;

import it.aulab.theaulabchronicle.dtos.CategoryDto;
import it.aulab.theaulabchronicle.models.Article;
import it.aulab.theaulabchronicle.models.Category;
import it.aulab.theaulabchronicle.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CrudService<CategoryDto, Category, Long> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDto> readAll() {
        List<CategoryDto> dtos = new ArrayList<CategoryDto>();
        for (Category category : categoryRepository.findAll()) {
            dtos.add(modelMapper.map(category, CategoryDto.class));
        }
        return dtos;
    }

    @Override
    public CategoryDto read(Long key) {
        return modelMapper.map(categoryRepository.findById(key).get(), CategoryDto.class);
    }

    @Override
    public CategoryDto create(Category model, Principal principal, MultipartFile file) {
        return modelMapper.map(categoryRepository.save(model), CategoryDto.class);
    }

    @Override
    public CategoryDto update(Long key, Category model, MultipartFile file) {
        if (categoryRepository.existsById(key)) {
            model.setId(key);
            return modelMapper.map(categoryRepository.save(model), CategoryDto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (categoryRepository.existsById(id)) {

            Category category = categoryRepository.findById(id).get();

            if (category.getArticles() != null) {
                Iterable<Article> articles = category.getArticles();
                for (Article article : articles) {
                    article.setCategory(null);
                }
            }

            categoryRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}