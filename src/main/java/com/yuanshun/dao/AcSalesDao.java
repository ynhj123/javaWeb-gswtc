package com.yuanshun.dao;

import com.yuanshun.entity.AcSales;
import java.util.List;

public abstract interface AcSalesDao
{
  public abstract AcSales queryById(String paramString);

  public abstract List<AcSales> queryAll(String paramString);

  public abstract List<AcSales> queryAllByType(String paramString);

  public abstract List<AcSales> queryLikeById(String paramString);

  public abstract void insert(AcSales paramAcSales);

  public abstract void update(AcSales paramAcSales);

  public abstract void delete(AcSales paramAcSales);

  public abstract List<AcSales> queryAllLikeType(String paramString1, String paramString2);

  public abstract void deletesBySalesKey(String paramString);
}