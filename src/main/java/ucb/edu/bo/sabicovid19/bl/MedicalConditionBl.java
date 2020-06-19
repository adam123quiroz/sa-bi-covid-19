package ucb.edu.bo.sabicovid19.bl;

import org.springframework.stereotype.Service;
import ucb.edu.bo.sabicovid19.enums.Status;
import ucb.edu.bo.sabicovid19.dao.BiMedicalConditionRepository;
import ucb.edu.bo.sabicovid19.domain.BiMedicalCondition;

@Service
public class MedicalConditionBl {
    final BiMedicalConditionRepository biMedicalConditionRepository;

    public MedicalConditionBl(BiMedicalConditionRepository biMedicalConditionRepository) {
        this.biMedicalConditionRepository = biMedicalConditionRepository;
    }

    public BiMedicalCondition findMedicalConditionActive(Integer idMedicalCondition) {
        return this.biMedicalConditionRepository.findByMedCondIdAndStatus(idMedicalCondition, Status.ACTIVE.getStatus());
    }
}
