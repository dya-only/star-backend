package kro.kr.gbsw_star.domain.user.service;

import kro.kr.gbsw_star.domain.user.dto.UserDto;
import kro.kr.gbsw_star.domain.user.entity.User;
import kro.kr.gbsw_star.domain.user.repository.UserRepository;
import kro.kr.gbsw_star.util.image.Image;
import kro.kr.gbsw_star.util.image.ImageUploader;
import kro.kr.gbsw_star.util.security.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ImageUploader imageUploader;
    private final JwtTokenService jwtTokenService;

    // create
    public void create(UserDto.Request userDto) throws Exception {
        String password = userDto.getPassword();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        Image image = imageUploader.upload(userDto.getImage(), "user");

        userDto.setPassword(hashedPassword);
        User user = new User(userDto, image.getStorePath());
        userRepository.save(user);
    }

    // findMe
    public UserDto.Response findMe(String token) throws NotFoundException {
        return new UserDto.Response(userRepository.findById(jwtTokenService.getId(token)).orElseThrow(NotFoundException::new));
    }

    // findById
    public UserDto.Response find(Long id) throws NotFoundException {
        return new UserDto.Response(userRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    // update
    public void update(Long id, UserDto.Request userDto) throws Exception {
        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        String password = userDto.getPassword();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        userDto.setPassword(hashedPassword);
        user.update(userDto, user.getImage());
        userRepository.save(user);
    }

    // delete
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    // login
    public String loginByPass(UserDto.Request userDto) throws NotFoundException {
        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(NotFoundException::new);
        String password = userDto.getPassword();
        String hashedPassword = user.getPassword();

        if (!BCrypt.checkpw(password, hashedPassword)) {
            throw new RuntimeException("Invalid password");
        }

        return jwtTokenService.generateToken(user.getId());
    }
}
