package com.viiup.web.flock.controllers;

import com.viiup.web.flock.models.Product;
import com.viiup.web.flock.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by amoyeen on 3/1/15.
 */
@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/product/list")
    public ModelAndView productList(@RequestParam String searchString){

        List<Product> productList = productService.searchProducts(searchString);

        ModelAndView modelAndView = new ModelAndView("productList");
        modelAndView.addObject("productList", productList);

        return modelAndView;
    }

    @RequestMapping("/product/detail")
    public ModelAndView productDetail(@RequestParam int productID){

        Product product = productService.getProductByProductID(productID);

        ModelAndView modelAndView = new ModelAndView("productDetails");
        modelAndView.addObject("product", product);

        return modelAndView;
    }

}
