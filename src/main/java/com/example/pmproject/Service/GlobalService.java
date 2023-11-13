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
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        memberDTO.setRole(Role.ROLE_USER);

        Member member = modelMapper.map(memberDTO, Member.class);
        memberRepository.save(member);
    }

}
