package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/toDo/")
    public List<Task> showAllTask() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();

        taskIterable.forEach(task -> tasks.add(task));
        return tasks;
    }

    @PostMapping("/toDo/")
    public int addTask(Task task) {
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @GetMapping("/toDo/{id}")
    public ResponseEntity getTask(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/toDo/")
    public ResponseEntity deleteAllTasks() {
        taskRepository.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/toDo/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            taskRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PatchMapping("/toDo/{id}")
    public ResponseEntity changeTask(@PathVariable int id, Task newTask) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            taskRepository.save(newTask);
            return new ResponseEntity(HttpStatus.OK);
        }
    }
}
