package ucb.edu.bo.sabicovid19.enums;

public enum Status {
    ACTIVE(1), INACTIVE(0);

    Status(int status) {
        this.status = status;
    }

    private final int status;

    public int getStatus() {
        return status;
    }
}