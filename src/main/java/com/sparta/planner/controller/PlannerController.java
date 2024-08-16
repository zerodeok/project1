package com.sparta.planner.controller;
import com.sparta.planner.dto.requestDto.PlanRequestDto;
import com.sparta.planner.dto.responseDto.PlanResponseDto;
import com.sparta.planner.entity.Plan;
import com.sparta.planner.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/api/planner")
    public Plan searchById(@RequestParam Long id){
        PlanService planService = new PlanService(jdbcTemplate);
        return planService.searchFindById(id);
    }

    @PutMapping("/api/planner/{id}")
    public Long updatePlan(@PathVariable("planId") long planId,@RequestBody PlanRequestDto planRequestDto){
        PlanService planService = new PlanService(jdbcTemplate);
        return planService.updatePlan(planId,planRequestDto);
    }

//
//    @GetMapping("/api/planner")
//    public List<PlanResponseDto> searchAll(LocalDateTime udt_dttm,String manager){
//        PlanService planService = new PlanService(jdbcTemplate);
//        return planService.searchByAll(udt_dttm,manager);
//    }
}
