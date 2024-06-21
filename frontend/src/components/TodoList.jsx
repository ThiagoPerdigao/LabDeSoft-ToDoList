import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash, faEdit } from '@fortawesome/free-solid-svg-icons'; // Importe o ícone de edição (faEdit)

export const TodoList = ({ task, deleteTodo, editTodo }) => {
  return (
    <div className="Todo">
      <p className={`${task.completed ? "completed" : "incompleted"}`}>
        {task.description}
      </p>
      <div>
        <FontAwesomeIcon className="delete-icon" icon={faTrash} onClick={() => deleteTodo(task.id)} />
        <button onClick={() => editTodo(task.id)}>
          <FontAwesomeIcon icon={faEdit} /> Edit {/* Adicione o ícone de edição aqui */}
        </button>
      </div>
    </div>
  );
}
