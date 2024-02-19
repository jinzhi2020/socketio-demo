package icu.hacking.socketio.http;

import icu.hacking.socketio.params.request.UserLoginRequestBody;
import icu.hacking.socketio.params.request.UserRequestBody;
import icu.hacking.socketio.params.response.UnifyResponse;
import icu.hacking.socketio.params.response.UserInfoResponse;
import icu.hacking.socketio.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final AccountService accountService;

    @PostMapping("/user")
    public UnifyResponse<Object> register(@RequestBody UserRequestBody body) {
        accountService.register(body);
        return new UnifyResponse<>(null);
    }

    @PostMapping("/user/login")
    public UnifyResponse<UserInfoResponse> login(@RequestBody UserLoginRequestBody body) {
        UserInfoResponse response = new UserInfoResponse();
        response.setToken(accountService.login(body));
        return new UnifyResponse<>(response);
    }
}
