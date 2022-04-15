package com.fixesS.HakathonBot.DataBase.Repositories;

import com.fixesS.HakathonBot.DataBase.Entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByManagerId(Long managerId);
}
