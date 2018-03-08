package com.yuanshun.service;

import com.github.pagehelper.PageInfo;
import com.yuanshun.entity.Product;

public abstract interface ProductService extends BaseService<Product>
{
  public abstract PageInfo<Product> queryByPage(int paramInt1, int paramInt2, String paramString);

  public abstract void deletes(String[] paramArrayOfString);
}