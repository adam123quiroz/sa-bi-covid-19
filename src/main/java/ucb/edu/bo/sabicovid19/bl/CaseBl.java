package ucb.edu.bo.sabicovid19.bl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ucb.edu.bo.sabicovid19.MedicalCondition;
import ucb.edu.bo.sabicovid19.Status;
import ucb.edu.bo.sabicovid19.dao.*;
import ucb.edu.bo.sabicovid19.domain.*;
import ucb.edu.bo.sabicovid19.dto.*;
import ucb.edu.bo.sabicovid19.model.CaseModel;

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
     *
     * @param caseRepository               BiCaseRepository
     * @param biGenderRepository           BiGenderRepository
     * @param biMedicalConditionRepository BiMedicalConditionRepository
     * @param biMunicipalityRepository     BiMunicipalityRepository
     * @param biOriginContagionRepository  BiOriginContagionRepository
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
     *
     * @param caseModel the format of the json
     * @return return the json if it save
     */
    public CaseModel createCase(@org.jetbrains.annotations.NotNull CaseModel caseModel) {
        BiCase biCase = new BiCase();
        biCase.setAge(caseModel.getAge());
        biCase.setUpdateDate(caseModel.getUpdateDate());
        biCase.setDistrict(caseModel.getDistrict());
        biCase.setZone(caseModel.getZone());

        BiGender biGender = this.biGenderRepository.findByGenderIdAndStatus(
                caseModel.getGanderId(),
                Status.ACTIVE.getStatus());
        BiMedicalCondition biMedicalCondition = this.biMedicalConditionRepository.findByMedCondIdAndStatus(
                caseModel.getMedCondId(),
                Status.ACTIVE.getStatus());
        BiMunicipality biMunicipality = this.biMunicipalityRepository.findByMunicipallyIdAndStatus(
                caseModel.getMunicipallyId(),
                Status.ACTIVE.getStatus());
        BiOriginContagion biOriginContagion = this.biOriginContagionRepository.findByOriContgIdAndStatus(
                caseModel.getOriContgId(),
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
        return caseModel;
    }

    public Integer countAllByMedCondIdActive(Integer idMedicalConditional) {
        return this.caseRepository.countAllByMedCondIdAndStatus(
                this.biMedicalConditionRepository.findByMedCondIdAndStatus(
                        idMedicalConditional, Status.ACTIVE.getStatus()
                ),
                Status.ACTIVE.getStatus()
        );
    }

    public Integer countAllCasesActive() {
        return this.caseRepository.countAllByMedCondIdNot(
                this.biMedicalConditionRepository.findByMedCondIdAndStatus(
                        MedicalCondition.Suspect.getStatus(),
                        Status.ACTIVE.getStatus()
                )
        );
    }

    public Integer countAllCasesToday() {
        return this.caseRepository.countAllByUpdateDate(new Date());
    }

    public Integer countAllCasesByMedicalConditionToday(Integer idMedicalCondition) {
        return this.caseRepository.countAllByMedCondIdAndUpdateDateAndStatus(
                this.biMedicalConditionRepository.findByMedCondIdAndStatus(
                        idMedicalCondition, Status.ACTIVE.getStatus()
                ),
                new Date(),
                Status.ACTIVE.getStatus()
        );
    }

    public Integer countAllByDepartmentAndMedicalConditionActive(String department,
                                                                 Integer idMedicalCondition) {
        return this.caseRepository.countByMunicipallyId_ProvinceId_DepartmentId_DepartmentAndMedCondIdAndStatus(
                department,
                this.biMedicalConditionRepository.findByMedCondIdAndStatus(
                        idMedicalCondition,
                        Status.ACTIVE.getStatus()
                ),
                Status.ACTIVE.getStatus()
        );
    }

    public Integer countAllCasesActiveByDepartment(String department) {
        return this.caseRepository.countByMunicipallyId_ProvinceId_DepartmentId_DepartmentAndMedCondIdNot(
                department,
                this.biMedicalConditionRepository.findByMedCondIdAndStatus(
                        MedicalCondition.Suspect.getStatus(),
                        Status.ACTIVE.getStatus()
                )

        );
    }

    public Integer countAllCasesTodayByDepartment(String department) {
        return this.caseRepository.countByMunicipallyId_ProvinceId_DepartmentId_DepartmentAndUpdateDate(
                department,
                new Date()
        );
    }

    public  Integer countAllCasesByMedicalConditionToday(String department) {
        return this.caseRepository.countByMunicipallyId_ProvinceId_DepartmentId_DepartmentAndMedCondIdAndUpdateDateAndStatus(
                department,
                this.biMedicalConditionRepository.findByMedCondIdAndStatus(
                        MedicalCondition.Death.getStatus(),
                        Status.ACTIVE.getStatus()
                ),
                new Date(),
                Status.ACTIVE.getStatus()
        );
    }
}

