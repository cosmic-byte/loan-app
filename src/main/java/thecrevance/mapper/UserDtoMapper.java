package thecrevance.mapper;

import thecrevance.dto.PreUser;
import thecrevance.dto.UserDto;
import thecrevance.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    private ModelMapper modelMapper;
    public UserDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PreUser toPreUser(User user) {
        PreUser preUser = modelMapper.map(user, PreUser.class);
        return preUser;
    }

    public User toUser(PreUser preUser) {
        User user = modelMapper.map(preUser, User.class);
        return user;
    }

    public UserDto toUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
