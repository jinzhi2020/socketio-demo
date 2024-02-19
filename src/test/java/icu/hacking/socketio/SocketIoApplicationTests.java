package icu.hacking.socketio;

import com.google.protobuf.InvalidProtocolBufferException;
import icu.hacking.protobuf.UserLoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class SocketIoApplicationTests {

    @Test
    void contextLoads() {
        UserLoginRequest request = UserLoginRequest.newBuilder()
                .setUsername("admin")
                .setPassword("admin")
                .build();
        StringBuilder binaryString = new StringBuilder();
        for (byte b : request.toByteArray()) {
            binaryString.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }
        System.out.println("binaryString = " + binaryString);
    }

}
