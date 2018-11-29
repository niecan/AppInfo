package cn.app.dao;

import cn.app.pojo.Version;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VersionMapper {
    List<Version> getVersionList(@Param("appId") Integer appId);
    int addVersion(Version version);
    int selByVersionNo(@Param("versionNo")String versionNo,@Param("appId")Integer appId );
    Version selById(@Param("id")Integer id);
    int updateVersion(Version version);
    int delVersion(Integer id);

}
