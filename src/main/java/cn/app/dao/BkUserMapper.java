package cn.app.dao;

import cn.app.pojo.BkUser;

public interface BkUserMapper {
    BkUser login(String userCode);
}
