package com.pramati.kart.productManagement.controller;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.pramati.kart.productManagement.config.SwaggerConfig;
import com.pramati.kart.productManagement.entity.Product;
import com.pramati.kart.productManagement.repository.ProductRepository;
import com.pramati.kart.productManagement.utility.BeanUtility;
import com.pramati.kart.productManagement.utility.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;


@Api(value="Product Management System", description="Operations pertaining to product in Product Management System")
@RestController
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private BeanUtility beanUtility;


    @ApiOperation(value = "View a detail of  product", response = List.class )
    @GetMapping("/product/{id}")
    public Product getProductByID(@PathVariable("id") long id) {
        return repository.findById(id).orElse(null);

    }

//    @GetMapping("/product")
//    public List<Product> getAllProducts() {
//        return repository.findAll();
//    }
    @ApiOperation(value = "Add a product")
    @PostMapping("/product")
    public Response createProduct(@RequestBody Product product) {
        repository.saveAndFlush(product);
        return new Response("Product Created Successfully");
    }

    @ApiOperation(value = "Udpate complete product details")
    @PutMapping("/product/{id}")
    public Product updateEmployee(@PathVariable("id") Long id,@RequestBody Product productdata) {
        Product product=repository.findById(id).orElse(null);
        if(product==null) return null;
        BeanUtils.copyProperties(productdata, product,beanUtility.getNullPropertyNames(productdata));
        repository.saveAndFlush(product);
        return  repository.findById(id).orElse(null);
    }

    @ApiOperation(value = "Delete a product ")
    @DeleteMapping("/product/{id}")
    public Response deleteProduct(@PathVariable("id") Long id){
        repository.deleteById(id);
        return new Response("Product Deleted Successfully");

    }

    @ApiOperation(value = "Search a product ")
    @GetMapping("/searchproduct")
    public List<Product> searchProduct(@RequestParam("search") String search){
        System.out.println(search);
       if(search==null)  return repository.findAll();
           return repository.findProduct(search);
    }

    @ApiOperation(value = "Get all products ")
    @GetMapping("/product")
    public List<Product> getAllProduct(){
          return repository.findAll();
    }
    @ApiOperation(value = "Update a product ")
    @PatchMapping("/product/{id}")
    public Product patchProduct(@PathVariable("id") Long id, @RequestBody HashMap<String, Object> fields) {
        Product product=repository.findById(id).orElse(null);
        String[] attributes = new String[]{"createdTime", "lastModifiedTime","id","createdBy"};
        List<String> list = Arrays.asList(attributes);
        if(product==null) return null;
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Product.class, k);
            if (field != null && !list.contains(field.getName()) )
            {
                field.setAccessible(true);
                Object fieldType = field.getType();
                if(field.getType().equals(Long.class)) {
                    ReflectionUtils.setField(field, product,((Number) v).longValue() );}
                if(field.getType().equals(Date.class)){
                    Date d=new Date();
                    try {
                        d=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(v.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ReflectionUtils.setField(field, product,d );}
               else  ReflectionUtils.setField(field, product, v);}
        });
        repository.saveAndFlush(product);
        return product;
    }

}
