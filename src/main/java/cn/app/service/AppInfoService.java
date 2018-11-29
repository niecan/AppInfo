package cn.app.service;

import cn.app.pojo.AppInfo;

import java.util.List;

public interface AppInfoService {
    List<AppInfo> getAppInfoList(String softwareName,String status,String flatformId,String categoryLevel1,String categoryLevel2,String categoryLevel3,Integer currentPageNo,Integer pageSize);
    int appCount(String softwareName,String status,String flatformId,String categoryLevel1,String categoryLevel2,String categoryLevel3);
    int add(AppInfo appInfo);
    AppInfo selAPK(String APKName);
    AppInfo selById(Integer id);
    int updateAppInfo(AppInfo appInfo);
    int delAppInfo(Integer id);
    int updateStatusUp(Integer id);
    int updateStatusDown(Integer id);
    int updateStatus(AppInfo appInfo);
    List<AppInfo> getAll();
}
