package com.sparta.planner.entity;

import com.sparta.planner.dto.requestDto.PlanRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class Plan {
    Long planId;          //plan 아이디
    String password;        //비밀번호
    LocalDateTime udt_dttm;          //수정일
    LocalDateTime reg_dttm;
    String cntn;            //할일
    String manager;         //담당자

    public Plan(PlanRequestDto planRequestDto){
        this.password = planRequestDto.getPassword();
        this.cntn = planRequestDto.getCntn();
        this.manager = planRequestDto.getManager();

    }
}
