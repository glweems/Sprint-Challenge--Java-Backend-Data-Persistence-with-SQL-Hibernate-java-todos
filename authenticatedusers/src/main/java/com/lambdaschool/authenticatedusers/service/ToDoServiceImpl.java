package com.lambdaschool.authenticatedusers.service;

import com.lambdaschool.authenticatedusers.model.ToDos;
import com.lambdaschool.authenticatedusers.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "todoService")
public class ToDoServiceImpl implements ToDoService
{
    @Autowired
    private ToDoRepository todorepo;

    @Override
    public List<ToDos> findAll()
    {
        List<ToDos> list = new ArrayList<>();
        todorepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public ToDos findQuoteById(long id)
    {
        return todorepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (todorepo.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (todorepo.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                todorepo.deleteById(id);
            }
            else
            {
                throw new EntityNotFoundException(Long.toString(id) + " " + authentication.getName());
            }
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public ToDos save(ToDos toDos)
    {
        return todorepo.save(toDos);
    }

    @Override
    public List<ToDos> findByUserName(String username)
    {
        List<ToDos> list = new ArrayList<>();
        todorepo.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }
}
