package com.sparta.planner.dto.requestDto;


import com.sparta.planner.dto.responseDto.PlanResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlanRequestDto {
    String cntn;            //할일
    String manager;          //유저아이디
    String password;

}
