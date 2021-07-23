package com.fc.service;

import com.fc.bean.Book;
import com.fc.bean.vo.BookVo;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 书籍接口
 */
public interface BooksService {
    /**
     * 查询出所有书籍
     * @return
     */
    List<Book> findAllBooks();

    /**
     *  根据书籍类型id查询
     * @param bookCategory
     * @return
     */
    List<BookVo> findBooksByCategoryId(int bookCategory);

    /**
     * 根据名字模糊查询
     * @param bookPartInfo
     * @return
     */
    List<BookVo> selectBooksByBookPartInfo(String bookPartInfo);
}
