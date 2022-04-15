package com.fixesS.HakathonBot.DataBase.Services;

import com.fixesS.HakathonBot.DataBase.Entities.Machine;
import com.fixesS.HakathonBot.DataBase.Repositories.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    public MachineService(){

    }
    @Transactional
    public Machine findByMachineId(long machineId){
        return machineRepository.findByMachineId(machineId);
    }

    @Transactional
    public List<Machine> findByManagerId(long managerId){
        return machineRepository.findByManagerId(managerId);
    }

    @Transactional
    public List<Machine> findByCategoryId(long categoryId){
        return machineRepository.findByCategoryId(categoryId);
    }


    @Transactional
    public List<Machine> findAll(){
        return machineRepository.findAll();
    }
}
