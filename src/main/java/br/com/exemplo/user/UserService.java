package br.com.exemplo.user;

import br.com.exemplo.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final UserRepository userRepository;

    public Optional<UserDTO> getById(Integer code) {
        return userRepository.findById(code).map(userMapper::toDTO);
    }

    public Page<UserDTO> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDTO);
    }

    public UserDTO save(UserDTO userDTO) {
        User user = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDTO(user);
    }

    public UserDTO update(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getCode()).orElseThrow(NotFoundException::new);
        userMapper.updateFromDTO(userDTO, user);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public void delete(Integer code) {
        User user = userRepository.findById(code).orElseThrow(NotFoundException::new);
        userRepository.deleteById(user.getCode());
    }

    public void saveCredential(Integer code, CredentialDTO credentialDTO) {
        User user = userRepository.getById(code);
        user.setCredential(userMapper.toEntity(credentialDTO));
        userRepository.save(user);

    }


}
