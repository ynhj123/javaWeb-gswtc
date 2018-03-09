package com.yuanshun.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanshun.dao.UserMapper;
import com.yuanshun.entity.User;
import com.yuanshun.service.UserService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{

  @Autowired
  private UserMapper userMapper;

  public int insert(User record)
  {
    return this.userMapper.insert(record);
  }

  public int insertSelective(User record)
  {
    return 0;
  }

  public int update(User record)
  {
    return this.userMapper.update(record);
  }

  public int delete(User record)
  {
    return 0;
  }

  public User queryById(String id)
  {
    return null;
  }

  public List<User> queryAll()
  {
    return this.userMapper.queryAll();
  }

  public PageInfo<User> queryByPage(int pageNum, int pageSize)
  {
    PageHelper.startPage(pageNum, pageSize);
    List list = this.userMapper.queryAll();
    return new PageInfo(list);
  }

  public PageInfo<User> queryByPage(int pageNum, int pageSize, String userphone)
  {
    PageHelper.startPage(pageNum, pageSize);
    List list = this.userMapper.queryUserListLikeName(userphone);
    return new PageInfo(list);
  }

  public void deletes(String[] ids)
  {
    String[] arrayOfString;
    LinkedList myIds = new LinkedList();
    int j = (arrayOfString = ids).length; for (int i = 0; i < j; ++i) { String id = arrayOfString[i];
      myIds.add(id);
    }
    this.userMapper.deletes(myIds);
  }

  public User queryByPhone(String phone)
  {
    return this.userMapper.queryByPhone(phone);
  }
}