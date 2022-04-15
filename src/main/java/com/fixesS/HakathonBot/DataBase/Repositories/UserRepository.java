package com.fixesS.HakathonBot.DataBase.Repositories;

import com.fixesS.HakathonBot.DataBase.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByChatId(Long chatId);
}
