package com.labdesoft.roteiro01.unit.controller;

import com.labdesoft.roteiro01.controller.taskController;
import com.labdesoft.roteiro01.entity.task;
import com.labdesoft.roteiro01.repository.taskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class taskControllerTest {

    @Mock
    private taskRepository taskRepository;

    @InjectMocks
    private taskController taskController;

    @BeforeEach
    public void setUp() {
        // Configura o comportamento do mock do taskRepository para o método save
        when(taskRepository.save(any(task.class)))
                .thenAnswer(invocation -> {
                    task savedTask = invocation.getArgument(0);
                    savedTask.setId(1L); // Supondo que o ID seja atribuído ao salvar
                    return savedTask;
                });
    }

    @Test
    @DisplayName("Should create a new task")
    public void shouldCreateNewTask() {
        // Cria uma nova tarefa para o teste
        task newTask = new task();
        newTask.setDescription("Nova Tarefa");

        // Chama o método createTask() no controller
        ResponseEntity<task> responseEntity = taskController.createTask(newTask);

        // Verifica se a resposta é bem-sucedida
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Verifica se a tarefa retornada possui um ID não nulo
        task createdTask = responseEntity.getBody();
        assertEquals(1L, createdTask.getId()); // Supondo que o ID atribuído seja 1
        assertEquals("Nova Tarefa", createdTask.getDescription()); // Verifica se a descrição está correta
    }
}
