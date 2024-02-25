package org.jpaassociationmapping.infrastructure.persistence;

import org.jpaassociationmapping.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
