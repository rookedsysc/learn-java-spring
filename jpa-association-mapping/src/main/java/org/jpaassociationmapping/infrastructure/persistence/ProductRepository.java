package org.jpaassociationmapping.infrastructure.persistence;

import org.jpaassociationmapping.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
