package com.lambdaschool.authenticatedusers.service;

import com.lambdaschool.authenticatedusers.model.ToDos;

import java.util.List;

public interface ToDoService
{
    List<ToDos> findAll();

    ToDos findQuoteById(long id);

    List<ToDos> findByUserName (String username);

    void delete(long id);

    ToDos save(ToDos toDos);
}
