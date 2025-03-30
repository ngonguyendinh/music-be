package com.example.demo.service.user;

import com.example.demo.config.CustomUserDetailService;
import com.example.demo.config.jwt.JwtProvider;
import com.example.demo.entity.user.User;
import com.example.demo.form.FormCreateUser;
import com.example.demo.form.FormUpdateUser;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CustomUserDetailService customUserDetailService;
    private JavaMailSender mailSender;
    @Override
    public User create(FormCreateUser formCreateUser) {
        User user = new User();
        user.setFullName(formCreateUser.getFullName());
        user.setPassword(passwordEncoder.encode(formCreateUser.getPassword()));
        user.setEmail(formCreateUser.getEmail());
        user.setGender(formCreateUser.getGender());
        user.setLevel("NORMAL");
        user.setRole("USER");
        return userRepository.save(user);
    }

    @Override
    public User findById(int userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User update(FormUpdateUser form, User user) {
        user.setAvatar(form.getAvatar()==null? user.getAvatar() : form.getAvatar());
        user.setFullName(form.getFullName()==null? user.getFullName() : form.getFullName());

        if (form.getNewPassword() != null){
            if(!passwordEncoder.matches(form.getOldPassword(), user.getPassword())){
                throw  new RuntimeException("please check password again");
            }
            user.setPassword(passwordEncoder.encode(form.getNewPassword()));
        }
        user.setBackground(form.getBackground()==null? user.getBackground() : form.getBackground());

        user.setLevel(form.getLevel()== null? String.valueOf(user.getLevel()):form.getLevel());

        return userRepository.save(user);
    }

    @Override
    public User followUser(int id, int idFollower) {
        User user1 = userRepository.findById(id).get();
        User user2 = userRepository.findById(idFollower).get();
        if(user1 == null || user2 == null){
            return null;
        }
        if (user1.getFollowing().contains(user2.getId())){
                user1.getFollowing().remove(user2.getId());
                user2.getFollower().remove(user1.getId());
        }else {
        user2.getFollower().add(user1.getId());
        user1.getFollowing().add(user2.getId());
        }
        userRepository.save(user1);
        userRepository.save(user2);

        return user1;
    }


    @Override
    public List<User> searchUser(String keySearch) {
        return userRepository.searchUser(keySearch);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email).get();
        return user;
    }

}
