package com.fixesS.HakathonBot.DataBase.Entities;

import javax.persistence.*;

@Entity
@Table(name = "machines", schema = "public")
public class Machine {

    @Id
    @Column( name = "machine_id" )
    private Long machineId;

    @Column(name = "name")
    private String name;

    @Column( name = "link")
    private String link;

    @Column(name = "keywords")
    private String keywords;

    @Column( name = "category_id")
    private Long categoryId;

    @Column(name = "manager_id")
    private Long managerId;

    public Long getMachineId() {
        return machineId;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getKeywords() {
        return keywords;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getManagerId() {
        return managerId;
    }

    @Override
    public String toString() {
        return "Machine\n{" +
                "\nmachineId=" + machineId +
                ",\nname='" + name + '\'' +
                ",\nlink='" + link + '\'' +
                ",\nkeywords=" + keywords +
                ",\ncategoryId=" + categoryId +
                ",\nmanagerId=" + managerId +
                "\n}";
    }
}
