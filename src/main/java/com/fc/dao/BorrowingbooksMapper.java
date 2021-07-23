package com.fc.dao;

import com.fc.bean.Borrowingbooks;
import com.fc.bean.BorrowingbooksExample;
import java.util.List;

import com.fc.bean.vo.BorrowingBooksVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingbooksMapper {
    long countByExample(BorrowingbooksExample example);

    int deleteByExample(BorrowingbooksExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Borrowingbooks record);

    int insertSelective(Borrowingbooks record);

    List<Borrowingbooks> selectByExample(BorrowingbooksExample example);

    Borrowingbooks selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Borrowingbooks record, @Param("example") BorrowingbooksExample example);

    int updateByExample(@Param("record") Borrowingbooks record, @Param("example") BorrowingbooksExample example);

    int updateByPrimaryKeySelective(Borrowingbooks record);

    int updateByPrimaryKey(Borrowingbooks record);

    /**
     * 多表联查
     * 返回中间类
     * 1.用户类
     * 2.书籍类
     * @param userId  用户id
     * @return
     */
    List<BorrowingBooksVo> selectAllBorrowRecord(@Param("userId") Integer userId);

    /**
     * 查询所有用户
     * @return
     */
    List<BorrowingBooksVo>  selectAllByPage();
}