package com.yuanshun.service;

import com.github.pagehelper.PageInfo;
import java.util.List;

public abstract interface BaseService<T>
{
  public abstract int insert(T paramT);

  public abstract int insertSelective(T paramT);

  public abstract int update(T paramT);

  public abstract int delete(T paramT);

  public abstract T queryById(String paramString);

  public abstract List<T> queryAll();

  public abstract PageInfo<T> queryByPage(int paramInt1, int paramInt2);
}