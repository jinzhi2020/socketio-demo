package icu.hacking.socketio.repositories;

import icu.hacking.socketio.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsernameAndPassword(String username, String password);
}
