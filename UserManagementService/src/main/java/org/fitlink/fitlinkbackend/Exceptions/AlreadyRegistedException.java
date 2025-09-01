package org.fitlink.fitlinkbackend.Exceptions;

public class AlreadyRegistedException extends  RuntimeException{

    public AlreadyRegistedException( String message) {
         super(message);
    }
}
