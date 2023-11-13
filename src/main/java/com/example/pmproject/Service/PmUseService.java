package com.example.pmproject.Service;

import com.example.pmproject.DTO.PmUseDTO;
import com.example.pmproject.Entity.Member;
import com.example.pmproject.Entity.Pm;
import com.example.pmproject.Entity.PmUse;
import com.example.pmproject.Repository.MemberRepository;
import com.example.pmproject.Repository.PmRepository;
import com.example.pmproject.Repository.PmUseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PmUseService {
    private final PmUseRepository pmUseRepository;
    private final PmRepository pmRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMappper=new ModelMapper();

    public List<PmUseDTO> pmUseDTOS(String memberName) {
        List<PmUse> pmUse = pmUseRepository.findByMemberName(memberName);

        return Arrays.asList(modelMappper.map(pmUse, PmUseDTO[].class));
    }

    public void register(PmUseDTO pmUseDTO) {
        PmUse pmUse=modelMappper.map(pmUseDTO, PmUse.class);
        pmUseRepository.save(pmUse);
    }

    public void modify(PmUseDTO pmUseDTO) {
        List<PmUse> pmUseList = pmUseRepository.findByMemberName(pmUseDTO.getMember_name());

        for(PmUse pmUse : pmUseList) {
            if(pmUse.getPm() != null) {
                Pm pm = pmUse.getPm();
                if(!pm.getIsUse()) {
                    pm.setIsUse(true);
                    pm.setLocation(pmUse.getFinishLocation());
                    pmRepository.save(pm);
                }
            }
        }

        PmUse modify=modelMappper.map(pmUseDTO,PmUse.class);
        pmUseRepository.save(modify);
    }

    public void updatePmLocation() {
        List<Pm> pmList = pmRepository.findAll();

        for (Pm pm : pmList) {
            PmUse lastPmUse = pmUseRepository.findTopByPmOrderByPmUseIdDesc(pm);

            if (lastPmUse != null) {
                String locationToUpdate = lastPmUse.getFinishLocation() != null ?
                        lastPmUse.getFinishLocation() :
                        lastPmUse.getStartLocation();

                pm.setLocation(locationToUpdate);
                pmRepository.save(pm);
            }
        }
    }
}
