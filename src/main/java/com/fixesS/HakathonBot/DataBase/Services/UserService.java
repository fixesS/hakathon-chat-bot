package com.fixesS.HakathonBot.DataBase.Services;

import com.fixesS.HakathonBot.DataBase.Entities.User;
import com.fixesS.HakathonBot.DataBase.Repositories.UserRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(){

    }

    @Transactional
    public void createUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public User findByChatId(long chatId){
        return userRepository.findByChatId(chatId);
    }

    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }
}
