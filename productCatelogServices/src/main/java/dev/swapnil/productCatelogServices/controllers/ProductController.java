package dev.swapnil.productCatelogServices.controllers;

import dev.swapnil.productCatelogServices.Models.Category;
import dev.swapnil.productCatelogServices.Models.Products;
import dev.swapnil.productCatelogServices.dtos.ProductDtos;
import dev.swapnil.productCatelogServices.services.ProductServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
//this annotation is used to register a class as a controller in the spring dispatcher
//@RequestMapping("/products"): this annotations is used to tell spring the all the functions present in this class are for /products endPoint.
// for PathVariable ie {"ABC"}, we just need to mention the variable. EX: @GetMapping("/{productId}")

public class ProductController {

    private ProductServiceImp productService;

    public ProductController(ProductServiceImp productService){
        this.productService = productService;
    }

    @GetMapping()
    public List<Products> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{productId}")
    public ResponseEntity<Products> getSingleProduct(@PathVariable("productId") long productId){
//        ResponseProductDto response= new ResponseProductDto();
//        response.setProduct(productService.getSingleProduct(productId));
//        return response;
        ResponseEntity<Products> response = new ResponseEntity(productService.getSingleProduct(productId), HttpStatus.OK);
        return response;

    }

    /* @RequestBody tells spring to take the data present in request body. spring will one by one try to pass the values
       ,received from the request body, in the productDtos class and try create an object of the productDtos class which
       then will be passed into the function
    * */

    /*controllers should not take a model, controller should only take a specific DTO, that has a data which is needed
    * to fulfil that request
    * */
    @PostMapping()
    public ResponseEntity<Products> addSingleProduct(@RequestBody ProductDtos productDto){
        Products newProduct = productService.addSingleProduct(productDto);
        ResponseEntity<Products> response = new ResponseEntity<>(newProduct, HttpStatus.OK );
        return response;
    }


    @PatchMapping("/{productId}")
    public ResponseEntity<Products> updateProduct(@PathVariable("productId") long productId, @RequestBody ProductDtos productDto){
        Products product= new Products();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription() );
        product.setImageUrl(productDto.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        Products ans = productService.updateProduct(productId, product);
        ResponseEntity<Products> response = new ResponseEntity<>(ans, HttpStatus.OK );
        return response;
    }

    @DeleteMapping("{productId}")
    public String deleteProduct(@PathVariable("productId") long productId){
        return "Deleting a Product with ID: "+ productId;
    }
}
