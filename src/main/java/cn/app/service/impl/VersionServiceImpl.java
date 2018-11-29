package cn.app.service.impl;

import cn.app.dao.VersionMapper;
import cn.app.pojo.Version;
import cn.app.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    private VersionMapper versionMapper;
    @Override
    public List<Version> getVersionList(Integer appId) {
        return versionMapper.getVersionList(appId);
    }

    @Override
    public int addVersion(Version version) {
        return versionMapper.addVersion(version);
    }

    @Override
    public int selByVersionNo(String versionNo,Integer appId) {
        return versionMapper.selByVersionNo(versionNo,appId);
    }

    @Override
    public Version selById(Integer id) {
        return versionMapper.selById(id);
    }

    @Override
    public int updateVersion(Version version) {
        return versionMapper.updateVersion(version);
    }

    @Override
    public int delVersion(Integer id) {
        return versionMapper.delVersion(id);
    }

}
