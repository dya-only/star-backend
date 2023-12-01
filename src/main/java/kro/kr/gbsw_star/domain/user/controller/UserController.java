package kro.kr.gbsw_star.domain.user.controller;

import kro.kr.gbsw_star.domain.user.dto.TokenDto;
import kro.kr.gbsw_star.domain.user.dto.UserDto;
import kro.kr.gbsw_star.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("health")
    public ResponseEntity<HttpStatus> healthCheck() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@ModelAttribute UserDto.Request userDto) throws Exception {
        userService.create(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("@me")
    public ResponseEntity<?> findMe(@RequestHeader(name = "Authorization") String token) throws NotFoundException {
        UserDto.Response userDto = userService.findMe(token);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("by-pass")
    public ResponseEntity<TokenDto> login(@RequestBody UserDto.Request userDto) throws NotFoundException {
        String token = userService.loginByPass(userDto);
        TokenDto tokenDto = new TokenDto();

        tokenDto.setToken(token);
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) throws NotFoundException {
        UserDto.Response userDto = userService.find(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable(name = "id") Long id, UserDto.Request userDto) throws Exception {
        userService.update(id, userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) throws Exception {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
