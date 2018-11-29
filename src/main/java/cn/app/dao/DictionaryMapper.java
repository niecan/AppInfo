package cn.app.dao;

import cn.app.pojo.Dictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictionaryMapper {
    List<Dictionary> getDictionaryList(@Param("typeCode") String typeCode);
}
