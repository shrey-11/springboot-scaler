package dev.swapnil.productCatelogServices.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Products extends BaseModel {

    private String title;
    private String description;
    private Double price;
    private Category category;
    private String imageUrl;
}
