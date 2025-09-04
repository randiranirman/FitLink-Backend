package org.fitlink.trackingservice.Exception;

public class ClientNotFound extends  RuntimeException{

    public ClientNotFound( String messsage) {
          super(messsage);
    }
}
