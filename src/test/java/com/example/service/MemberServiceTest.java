package com.example.service;

import com.example.repository.member.MemberDto;
import com.example.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    void 회원가입() {
        //given
        MemberDto member = MemberDto.builder().name("wonpyol").build();
        //when
        Long saveId = memberService.join(member);
        //then
        assertNotNull(memberRepository.findOne(saveId));
    }

    @Test
    @Rollback(value = true)
    void 중복_회원_예외() {
        //given
        MemberDto member = MemberDto.builder().name("wonpyol").build();
        MemberDto member1 = MemberDto.builder().name("wonpyol").build();
        //when
        memberService.join(member);

        //then
        assertThrows(IllegalStateException.class, () -> memberService.join(member1));
    }
}