package org.fitlink.trackingservice.Models;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document( collection = "exersice")
public class Exersice {

    private String  name ;
    private String type;
    private String muscle;
    private String equipment;
    private String difficulty;
    private String instructions;
    private Integer sets;
    private Integer reps;
    private Integer restSeconds;

}
