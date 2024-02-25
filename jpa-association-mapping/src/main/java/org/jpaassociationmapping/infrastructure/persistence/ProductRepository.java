package org.jpaassociationmapping.infrastructure.persistence;

import org.jpaassociationmapping.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    public Optional<Product> findProductById(String id);
}
