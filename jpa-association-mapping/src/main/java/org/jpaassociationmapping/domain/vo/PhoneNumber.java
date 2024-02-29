package org.jpaassociationmapping.domain.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jpaassociationmapping.domain.PhoneProvider;

@Embeddable @Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneNumber {
    String areaCode;
    String localNumber;

    @ManyToOne
    @JoinColumn(name = "phone_provider_id")
    PhoneProvider phoneProvider;
}
