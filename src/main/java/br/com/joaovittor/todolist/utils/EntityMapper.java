package br.com.joaovittor.todolist.utils;

import java.util.List;

public interface EntityMapper<D, M> {

    M toModel(D dto);

    D toDTO(M model);

    List<M> toModel(List<D> dtoList);

    List<D> toDTO(List<M> modelList);
}
