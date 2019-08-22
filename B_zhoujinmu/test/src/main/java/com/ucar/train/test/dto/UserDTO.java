package com.ucar.train.test.dto;

import java.io.Serializable;

/**
 * @author zhoujinmu
 * @title UserDTO
 * @projectName test
 * @description TODO
 * @created 2019-08-07 09:14
 **/
public class UserDTO implements Serializable {
    private Long id;
    private String name;
    private String psw;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
