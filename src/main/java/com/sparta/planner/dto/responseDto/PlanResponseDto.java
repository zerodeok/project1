package com.sparta.planner.dto.responseDto;

import com.sparta.planner.entity.Plan;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
public class PlanResponseDto {


    Long planId;          //유저아이디
    String password;        //비밀번호
    LocalDateTime udt_dttm;          //수정일
    LocalDateTime reg_dttm;
    String cntn;            //할일
    String manager;

    public PlanResponseDto(Plan savePlan) {
        this.planId = savePlan.getPlanId();
        this.password = savePlan.getPassword();
        this.udt_dttm = savePlan.getUdt_dttm();
        this.reg_dttm = savePlan.getReg_dttm();
        this.cntn = savePlan.getCntn();
        this.manager = savePlan.getManager();
    }

}
