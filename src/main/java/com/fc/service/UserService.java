package com.fc.service;

import com.fc.bean.Dept;
import com.fc.bean.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    /**
     *查询账号是否存在
     * @param userName
     * @param password
     * @return
     */
     User userLogin(String userName, String password);

    /**
     * 查询名字为UserName 的所有用户
     * @param userName
     * @return
     */
     List<User> findUserByUserName(String userName);

    /**
     * 查询所有部门
     * @return
     */
     List<Dept> findAllDept();

    /**
     * 查询所有用户
     * @return
     */
     List<User> findAllUser();

    /**
     * 更新用户信息
     * @param user
     * @param session
     * @return
     */
     boolean updateUser(User user, HttpSession session);

    /**
     * 用户还书
     * @param bookId
     * @param session
     * @return
     */
     boolean userReturnBook(int bookId, HttpSession session);

    /**
     * 用户借书
     * @param bookId
     * @param session
     * @return
     */
     boolean userBorrowingBook(int bookId, HttpSession session);

    /**
     * 根据id删除
     * @param userId
     * @return
     */
    int deleteUserById(int userId);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int insertUser(User user);
}
