package todo.backend.api.service;

import org.springframework.stereotype.Service;
import todo.backend.api.model.Priority;
import todo.backend.api.model.Task;
import todo.backend.api.repository.TaskRepository;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository){
        this.repository = repository;
    }

    //Save task
    public Task createTask(Task newTask){
        String insertedPriority = String.valueOf(newTask.getPriority());
        newTask.setPriority(checkPriority(insertedPriority));
        String insertedDate = String.valueOf(newTask.getDueDate());
        newTask.setDueDate(checkDueDate(insertedDate));
        return repository.save(newTask);
    }

    //Get all tasks
    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Task getTaskById(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Task ID was not found"));
        //return task1;

    }

    //Get Each Task
    public Task getSingleTask(Long id){
        return getTaskById(id);

    }



    public Task editTask(Long id,Task task2){
        Task task = getTaskById(id);
        task.setTitle(task2.getTitle());
        task.setDescription(task2.getDescription());
        String insertedPriority = String.valueOf(task2.getPriority());
        task.setPriority(checkPriority(insertedPriority));
        String insertedDate = String.valueOf(task2.getDueDate());
        task.setDueDate(checkDueDate(insertedDate));
        return task;
    }

    //Change Completion
    public Task changeCompletion(Long id){
        Task task = getTaskById(id);
        if(task.isCompleted()){
            task.setCompleted(false);
        }
        else{
            task.setCompleted(true);
        }
        return repository.save(task);
    }

    //Delete Task
    public void deleteTask(Long id){
        repository.deleteById(id);
    }

    public Priority checkPriority(String insertedPriority){
        Priority newp;
        try{
            if(insertedPriority.isEmpty()){
                newp = Priority.LOW;
            }
            else {
                newp = Priority.valueOf(insertedPriority.toUpperCase());
            }
        }catch(Exception e){
            throw new RuntimeException("Priority values are LOW, MEDIUM & HIGH");
        }
        return newp;
    }

    public LocalDate checkDueDate(String insertedDate){
        LocalDate newDate;
        try{
            newDate = LocalDate.parse(insertedDate);
            if(newDate.isBefore(LocalDate.now())){
                throw new RuntimeException("Due date cannot be before Today's Date");
            }
        } catch (DateTimeParseException e) {
            throw new DateTimeException("Date Format. YYYY-MM-DD");
        }
        return newDate;
    }

}
