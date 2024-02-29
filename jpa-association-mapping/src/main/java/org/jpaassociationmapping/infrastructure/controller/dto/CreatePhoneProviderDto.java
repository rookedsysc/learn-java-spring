package org.jpaassociationmapping.infrastructure.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jpaassociationmapping.domain.PhoneProvider;

@Getter @AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePhoneProviderDto {
    private String name;

    public PhoneProvider toEntity() {
        PhoneProvider entity = PhoneProvider.builder().name(this.name).build();
        return entity;
    }
}
