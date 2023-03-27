package com.example.springhttp.toDo.service;

import com.example.springhttp.toDo.model.ToDoStatus;
import org.springframework.stereotype.Service;

@Service
public class ToDoUtils {

    public String color(ToDoStatus status) {
        switch (status){
            case NEW:
                return "#00cc66";
            case IN_PROGRESS:
                return "#cccc00";
            case DONE:
                return "#0066cc";
            default:
                return "#000000";
        }
    }
}
