package org.fitlink.fitlinkbackend.Repository;


import org.fitlink.fitlinkbackend.Dto.ClientRegisterForTrainerRequest;
import org.fitlink.fitlinkbackend.Models.AppUser;
import org.fitlink.fitlinkbackend.Models.AppUserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends MongoRepository<AppUser, String> {
    List<AppUser> findByUserRoleAndNameContainingIgnoreCase(AppUserRole role, String name);






}
