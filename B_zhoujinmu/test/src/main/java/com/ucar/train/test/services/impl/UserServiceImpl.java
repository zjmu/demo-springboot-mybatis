package com.ucar.train.test.services.impl;

import com.ucar.train.test.dto.UserDTO;
import com.ucar.train.test.mapper.UserMapper;
import com.ucar.train.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoujinmu
 * @title UserService
 * @projectName test
 * @description TODO
 * @created 2019-08-07 09:11
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<UserDTO> getAllUser() {
        return userMapper.getAllUser();
    }
}
