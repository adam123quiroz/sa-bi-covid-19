package ucb.edu.bo.sabicovid19.enums;

public enum MedicalCondition {
    Confirmed(1), Recovered(2), Death(3), Suspect(4);

    MedicalCondition(int medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    private final int medicalCondition;

    public int getStatus() {
        return medicalCondition;
    }
}