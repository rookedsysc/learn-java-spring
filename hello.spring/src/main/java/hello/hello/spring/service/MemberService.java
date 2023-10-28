package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.MemberRepository;
import hello.hello.spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
  private final MemberRepository memberRepository;

  /// 외부에서 Repository를 넣어주도록 바꿔줌으로써 Test에서 Repository를 사용하더라도
  /// Service에서 사용하는 Repository와 Test에서 사용하는 Repository가 동일할 수 있다.
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /**
   * 회원 가입
   */
  public Long join(Member member) {
    // 같은 이름이 있는 중복 회원 X
    validateDuplicateMember(member);

    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName()).
        ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
  }

  /**
   * 전체 회원 조회
   */
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
