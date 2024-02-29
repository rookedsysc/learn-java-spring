package org.jpaassociationmapping.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.jpaassociationmapping.domain.PhoneProvider;
import org.jpaassociationmapping.infrastructure.controller.dto.CreatePhoneProviderDto;
import org.jpaassociationmapping.service.PhoneProviderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/phone-provider")
@RequiredArgsConstructor
public class PhoneProviderController {
    private final PhoneProviderService phoneProviderService;

    @PostMapping("/save")
    public PhoneProvider save(CreatePhoneProviderDto phoneProvider) {
        PhoneProvider entity = phoneProvider.toEntity();
        return phoneProviderService.save(entity);
    }
}
