package cn.app.dao;

import cn.app.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    List<Category> getCategoryList(@Param("parentId") String parentId );
}
