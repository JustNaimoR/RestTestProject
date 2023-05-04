package Romario.demo.util;

import Romario.demo.util.Exceptions.ObjectNotSavedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorResponse {
    private String msg;
    private long timestamp;

    public ErrorResponse(String msg, long timestamp) {
        this.msg = msg;
        this.timestamp = timestamp;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static void returnErrors(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();

        for (FieldError err: errors) {
            msg.append(err.getField()).append(" - ")
                    .append(err.getDefaultMessage()).append(";  ");
        }

        throw new ObjectNotSavedException(msg.toString());
    }
}