package com.lambdaschool.authenticatedusers.repository;

import com.lambdaschool.authenticatedusers.model.ToDos;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ToDoRepository extends CrudRepository<ToDos, Long>
{
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO ToDos(userid, roleid) values (:userid, :roleid)", nativeQuery = true)
    void insertUserTodo(long userid, long roleid);
}
