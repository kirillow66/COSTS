package ru.sberbank.jd.mappers;

import org.mapstruct.Mapper;
import ru.sberbank.jd.dto.UserDto;
import ru.sberbank.jd.entity.User;

/**
 * The interface User mapper.
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto>{
}
