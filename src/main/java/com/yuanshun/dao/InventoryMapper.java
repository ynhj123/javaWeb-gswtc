package com.yuanshun.dao;

import com.yuanshun.entity.Inventory;
import java.util.List;

public abstract interface InventoryMapper
{
  public abstract int deleteByPrimaryKey(String paramString);

  public abstract int insert(Inventory paramInventory);

  public abstract int insertSelective(Inventory paramInventory);

  public abstract Inventory selectByPrimaryKey(String paramString);

  public abstract int updateByPrimaryKeySelective(Inventory paramInventory);

  public abstract int updateByPrimaryKey(Inventory paramInventory);

  public abstract List<Inventory> queryAll();

  public abstract List<Inventory> queryInventoryListByProductkey(String paramString);

  public abstract Inventory getInventoryByProduct(String paramString);

  public abstract int update(Inventory paramInventory);

  public abstract Inventory getInventoryByProductName(String paramString);
}