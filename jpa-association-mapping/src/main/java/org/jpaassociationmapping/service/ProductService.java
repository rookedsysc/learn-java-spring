package org.jpaassociationmapping.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jpaassociationmapping.domain.Product;
import org.jpaassociationmapping.infrastructure.persistence.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepo;

    public Product findById(String id) {
        return productRepo.findProductById(id).orElseThrow(
                () -> new NullPointerException("해당 제품이 존재하지 않습니다.")
        );
    }

    public Product save(Product product) {
        return productRepo.save(product);
    }
}
