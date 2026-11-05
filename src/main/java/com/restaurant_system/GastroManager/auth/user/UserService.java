package com.restaurant_system.GastroManager.auth.user;

import com.restaurant_system.GastroManager.auth.dto.UserRequestDto;
import com.restaurant_system.GastroManager.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserService implements  IUserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User findEntityById(Long id) {
        return userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("user not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        return userMapper.toResponseDtoList(userRepository.findAll())  ;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        return userMapper.toResponseDto(this.findEntityById(id));
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        if(userRepository.existsByUsername(userRequestDto.getUsername())){
            throw new KeyAlreadyExistsException("user already exists");
        }
        if(userRepository.existsByEmail(userRequestDto.getEmail())){
            throw new KeyAlreadyExistsException("email already exists");
        }
        User user =  userMapper.registerDtoToUser(userRequestDto);

        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));



        return userMapper.toResponseDto( userRepository.save(user));
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        User user = this.findEntityById(id);
        userMapper.updateUserFromMapper(userRequestDto , user);
        return userMapper.toResponseDto(userRepository.save(user));
    }


    @Override
    @Transactional(readOnly = true)
    public Boolean exitsByName(String name) {
        return userRepository.existsByUsername(name);
    }

    @Override
    public void deleteById(Long id) {
        User user = this.findEntityById(id);
        userRepository.delete(user);
    }
}
