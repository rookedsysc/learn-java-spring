package org.jpaassociationmapping.service;

import lombok.RequiredArgsConstructor;
import org.jpaassociationmapping.domain.PhoneProvider;
import org.jpaassociationmapping.infrastructure.persistence.PhoneProviderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor @Service
public class PhoneProviderService {
    private final PhoneProviderRepository phoneProviderRepository;
    public PhoneProvider save(PhoneProvider phoneProvider) {
        return phoneProviderRepository.save(phoneProvider);
    }

    public PhoneProvider findByName(String providerName) {
        PhoneProvider phoneProvider = phoneProviderRepository.findTop1ByName(providerName).orElseThrow(
                () -> new RuntimeException("Phone provider not found")
        );
        return phoneProvider;
    }
}
