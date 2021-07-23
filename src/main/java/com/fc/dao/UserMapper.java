package com.fc.dao;

import com.fc.bean.User;
import com.fc.bean.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    long countByExample(UserExample example);


    int deleteByExample(UserExample example);
    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    User selectByPrimaryKey(@Param("id") Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 查询
     * @param userName
     * @return
     */
    User findUserByUserName(@Param("name") String userName);

    /**
     * 查询出全部用户
     * @return
     */
    List<User> findAllUser();

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updateById(User user);
}