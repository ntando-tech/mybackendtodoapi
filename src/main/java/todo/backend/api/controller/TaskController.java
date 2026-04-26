package todo.backend.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import todo.backend.api.model.Task;
import todo.backend.api.service.TaskService;

import java.util.List;

@RestController
@RequestMapping
@Tag(name = "Tasks", description = "Endpoints for creating and managing todo tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }

    @PostMapping("/todo/save")
    @Operation(summary = "Create a task")
    public Task createTask(@RequestBody Task task){
        return service.createTask(task);
    }

    @GetMapping("/todos")
    @Operation(summary = "Get all tasks")
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }

    @GetMapping("/todo/{id}")
    @Operation(summary = "Get a single task by ID")
    public Task getSingleTask(@PathVariable Long id){
        return service.getSingleTask(id);
    }

    @PutMapping("/todo/{id}")
//    public Task editTask(@RequestParam Long id, @RequestBody Task task){
//        return service.editTask(id, task.getTitle(), task.getDescription(), task.getPriority(), task.getDueDate());
//    }
    @Operation(summary = "Replace an existing task")
    public Task editTask(@PathVariable Long id, @RequestBody Task task){
        return service.editTask(id, task);
    }

    @PatchMapping("/todo/{id}")
    @Operation(summary = "Toggle task completion")
    public Task changeCompletion(@PathVariable Long id){
        return service.changeCompletion(id);
    }

    @DeleteMapping("/todo/{id}")
    @Operation(summary = "Delete a task")
    public void deleteTask(@PathVariable Long id){
        service.deleteTask(id);;
    }
}
