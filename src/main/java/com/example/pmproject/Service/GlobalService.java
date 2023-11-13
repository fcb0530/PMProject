package com.example.pmproject.Service;

import com.example.pmproject.Constant.Role;
import com.example.pmproject.DTO.MemberDTO;
import com.example.pmproject.Entity.Member;
import com.example.pmproject.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GlobalService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper = new ModelMapper();

    public void register(MemberDTO memberDTO) {
        String password = passwordEncoder.encode(memberDTO.getPassword());

        Member member = Member.builder()
                .email(memberDTO.getEmail())
                .password(password)
                .name(memberDTO.getName())
                .address(memberDTO.getAddress())
                .findPwdHint(memberDTO.getFindPwdHint())
                .findPwdAnswer(memberDTO.getFindPwdAnswer())
                .role(Role.ROLE_USER)
                .build();

        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMemberByEmail = memberRepository.findByEmail(member.getEmail());
        Member findMemberByName = memberRepository.findByName(member.getName());

        if(findMemberByEmail != null) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
        if(findMemberByName != null) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }



}
