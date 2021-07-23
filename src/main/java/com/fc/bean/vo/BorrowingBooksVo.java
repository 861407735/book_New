package com.fc.bean.vo;

import com.fc.bean.Book;
import com.fc.bean.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BorrowingBooksVo {
    private User user;   //用户类
    private Book book;  //借阅书籍
   // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String dateOfBorrowing;  //借书日期
  //  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String dateOfReturn;    //还书日期
}
