package cn.app.service.impl;

import cn.app.dao.DictionaryMapper;
import cn.app.pojo.Dictionary;
import cn.app.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Override
    public List<Dictionary> getDictionaryList(String typeCode) {
        return dictionaryMapper.getDictionaryList(typeCode);
    }
}
