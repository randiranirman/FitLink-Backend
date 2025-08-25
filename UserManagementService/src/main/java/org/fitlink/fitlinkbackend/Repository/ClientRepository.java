package org.fitlink.fitlinkbackend.Repository;


import org.fitlink.fitlinkbackend.Dto.ClientRegisterForTrainerRequest;
import org.fitlink.fitlinkbackend.Models.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<AppUser, String> {






}
