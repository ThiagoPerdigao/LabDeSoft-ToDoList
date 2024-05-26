package com.labdesoft.roteiro01.unit.service;

import com.labdesoft.roteiro01.entity.task;
import com.labdesoft.roteiro01.mock.taskMockTest;
import com.labdesoft.roteiro01.repository.taskRepository;
import com.labdesoft.roteiro01.service.taskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

public class taskServiceTest {

    @Mock
    taskRepository tasksRepository;

    private taskService taskService;

    @BeforeEach
public void setup() {
    taskService = new taskService(tasksRepository);

    // Criação da lista de tarefas
    List<task> tasks = taskMockTest.createTasks();
    
    // Criar um objeto Page com a lista de tarefas
    Page<task> taskPage = new PageImpl<>(tasks);

    // Mock do objeto Pageable
    Pageable pageable = PageRequest.of(0, 5, Sort.by(
            Sort.Order.asc("id")));

    // Mock do retorno do método findAll() do taskRepository
    Mockito.when(tasksRepository.findAll(pageable)).thenReturn(taskPage);

    // Se precisar usar o método deleteById(), você pode configurar o comportamento dele aqui
    Mockito.doNothing().when(tasksRepository).deleteById(Mockito.anyLong());
}



    @Test
    @DisplayName("Should return all tasks")
    public void should_list_all_tasks_repository() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.desc("id")));
        Page<task> tasks = taskService.listAll(pageable);
        assertEquals(tasks.getTotalPages(),1);
        assertEquals(tasks.getNumberOfElements(), 2);
        assertNotNull(tasks);
    }
}
