package com.fc.service;

import com.fc.bean.Admin;
import com.fc.bean.Book;
import com.fc.bean.BookCategory;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AdminService {
    /**
     * 查询admin是否存在
     * @param adminName
     * @return
     */
    boolean adminIsExist(String adminName);

    /**
     * 登录判断
     * @param userName
     * @param password
     * @return
     */
    Admin adminLogin(String userName, String password);

    /**
     * 更改管理信息
     * @param admin
     * @param session
     * @return
     */
    boolean updateAdmin(Admin admin, HttpSession session);

    /**
     * 录入新书
     * @param book
     * @return
     */
    boolean addBook(Book book);

    /**
     * 新建书籍种类
     * @param bookCategory
     * @return
     */
    boolean addBookCategory(BookCategory bookCategory);
}
