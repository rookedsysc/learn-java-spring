package org.jpaassociationmapping.infrastructure.controller;


import lombok.RequiredArgsConstructor;
import org.jpaassociationmapping.domain.Product;
import org.jpaassociationmapping.infrastructure.controller.dto.ProductSaveDto;
import org.jpaassociationmapping.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/save")
    public Product save(@RequestBody ProductSaveDto productDto) {
        Product product = Product.builder().name(productDto.getName()).id(productDto.getId()).
                build();
        return productService.save(product);
    }

    @GetMapping("/find/{id}")
    public Product findById(@PathVariable String id) {
        return productService.findById(id);
    }
}
