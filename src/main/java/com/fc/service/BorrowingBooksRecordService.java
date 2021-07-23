package com.fc.service;

import com.fc.bean.vo.BorrowingBooksVo;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface BorrowingBooksRecordService {
    /**
     * 查询出本用户所有的还书记录
     * @param session
     * @return
     */
    List<BorrowingBooksVo>  selectAllBorrowRecord(HttpSession session);

    /**
     *  查询所有用户借书
     * @return
     */
    List<BorrowingBooksVo> selectAllByPage();
}
