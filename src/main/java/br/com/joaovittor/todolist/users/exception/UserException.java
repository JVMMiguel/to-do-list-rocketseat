package br.com.joaovittor.todolist.users.exception;

import br.com.joaovittor.todolist.exceptions.ToDoListException;

public class UserException extends ToDoListException {
    public UserException(String msg) {
        super(msg);
    }
}
