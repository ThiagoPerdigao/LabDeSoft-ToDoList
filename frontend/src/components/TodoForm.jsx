import React, { useState } from 'react';

export const TodoForm = ({ addTodo }) => {
  const [description, setDescription] = useState('');
  const [type, setType] = useState('');
  const [priority, setPriority] = useState('');
  const [finalDate, setFinalDate] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (description && type && priority) {
      try {
        const response = await fetch('http://localhost:8080/api/task', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            description,
            type,
            priority,
            finalDate: finalDate || null,
            completed: false,
          }),
        });
        if (!response.ok) {
          throw new Error('Erro ao criar tarefa');
        }
        const data = await response.json();
        addTodo(data);
        setDescription('');
        setType('');
        setPriority('');
        setFinalDate('');
      } catch (error) {
        console.error('Erro ao criar tarefa:', error);
      }
    }
  };

  return (
    <form className="TodoForm" onSubmit={handleSubmit}>
      <input 
        type="text" 
        value={description} 
        onChange={(e) => setDescription(e.target.value)} 
        className="todo-input" 
        placeholder='Descrição da Tarefa' 
      />
      <select value={type} onChange={(e) => setType(e.target.value)} required>
        <option value="">Selecione o tipo</option>
        <option value="DATA">Data</option>
        <option value="PRAZO">Prazo</option>
        <option value="LIVRE">Livre</option>
      </select>
      <select value={priority} onChange={(e) => setPriority(e.target.value)} required>
        <option value="">Selecione a prioridade</option>
        <option value="ALTA">Alta</option>
        <option value="MEDIA">Média</option>
        <option value="BAIXA">Baixa</option>
      </select>
      <input 
        type="date" 
        value={finalDate} 
        onChange={(e) => setFinalDate(e.target.value)} 
        className="todo-input" 
        placeholder='Data Final' 
      />
      <button type="submit" className='todo-btn'>Adicionar Tarefa</button>
    </form>
  );
};
