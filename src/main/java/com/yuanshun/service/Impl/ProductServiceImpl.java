package com.yuanshun.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanshun.dao.ProductMapper;
import com.yuanshun.entity.Product;
import com.yuanshun.service.ProductService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl
  implements ProductService
{

  @Autowired
  private ProductMapper ProductMapper;

  public int insert(Product record)
  {
    return this.ProductMapper.insert(record);
  }

  public int insertSelective(Product record)
  {
    return 0;
  }

  public int update(Product record)
  {
    return this.ProductMapper.updateByPrimaryKey(record);
  }

  public int delete(Product record)
  {
    return 0;
  }

  public Product queryById(String id)
  {
    return null;
  }

  public List<Product> queryAll()
  {
    return this.ProductMapper.queryAll();
  }

  public PageInfo<Product> queryByPage(int pageNum, int pageSize)
  {
    PageHelper.startPage(pageNum, pageSize);
    List list = this.ProductMapper.queryAll();
    return new PageInfo(list);
  }

  public PageInfo<Product> queryByPage(int pageNum, int pageSize, String Productname)
  {
    PageHelper.startPage(pageNum, pageSize);
    List list = this.ProductMapper.queryProductListLikeName(Productname);
    return new PageInfo(list);
  }

  public void deletes(String[] ids)
  {
    String[] arrayOfString;
    LinkedList myIds = new LinkedList();
    int j = (arrayOfString = ids).length; for (int i = 0; i < j; ++i) { String id = arrayOfString[i];
      myIds.add(id);
    }
    this.ProductMapper.deletes(myIds);
  }
}