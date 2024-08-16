package com.sparta.planner.repository;

import com.sparta.planner.dto.requestDto.PlanRequestDto;
import com.sparta.planner.dto.responseDto.PlanResponseDto;
import com.sparta.planner.entity.Plan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

public class PlanRepository {

    private final JdbcTemplate jdbcTemplate;

    public PlanRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Plan save(Plan plan) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO plan (password, udt_dttm , reg_dttm, cntn , manager) VALUES (?, ? , ?, ?, ?)";
        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, plan.getPassword());
                    preparedStatement.setString(2, now.toString());
                    preparedStatement.setString(3, now.toString());
                    preparedStatement.setString(4, plan.getCntn());
                    preparedStatement.setString(5, plan.getManager());
                   return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        plan.setPlanId(id);
        plan.setUdt_dttm(now);
        plan.setReg_dttm(now);

        return plan;
    }

    public Plan findId(Long id) {

        String sql = "SELECT * FROM PLAN WHERE plan_id = ? ";
        return jdbcTemplate.query(sql,resultSet -> {
            if (resultSet.next()){
                Plan plan = new Plan();
                plan.setPlanId(resultSet.getLong("plan_id"));
                plan.setManager(resultSet.getString("manager"));
                return plan;
            }else {
                return null;
            }
        } ,id);

    }


    public void update(Long id, PlanRequestDto planRequestDto ){
        String sql = " UPDATE plan SET manager = ?,cntn = ?, udt_dttm = ? WHERE id = ?";
        jdbcTemplate.update(sql,planRequestDto.getManager(),planRequestDto.getCntn(),LocalDateTime.now(), id);
    }












//    public List<PlanResponseDto> searchAll(LocalDateTime udt_dttm,String manager) {
//        String sql = "SELECT * FROM PLAN";
//
//        return jdbcTemplate.query(sql, new RowMapper<PlanResponseDto>() {
//            @Override
//            public PlanResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Long planid = rs.getLong("palnid");
//                String manager = rs.getString("manager");
//                String cntn = rs.getString("cntn");
//                return new PlanResponseDto(planid, manager, cntn);
//            }
//        });
//    }


}









// body에서 입력을 받으면 -> 리퀘스트디티오에 담아 -> 컨트롤에서 받아서 -> 서비스에서 가공해 ->
//                      -> 리포지토리에 넘기고 리포지토리는 데이터베이스에 값을 반영한다.

//데이터베이스 -> 리스폰스디티오에 값을 담아와