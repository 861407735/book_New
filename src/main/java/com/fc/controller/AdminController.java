package com.fc.controller;

import com.fc.bean.Admin;
import com.fc.bean.Book;
import com.fc.bean.BookCategory;
import com.fc.bean.User;
import com.fc.service.AdminService;
import com.fc.service.BookCategoryService;
import com.fc.service.BooksService;
import com.fc.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private BookCategoryService bookCategoryService;  //书籍分类
    @Autowired
    private UserService userService;  //用户业务
    @Autowired
    private BooksService booksService; //书籍

    /**
     * 判断admin是否存在
//     */
//    @RequestMapping("/isAdminExist")
//    public String  adminIsExist(@RequestParam("adminName") String adminName){
//        boolean b = adminService.adminIsExist(adminName);
//        if (b) {
//            return "true";
//        } else {
//            return "false";
//        }
//    }

    /**
     * 管理员账号登录判断
     * @param userName
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/adminLogin")
    public String adminLogin(@RequestParam("userName")String userName, @RequestParam("password")String password, HttpSession session){
        Admin admin = adminService.adminLogin(userName, password);
        if (admin == null) {
            // flag 为 1 表示 登录失败
            session.setAttribute("flag", 1);
            return "index";
        }
        // flag = 0 表示用户名密码校验成功
        session.setAttribute("flag", 0);
        session.setAttribute("admin", admin);
        return "admin/index";
    }

    /**
     * 返回添加类别页面
     * 并分页返回
     */
    @RequestMapping("/addCategoryPage")
    public String addCategoryPage(@RequestParam(value = "pageNum",required = false) int pageNum, Model model) {
        PageHelper.startPage(pageNum,10);
        List<BookCategory> allBookCategory = bookCategoryService.findAllBookCategory();
        PageInfo<BookCategory> page = new PageInfo<>(allBookCategory);
        model.addAttribute("page", page);
        return "admin/addCategory";
    }



    /**
     * 返回查询用户页面
     */
    @RequestMapping("/showUsersPage")
    public String showUsersPage(Model model, @RequestParam(value = "pageNum",required = false) int pageNum) {
        PageHelper.startPage(pageNum,10);
        List<User> allUser = userService.findAllUser();

        PageInfo<User> page = new PageInfo<>(allUser);

        model.addAttribute("page", page);
        return "admin/showUsers";
    }
    /**
     * 返回查询书籍页面
     */
    @RequestMapping("/showBooksPage")
    public String showBooksPage(Model model) {
        PageHelper.startPage(1,10);
        List<Book> allBooks = booksService.findAllBooks();
        PageInfo<Book> page = new PageInfo<>(allBooks);
        model.addAttribute("page", page);
        return "admin/showBooks";
    }

    /**
     * 管理员退出登陆
     *
     * @param
     * @return
     */
    @RequestMapping("/adminLogOut")
    public String userLogOut(HttpSession session) {
        session.invalidate();
        return "index";
    }
    /**
     * 更新管理员信息
     *
     * @param admin
     * @param
     * @return
     */
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public boolean updateAdmin(Admin admin, HttpSession session) {
        return adminService.updateAdmin(admin, session);
    }

    /**
     * 返回书籍类型
     * @return
     */
    @RequestMapping("/findAllBookCategory")
    @ResponseBody
    public List<BookCategory> findAllBookCategory(){
        List<BookCategory> allBookCategory = bookCategoryService.findAllBookCategory();
        return allBookCategory;
    }

}
