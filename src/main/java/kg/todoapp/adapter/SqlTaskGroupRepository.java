package kg.todoapp.adapter;

import kg.todoapp.model.TaskGroup;
import kg.todoapp.model.TaskGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlTaskGroupRepository extends JpaRepository<TaskGroup,Integer>, TaskGroupRepository {

    @Override
    @Query("from TaskGroup g join fetch g.tasks ")
    List<TaskGroup> findAll();

    List<TaskGroup> findAllByDoneFalseOrderByProject();

}
