package com.pratiksha.blogApp.services.Impls;

import com.pratiksha.blogApp.entities.User;
import com.pratiksha.blogApp.exceptions.ResourceNotFoundException;
import com.pratiksha.blogApp.payloads.UserDto;
import com.pratiksha.blogApp.repositories.UserRepositories;
import com.pratiksha.blogApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.pratiksha.blogApp.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepositories userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = userRepository.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = this.userRepository.save(user);
        UserDto userDto1= this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
       List<UserDto> userDtos= users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
       return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        this.userRepository.delete(user);

    }

    private com.pratiksha.blogApp.entities.User dtoToUser(UserDto userDto){
        com.pratiksha.blogApp.entities.User  user = new com.pratiksha.blogApp.entities.User();
        user.setName(userDto.getName());
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }

    private UserDto userToDto(com.pratiksha.blogApp.entities.User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
