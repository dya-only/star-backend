package kro.kr.gbsw_star.domain.user.dto;

import kro.kr.gbsw_star.domain.user.entity.User;
import kro.kr.gbsw_star.domain.user.service.UserService;
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
    public static class Login {
        String email;
        String password;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        String email;
        String githubId;
        String password;
        String isRanking;
        Integer stars;

        MultipartFile image;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        Long id;
        String email;
        String githubId;
        String isRanking;
        Integer stars;

        String image;
        public Response (User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.githubId = user.getGithubId();
            this.isRanking = user.getIsRanking();
            this.stars = user.getStars();

            this.image = user.getImage();
        }
    }
}
