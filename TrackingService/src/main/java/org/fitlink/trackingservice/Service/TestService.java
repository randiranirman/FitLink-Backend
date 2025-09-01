package org.fitlink.trackingservice.Service;



import lombok.RequiredArgsConstructor;
import org.fitlink.trackingservice.Client.UserClient;
import org.fitlink.trackingservice.Dto.ClientDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class TestService {

     private final UserClient userClient ;


     public ClientDto getClientByID( String id ) {
         var client =  userClient.getUserById( id);

         return new ClientDto ( client.id(), client.name(), client.email() , client.role());




     }
}
