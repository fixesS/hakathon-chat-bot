package com.fixesS.HakathonBot.DataBase.Repositories;

import com.fixesS.HakathonBot.DataBase.Entities.Machine;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
    Machine findByMachineId(Long machineId);
    List<Machine> findByManagerId(Long managerId);
    List<Machine> findByCategoryId(Long categoryId);

}
