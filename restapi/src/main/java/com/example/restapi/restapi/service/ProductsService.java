package com.example.restapi.restapi.service;

import com.example.restapi.restapi.model.Product;
import com.example.restapi.restapi.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
//autowire repository,
//this is going to have all the methods to extract info
public class ProductsService {

    private ProductRepository productRepository;
    //get logger for messages in this class slf4j
    private Logger LOG = LoggerFactory.getLogger(ProductsService.class);
    //autowire by setter injection

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


   //responses to GET request controller
    public Product getProduct(String id){
        Optional<Product> product = this.productRepository.findById(id);
            if (product.isPresent()) {
                LOG.info("Getting the product ID" +id);
                return product.get();
            } else {
                return null;
            }

        }

   //responses to POST request
    public Product saveProduct (Product product){
        Product productToSave;
        try {
            LOG.info("saving product ...");
            productToSave= productRepository.save(product);
            return productToSave;
            }catch(Exception e){
            LOG.error("An error ocurred during product saving" +e.getMessage());
        }
        return new Product();

    }


   //respondes to PUT method
    public Product updateProduct (Product producttoUpdate, String id) {
        // El metodo find one no sirve con la vrsion de spring founProduct = productRepository.findOne(id);
        Optional<Product> product = this.productRepository.findById(id);

        try {
            //retieving the product find with same attributes
            Product newProduct = product.get();
            newProduct.setName(producttoUpdate.getName());
            newProduct.setDescription(producttoUpdate.getDescription());
            newProduct.setType(producttoUpdate.getType());
            newProduct.setCategory(producttoUpdate.getCategory());
            return productRepository.save(newProduct);
        } catch (Exception e) {
            LOG.error("An error occurred during the update of product" + e.getMessage());
        }

        return producttoUpdate;
    }



    //respondes to DELETE request
    public void deleteProduct(String id){
        //first find product in DB
        // El metodo find one no sirve con la vrsion de spring founProduct = productRepository.findOne(id);
        try{
            Optional<Product> product = this.productRepository.findById(id);
            if (product.isPresent()) {
                //the product with the ID providen was found
                //product.get() gets the entire class product
                productRepository.delete(product.get());

            } else {
                // LOG.info("No products were found to delete");
            }

        }catch (Exception e){
            LOG.error(e.getMessage());
        }

    }
}
