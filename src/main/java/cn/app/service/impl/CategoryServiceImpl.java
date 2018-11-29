package cn.app.service.impl;

import cn.app.dao.CategoryMapper;
import cn.app.pojo.Category;
import cn.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> getCategoryList(String parentId) {
        return categoryMapper.getCategoryList(parentId);
    }
}
