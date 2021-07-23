package com.fc.service.impl;

import com.fc.bean.Book;
import com.fc.bean.Borrowingbooks;
import com.fc.bean.vo.BookVo;
import com.fc.dao.BookMapper;
import com.fc.dao.BorrowingbooksMapper;
import com.fc.service.BooksService;
import com.fc.service.BorrowingBooksRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BorrowingbooksMapper borrowingbooksMapper;

    @Override
    public List<Book> findAllBooks() {

        return bookMapper.findAllBooks();
    }

    @Override
    public List<BookVo> findBooksByCategoryId(int bookCategory) {
        List<Book> booksByCategoryId = bookMapper.findBooksByCategoryId(bookCategory);

        List<BookVo> bookVos = new ArrayList<>();
        for (Book book : booksByCategoryId) {
            BookVo bookVo = new BookVo();
            bookVo.setBookId(book.getBookId());
            bookVo.setBookAuthor(book.getBookAuthor());
            bookVo.setBookName(book.getBookName());
            bookVo.setBookPublish(book.getBookPublish());
            Borrowingbooks borrowingbooks = borrowingbooksMapper.selectByPrimaryKey(bookVo.getBookId());
            if(null == borrowingbooks){
                bookVo.setIsExist("可借");
            }else{
                bookVo.setIsExist("不课借出");
            }
            bookVos.add(bookVo);
        }
        return bookVos;
    }

    @Override
    public List<BookVo> selectBooksByBookPartInfo(String bookPartInfo) {

        List<Book> booksByCategoryId = bookMapper.selectBooksByBookPartInfo("%"+bookPartInfo+"%");

        List<BookVo> bookVos = new ArrayList<>();
        for (Book book : booksByCategoryId) {
            BookVo bookVo = new BookVo();
            bookVo.setBookId(book.getBookId());
            bookVo.setBookAuthor(book.getBookAuthor());
            bookVo.setBookName(book.getBookName());
            bookVo.setBookPublish(book.getBookPublish());
            Borrowingbooks borrowingbooks = borrowingbooksMapper.selectByPrimaryKey(bookVo.getBookId());
            if(null == borrowingbooks){
                bookVo.setIsExist("可借");
            }else{
                bookVo.setIsExist("不课借出");
            }
            bookVos.add(bookVo);
        }
        return bookVos;
    }
}
