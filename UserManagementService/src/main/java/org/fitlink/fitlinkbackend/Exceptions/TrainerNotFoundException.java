package org.fitlink.fitlinkbackend.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.NOT_FOUND)
public class TrainerNotFoundException extends  RuntimeException{

    public TrainerNotFoundException(String message) {
        super(message);
    }

}
