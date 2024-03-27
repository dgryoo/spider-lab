package com.spiderlab.api.dto.member;

import com.spiderlab.api.entity.Member;
import lombok.Getter;

@Getter
public class MemberDto {

    private String id;

    private String name;

    private String email;

    private String phoneNumber;

    public static MemberDto fromEntity(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.id = member.getId();
        memberDto.name = member.getName();
        memberDto.email = member.getEmail();
        memberDto.phoneNumber = member.getPhoneNumber();
        return memberDto;
    }
}
