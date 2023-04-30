package demo.mathapp.mapper;

import demo.mathapp.DTO.User.UserDTO;
import demo.mathapp.model.User;

public class UserMapper {

    public User dtoToEntity(UserDTO dto) {
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setSecondName(dto.getSecondName());
        entity.setRole(dto.getRole());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    //TODO: Zmiania nazwy
    public UserDTO entityToDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setFirstName(entity.getFirstName());
        dto.setSecondName(entity.getSecondName());
        return dto;
    }

    //TODO: Zmiania nazwy
    public UserDTO entityToDTO2(User entity) {
        UserDTO dto = new UserDTO();
        dto.setFirstName(entity.getFirstName());
        dto.setSecondName(entity.getSecondName());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}
