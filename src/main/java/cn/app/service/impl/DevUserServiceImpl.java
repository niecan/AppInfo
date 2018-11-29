package cn.app.service.impl;

import cn.app.dao.DevUserMapper;
import cn.app.pojo.DevUser;
import cn.app.service.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevUserServiceImpl implements DevUserService {
    @Autowired
    private DevUserMapper devUserMapper;
    @Override
    public DevUser login(String devCode, String devPassword) {
        DevUser devUser=devUserMapper.login(devCode);
        if(null!=devUser) {
            if (devUser.getDevPassword().equals(devPassword)) {
                return devUser;
            }
        }
        return null;
    }

}
