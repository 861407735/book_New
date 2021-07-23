package com.fc.service.impl;

import com.fc.bean.*;
import com.fc.dao.BorrowingbooksMapper;
import com.fc.dao.DeptMapper;
import com.fc.dao.UserMapper;
import com.fc.service.BorrowingBooksRecordService;
import com.fc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private BorrowingbooksMapper borrowingbooksMapper;
    @Override
    public User userLogin(String userName, String password) {
        User user = userMapper.findUserByUserName(userName);
        if (null == user) {
            return null;
        }
        if(user.getUserPwd().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public List<User> findUserByUserName(String userName) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(userName);

        List<User> list = userMapper.selectByExample(userExample);
        return list;
    }

    @Override
    public List<Dept> findAllDept() {
        DeptExample deptExample = new DeptExample();
     //   DeptExample.Criteria criteria = deptExample.createCriteria();
        List<Dept> depts = deptMapper.selectByExample(deptExample);
        return depts;
    }

    @Override
    public List<User> findAllUser() {

        return userMapper.findAllUser();
    }

    @Override
    public boolean updateUser(User user, HttpSession session) {
        User user1 = (User) session.getAttribute("user");
        user.setUserId(user1.getUserId());
        int Rows = userMapper.updateById(user);//修改信息
        if(Rows>0){
            User user2 = userMapper.selectByPrimaryKey(user.getUserId());
            session.setAttribute("user",user2);
            return true;
        }
        return false;
    }

    @Override
    public boolean userReturnBook(int bookId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        BorrowingbooksExample example = new BorrowingbooksExample();
        BorrowingbooksExample.Criteria criteria = example.createCriteria();
        criteria.andBookIdEqualTo(bookId);
        criteria.andUserIdEqualTo(user.getUserId());
        int i = borrowingbooksMapper.deleteByExample(example);
        if (i>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean userBorrowingBook(int bookId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //首先从借书记录中查出此书是否被借出
        BorrowingbooksExample borrowingbooksExample = new BorrowingbooksExample();
        BorrowingbooksExample.Criteria criteria = borrowingbooksExample.createCriteria();
        criteria.andBookIdEqualTo(bookId);

        List<Borrowingbooks> list = borrowingbooksMapper.selectByExample(borrowingbooksExample);

        if (list.size()>0){ //说明已经被借出
            return false;
        }

        Borrowingbooks borrowingbooks = new Borrowingbooks();
        borrowingbooks.setBookId(bookId);
        borrowingbooks.setDate(new Date());
        borrowingbooks.setUserId(user.getUserId());
        int i=0;
        try {
            i = borrowingbooksMapper.insertSelective(borrowingbooks);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (i>0) {
            return true;  //借书成功
        }
        return false;
    }

    @Override
    public int deleteUserById(int userId) {

        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertSelective(user);
    }
}
