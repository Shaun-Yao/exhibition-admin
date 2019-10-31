package com.honji.exhibition.admin.model;

import com.honji.exhibition.admin.entity.Participant;
import com.honji.exhibition.admin.enums.RoomTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoomVO {

    private Long id;
    private Long userId;
    private RoomTypeEnum type;
    private String userNames;
    private List<Participant> participants;

    public RoomVO(Long id, Long userId, RoomTypeEnum type) {
        this.id = id;
        this.userId = userId;
        this.type = type;
    }

    public void parse(List<Participant> participants) {

        //this.total = participants.size();
        StringBuffer name = new StringBuffer();
        for (Participant participant : participants) {
            name.append(participant.getName());
            name.append(", ");
        }
        this.participants = participants;
        this.userNames = name.toString();
    }
}
