package org.jpaassociationmapping.infrastructure.controller.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductSaveDto {
    private String id;
    private String name;
}
