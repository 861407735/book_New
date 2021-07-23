package com.fc.controller;

import com.fc.bean.Book;
import com.fc.bean.BookCategory;
import com.fc.bean.vo.BookVo;
import com.fc.service.AdminService;
import com.fc.service.BookCategoryService;
import com.fc.service.BooksService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BooksService bookService;

    @Autowired
    private AdminService adminService;
    @Autowired
    private BookCategoryService bookCategoryService;

    /**
     * 管理员&emsp;&emsp;录入新书
     *
     * @param book
     * @return
     */
    @RequestMapping("/addBook")
    @ResponseBody
    public String addBook(Book book) {
        boolean res = adminService.addBook(book);
        if (res) {
            return "true";
        }
        return "false";
    }

    /**
     * 根据书籍类型查询
     * @param pageNum
     * @param bookCategory
     * @param model
     * @return
     */
    @RequestMapping("/showBooksResultPageByCategoryId")
    public String showBooksResultPageByCategoryId(@RequestParam(value = "pageNum",required = false) int pageNum, @RequestParam("bookCategory") int bookCategory, Model model) {
        PageHelper.startPage(pageNum,10);
        List<BookVo> booksByCategoryId = bookService.findBooksByCategoryId(bookCategory);
        PageInfo<BookVo> page = new PageInfo<>(booksByCategoryId);
        model.addAttribute("page", page);
        model.addAttribute("bookCategory", bookCategory);
        return "admin/showBooks";
    }

    /**
     * 根据书籍信息模糊查询
     *
     * @param bookPartInfo
     * @return
     */
    @RequestMapping("/findBookByBookPartInfo")
    public String findBooksResultPage(@RequestParam("bookPartInfo") String bookPartInfo, Model model) {

        List<BookVo> bookVos = bookService.selectBooksByBookPartInfo(bookPartInfo);

        model.addAttribute("bookList", bookVos);
        return "user/findBook";
    }

    /**
     * 查询出所有书籍种类
     * @return
     */
//    @RequestMapping("/findAllBookCategory")
//    @ResponseBody
//    public List<BookCategory> findAllBookCategory(){
//        return bookCategoryService.findAllBookCategory();
//    }

    /**
     * 新建书籍种类
     *
     * @param bookCategory
     * @return
     */
    @RequestMapping("/addBookCategory")
    @ResponseBody
    public String addBookCategory(BookCategory bookCategory) {
        boolean b = adminService.addBookCategory(bookCategory);
        if (b) {
            return "true";
        }
        return "false";
    }

    /**
     * 根据书籍种类id删除种类
     *
     * @param bookCategoryId
     * @return
     */
    @RequestMapping("/deleteCategory")
    @ResponseBody
    public String deleteBookCategoryById(@RequestParam("bookCategoryId") int bookCategoryId) {
        int res = bookCategoryService.deleteBookCategoryById(bookCategoryId);
        if (res > 0) {
            return "true";
        }
        return "false";
    }

}

