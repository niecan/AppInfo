package cn.app.service;

import cn.app.pojo.Version;

import java.util.List;

public interface VersionService {
    List<Version> getVersionList(Integer appId);
    int addVersion(Version version);
    int selByVersionNo(String versionNo,Integer appId);
    Version selById(Integer id);
    int updateVersion(Version version);
    int delVersion(Integer id);
}
