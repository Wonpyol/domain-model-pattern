package com.example.service;

import com.example.domain.Member;
import com.example.repository.member.MemberDto;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    final private MemberRepository memberRepository;

    //가입
    @Transactional
    public Long join(MemberDto memberDto) {
        Member member = Member.builder().name(memberDto.getName()).build();
        validateDuplicateMember(memberDto.getName());
        memberRepository.save(member);
        return member.getId();
    }

    public void modifyName(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.changeName(name);
    }

    private void validateDuplicateMember(String name) {
        List<Member> findMembers = memberRepository.findByName(name);
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    //전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();

    }
    //단건 조회
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

}
