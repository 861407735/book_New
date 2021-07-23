package com.fc.service;

import com.fc.bean.BookCategory;

import java.util.List;

public interface BookCategoryService {
    /**
     * 查询出所有的图书分类
     * @return
     */
    List<BookCategory> findAllBookCategory();

    /**
     * 根据id删除种类
     * @param bookCategoryId
     * @return
     */
    int deleteBookCategoryById(int bookCategoryId);
}
