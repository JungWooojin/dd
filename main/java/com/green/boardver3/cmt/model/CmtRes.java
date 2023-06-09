package com.green.boardver3.cmt.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CmtRes {
    private int isMore; //0 댓글 더 없음 , 1 댓글 더 있음
    private List<BoardCmtVo> list;
    private int maxpage;
    private int row;
}
