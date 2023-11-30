package kro.kr.gbsw_star.domain.user.entity;

import jakarta.persistence.*;
import kro.kr.gbsw_star.domain.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String email;

    String githubId;
    String password;

    String image;

    public User(UserDto.Request userDto, String image) {
        this.email = userDto.getEmail();
        this.githubId = userDto.getGithubId();
        this.password = userDto.getPassword();

        this.image = image;
    }

    public void update(UserDto.Request userDto, String image) {
        this.email = userDto.getEmail();
        this.githubId = userDto.getGithubId();
        this.password = userDto.getPassword();

        this.image = image;
    }
}
