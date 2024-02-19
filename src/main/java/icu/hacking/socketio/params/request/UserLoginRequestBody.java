package icu.hacking.socketio.params.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestBody {

    private String username;

    private String password;
}
