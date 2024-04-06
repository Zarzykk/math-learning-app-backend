package demo.mathapp;

import demo.mathapp.transferobject.UserInfoDTO;
import lombok.Getter;

import java.io.Serializable;
@Getter
public class LoginResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private String token;
    private UserInfoDTO userInfo;

    public LoginResponse(UserInfoDTO userInfo, String token) {
        this.userInfo = userInfo;
        this.token = token;
    }

}

