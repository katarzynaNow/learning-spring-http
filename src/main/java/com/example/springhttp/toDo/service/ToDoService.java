package com.example.springhttp.toDo.service;

import com.example.springhttp.common.CrudService;
import com.example.springhttp.toDo.model.ToDo;
import org.springframework.stereotype.Service;

@Service
public class ToDoService extends CrudService<ToDo, Integer> {
  public ToDoService() {

    super(Integer.class);
  }
}
