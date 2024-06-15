package dev.swapnil.productCatelogServices.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//DTOs(Data transfer objects): used for data from or to the system as objects
public class ProductDtos {
    private long id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;

    public String toString() {
        return "ProductDtos(title=" + this.getTitle() + ", price=" + this.getPrice() + ", description=" + this.getDescription() + ", image=" + this.getImage() + ", category=" + this.getCategory() + ")";
    }
}
