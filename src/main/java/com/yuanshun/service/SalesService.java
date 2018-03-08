package com.yuanshun.service;

import com.github.pagehelper.PageInfo;
import com.yuanshun.entity.Sales;

public abstract interface SalesService extends BaseService<Sales>
{
  public abstract PageInfo<Sales> queryByPageAndPhone(int paramInt1, int paramInt2, String paramString);

  public abstract void updateSate(String paramString);
}