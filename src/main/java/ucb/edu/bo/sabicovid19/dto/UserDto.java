package ucb.edu.bo.sabicovid19.dto;

import lombok.Data;
import ucb.edu.bo.sabicovid19.domain.BiUser;
import ucb.edu.bo.sabicovid19.domain.BiUserRole;

import java.util.List;

@Data
public class UserDto {
    private Integer userId;
    private String username;
    private String password;
    private int accountExpired;
    private int accountLocked;
    private int credentialsExpired;

    private PersonDto personId;
    private List<BiUserRole> biUserRoleList;

    public UserDto(BiUser biUser) {
        this.userId = biUser.getUserId();
        this.username = biUser.getUsername();
        this.password = biUser.getPassword();
        this.accountExpired = biUser.getAccountExpired();
        this.accountLocked = biUser.getAccountLocked();
        this.credentialsExpired = biUser.getCredentialsExpired();
    }
}
