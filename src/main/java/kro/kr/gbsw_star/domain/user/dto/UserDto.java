package kro.kr.gbsw_star.domain.user.dto;

import kro.kr.gbsw_star.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class UserDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        String email;
        String githubId;
        String password;
        Integer stars;

        MultipartFile image;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        String email;
        String githubId;
        Integer stars;

        String image;
        public Response (User user) {
            this.email = user.getEmail();
            this.githubId = user.getGithubId();

            this.image = user.getImage();
        }
    }
}
