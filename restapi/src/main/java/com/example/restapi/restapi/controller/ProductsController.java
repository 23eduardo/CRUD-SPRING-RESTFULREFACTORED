package com.example.restapi.restapi.controller;

import com.example.restapi.restapi.model.Product;
import com.example.restapi.restapi.repository.ProductRepository;
import com.example.restapi.restapi.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

//this is required for bring only one instance
import java.awt.*;
import java.util.Optional;

//provide as rest interface functionality with productcontrollerclass
@RestController
//provide the path to consume /api/products/ {REST API GUIDELINES}
@RequestMapping(path = "/api/products/")
public class ProductsController {

    private Logger LOG = LoggerFactory.getLogger(ProductsController.class);
    private ProductsService productsService;

    //autowire productsservice to be able to access methods from the service class
    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    //the way of getting a variable of and endpoint
    // FINAL ENDPOINT WILL BE /api/products/{ID} WHERE ID IS A PATH VARIABLE
    @RequestMapping(path = "{id}", method = RequestMethod.GET)


    //method GET is used to bring an existing resource
    public Product getProduct(@PathVariable(name = "id") String id) {
       //calling the service class for getting One product
        return productsService.getProduct(id);
    }

    //POST is for creating a new
    //POST doesnt require a path, because a post must behavior like that to the end point /api/products/
    /*
    this method is expecting data in JSON to be new like this
    En el header del request: Content-Type application/json
    {
	"name": "Posted Product",
  	"description": "Posted Description",
  	"type" : "New Type",
  	"category":"POST",
  	"price":"2010"
}*/
    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    //@RequestBody expect th full entity to save
    public Product saveProduct(@RequestBody Product producttoSave){
        //spring understands the JSON spring,
       //calling the service class to handle request
        return productsService.saveProduct(producttoSave);

    }

    //PUT ENDPOINT
    //WE will recieve and ID as path variable if exist to modify
   @RequestMapping(path="{id}", method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product producttoUpdate, @PathVariable(name="id") String id){
        return productsService.updateProduct(producttoUpdate,id);
    }


    //DELETE SERVICE
    @RequestMapping(path="{id}", method =RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(name="id") String id){
    //call service class will handle delete product
    productsService.deleteProduct(id);
    }
}
