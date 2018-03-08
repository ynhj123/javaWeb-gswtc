package com.yuanshun.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanshun.dao.AcSalesDao;
import com.yuanshun.entity.AcSales;
import com.yuanshun.service.AcSalesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcSalesServiceImpl
  implements AcSalesService
{

  @Autowired
  private AcSalesDao acSalesDao;

  public AcSales getById(String acSalesKey)
  {
    return this.acSalesDao.queryById(acSalesKey);
  }

  public void insert(AcSales acSales)
  {
    this.acSalesDao.insert(acSales);
  }

  public void update(AcSales acSales)
  {
    this.acSalesDao.update(acSales);
  }

  public void delete(AcSales acSales)
  {
    this.acSalesDao.delete(acSales);
  }

  public List<AcSales> LikeById(String acSalesKey)
  {
    return this.acSalesDao.queryLikeById(acSalesKey);
  }

  public List<AcSales> getListBySales(String salesKey)
  {
    return this.acSalesDao.queryAll(salesKey);
  }

  public PageInfo<AcSales> getListByType(String type, int pageNum, int pageSize)
  {
    PageHelper.startPage(pageNum, pageSize);
    List list = this.acSalesDao.queryAllByType(type);
    return new PageInfo(list);
  }

  public PageInfo<AcSales> getListLikeType(String productname, String typeInfo, int pageNum, int pageSize)
  {
    PageHelper.startPage(pageNum, pageSize);
    List list = this.acSalesDao.queryAllLikeType(productname, typeInfo);
    return new PageInfo(list);
  }

  public AcSales getAcSalesByProduct(String productname, String typeInfo)
  {
    try
    {
      return ((AcSales)this.acSalesDao.queryAllLikeType(productname, typeInfo).get(0)); } catch (Exception e) {
    }
    return null;
  }

  public void deletesBySalesKey(String salesKey)
  {
    this.acSalesDao.deletesBySalesKey(salesKey);
  }
}