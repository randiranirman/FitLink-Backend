package org.fitlink.fitlinkbackend.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "app_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter



public class AppUser {


    @Id
    private Long Id;
    @Indexed(  unique = true)
    private String email ;
    private String hashedPassword ;
    private String name   ;
    private AppUserRole userRole;
    private boolean enabled= true;
    private boolean fromOauth = false ;


    private Instant createdAt = Instant.now();
}
