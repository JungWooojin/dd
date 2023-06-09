package com.example.board7.cmt;

import com.example.board7.cmt.model.BoardCmtDelDto;
import com.example.board7.cmt.model.BoardCmtDto;
import com.example.board7.cmt.model.BoardCmtInsDto;
import com.example.board7.cmt.model.BoardCmtVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmtMapper {
    int cmtIns(BoardCmtInsDto dto);
    int delBoardCmt(BoardCmtDelDto dto);
    List<BoardCmtVo> selBoardCmt(BoardCmtDto dto);
}
