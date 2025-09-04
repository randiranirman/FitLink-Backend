package org.fitlink.trackingservice.Service;



import lombok.RequiredArgsConstructor;
import org.fitlink.trackingservice.Client.UserClient;
import org.fitlink.trackingservice.Dto.UserDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {

     private final UserClient userClient ;


     public UserDto getClientByID(String id ) {
         var client =  userClient.getUserById( id);

         return new UserDto( client.id(), client.name(), client.email() , client.appUserRole());




     }
}
