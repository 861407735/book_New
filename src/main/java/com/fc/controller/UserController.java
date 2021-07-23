package com.fc.controller;

import com.fc.bean.Dept;
import com.fc.bean.User;
import com.fc.bean.vo.BorrowingBooksVo;
import com.fc.service.BorrowingBooksRecordService;
import com.fc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BorrowingBooksRecordService borrowingBooksRecordService;  //借书记录

    /**
     * 账号登录
     * @param userName
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/userLogin")
    public String userLogin(@RequestParam("userName")String userName, @RequestParam("password")String password,HttpSession session){

        User user = userService.userLogin(userName, password);
        if (null != user) {
            session.setAttribute("flag",0);
            session.setAttribute("user",user);
            return "user/index";
        }
        return "index";
    }

    /**
     * 验证用户是否存在
     * @param userName
     * @return
     */
    @RequestMapping("/isUserExist")
    @ResponseBody
    public String isUserExist(@RequestParam("userName") String userName){
        List<User> users = userService.findUserByUserName(userName);
        if(null == users){
            return "false";
        }else if(users.size()<1){
            return "false";
        }
        return "true";
    }

    /**
     * 查询全部部门
     * @return
     */
    @RequestMapping("/getDepts")
    @ResponseBody
    public List<Dept> getDepts(){
        List<Dept> allDept = userService.findAllDept();
        return allDept;
    }

    /**
     *
     *  返回用户借书界面
     * @return
     */
    @RequestMapping("/userBorrowBookRecord")
    public String userBorrowBookRecord(Model model,HttpSession session){
        List<BorrowingBooksVo> res = borrowingBooksRecordService.selectAllBorrowRecord(session);
        model.addAttribute("borrowingBooksList", res);
        return "user/borrowingBooksRecord";
    }

    /**
     * 返回个人信息页面
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/userMessagePage")
    public String userMessagePage(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("message_user",user);
        return "user/userMessage";
    }

    /**
     * 更新用户信息
     * @return
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public boolean updateUser(User user,HttpSession session){
        return userService.updateUser(user,session);
    }

    /**
     * 用户还书
     * @return
     */
    @RequestMapping("/userReturnBook")
    @ResponseBody
    public boolean userReturnBook(int bookId,HttpSession session){

        return userService.userReturnBook(bookId,session);
    }

    /**
     * 用户借书
     *
     * @param bookId
     * @param session
     * @return
     */
    @RequestMapping("/userBorrowingBook")
    @ResponseBody
    public boolean borrowingBook(int bookId, HttpSession session) {
        return userService.userBorrowingBook(bookId, session);
    }

    /**
     * 用户退出登录
     * @param session
     * @return
     */
    @RequestMapping("/userLogOut")
    public String userLogOut(HttpSession session) {
        session.invalidate();
        return "index";
    }

    /**
     * 根据用户id删除用户
     *
     * @param userId
     * @return
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public String deleteUserByUserId(@RequestParam("userId") int userId) {
        int res = userService.deleteUserById(userId);
        if (res > 0) {
            return "true";
        }
        return "false";
    }
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(User user) {
        int res = userService.insertUser(user);
        if (res > 0) {
            return "true";
        } else {
            return "false";
        }
    }

}
