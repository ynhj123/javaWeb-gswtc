package com.yuanshun.service;

import com.github.pagehelper.PageInfo;
import com.yuanshun.entity.AcSales;
import java.util.List;

public abstract interface AcSalesService
{
  public abstract AcSales getById(String paramString);

  public abstract void insert(AcSales paramAcSales);

  public abstract void update(AcSales paramAcSales);

  public abstract void delete(AcSales paramAcSales);

  public abstract List<AcSales> LikeById(String paramString);

  public abstract List<AcSales> getListBySales(String paramString);

  public abstract PageInfo<AcSales> getListByType(String paramString, int paramInt1, int paramInt2);

  public abstract PageInfo<AcSales> getListLikeType(String paramString1, String paramString2, int paramInt1, int paramInt2);

  public abstract AcSales getAcSalesByProduct(String paramString1, String paramString2);

  public abstract void deletesBySalesKey(String paramString);
}