package kg.todoapp.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private boolean done;
    private LocalDateTime deadline;
    @Embedded
    private Audit audit;
    @ManyToOne
    @JoinColumn(name = "task_group_id")
    private TaskGroup taskGroup;


    public Task() {

    }

    public Task(String description, LocalDateTime deadline){
        this.description=description;
        this.deadline=deadline;

    }


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }



    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public TaskGroup getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(TaskGroup taskGroup) {
        this.taskGroup = taskGroup;
    }

    public void updateFrom(final Task source) {
        description = source.description;
        done = source.done;
        deadline = source.deadline;
        taskGroup=source.taskGroup;

    }


}
