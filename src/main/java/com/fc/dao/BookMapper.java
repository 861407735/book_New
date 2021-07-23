package com.fc.dao;

import com.fc.bean.Book;
import com.fc.bean.BookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMapper {
    long countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(Integer bookId);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExample(BookExample example);

    Book selectByPrimaryKey(Integer bookId);

    int updateByExampleSelective(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    /**
     * 查询所有书籍
     * @return
     */
    List<Book> findAllBooks();

    /**
     *  通过类型查询出
     * @param bookCategory
     */
    List<Book> findBooksByCategoryId(@Param("id") int bookCategory);

    /**
     * 通过书籍名称 模糊查询
     * @param bookPartInfo
     * @return
     */
    List<Book> selectBooksByBookPartInfo(@Param("bookName") String bookPartInfo);
}