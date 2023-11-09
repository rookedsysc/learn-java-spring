package com.fastcampus.batchcampus;

import com.fastcampus.batchcampus.customer.Customer;
import com.fastcampus.batchcampus.customer.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DormantBatchJobTest {
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private DormantBatchJob dormantBatchJob;

  @Test
  @DisplayName("로그인 시간이 일년을 경과한 고객이 3명이고, 일년 이내에 로그인한 고객이 5명이면 3명의 고객이 휴면전환 대상이다.")
  void test1() {
    final ArrayList<Customer> customers = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      var customer = saveCustomer(30);
      customers.add(customer);
    }
    for (int i = 0; i < 3; i++) {
      var customer = saveCustomer(366);
      customers.add(customer);
    }

    dormantBatchJob.execute();

    final long dormatnCount = customerRepository.findAll()
        .stream()
        .filter(it -> it.getStatus() == Customer.Status.DORMANT)
        .count();

    Assertions.assertThat(dormatnCount)
        .isEqualTo(3);

    customerRepository.deleteAll(customers);
  }

  @Test
  @DisplayName("고객이 10명 있지만 모두 다 휴먼전환대상이면 아니면 휴면전환 대상은 10명이다.")
  void test2() {
    final ArrayList<Customer> customers = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      var customer = saveCustomer(400);
      customers.add(customer);
    }

    dormantBatchJob.execute();

    final long dormatnCount = customerRepository.findAll()
        .stream()
        .filter(it -> it.getStatus() == Customer.Status.DORMANT)
        .count();

    Assertions.assertThat(dormatnCount)
        .isEqualTo(10);
    customerRepository.deleteAll(customers);
  }

  @Test
  @DisplayName("고객이 없는 경우에도 배치는 정상동작해야한다.")
  void test() {
    dormantBatchJob.execute();

    final long dormatnCount = customerRepository.findAll()
        .stream()
        .filter(it -> it.getStatus() == Customer.Status.DORMANT)
        .count();

    Assertions.assertThat(dormatnCount)
        .isEqualTo(0);
  }

  private Customer saveCustomer(long loginMinusDays) {
    final String uuid = UUID.randomUUID()
        .toString();
    final Customer test = new Customer(uuid, uuid + "@fastcampus.com");
    test.setLoginAt(LocalDateTime.now()
        .minusDays(loginMinusDays));
    customerRepository.save(test);

    return test;
  }
}