package org.fitlink.fitlinkbackend.Repository;


import org.fitlink.fitlinkbackend.Models.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser, String> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String username);
    boolean existsByEmail( String email );
    boolean existsByUsername( String username );


}
