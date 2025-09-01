package org.fitlink.trackingservice.Client;


import org.fitlink.trackingservice.Dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "user-managemenet-service " ,url = "http://localhost:8080/api/users/user")
public interface UserClient {
    @GetMapping("/{id}")
    ClientDto getUserById( @PathVariable  Long id ) ;
}
