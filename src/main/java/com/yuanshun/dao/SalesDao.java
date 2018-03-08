package com.yuanshun.dao;

import com.yuanshun.entity.Sales;
import java.util.List;

public abstract interface SalesDao
{
  public abstract Sales queryById(String paramString);

  public abstract List<Sales> queryAll();

  public abstract List<Sales> queryLikeByPhone(String paramString);

  public abstract int insert(Sales paramSales);

  public abstract int update(Sales paramSales);

  public abstract int delete(Sales paramSales);

  public abstract void updateSate(String paramString);
}