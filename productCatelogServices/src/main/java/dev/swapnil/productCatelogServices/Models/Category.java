package dev.swapnil.productCatelogServices.Models;

import java.util.List;

public class Category extends BaseModel{

    private String name;
    private String Description;
    private List<Products> products;


    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.Description;
    }

    public List<Products> getProducts() {
        return this.products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscreption(String Description) {
        this.Description = Description;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
