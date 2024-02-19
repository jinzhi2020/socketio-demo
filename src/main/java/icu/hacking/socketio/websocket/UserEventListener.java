package icu.hacking.socketio.websocket;

import icu.hacking.socketio.SocketIOEventMapping;
import icu.hacking.socketio.SocketIOListener;

@SocketIOListener
public class UserEventListener {

    @SocketIOEventMapping(event = "login")
    public void handleLogin(icu.hacking.protobuf.UserLoginRequest params) {
        System.out.println("params.getUsername() = " + params.getUsername());
        System.out.println("params.getPassword() = " + params.getPassword());
    }

}
