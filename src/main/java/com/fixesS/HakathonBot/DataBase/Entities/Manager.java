package com.fixesS.HakathonBot.DataBase.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "managers", schema = "public")
public class Manager {

    @Id
    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "surname")
    private String surname;

    @Column(name = "telegram_name")
    private String telegramName;

    public String getTelegramName() {
        return telegramName;
    }

    public Long getManagerId() {
        return managerId;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Manager\n{" +
                "\nmanagerId=" + managerId +
                ",\nsurname='" + surname + '\'' +
                ",\ntelegramName='" + telegramName + '\'' +
                "\n}";
    }
}
