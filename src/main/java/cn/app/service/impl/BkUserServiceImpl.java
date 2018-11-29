package cn.app.service.impl;

import cn.app.dao.BkUserMapper;
import cn.app.pojo.BkUser;
import cn.app.service.BkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BkUserServiceImpl implements BkUserService {
    @Autowired
    private BkUserMapper bkUserMapper;
    @Override
    public BkUser login(String userCode, String userPassword) {
        BkUser bkUser=bkUserMapper.login(userCode);
        if(null!=bkUser) {
            if (bkUser.getUserPassword().equals(userPassword)) {
                return bkUser;
            }
        }
        return null;
    }
}
