package todo.backend.api.controller;

import org.springframework.web.bind.annotation.*;
import todo.backend.api.model.Task;
import todo.backend.api.service.TaskService;

import java.util.List;

@RestController
@RequestMapping
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }

    @PostMapping("/todo/save")
    public Task createTask(@RequestBody Task task){
        return service.createTask(task);
    }

    @GetMapping("/todos")
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }

    @GetMapping("/todo/{id}")
    public Task getSingleTask(@PathVariable Long id){
        return service.getSingleTask(id);
    }

    @PutMapping("/todo/{id}")
//    public Task editTask(@RequestParam Long id, @RequestBody Task task){
//        return service.editTask(id, task.getTitle(), task.getDescription(), task.getPriority(), task.getDueDate());
//    }
    public Task editTask(@PathVariable Long id, @RequestBody Task task){
        return service.editTask(id, task);
    }

    @PatchMapping("/todo/{id}")
    public Task changeCompletion(@PathVariable Long id){
        return service.changeCompletion(id);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteTask(@PathVariable Long id){
        service.deleteTask(id);;
    }
}
