package ru.max.projects.WebDiary.Converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.max.projects.WebDiary.Entities.Category;

@Component
public class StringToCategoryConverter implements Converter<String, Category> {
    @Override
    public Category convert(String source) {
        Integer categoryId = Integer.valueOf(source);
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }
}