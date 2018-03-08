package com.yuanshun.dao;

import com.yuanshun.entity.Product;
import java.util.LinkedList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface ProductMapper
{
  public abstract int deleteByPrimaryKey(String paramString);

  public abstract int insert(Product paramProduct);

  public abstract int insertSelective(Product paramProduct);

  public abstract Product selectByPrimaryKey(String paramString);

  public abstract int updateByPrimaryKeySelective(Product paramProduct);

  public abstract int updateByPrimaryKey(Product paramProduct);

  public abstract List<Product> queryAll();

  public abstract void deletes(@Param("deleteList") LinkedList<String> paramLinkedList);

  public abstract List<Product> queryProductListLikeName(String paramString);
}