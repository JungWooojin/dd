package com.green.boardver3.board.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardDetailVo {
    private int iboard;
    private String title;
    private String Ctnt;
    private String createdAt;
    private String writer;
    private String writerMainPic;
}
