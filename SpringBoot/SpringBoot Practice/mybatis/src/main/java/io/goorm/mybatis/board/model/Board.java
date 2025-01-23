package io.goorm.mybatis.board.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class Board {
    private long seq;
    private String title;
    private String contents;
    private String regUser;
    private LocalDateTime regDate;
}
