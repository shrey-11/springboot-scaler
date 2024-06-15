package dev.swapnil.productCatelogServices.dtos;

import dev.swapnil.productCatelogServices.Models.Products;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseProductDto {
    private Products product;
}
