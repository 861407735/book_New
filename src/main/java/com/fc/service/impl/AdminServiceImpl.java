package com.fc.service.impl;

import com.fc.bean.Admin;
import com.fc.bean.AdminExample;
import com.fc.bean.Book;
import com.fc.bean.BookCategory;
import com.fc.dao.AdminMapper;
import com.fc.dao.BookCategoryMapper;
import com.fc.dao.BookMapper;
import com.fc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookCategoryMapper bookCategoryMapper;
    @Override
    public boolean adminIsExist(String adminName) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andAdminNameEqualTo(adminName);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if ( null == admins) {
            return false;
        }else if(admins.size()<1){
            return  false;
        }
        return true;
    }

    @Override
    public Admin adminLogin(String userName, String password) {
        Admin admin = adminMapper.loginJudge(userName);
        if (null == admin){
            return null;
        }
        if (admin.getAdminPwd().equals(password)) {
            return admin;
        }
        return null;
    }

    @Override
    public boolean updateAdmin(Admin admin, HttpSession session) {
        Admin sessionAdmin = (Admin) session.getAttribute("admin");
        admin.setAdminId(sessionAdmin.getAdminId());
        int resultRows = adminMapper.updateByPrimaryKey(admin);

        if (resultRows>0) {
            Admin adminNew = adminMapper.selectByPrimaryKey(admin.getAdminId());
            session.setAttribute("admin",adminNew);
            return true;
        }
        return false;
    }

    @Override
    public boolean addBook(Book book) {
        int insert = bookMapper.insert(book);
        if (insert>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addBookCategory(BookCategory bookCategory) {
        int insert = bookCategoryMapper.insert(bookCategory);
        if (insert>0) {
            return true;
        }
        return false;
    }

}
