package io.goorm.mybatis.dynamic.controller;

import io.goorm.mybatis.board.model.Board;
import io.goorm.mybatis.dynamic.mapper.DynamicMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dynamic")
public class DynamicRestController {

    @Autowired
    private DynamicMapper dynamicMapper;



    //resultMap 사용 모델과 디비테이블이 매칭 되지 않을 경우
    @GetMapping("/boards")
    public List<Board> selectAll() {

        return dynamicMapper.selectAll();

    }

    //# 사용하기
    @GetMapping("/boards2")
    public List<Board> select2() {
        //@Param("orderBy") String orderBy

        String orderBy ="reg_date"; //seq, reg_date
        String orderDirection = "DESC"; //ASC DESC
        log.debug("test2");
        return dynamicMapper.select2(orderBy,orderDirection);
    }

    //IN 사용하기
    @GetMapping("/boards3")
    public List<Board> select3() {

        String[] ids = {"topolo","guest"};

        return dynamicMapper.select3(ids);

    }

    //like 사용하기
    @GetMapping("/boards4")
    public List<Board> select4() {
        // @Param("condition") String condition, @Param("searctext") String searctext

        String condition = "title"; //all, title, contents
        //condition = "contents"; //all, title, contents
        //condition = "all"; //all, title, contents
        String searchtxt = "11";

        log.debug("test4");
        return dynamicMapper.select4(condition,searchtxt);

    }


}