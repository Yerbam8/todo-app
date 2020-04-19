package kg.todoapp.model;

import java.util.List;
import java.util.Optional;

public interface TaskGroupRepository {

    List<TaskGroup> findAll();

    List<TaskGroup> findAllByDoneFalseOrderByProject();

    Optional<TaskGroup> findById(Integer id);

    TaskGroup save(TaskGroup taskGroup);


}
