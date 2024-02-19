package icu.hacking.socketio.exceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private final Long code = 400L;

    private final String message = "Bad Request";

}
