package todo.backend.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todo.backend.api.model.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
