package com.example.springhttp.toDo.controller;

import com.example.springhttp.toDo.model.ToDo;
import com.example.springhttp.toDo.model.ToDoStatus;
import com.example.springhttp.toDo.service.ToDoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @PostConstruct
    public void init(){
        ToDo newToDo = new ToDo();
        newToDo.setStatus(ToDoStatus.NEW);
        newToDo.setDeadline("2023-04-26");
        newToDo.setTask("Buy present for birthday");
        toDoService.create(newToDo);

        ToDo inProgressToDo = new ToDo();
        inProgressToDo.setStatus(ToDoStatus.IN_PROGRESS);
        inProgressToDo.setDeadline("2023-04-09");
        inProgressToDo.setTask("Bungee jump");
        toDoService.create(inProgressToDo);

        ToDo doneToDo = new ToDo();
        doneToDo.setStatus(ToDoStatus.DONE);
        doneToDo.setDeadline("2023-03-30");
        doneToDo.setTask("Do the shopping");
        toDoService.create(doneToDo);
    }

    @GetMapping
    public String listView(Model model){
        List<ToDo> todos = toDoService.list();
        model.addAttribute("todos", todos);
        return "todos/list";
    }

    @GetMapping("/create")
    public String cresteView(Model model){
        ToDo newToDo = new ToDo();
        model.addAttribute("newToDo", newToDo);//we need to add prototype to our model
        return "todos/create";
    }

    @PostMapping("/create")
    public String createAction(ToDo newToDo){
        newToDo.setStatus(ToDoStatus.NEW);
        toDoService.create(newToDo);
        return "redirect:/todos";
    }

    @GetMapping("/update")
    public String updateStatus(@RequestParam Integer id, @RequestParam ToDoStatus status){
        ToDo existing = toDoService.get(id);
        existing.setStatus(status);
        toDoService.update(existing);
        return "redirect:/todos";
    }
}
