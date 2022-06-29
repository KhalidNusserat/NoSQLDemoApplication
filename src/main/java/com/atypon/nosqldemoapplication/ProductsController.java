package com.atypon.nosqldemoapplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductsController {

    private final ProductService service;

    public ProductsController(ProductService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String homePage(Model model) {
        List<Product> products = service.getAll();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/new")
    public String newProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);
        return "redirect:/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") Product product) {
        service.delete(product.getName());
        service.save(product);
        return "redirect:/";
    }

    @RequestMapping("/edit/{name}")
    public ModelAndView showEditProductPage(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView("edit_product");
        Product product = service.get(name);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @RequestMapping("/delete/{name}")
    public String deleteProduct(@PathVariable String name) {
        service.delete(name);
        return "redirect:/";
    }
}
