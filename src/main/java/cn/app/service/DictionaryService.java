package cn.app.service;

import cn.app.pojo.Dictionary;

import java.util.List;

public interface DictionaryService {
    List<Dictionary> getDictionaryList(String typeCode);
}
