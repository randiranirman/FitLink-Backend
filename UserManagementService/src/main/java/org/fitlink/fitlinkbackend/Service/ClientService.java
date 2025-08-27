package org.fitlink.fitlinkbackend.Service;


import com.nimbusds.oauth2.sdk.client.ClientDeleteRequest;
import lombok.RequiredArgsConstructor;
import org.fitlink.fitlinkbackend.Dto.ClientRegisterForTrainerRequest;
import org.fitlink.fitlinkbackend.Dto.UserDto;
import org.fitlink.fitlinkbackend.Exceptions.TrainerNotFoundException;
import org.fitlink.fitlinkbackend.Exceptions.UserNotFoundException;
import org.fitlink.fitlinkbackend.Models.AppUserRole;
import org.fitlink.fitlinkbackend.Models.ClientDetails;
import org.fitlink.fitlinkbackend.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;


    public void registerForTrainer(String trainerId, ClientRegisterForTrainerRequest request) {


        var trainer = repository.findAll().stream()
                .filter(user -> user.getId().equals(trainerId))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Trainer not found with id: " + trainerId));

        if (trainer != null) {
            // Initialize clientDetails if it's null (defensive programming)
            if (trainer.getClientDetails() == null) {
                trainer.setClientDetails(new java.util.ArrayList<>());
            }
            trainer.getClientDetails().add(new ClientDetails(request.clientId(), request.name(), request.email(), request.contactNumber(), request.address(), request.gender(), request.age(), request.weight()));
            repository.save(trainer);


        }


    }

    public List<UserDto> searchTrainerByName(String name) {
        var trainers = repository.findByUserRoleAndNameContainingIgnoreCase(AppUserRole.TRAINER, name);

        if (trainers.isEmpty()) {
            throw new TrainerNotFoundException("Trainer not found with name: " + name);
        }

        return trainers.stream()
                .map(trainer -> new UserDto(
                        trainer.getEmail(),
                        trainer.getName(),
                        trainer.getId(),
                        trainer.getUserRole()
                ))
                .toList();
    }

}

