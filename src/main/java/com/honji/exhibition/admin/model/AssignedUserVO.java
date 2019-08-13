package com.honji.exhibition.admin.model;

import com.honji.exhibition.admin.entity.Participant;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AssignedUserVO {

    private Long id;
    private Long userId;
    //private Long busUserId;
    //private String busName;
    private String shopCode;
    private String shopName;
    private String userNames;
    private int station;
    private int total;


    public AssignedUserVO(Long id, Long userId, String shopCode, String shopName) {
        this.id = id;
        this.userId = userId;
        this.shopCode = shopCode;
        this.shopName = shopName;
        //this.station = station;
        //StationEnum.
    }

    public void initParticipants(List<Participant> participants) {

        this.total = participants.size();
        StringBuffer name = new StringBuffer();
        for (Participant participant : participants) {
            name.append(participant.getName());
            name.append(", ");
        }

        this.userNames = name.toString();
    }
}
