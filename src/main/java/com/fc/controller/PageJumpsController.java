package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 此页面用于页面跳转
 */
@Controller
public class PageJumpsController {

    /**
     * 返回新增用户页面
     */
    @RequestMapping("/addUserPage")
    public String addUserPage() {
        return "admin/addUser";
    }

    @RequestMapping("/adminInfoPage")
    public String adminInfo() {
        return "admin/adminInfo";
    }
    /**
     * 返回查询状态页面
     */
    @RequestMapping("/showStausPage")
    public String showStatusPage() {
        return "admin/showStaus";
    }
    /**
     * 返回管理员首页
     */
    @RequestMapping("/adminIndex")
    public String returnAdminIndexPage() {
        return "admin/index";
    }
    /**
     * 返回添加书籍页面
     */
    @RequestMapping("/addBookPage")
    public String addBookPage() {
        return "admin/addBook";
    }
    /**
     * 返回还书页面
     */
    @RequestMapping("/userReturnBooksPage")
    public String userReturnBooksPage() {
        return "user/returnBooks";
    }
    /**
     * 返回借书页面
     */
    @RequestMapping("/borrowingPage")
    public String borrowing() {
        return "user/borrowingBooks";
    }

    /**
     * 返回用户首页
     */
    @RequestMapping("/userIndex")
    public String userIndex() {
        return "user/index";
    }
    /**
     * 返回管理员登陆界面
     */
    @RequestMapping("/adminLoginPage")
    public String adminLoginPage() {
        return "adminLogin";
    }
    /**
     * 返回用户索书页面
     */
    @RequestMapping("/findBookPage")
    public String findBookPage() {
        return "user/findBook";
    }

}
