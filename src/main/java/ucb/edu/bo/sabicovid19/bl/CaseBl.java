package ucb.edu.bo.sabicovid19.bl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ucb.edu.bo.sabicovid19.Status;
import ucb.edu.bo.sabicovid19.dao.*;
import ucb.edu.bo.sabicovid19.domain.*;
import ucb.edu.bo.sabicovid19.dto.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CaseBl {
    BiCaseRepository caseRepository;
    BiGenderRepository biGenderRepository;
    BiMedicalConditionRepository biMedicalConditionRepository;
    BiMunicipalityRepository biMunicipalityRepository;
    BiOriginContagionRepository biOriginContagionRepository;

    /**
     * Constructor, it receives the beans that Spring give us
     * @param caseRepository BiCaseRepository
     * @param biGenderRepository BiGenderRepository
     * @param biMedicalConditionRepository BiMedicalConditionRepository
     * @param biMunicipalityRepository BiMunicipalityRepository
     * @param biOriginContagionRepository BiOriginContagionRepository
     */
    public CaseBl(BiCaseRepository caseRepository,
                  BiGenderRepository biGenderRepository,
                  BiMedicalConditionRepository biMedicalConditionRepository,
                  BiMunicipalityRepository biMunicipalityRepository,
                  BiOriginContagionRepository biOriginContagionRepository) {
        this.caseRepository = caseRepository;
        this.biGenderRepository = biGenderRepository;
        this.biMedicalConditionRepository = biMedicalConditionRepository;
        this.biMunicipalityRepository = biMunicipalityRepository;
        this.biOriginContagionRepository = biOriginContagionRepository;
    }

    /**
     * method to save new cases
     *
     * @return A list to all the active cases
     */
    public List<CaseDto> findAllCases() {
        List<CaseDto> caseDtoList = new ArrayList<>();
        caseRepository.findAllByStatus(Status.ACTIVE.getStatus()).forEach(biCase -> {
            CaseDto caseDto = new CaseDto(biCase);

            GenderDto genderDto = new GenderDto(biCase.getGanderId());
            MedicalConditionDto medicalConditionDto = new MedicalConditionDto(biCase.getMedCondId());
            MunicipalityDto municipalityDto = new MunicipalityDto(biCase.getMunicipallyId());
            OriginContagionDto originContagionDto = new OriginContagionDto(biCase.getOriContgId());

            caseDto.setGanderId(genderDto);
            caseDto.setMedCondId(medicalConditionDto);
            caseDto.setMunicipallyId(municipalityDto);
            caseDto.setOriContgId(originContagionDto);

            caseDtoList.add(caseDto);

        });
        return caseDtoList;
    }

    /**
     * this method save the new Case with a specific json data structure that
     * @param casePostDto the format of the json
     * @return return the json if it save
     */
    public CasePostDto createCase(@org.jetbrains.annotations.NotNull CasePostDto casePostDto) {
        BiCase biCase = new BiCase();
        biCase.setAge(casePostDto.getAge());
        biCase.setUpdateDate(casePostDto.getUpdateDate());
        biCase.setDistrict(casePostDto.getDistrict());
        biCase.setZone(casePostDto.getZone());

        BiGender biGender = this.biGenderRepository.findByGenderIdAndStatus(casePostDto.getGanderId(),
                Status.ACTIVE.getStatus());
        BiMedicalCondition biMedicalCondition  = this.biMedicalConditionRepository.findByMedCondIdAndStatus(casePostDto.getMedCondId(),
                Status.ACTIVE.getStatus());
        BiMunicipality biMunicipality = this.biMunicipalityRepository.findByMunicipallyIdAndStatus(casePostDto.getMunicipallyId(),
                Status.ACTIVE.getStatus());
        BiOriginContagion biOriginContagion = this.biOriginContagionRepository.findByOriContgIdAndStatus(casePostDto.getOriContgId(),
                Status.ACTIVE.getStatus());

        biCase.setGanderId(biGender);
        biCase.setMedCondId(biMedicalCondition);
        biCase.setMunicipallyId(biMunicipality);
        biCase.setOriContgId(biOriginContagion);

        biCase.setStatus(Status.ACTIVE.getStatus());
        biCase.setTextUser("Admin");
        biCase.setTextDate(new Date());
        biCase.setTextHost("localhost");

        caseRepository.save(biCase);

        return  casePostDto;
    }
}

