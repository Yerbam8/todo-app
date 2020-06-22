package kg.todoapp.service;

import kg.todoapp.TaskConfigurationProperties;
import kg.todoapp.model.TaskGroup;
import kg.todoapp.model.TaskGroupRepository;
import kg.todoapp.model.TaskRespository;
import kg.todoapp.model.projection.GroupReadModel;
import kg.todoapp.model.projection.GroupTaskWriteModel;
import kg.todoapp.model.projection.GroupWriteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskGroupService {
    private TaskGroupRepository repository;
    private TaskRespository taskRespository;

    @Autowired
    public TaskGroupService(TaskGroupRepository repository, TaskRespository taskRespository) {
        this.repository = repository;
        this.taskRespository = taskRespository;
    }


    public GroupReadModel createGroup(GroupWriteModel groupWriteModel) {
        TaskGroup result = repository.save(groupWriteModel.toGroup());
        return new GroupReadModel(result);
    }

    public List<GroupReadModel> readAll() {
        return repository.findAll().stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public void toggleGroup(int groupId) {
        if (
                repository.existsByDoneIsFalseAndProject_Id(groupId)) {
            throw new IllegalStateException("Group has undone tasks.Done all the tasks first");
        }
        TaskGroup result = repository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("TaskGroup with given id not found"));
        result.setDone(!result.isDone());

    }

}
