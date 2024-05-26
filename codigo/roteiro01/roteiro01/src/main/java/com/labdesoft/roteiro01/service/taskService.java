package com.labdesoft.roteiro01.service;

import com.labdesoft.roteiro01.repository.taskRepository;
import com.labdesoft.roteiro01.entity.task;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class taskService {

    private final taskRepository taskRepository;

    public taskService(taskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Operation(summary = "Excluir uma tarefa pelo ID")
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Operation(summary = "Listar todas as tarefas")
    public Page<task> listAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
}
