package com.example.springhttp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/colors")
public class ColorsController {

    enum Color {
        RED("Red","#ff0000" ),
        BLUE("Blue", "#0000ff"),
        GREEN("Green","008000"),
        BLACK("Black", "#000000" ),
        YELLOW("Yellow", "#FDE456" ),
        BROWN("Brown", "#6E4030" );

        private String name;
        private String color;

        Color(String name, String color) {
            this.name = name;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    @GetMapping
    public String test(Model model,@RequestParam(required = false) Color color) {
        if (color == null){
            color = Color.BLACK;
        }

        model.addAttribute("color", color);
        model.addAttribute("all", Color.values());

        return "colors";
    }
}
