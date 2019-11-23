package com.honji.exhibition.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserVO {

    private  Long id;
    private String shopCode;
    private String shopName;
    private String area;
    private String userNames;
    //private int station;
    //private int total;


/*
    public void initParticipants(List<Participant> participants) {

        //this.total = participants.size();
        StringBuffer name = new StringBuffer();
        for (Participant participant : participants) {
            name.append(participant.getName());
            name.append(", ");
        }

        this.userNames = name.toString();
    }*/
}
