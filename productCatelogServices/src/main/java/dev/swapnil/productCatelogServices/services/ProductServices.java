package dev.swapnil.productCatelogServices.services;

import dev.swapnil.productCatelogServices.Models.Products;
import dev.swapnil.productCatelogServices.dtos.ProductDtos;

import java.util.List;

public interface ProductServices {

    List<Products> getAllProducts();

    Products getSingleProduct( long productId);

    Products addSingleProduct(ProductDtos productDto);

    Products updateProduct(long productId, Products product);

    Products replaceProduct(long productId, ProductDtos product);
}
