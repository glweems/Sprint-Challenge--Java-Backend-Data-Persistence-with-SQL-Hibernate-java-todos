package com.lambdaschool.todos.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "todo")
public class ToDo
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    private String task;

}
