package com.honji.exhibition.admin.model;

import com.honji.exhibition.admin.enums.SexEnum;
import com.honji.exhibition.admin.enums.ShopTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParticipantVO {

    private  Long id;
    private Long userId;
    private String name;
    private String mobile;
    private SexEnum sex;
    private boolean attendTraining;
    private ShopTypeEnum shopType;
    private String shopCode;
    private String shopName;
    private String area;
    private String smallArea;


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
