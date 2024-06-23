import React from 'react';

export const TodoList = ({ task, deleteTodo, editTodo }) => {
  const handleDelete = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/api/task/${id}`, {
        method: 'DELETE',
      });
      if (!response.ok) {
        throw new Error('Erro ao deletar tarefa');
      }
      deleteTodo(id);
    } catch (error) {
      console.error('Erro ao deletar tarefa:', error);
    }
  };

  const handleComplete = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/api/task/${id}/complete`, {
        method: 'PUT',
      });
      if (!response.ok) {
        throw new Error('Erro ao completar tarefa');
      }
      const updatedTask = await response.json();
      editTodo(updatedTask);
    } catch (error) {
      console.error('Erro ao completar tarefa:', error);
    }
  };

  return (
    <div className={`Todo ${task.completed ? "completed" : "incompleted"}`}>
      <p>
        <strong>Descrição:</strong> {task.description}
      </p>
      <p><strong>Tipo:</strong> {task.type}</p>
      <p><strong>Prioridade:</strong> {task.priority}</p>
      {task.finalDate && (
        <p><strong>Data Final:</strong> {task.finalDate}</p>
      )}
      <p><strong>Status:</strong> {task.completed ? 'Concluída' : 'Pendente'}</p>
      <div className="buttons">
        <button className="delete-icon" onClick={() => handleDelete(task.id)}>Deletar</button>
        {!task.completed && (
          <button className="edit-icon" onClick={() => handleComplete(task.id)}>Completar</button>
        )}
      </div>
    </div>
  );
};
