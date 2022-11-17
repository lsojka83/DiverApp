package pl.coderslab.service;

import pl.coderslab.model.User;

public interface UserService {
    User findByUserName(String name);
    User findByEmail(String email);

    User findById(Long id);

    void saveUser(User user);

    void saveAdmin(User admin);

    void updateUser(User user);
    void updateUser(User user, boolean updatePassword);

}
