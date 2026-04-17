package org.session10.controller;

import org.session10.model.TaskItem;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    private List<TaskItem> taskList = new ArrayList<>();

    public TaskController() {
        TaskItem t1 = new TaskItem();
        t1.setId("1");
        t1.setTitle("Học Spring MVC");
        t1.setDeadline(LocalDate.now().plusDays(2));
        t1.setPriority("HIGH");

        TaskItem t2 = new TaskItem();
        t2.setId("2");
        t2.setTitle("Làm bài tập Java");
        t2.setDeadline(LocalDate.now().plusDays(5));
        t2.setPriority("MEDIUM");

        taskList.add(t1);
        taskList.add(t2);
    }

    @GetMapping({"/","/tasks"})
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskList);
        return "task-list";
    }

    @GetMapping("/tasks/add")
    public String showForm(Model model) {
        model.addAttribute("task", new TaskItem());
        return "task-form";
    }

    @PostMapping("/tasks/add")
    public String addTask(@Valid @ModelAttribute("task") TaskItem task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "task-form";
        }
        taskList.add(task);

        return "redirect:/tasks"; // QUAN TRỌNG
    }
}
