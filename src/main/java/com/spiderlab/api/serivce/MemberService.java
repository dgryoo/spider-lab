package com.spiderlab.api.serivce;


import com.spiderlab.api.common.exception.custom.DuplicateKeyException;
import com.spiderlab.api.dto.member.MemberDto;
import com.spiderlab.api.dto.member.SignInRequestDto;
import com.spiderlab.api.entity.Member;
import com.spiderlab.api.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;


    public MemberDto signIn(SignInRequestDto signInRequestDto) {
        // 해당 이메일로 가입된 유저 조회
        Optional<Member> duplicatedMember = memberRepository.findByEmail(signInRequestDto.getEmail());

        // SignInRequest와 동일한 이메일을 사용하는 유저가 이미 있을 때 Exception 발생
        if (duplicatedMember.isPresent()) {
            throw new DuplicateKeyException();
        }

        // 암호화된 password로 변경
        signInRequestDto.setPassword(passwordEncoder.encode(signInRequestDto.getPassword()));

        // 유저 등록
        Member savedMember = memberRepository.save(signInRequestDto.toEntity());

        // 등록된 유저 리턴
        return MemberDto.fromEntity(savedMember);
    }
}

