package com.springexample.task_manager_example.entities;

import lombok.Data;

@Data
public class NoteEntity {
    private int id;
    private String title;
    private String body;
}
