package com.example.exp7.service;
import com.example.exp7.model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    public String registerUser(String id, String password, String email){
        for(User user : users){
            if(user.getId().equals(id) && user.getEmail().equals(email)){
                return "이미 존재하는 아이디와 이메일입니다.";
            }
            if(user.getId().equals(id)){
                return "이미 존재하는 아이디 입니다.";
            }
            if(user.getEmail().equals(email)){
                return "이미 가입된 이메일 입니다.";
            }
        }
        users.add(new User(id,password,email));
        return "회원가입 성공";
    }
    public String loginUser(String id, String password){
        for (User user : users){
            if(user.getId().equals(id) && user.getPassword().equals(password)){
                return "로그인 성공";
            }
        }
        return "로그인 실패 : 아이디, 비밀번호를 확인하세요";
    }
    public User findByEmail(String email){
        for(User user : users){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }
}
