package com.spiderlab.api.controller;

import com.spiderlab.api.dto.member.MemberDto;
import com.spiderlab.api.dto.member.SignInRequestDto;
import com.spiderlab.api.serivce.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-in")
    public MemberDto signIn(@Valid @RequestBody SignInRequestDto signInRequestDto) {
        return memberService.signIn(signInRequestDto);
    }

    @GetMapping("")
    public String as() {
        return "hello";
    }
}
