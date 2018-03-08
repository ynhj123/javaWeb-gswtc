package com.yuanshun.dao;

import com.yuanshun.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface UserMapper
{
  public abstract int insert(User paramUser);

  public abstract int insertSelective(User paramUser);

  public abstract List<User> queryUserListLikeName(@Param("userphone") String paramString);

  public abstract List<User> queryAll();

  public abstract List<User> queryAll(String paramString);

  public abstract int update(User paramUser);

  public abstract void deletes(@Param("deleteList") List<String> paramList);

  public abstract User queryByPhone(String paramString);
}