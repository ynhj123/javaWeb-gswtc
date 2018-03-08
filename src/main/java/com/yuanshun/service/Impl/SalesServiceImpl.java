package com.yuanshun.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanshun.dao.SalesDao;
import com.yuanshun.entity.Sales;
import com.yuanshun.service.SalesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesServiceImpl
  implements SalesService
{

  @Autowired
  private SalesDao salesDao;

  public int insert(Sales record)
  {
    return this.salesDao.insert(record);
  }

  public int insertSelective(Sales record)
  {
    return 0;
  }

  public int update(Sales record)
  {
    return this.salesDao.update(record);
  }

  public int delete(Sales record)
  {
    return this.salesDao.delete(record);
  }

  public Sales queryById(String id)
  {
    return this.salesDao.queryById(id);
  }

  public Page<Sales> queryAll()
  {
    return null;
  }

  public PageInfo<Sales> queryByPage(int pageNum, int pageSize)
  {
    PageHelper.startPage(pageNum, pageSize);
    List list = this.salesDao.queryAll();
    return new PageInfo(list);
  }

  public PageInfo<Sales> queryByPageAndPhone(int pageNum, int pageSize, String phone)
  {
    PageHelper.startPage(pageNum, pageSize);
    List list = this.salesDao.queryLikeByPhone(phone);
    return new PageInfo(list);
  }

  public void updateSate(String id)
  {
    this.salesDao.updateSate(id);
  }
}