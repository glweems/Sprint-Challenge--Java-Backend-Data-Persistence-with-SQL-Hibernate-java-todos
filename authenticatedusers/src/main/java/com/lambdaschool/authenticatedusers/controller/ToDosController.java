package com.lambdaschool.authenticatedusers.controller;

import com.lambdaschool.authenticatedusers.model.ToDos;
import com.lambdaschool.authenticatedusers.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDosController
{
    @Autowired
    ToDoService toDoService;

    @GetMapping(value = "/todos", produces = {"application/json"})
    public ResponseEntity<?> listAllQuotes()
    {
        List<ToDos> allToDos = toDoService.findAll();
        return new ResponseEntity<>(allToDos, HttpStatus.OK);
    }


    @GetMapping(value = "/todos/{todoId}", produces = {"application/json"})
    public ResponseEntity<?> getQuote(@PathVariable Long todoId)
    {
        ToDos q = toDoService.findQuoteById(todoId);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}", produces = {"application/json"})
    public ResponseEntity<?> findQuoteByUserName(@PathVariable String userName)
    {
        List<ToDos> theToDos = toDoService.findByUserName(userName);
        return new ResponseEntity<>(theToDos, HttpStatus.OK);
    }



    @PostMapping(value = "/todo")
    public ResponseEntity<?> addNewQuote(@Valid @RequestBody ToDos newToDos) throws URISyntaxException
    {
        newToDos = toDoService.save(newToDos);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newQuoteURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{todoId}")
                .buildAndExpand(newToDos.getTodo())
                .toUri();
        responseHeaders.setLocation(newQuoteURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> deleteQuoteById(@PathVariable long id)
    {
        toDoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
