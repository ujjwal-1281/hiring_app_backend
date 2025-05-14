package com.project.services;

import com.project.dto.BankDTO;
import com.project.dto.CandidateDTO;
import com.project.dto.EducationDTO;
import com.project.dto.PersonalDTO;
import com.project.enities.BankEntity;
import com.project.enities.CandidateEntity;
import com.project.enities.EducationEntity;
import com.project.enities.PersonalEntity;
import com.project.exception.BadRequestException;
import com.project.exception.CandidateNotFoundException;
import com.project.mappers.BankMapper;
import com.project.mappers.CandidateMapper;
import com.project.mappers.EducationMapper;
import com.project.mappers.PersonalMapper;
import com.project.repository.BankRepo;
import com.project.repository.CandidateRepo;
import com.project.repository.EducationRepo;
import com.project.repository.PersonalRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepo crepo;
    private final PersonalRepo prepo;
    private final EducationRepo edrepo;
    private final BankRepo brepo;

    private final CandidateMapper candidateMapper;
    private final PersonalMapper personalMapper;
    private final BankMapper bankMapper;
    private final EducationMapper educationMapper;
    private final BankRepo bankRepo;


    public CandidateDTO addUser(CandidateDTO dto) {
       //convert dto to entity
       CandidateEntity candidate =  candidateMapper.toEntity(dto);

        candidate.setStatus(CandidateEntity.Status.APPLIED);
        candidate.setOnboardStatus(CandidateEntity.OnboardStatus.NOT_STARTED);

        //save the candidate
        candidate=crepo.save(candidate);
       return candidateMapper.toDTO(candidate);

    }


    public CandidateDTO updateUser(Long id, CandidateDTO dto) {
        //otherWAy
//        Optional<CandidateD> user= crepo.findById(id);
//        if(user==null){throw new BadRequestException("User not found");}
//        else{CandidateD c1=user.get();}

        CandidateEntity existing=crepo.findById(id)
                .orElseThrow(()-> new CandidateNotFoundException(id));

        candidateMapper.updateCandidateFromDto(dto, existing);
        CandidateEntity updatedCandidate = crepo.save(existing);
        return candidateMapper.toDTO(updatedCandidate);

    }

    public void addPersonalInfo(Long id, PersonalDTO dto) {
        CandidateEntity candidate = crepo.findById(id)
                .orElseThrow(()-> new CandidateNotFoundException(id));
        PersonalEntity personal= personalMapper.toEntity(dto);
        personal.setCandidate(candidate);
        prepo.save(personal);

    }
    public void addBankInfo(Long id, BankDTO dto) {
        CandidateEntity candidate= crepo.findById(id)
                .orElseThrow(()-> new CandidateNotFoundException(id));
        BankEntity bank= bankMapper.toEntity(dto);
        bank.setCandidate(candidate);
        brepo.save(bank);

    }
    public void addEducationInfo(Long id, EducationDTO dto) {
        CandidateEntity candidate = crepo.findById(id)
                .orElseThrow(()-> new CandidateNotFoundException(id));
        EducationEntity education= educationMapper.toEntity(dto);
        education.setCandidate(candidate);
        edrepo.save(education);
    }
    
    
    private boolean isValidStatus(CandidateEntity.Status current, CandidateEntity.Status updated) {
        return switch(current){
            case APPLIED -> updated == CandidateEntity.Status.INTERVIEWED;
            case INTERVIEWED -> updated == CandidateEntity.Status.OFFERED;
            case OFFERED -> updated == CandidateEntity.Status.ONBOARDED;
            default -> false;
        };
    }

    public void updateStatus(Long id, CandidateDTO dto) {
            CandidateEntity status = crepo.findById(id)
                    .orElseThrow(()-> new CandidateNotFoundException(id));
            CandidateEntity.Status newStatus = CandidateEntity.Status.valueOf(String.valueOf(status.getStatus()));
            if(!isValidStatus(status.getStatus(), newStatus)){
                throw new BadRequestException("Invalid status");
            }
            status.setStatus(newStatus);
            crepo.save(status);
    }

    public void onboardingStatus(Long id, String onboardStatus) {
        CandidateEntity obstatus= crepo.findById(id)
                .orElseThrow(()-> new CandidateNotFoundException(id));
        CandidateEntity.Status newStatus = CandidateEntity.Status.valueOf(String.valueOf(obstatus.getStatus()));
        obstatus.setStatus(newStatus);
        crepo.save(obstatus);

    }
    
    public Long getCandidateCount() {
        return crepo.count();
    }
    public CandidateDTO viewCandidate(Long id) {
       CandidateEntity candidate= crepo.findById(id)
                .orElseThrow(()-> new CandidateNotFoundException(id));
        return candidateMapper.toDTO(candidate);

    }

}
