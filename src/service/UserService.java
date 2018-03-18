package service;

import dao.UserDao;
import dto.UserDto;
import entity.Role;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    public User login(String name, String password) {
        return UserDao.getInstance().login(name, password);
    }

    public User save(UserDto userDto) {
        User user = User.builder()
                .name(userDto.getLogin())
                .password(userDto.getPassword())
                .addresMailBox(userDto.getMailBox())
                .rols(Role.builder()
                        .name("User")
                        .id(11)
                        .build())
                .build();
        user = UserDao.getInstance().save(user);

        return user;
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
