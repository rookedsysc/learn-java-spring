package org.jpaassociationmapping.service;

import jakarta.transaction.Transactional;
import org.jpaassociationmapping.domain.EmbededMember;
import org.jpaassociationmapping.domain.PhoneProvider;
import org.jpaassociationmapping.domain.vo.Address;
import org.jpaassociationmapping.domain.vo.PhoneNumber;
import org.jpaassociationmapping.domain.vo.Zipcode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EmbededMemberServiceTest {
    @Autowired
    PhoneProviderService phoneProviderService;

    @Autowired
    EmbededMemberService embededMemberService;

    @Test
    void EMBEDED_MEMBER_생성() {
        // given
        PhoneProvider skt = phoneProviderService.findByName("skt");
        Zipcode zipcode = new Zipcode("12345", "123-45");
        Address address = new Address("신사동", "서울시", "한국");
        PhoneNumber phoneNumber = new PhoneNumber("92", "010-1234-5678", skt);
        EmbededMember embededMember = EmbededMember.builder()
                .address(address)
                .zipcode(zipcode)
                .phoneNumber(phoneNumber)
                .build();


        // when
        EmbededMember savedEmbededMember = embededMemberService.save(embededMember);

        // then
        assertEquals(savedEmbededMember.getAddress().getCity(), address.getCity());
        assertEquals(savedEmbededMember.getPhoneNumber().getLocalNumber(), phoneNumber.getLocalNumber());
        assertEquals(savedEmbededMember.getPhoneNumber().getPhoneProvider().getName(), skt.getName());
        assertEquals(savedEmbededMember.getZipcode().getZip(), zipcode.getZip());
    }

}