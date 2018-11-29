package cn.app.dao;

import cn.app.pojo.DevUser;

public interface DevUserMapper {
    DevUser login(String devCode);
}
