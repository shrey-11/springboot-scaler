package dev.swapnil.productCatelogServices.controllers;

import dev.swapnil.productCatelogServices.dtos.ProductDtos;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @GetMapping()
    public String getAllCategory(){
        return "Getting All Category";
    }

    @DeleteMapping("{CategoryId}")
    public String deleteCategory(@PathVariable("CategoryId") long CategoryId){
        return "Deleting a Category with ID: "+ CategoryId;
    }
}
