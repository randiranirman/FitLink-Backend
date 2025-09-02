package org.fitlink.trackingservice.Client;


import org.fitlink.trackingservice.Dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "user-management-service" ,url = "http://localhost:8080/api/users/user")
public interface UserClient {
    @GetMapping("/{id}")
    UserDto getUserById(@PathVariable  String  id ) ;
}
