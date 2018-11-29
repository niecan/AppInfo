package cn.app.service.impl;

import cn.app.dao.AppInfoMapper;
import cn.app.pojo.AppInfo;
import cn.app.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppInfoServiceImpl implements AppInfoService {
    @Autowired
    private AppInfoMapper appInfoMapper;
    @Override
    public List<AppInfo> getAppInfoList(String softwareName,String status,String flatformId,String categoryLevel1,String categoryLevel2,String categoryLevel3,Integer currentPageNo, Integer pageSize) {
        return appInfoMapper.getAppInfoList(softwareName,status,flatformId,categoryLevel1,categoryLevel2,categoryLevel3,currentPageNo,pageSize);
    }

    @Override
    public int appCount(String softwareName,String status,String flatformId,String categoryLevel1,String categoryLevel2,String categoryLevel3) {
        return appInfoMapper.appCount(softwareName,status,flatformId,categoryLevel1,categoryLevel2,categoryLevel3);
    }

    @Override
    public int add(AppInfo appInfo) {
        return appInfoMapper.add(appInfo);
    }

    @Override
    public AppInfo selAPK(String APKName) {
        return appInfoMapper.selAPK(APKName);
    }

    @Override
    public AppInfo selById(Integer id) {
        return appInfoMapper.selById(id);
    }

    @Override
    public int updateAppInfo(AppInfo appInfo) {
        return appInfoMapper.updateAppInfo(appInfo);
    }

    @Override
    public int delAppInfo(Integer id) {
        return appInfoMapper.delAppInfo(id);
    }

    @Override
    public int updateStatusUp(Integer id) {
        return appInfoMapper.updateStatusUp(id);
    }

    @Override
    public int updateStatusDown(Integer id) {
        return appInfoMapper.updateStatusDown(id);
    }

    @Override
    public int updateStatus(AppInfo appInfo) {
        return appInfoMapper.updateStatus(appInfo);
    }

    @Override
    public List<AppInfo> getAll() {
        return appInfoMapper.getAll();
    }

}
