package com.fc.service.impl;

import com.fc.bean.BorrowingbooksExample;
import com.fc.bean.User;
import com.fc.bean.vo.BorrowingBooksVo;
import com.fc.dao.BorrowingbooksMapper;
import com.fc.service.BorrowingBooksRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BorrowBRServiceImpl implements BorrowingBooksRecordService {
    @Autowired
    private BorrowingbooksMapper borrowingbooksMapper;  //还书记录

    @Override
    public List<BorrowingBooksVo> selectAllBorrowRecord(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<BorrowingBooksVo> borrowingBooksVos = borrowingbooksMapper.selectAllBorrowRecord(user.getUserId());
        if (null == borrowingBooksVos){
            return null;
        }
        for (BorrowingBooksVo booksVo : borrowingBooksVos) {
            String date = booksVo.getDateOfBorrowing();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //计算出还书日期
            Date returnTime=null;
            try {
                Date parse = sdf.parse(date);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(parse);
                calendar.add(Calendar.MONTH,1); //增加一个月时间
                returnTime = calendar.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //因为都是时间戳  在这做个转换
            booksVo.setDateOfBorrowing(date);

            booksVo.setDateOfReturn(sdf.format(returnTime));
        }
        return borrowingBooksVos;
    }

    @Override
    public List<BorrowingBooksVo> selectAllByPage() {

        return  borrowingbooksMapper.selectAllByPage();
    }
}
