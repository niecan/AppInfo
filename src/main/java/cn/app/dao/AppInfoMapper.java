package cn.app.dao;

import cn.app.pojo.AppInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface AppInfoMapper {
    List<AppInfo> getAppInfoList(@Param(value="softwareName")String softwareName,
                                 @Param(value="status")String status,
                                 @Param(value="flatformId")String flatformId,
                                 @Param(value="categoryLevel1")String categoryLevel1,
                                 @Param(value="categoryLevel2")String categoryLevel2,
                                 @Param(value="categoryLevel3")String categoryLevel3,
                                 @Param(value="currentPageNo")Integer currentPageNo,
                                 @Param(value="pageSize")Integer pageSize);
    int appCount(@Param(value="softwareName")String softwareName,
                 @Param(value="status")String status,
                 @Param(value="flatformId")String flatformId,
                 @Param(value="categoryLevel1")String categoryLevel1,
                 @Param(value="categoryLevel2")String categoryLevel2,
                 @Param(value="categoryLevel3")String categoryLevel3);
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
