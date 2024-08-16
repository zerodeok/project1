package com.sparta.planner.service;

import com.sparta.planner.dto.requestDto.PlanRequestDto;
import com.sparta.planner.dto.responseDto.PlanResponseDto;
import com.sparta.planner.entity.Plan;
import com.sparta.planner.repository.PlanRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

public class PlanService {

    private final JdbcTemplate jdbcTemplate;

    public PlanService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PlanResponseDto createPlan(PlanRequestDto planRequestDto) {
        // RequestDto -> Entity
        Plan plan = new Plan(planRequestDto);

        // DB 저장
        PlanRepository planRepository = new PlanRepository(jdbcTemplate);
        Plan savePlan = planRepository.save(plan);


        // Entity -> ResponseDto
        PlanResponseDto planResponseDto = new PlanResponseDto(savePlan);

        return planResponseDto;
    }

    public Plan searchFindById(Long id) {
        PlanRepository planRepository = new PlanRepository(jdbcTemplate);
        return planRepository.findId(id);
    }

    public Long updatePlan(Long id, PlanRequestDto planRequestDto){
        PlanRepository planRepository = new PlanRepository(jdbcTemplate);

        Plan plan = planRepository.findId(id);
        if(plan != null){
            planRepository.update(id,planRequestDto);

            return id;
        }else {
            throw new IllegalArgumentException("선택한 일정이 없습니다.");
        }
    }

//
//    public List<PlanResponseDto> searchByAll(LocalDateTime udt_dttm,String manager){
//        PlanRepository planRepository = new PlanRepository(jdbcTemplate);
//        return planRepository.searchAll(udt_dttm, manager);
//    }

}