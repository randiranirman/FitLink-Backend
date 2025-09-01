package org.fitlink.trackingservice.Dto;


import lombok.Data;

@Data
public class ClientDto {

    private Long  id ;
       private String name ;
       private  String email  ;
       private String  role ; // CLIENT , TRAINER


}
