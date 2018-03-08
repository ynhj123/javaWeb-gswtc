package com.yuanshun.service;

import com.github.pagehelper.PageInfo;
import com.yuanshun.entity.Inventory;

public abstract interface InventoryService extends BaseService<Inventory>
{
  public abstract PageInfo<Inventory> queryByPage(int paramInt1, int paramInt2, String paramString);

  public abstract void deletes(String[] paramArrayOfString);

  public abstract Inventory getInventoryByProduct(String paramString);

  public abstract Inventory getInventoryByProductName(String paramString);
}