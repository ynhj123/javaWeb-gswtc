package com.yuanshun.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanshun.dao.InventoryMapper;
import com.yuanshun.entity.Inventory;
import com.yuanshun.service.InventoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl
  implements InventoryService
{

  @Autowired
  private InventoryMapper inventoryMapper;

  public int insert(Inventory record)
  {
    return this.inventoryMapper.insert(record);
  }

  public int insertSelective(Inventory record)
  {
    return 0;
  }

  public int update(Inventory record)
  {
    return this.inventoryMapper.update(record);
  }

  public int delete(Inventory record)
  {
    return 0;
  }

  public Inventory queryById(String id)
  {
    return null;
  }

  public List<Inventory> queryAll()
  {
    return null;
  }

  public PageInfo<Inventory> queryByPage(int pageNum, int pageSize)
  {
    PageHelper.startPage(pageNum, pageSize);
    List list = this.inventoryMapper.queryAll();
    return new PageInfo(list);
  }

  public PageInfo<Inventory> queryByPage(int pageNum, int pageSize, String productkey)
  {
    PageHelper.startPage(pageNum, pageSize);
    List list = this.inventoryMapper.queryInventoryListByProductkey(productkey);
    return new PageInfo(list);
  }

  public void deletes(String[] ids)
  {
  }

  public Inventory getInventoryByProduct(String productKey)
  {
    try
    {
      return ((Inventory)this.inventoryMapper.queryInventoryListByProductkey(productKey).get(0)); } catch (Exception e) {
    }
    return null;
  }

  public Inventory getInventoryByProductName(String productname)
  {
    return this.inventoryMapper.getInventoryByProductName(productname);
  }
}