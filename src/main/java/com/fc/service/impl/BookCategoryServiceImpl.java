package com.fc.service.impl;

import com.fc.bean.BookCategory;
import com.fc.dao.BookCategoryMapper;
import com.fc.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图书
 */
@Service
public class BookCategoryServiceImpl implements BookCategoryService {
    @Autowired
    private BookCategoryMapper categoryMapper;
    @Override
    public List<BookCategory> findAllBookCategory() {
        return categoryMapper.findAllBookCategory();
    }

    @Override
    public int deleteBookCategoryById(int bookCategoryId) {
        return categoryMapper.deleteByPrimaryKey(bookCategoryId);
    }
}
