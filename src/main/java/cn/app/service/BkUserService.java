package cn.app.service;

import cn.app.pojo.BkUser;

public interface BkUserService {
    BkUser login(String userCode, String userPassword);
}
