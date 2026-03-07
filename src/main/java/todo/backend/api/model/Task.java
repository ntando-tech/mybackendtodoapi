package todo.backend.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;


    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @NotNull
    private LocalDateTime created;

    private LocalDate dueDate;

    private boolean completed;

    public Task(String title, String description, String priority,
                String dueDate) {
        this.title = title;
        this.description = description;
        this.priority = Priority.valueOf(priority);
        this.created = LocalDateTime.now();
        this.completed = false;
        this.dueDate = LocalDate.parse(dueDate);

    }

    protected Task(){}

}
