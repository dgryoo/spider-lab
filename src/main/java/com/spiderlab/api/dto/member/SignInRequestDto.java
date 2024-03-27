package com.spiderlab.api.dto.member;

import com.spiderlab.api.entity.Member;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignInRequestDto {

    private String name;

    private String email;

    private String phoneNumber;

    @Pattern(regexp = "^(?=(.*[a-z].*[A-Z])|(?=.*[a-z].*\\d)|(?=.*[A-Z].*\\d))[a-zA-Z\\d]{6,10}$", message = "비밀번호는 최소 6자 이상, 10자 이하이며 영문 소문자, 대문자, 숫자 중 최소 두 가지 이상 조합이 필요합니다.")
    private String password;

    public Member toEntity() {
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setPhoneNumber(phoneNumber);
        member.setPassword(password);
        return member;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
