package org.jpaassociationmapping.service;

import lombok.RequiredArgsConstructor;
import org.jpaassociationmapping.domain.Product;
import org.jpaassociationmapping.infrastructure.persistence.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepo;
    public Product save(Product product) {
        return productRepo.save(product);
    }
}
