package com.green.todoapp.model;

import lombok.Data;

@Data
public class TodoUpdDto {
    private int itodo;
    private int finishYn;
    private String finishedAt;
}
