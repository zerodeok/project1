package com.sparta.planner.controller;
import com.sparta.planner.dto.requestDto.PlanRequestDto;
import com.sparta.planner.dto.responseDto.PlanResponseDto;
import com.sparta.planner.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlannerController {

    private final JdbcTemplate jdbcTemplate;

    public PlannerController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/planner")
    public PlanResponseDto createPlan(@RequestBody PlanRequestDto planRequestDto) {
        PlanService planService = new PlanService(jdbcTemplate);
        PlanResponseDto planResponseDto = planService.createPlan(planRequestDto);
        return planResponseDto;
    }



}
