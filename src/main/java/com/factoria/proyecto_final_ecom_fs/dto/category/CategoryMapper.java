package com.factoria.proyecto_final_ecom_fs.dto.category;
import com.factoria.proyecto_final_ecom_fs.dto.category.CategoryDTORequest;
import com.factoria.proyecto_final_ecom_fs.dto.category.CategoryDTOResponse;
import com.factoria.proyecto_final_ecom_fs.model.Category;

public class CategoryMapper {

    public static Category dtoToEntity(CategoryDTORequest categoryDTORequest) {
        return new Category(
                categoryDTORequest.name()

        );
    }

    public static CategoryDTOResponse entityToDTO(Category category) {
        return new CategoryDTOResponse(
                category.getId(),
                category.getName()
        );
    }
}
