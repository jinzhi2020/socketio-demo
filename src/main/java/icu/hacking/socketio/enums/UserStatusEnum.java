package icu.hacking.socketio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    VALID("VALID"),

    INVALID("INVALID");

    private final String name;
}
