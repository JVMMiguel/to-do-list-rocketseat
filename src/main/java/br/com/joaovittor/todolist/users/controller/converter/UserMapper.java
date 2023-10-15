package br.com.joaovittor.todolist.users.controller.converter;

import br.com.joaovittor.todolist.users.dto.UserDTO;
import br.com.joaovittor.todolist.users.model.User;
import br.com.joaovittor.todolist.utils.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<UserDTO, User> {

    @Override
    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User model);
}
