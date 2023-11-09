package com.fastcampus.batchcampus;

import com.fastcampus.batchcampus.customer.Customer;
import com.fastcampus.batchcampus.customer.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DormantBatchJob {
  private final CustomerRepository customerRepository;
  private final EmailProvider emailProvider;

  public DormantBatchJob(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
    this.emailProvider = new EmailProvider.Fake();
  }

  /***
   * 1. 유저를 조회한다.
   * 2. 휴면계정 대상을 추출 및 변환한다.
   * 3. 휴면계정으로 상태를 변경한다.
   * 4. 메일을 보낸다.
   */
  public void execute() {
    int pageNo = 0;
    while (true) {
      // 1. 유저를 조회한다.
      final PageRequest pageRequest = PageRequest.of(pageNo, 1, Sort.by("id").ascending());
      final Page<Customer> page = customerRepository.findAll(pageRequest);
      final Customer customer;
      if (page.isEmpty()) {
        break;
      } else {
        pageNo++;
        customer = page.getContent().get(0);
      }
      // 2. 휴면계정 대상을 추출 및 변환한다.
      final boolean isDormatnTarget = LocalDate.now()
          .minusDays(365)
          .isAfter(customer.getLoginAt()
              .toLocalDate());

      if(isDormatnTarget) {
        customer.setStatus(Customer.Status.DORMANT);
      } else {
        continue;
      }
      // 3. 휴면계정으로 상태를 변경한다.
      customerRepository.save(customer);
      // 4. 메일을 보낸다.
      emailProvider.send(customer.getEmail(), "휴면계정 전환 안내", "휴면계정 전환 안내 메일입니다.");
    }
  }
}
