package com.green.todoapp;

import com.google.gson.Gson;
import com.green.todoapp.model.TodoITodoDto;
import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.model.TodoSelVo;
import com.green.todoapp.model.TodoUpdDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mvc;// 테스트 단에서 요청보내기

    @MockBean
    private TodoService service;

    @Test
    @DisplayName("TODO - 등록")
    void postTodo() throws Exception {
        //given환경설정 - when실제로 데이터결과값을 가져오고나서 - then 검증

        //given - 테스트 세팅 why?? 컨트롤러를 테스트를 할건데 서비스까지 연관되어 있어 실행시 시간이 너무 오래걸리므로 매퍼까지 가지않도록 가짜를 만들어서 시간을 효율적이게 쓰고 검증을 하기위함
        given(service.insTodo(any(TodoInsDto.class))).willReturn(3);//service.insDto요청이 들어오면  TodoInsDto에 있는 값 아무거나가 들어가게 된다 any()로 인해서 그러나 willReturn으로 무조건 3으로 리턴해준다. 잘작성했는지 검증하기 위함 가짜서비스만듬

        //when -실제 실행
        TodoInsDto dto = new TodoInsDto();
        dto.setCtnt("빨래 개기");

        Gson gson = new Gson();
        //String json = "{ \"ctnt\": \"빨래 개기\" }";
        String json = gson.toJson(dto);
        ResultActions ra = mvc.perform(post("/api/todo") //응답
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
        //content 로 제이슨형식을 표현하고 제이슨방식으로 보낸다

        //then 검증
        ra.andExpect(status().isOk())// 200(실행)이 뜨는지 체크하는 행위
                .andExpect(content().string("3")) //응답에 문자열에 기댓값은 3
                .andDo(print());//콘솔에다가 결과값을 찍었으면 좋겠다
        verify(service).insTodo(any());// verify==> 실제로 호출이 되었는지 검증을 하겠다
    }

    @Test
    @DisplayName("TODO - 등록")
    void getTodo() throws Exception {
        //given -when -then
        List<TodoSelVo> mockList = new ArrayList<>();
        mockList.add(new TodoSelVo(1, "테스트", "2023", null, 1, "2023-5-11"));
        mockList.add(new TodoSelVo(2, "테스트2", "2022", "abc.jpg", 0, "2023-5-12"));
        given(service.selTodo()).willReturn(mockList);


        //when
        ResultActions ra = mvc.perform(get("/api/todo"));

        //then
        ra.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(mockList.size())))
                .andExpect(jsonPath("$[*].itodo").exists()) //itodo가 있는지 확인해주는 메소드
                .andExpect(jsonPath("$[0].itodo").value(1))
                .andExpect(jsonPath("$[0].ctnt").value("테스트"))
                .andDo(print());

        verify(service).selTodo();
    }

    @Test
    @DisplayName("TODO - 완료처리 토글")
    void patchTodo() throws Exception {
        given(service.updFinish(any())).willReturn(1);

        TodoUpdDto dto = new TodoUpdDto();
        dto.setItodo(1);

        Gson gson = new Gson();
        String json = gson.toJson(dto); // json에다가 제이슨형태로 바꿔줄래 ~ 해주는 거
        // itodo : 1
        ResultActions ra = mvc.perform(patch("/api/todo").
                content(json).
                contentType(MediaType.APPLICATION_JSON));


         ra.andExpect(status().isOk())
                .andExpect(content().string("1"))
                .andDo(print());
        verify(service).updFinish(any());

    }
    @Test
    @DisplayName("TODO - 삭제완료")
    void delTodo() throws Exception{
        given(service.delTodo(any(TodoITodoDto.class))).willReturn(300); //무조건 300뱉어라 service.delTodo에 어떤값을넣든
        TodoITodoDto dto = new TodoITodoDto();
        dto.setItodo(1);


        ResultActions ra = mvc.perform(delete("/api/todo") //응답
                        .param("itodo","1"));
                    ra.andExpect(status().isOk())
                 .andExpect(content().string("300")) // 통신이 실제로 되고있는지 확인하는데 요청을 보내는데 요청보낸결과가 실제로 300인지 확인 ,윌리턴값으로 준값이 스트링값과 일치할경우  테스트 성공을 나타내준다.
                    .andDo(print());
                    verify(service).delTodo(any());

    }

//작동잘되는지 , any는 객체타입만가능 anyint anydouble 등등..
}