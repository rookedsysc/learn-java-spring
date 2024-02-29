package org.jpaassociationmapping.infrastructure.persistence;

import org.jpaassociationmapping.domain.PhoneProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneProviderRepository extends JpaRepository<PhoneProvider, Long> {
    public Optional<PhoneProvider> findTop1ByName(String providerName);
}
