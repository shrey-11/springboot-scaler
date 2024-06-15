package dev.swapnil.productCatelogServices.services;

import dev.swapnil.productCatelogServices.Models.Category;
import dev.swapnil.productCatelogServices.Models.Products;
import dev.swapnil.productCatelogServices.dtos.FakeStoreProductDto;
import dev.swapnil.productCatelogServices.dtos.ProductDtos;
import jakarta.annotation.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImp implements ProductServices{
    /*
      explaination for using: restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class).build();

    * here we are using a 3rd party HTTP client library.
    * As, restTemplate is a library that works on the HTTP client present in java.
    * HTTP client in java by default does not supports a patch request. so instead of asking our restTemplate
    * to use the default HTTP client library, we used a 3rd party HTTP client library.
    * */

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object urlVariable) throws RestClientException{
        RestTemplate restTemplate= restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback= restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor= restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, urlVariable);
    }

    private Products convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){

        Products product= new Products();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        Category category= new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(fakeStoreProductDto.getImage());
        return product;
    }


    //dependency injection of restTeplateBuilder(Bean)
    private RestTemplateBuilder restTemplateBuilder;
    public ProductServiceImp(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    @Override
    /*
     * while calling a 3rd party API, a service should always receive a DTO object of a specific DTO class build for that specific endpoint
     * here the below method is receiving a FakeStoreProductDto object from 3rd part API
     * we convert the FakeStoreProductDto object to object of our model and returning it
     * */
    //    COPY
    /*
     * while calling a 3rd party API, a service should always receive a DTO object of a specific DTO class build for that specific endpoint
     * here the below method is receiving a FakeStoreProductDto object from 3rd part API
     * we convert the FakeStoreProductDto object to object of our model and returning it
     * */
    public List<Products> getAllProducts() {
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> list= restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        /*
        The code uses the RestTemplate class to make an HTTP GET request to the specified URL: "https://fakestoreapi.com/products".
        The getForEntity method is part of the Spring Framework’s RestTemplate and is commonly used for making REST API calls.
        It returns a ResponseEntity object, which contains information about the HTTP response, including the response body, headers, and status code.
        The second argument, FakeStoreProductDto[].class, specifies the expected response type. In this case, it expects an array of FakeStoreProductDto (where FakeStoreProductDto is a custom class representing product data from FakeStoreProduct).

        we cannot receive a list from a request directly because of type eraser

        * restTemplate returns object of hashMap when the specific object of a class to return is not defined
        * so we will feed the data from hashmap object to product
        * */
        List<Products> answer= new ArrayList<>();
        for(FakeStoreProductDto Object: list.getBody()) {
            FakeStoreProductDto fakeStoreProductDto= Object;
            Products product= convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
            answer.add(product);
        }
        return answer;
    }

    @Override
    public Products getSingleProduct(long productId) {

        RestTemplate restTemplate= restTemplateBuilder.build();
        //above class allows us to call third party APIs
        ResponseEntity<FakeStoreProductDto> response= restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class,
                productId
        );
        /*
        * restTemplate.getForEntity(RequiredURL if variable is present then define the variable using{},
        * what datatype to convert that json into(attributed of that data type should be same as present in the json file),
        * parameters present in path variable)
        *ResponseEntity: learn More(helps in getting status of request/errors)
        *
        * */

        //creating a product and setting its value as present in fakeStoreProductDto we got from the third party api
        //for now ID of category will be null, will see later
        FakeStoreProductDto fakeStoreProductDto= response.getBody();
        Products product= convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        return product;
    }

    //Service will take productDTO as argument
    @Override
    public Products addSingleProduct(ProductDtos product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,
                FakeStoreProductDto.class
        );
        FakeStoreProductDto fakeStoreProductDto= response.getBody();
        Products product1= convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        return product1;
    }

    @Override
    public Products updateProduct(long productId, Products product) {
        RestTemplate restTemplate= restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto= new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());
        ResponseEntity<FakeStoreProductDto> response= requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );
        return convertFakeStoreProductDtoToProduct(response.getBody());
    }

    @Override
    public Products replaceProduct(long productId, ProductDtos product) {
        return null;
    }
}
