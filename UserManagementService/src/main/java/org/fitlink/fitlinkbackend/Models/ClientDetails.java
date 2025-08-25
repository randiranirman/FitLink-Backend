package org.fitlink.fitlinkbackend.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ClientDetails {
    private String clientId;
    private String clientName ;
    private String clientEmail;
    private String clientPhoneNumber;
    private String clientAddress;
    private String clientGender;
    private Double clientAge;
    private Double clientWeight;

}
