package kg.todoapp.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    public static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRespository taskRespository ;

    @Autowired
    public TaskController(final TaskRespository taskRespository) {
        this.taskRespository = taskRespository;
    }

    @PostMapping
    Task createTask(@RequestBody @Valid Task task) {
        return taskRespository.save(task);
    }

    @GetMapping
    ResponseEntity<List<Task>> readAllTask() {
        logger.warn("Exposing all the task");
        return ResponseEntity.ok(taskRespository.findAll());
    }


    @PutMapping("/{id}")
    ResponseEntity<?> updateTask(@RequestBody @Valid Task taskToUpdate,@PathVariable Integer id){
        if(!taskRespository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        taskRespository.findById(id).ifPresent(task -> {
            task.updateFrom(taskToUpdate);
            taskRespository.save(taskToUpdate);
        });
        return ResponseEntity.noContent().build();
    }
}
