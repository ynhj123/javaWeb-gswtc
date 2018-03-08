package com.yuanshun.service;

import com.github.pagehelper.PageInfo;
import com.yuanshun.entity.User;

public abstract interface UserService extends BaseService<User>
{
  public abstract PageInfo<User> queryByPage(int paramInt1, int paramInt2, String paramString);

  public abstract void deletes(String[] paramArrayOfString);

  public abstract User queryByPhone(String paramString);
}