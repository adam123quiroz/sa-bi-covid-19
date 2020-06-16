package ucb.edu.bo.sabicovid19.model;

import lombok.Data;

@Data
public class UserModel {
    private Integer userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String catUserStatus;

    public UserModel(Integer userId, String username, String email, String phoneNumber, String catUserStatus) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.catUserStatus = catUserStatus;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", catUserStatus='" + catUserStatus + '\'' +
                '}';
    }
}
