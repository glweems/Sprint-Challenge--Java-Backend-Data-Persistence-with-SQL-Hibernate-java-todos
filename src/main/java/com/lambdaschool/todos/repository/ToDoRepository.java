package com.lambdaschool.todos.repository;

import com.lambdaschool.todos.model.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDo, Long>
{
}
