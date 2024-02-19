package icu.hacking.socketio.services;

import icu.hacking.socketio.entities.UserEntity;
import icu.hacking.socketio.exceptions.BadRequestException;
import icu.hacking.socketio.params.request.UserLoginRequestBody;
import icu.hacking.socketio.params.request.UserRequestBody;
import icu.hacking.socketio.repositories.UserEntityRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserEntityRepository userEntityRepository;

    private final StringRedisTemplate stringRedisTemplate;


    public void register(UserRequestBody body) {
        UserEntity user = new UserEntity();
        user.setUsername(body.getUsername());
        user.setPassword(body.getPassword());
        userEntityRepository.save(user);
    }

    public String login(UserLoginRequestBody body) {
        UserEntity user = userEntityRepository.findByUsernameAndPassword(body.getUsername(), body.getPassword());
        if (user != null) {
            String token = generateToken();
            // Save token to redis
            stringRedisTemplate.opsForValue().set("USER:LOGIN:TOKEN:" + token, user.getId().toString());
            return token;
        }
        throw new BadRequestException();
    }


    @SneakyThrows
    private String generateToken() {
        UUID uuid = UUID.randomUUID();
        byte[] bytes = uuid.toString().getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(bytes);
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
