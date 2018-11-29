package cn.app.service;

import cn.app.pojo.DevUser;

public interface DevUserService {
    DevUser login(String devCode, String devPassword);
}
