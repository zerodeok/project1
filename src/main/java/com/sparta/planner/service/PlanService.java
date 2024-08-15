package com.sparta.planner.service;

import com.sparta.planner.dto.requestDto.PlanRequestDto;
import com.sparta.planner.dto.responseDto.PlanResponseDto;
import com.sparta.planner.entity.Plan;
import com.sparta.planner.repository.PlanRepository;
import org.springframework.jdbc.core.JdbcTemplate;

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

    public Plan searchFindById(int id) {
        PlanRepository planRepository = new PlanRepository(jdbcTemplate);
        return planRepository.findId(id);
    }

}