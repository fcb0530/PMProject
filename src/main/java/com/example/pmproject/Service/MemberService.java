package com.example.pmproject.Service;

import com.example.pmproject.DTO.MemberDTO;
import com.example.pmproject.DTO.MemberUpdateDTO;
import com.example.pmproject.Entity.Member;
import com.example.pmproject.Entity.PmUse;
import com.example.pmproject.Repository.MemberRepository;
import com.example.pmproject.Repository.PmUseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper=new ModelMapper();

    public Page<MemberDTO> memberDTOS(Pageable pageable) {
        int page=pageable.getPageNumber()-1;
        int pageLimit=10;

        Page<Member> paging=memberRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC,"memberId")));

        return paging.map(member -> MemberDTO.builder()
                .email(member.getEmail())
                .name(member.getName())
                .regDate(member.getRegDate())
                .modDate(member.getModDate())
                .build());
    }

    public MemberDTO listOne(String email) {
        Member member=memberRepository.findByEmail(email).orElseThrow();

        return MemberDTO.builder()
                .email(member.getEmail())
                .name(member.getName())
                .address(member.getAddress())
                .tel(member.getTel())
                .role(member.getRole())
                .regDate(member.getRegDate())
                .build();
    }

    public String update(MemberUpdateDTO memberUpdateDTO, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow();

        if(!passwordEncoder.matches(memberUpdateDTO.getRecentPassword(), member.getPassword())) {
            return null;
        }
        memberUpdateDTO.setNewPassword(passwordEncoder.encode(memberUpdateDTO.getNewPassword()));
        Member modify = modelMapper.map(memberUpdateDTO, Member.class);
        memberRepository.save(modify);

        return modify.getEmail();
    }

    public boolean withdrawal(String email, String password) {
        Member member = memberRepository.findByEmail(email).orElseThrow();

        if(passwordEncoder.matches(password, member.getPassword())) {
            memberRepository.delete(member);
            return true;
        }else {
            return false;
        }
    }


}
