package com.labdesoft.roteiro01.mock;

import com.labdesoft.roteiro01.entity.task;
import com.labdesoft.roteiro01.entity.taskPriority;
import com.labdesoft.roteiro01.entity.taskType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class taskMockTest {

    public static List<task> createTasks() {
        List<task> tasks = new ArrayList<>();

        // Criando algumas tarefas fictícias
        task task1 = new task("Descrição da Tarefa 1", taskType.DATA, taskPriority.ALTA);
        task1.setFinalDate(LocalDate.now().plusDays(7)); // Defina a data final da tarefa
        task1.setCompleted(false); // Defina se a tarefa está completa ou não

        task task2 = new task("Descrição da Tarefa 2", taskType.PRAZO, taskPriority.MEDIA);
        task2.setFinalDate(LocalDate.now().plusDays(5)); // Defina a data final da tarefa
        task2.setCompleted(true); // Defina se a tarefa está completa ou não

        // Adicionando as tarefas à lista
        tasks.add(task1);
        tasks.add(task2);

        return tasks;
    }
}
