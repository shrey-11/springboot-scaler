package dev.swapnil.productCatelogServices.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
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
