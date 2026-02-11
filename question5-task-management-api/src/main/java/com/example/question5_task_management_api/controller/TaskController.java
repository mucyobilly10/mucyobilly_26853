package com.example.question5_task_management_api.controller;

import com.example.question5_task_management_api.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    // list to store tasks
    private List<Task> taskList = new ArrayList<>();
    
    // constructor to add sample data
    public TaskController() {
        // adding sample tasks with different priorities and statuses
        taskList.add(new Task(1L, "Complete Spring Boot assignment", "Finish all 5 questions for the REST API assignment", false, "HIGH", "2026-02-15"));
        taskList.add(new Task(2L, "Buy groceries", "Get milk, eggs, bread, and vegetables", false, "MEDIUM", "2026-02-11"));
        taskList.add(new Task(3L, "Study for exam", "Review chapters 5-8 for database exam", false, "HIGH", "2026-02-14"));
        taskList.add(new Task(4L, "Call dentist", "Schedule appointment for tooth cleaning", true, "LOW", "2026-02-10"));
        taskList.add(new Task(5L, "Gym workout", "Leg day - squats and deadlifts", true, "MEDIUM", "2026-02-09"));
        taskList.add(new Task(6L, "Read book", "Finish reading Clean Code book", false, "LOW", "2026-02-20"));
        taskList.add(new Task(7L, "Team meeting", "Discuss project timeline and deliverables", false, "HIGH", "2026-02-12"));
        taskList.add(new Task(8L, "Clean room", "Organize desk and do laundry", false, "MEDIUM", "2026-02-13"));
    }
    
    // GET /api/tasks - get all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskList);
    }
    
    // GET /api/tasks/{taskId} - get task by id
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        for (Task task : taskList) {
            if (task.getTaskId().equals(taskId)) {
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    // GET /api/tasks/status?completed={true/false}
    @GetMapping("/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam boolean completed) {
        List<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.isCompleted() == completed) {
                result.add(task);
            }
        }
        return ResponseEntity.ok(result);
    }
    
    // GET /api/tasks/priority/{priority} - get tasks by priority
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String priority) {
        List<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                result.add(task);
            }
        }
        return ResponseEntity.ok(result);
    }
    
    // POST /api/tasks - create new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        // find the highest ID and add 1
        Long newId = 1L;
        for (Task t : taskList) {
            if (t.getTaskId() >= newId) {
                newId = t.getTaskId() + 1;
            }
        }
        task.setTaskId(newId);
        taskList.add(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }
    
    // PUT /api/tasks/{taskId} - update task
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTaskId().equals(taskId)) {
                updatedTask.setTaskId(taskId);
                taskList.set(i, updatedTask);
                return ResponseEntity.ok(updatedTask);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    // PATCH /api/tasks/{taskId}/complete - mark task as completed
    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskComplete(@PathVariable Long taskId) {
        for (Task task : taskList) {
            if (task.getTaskId().equals(taskId)) {
                task.setCompleted(true);
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    // DELETE /api/tasks/{taskId} - delete task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTaskId().equals(taskId)) {
                taskList.remove(i);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
