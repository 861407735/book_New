package com.fc.controller;

import com.fc.bean.vo.BorrowingBooksVo;
import com.fc.service.BorrowingBooksRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BorrowingController {

    @Autowired
    private BorrowingBooksRecordService borrowingBooksRecordService;

    /**
     * 返回所有借书页面
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping("/allBorrowBooksRecordPage")
    public String allBorrowingBooksRecordPage(Model model, @RequestParam("pageNum") int pageNum) {
        PageHelper.startPage(pageNum,10);
        List<BorrowingBooksVo> borrowingBooksVos = borrowingBooksRecordService.selectAllByPage();
        PageInfo<BorrowingBooksVo> page = new PageInfo<>(borrowingBooksVos);
        model.addAttribute("page", page);
        return "admin/allBorrowingBooksRecord";
    }
}
