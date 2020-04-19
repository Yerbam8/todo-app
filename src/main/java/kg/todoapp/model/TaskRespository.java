package kg.todoapp.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRespository {
    List<Task> findAll();

    Page<Task> findAll(Pageable pageable);

    Optional<Task> findById(Integer id);

    Task save(Task tak);

    List<Task> findByDone(@Param("state") boolean done);

    boolean existsByDoneIsFalseAndTaskGroupId(Integer groupId);


    boolean existsById(Integer id);
}
