package icu.hacking.socketio.params.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnifyResponse<T> {

    private final Long code = 0L;

    private final String message = "Success";

    private T data = null;

    public UnifyResponse(T data) {
        this.data = data;
    }

}
