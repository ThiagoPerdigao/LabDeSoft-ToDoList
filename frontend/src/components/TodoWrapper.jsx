import React, { useState, useEffect } from 'react';
import { TodoForm } from './TodoForm';
import { TodoList } from './TodoList';

export const TodoWrapper = () => {
  const [todos, setTodos] = useState([]);

  useEffect(() => {
    const fetchTodos = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/task');
        if (!response.ok) {
          throw new Error('Erro ao buscar tarefas');
        }
        const data = await response.json();
        setTodos(data);
      } catch (error) {
        console.error('Erro ao buscar tarefas:', error);
      }
    };
    fetchTodos();
  }, []);

  const addTodo = (todo) => {
    setTodos([...todos, todo]);
  };

  const deleteTodo = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/api/task/${id}`, {
        method: 'DELETE',
      });
      if (!response.ok) {
        throw new Error('Erro ao deletar tarefa');
      }
      setTodos(todos.filter((todo) => todo.id !== id));
    } catch (error) {
      console.error('Erro ao deletar tarefa:', error);
    }
  };

  const editTodo = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/api/task/${id}/complete`, {
        method: 'PUT',
      });
      if (!response.ok) {
        throw new Error('Erro ao completar tarefa');
      }
      const updatedTask = await response.json();
      setTodos(
        todos.map((todo) =>
          todo.id === updatedTask.id ? updatedTask : todo
        )
      );
    } catch (error) {
      console.error('Erro ao completar tarefa:', error);
    }
  };

  // Função para ordenar as tarefas: pendentes primeiro, concluídas depois
  const sortedTodos = [...todos].sort((a, b) => {
    if (a.completed && !b.completed) {
      return 1; // Coloca 'a' (concluída) depois de 'b' (pendente)
    }
    if (!a.completed && b.completed) {
      return -1; // Coloca 'a' (pendente) antes de 'b' (concluída)
    }
    return 0; // Mantém a ordem original se ambas forem do mesmo tipo (pendente ou concluída)
  });

  return (
    <div className='TodoWrapper'>
      <h1>Lista de Tarefas</h1>
      <TodoForm addTodo={addTodo} />
      {sortedTodos.map((todo) => (
        <TodoList
          key={todo.id}
          task={todo}
          deleteTodo={() => deleteTodo(todo.id)}
          editTodo={() => editTodo(todo.id)}
        />
      ))}
    </div>
  );
};
