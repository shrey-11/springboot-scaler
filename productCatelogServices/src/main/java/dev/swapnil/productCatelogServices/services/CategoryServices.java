package dev.swapnil.productCatelogServices.services;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CategoryServices {

    String getAllCategory();

    String getSingleCategory();

    String deleteCategory(long CategoryId);

}
