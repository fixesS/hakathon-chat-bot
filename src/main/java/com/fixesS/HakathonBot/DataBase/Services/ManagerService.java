package com.fixesS.HakathonBot.DataBase.Services;

import com.fixesS.HakathonBot.DataBase.Entities.Manager;
import com.fixesS.HakathonBot.DataBase.Repositories.ManagerRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public ManagerService(){

    }

    @Transactional
    public Manager findByManagerId(long managerId){
        return managerRepository.findByManagerId(managerId);
    }

    @Transactional
    public List<Manager> findAll(){
        return managerRepository.findAll();
    }
}
