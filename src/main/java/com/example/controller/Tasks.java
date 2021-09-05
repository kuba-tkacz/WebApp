package com.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Tasks {


    @RequestMapping(value = {"/tasks"}, method = RequestMethod.GET)
    public String getTasks() {
        return "tasks/tasks";
    }

    @RequestMapping(value = {"/addTask"}, method = RequestMethod.GET)
    public String getAddTask() {
        return "tasks/addTask";
    }


}
