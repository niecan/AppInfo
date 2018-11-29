package cn.app.service;

import cn.app.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryList(String parentId);
}
