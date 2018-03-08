package com.yuanshun.entity;

import java.io.Serializable;

public class Inventory
  implements Serializable
{
  private String inventroyKey;
  private String productKey;
  private String inventroyDate;
  private Integer productInto;
  private Integer productOut;
  private Integer productNum;
  private Double money;
  private Product product;
  private static final long serialVersionUID = 1L;

  public Product getProduct()
  {
    return this.product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public String getInventroyKey()
  {
    return this.inventroyKey;
  }

  public void setInventroyKey(String inventroyKey) {
    this.inventroyKey = ((inventroyKey == null) ? null : inventroyKey.trim());
  }

  public String getProductKey() {
    return this.productKey;
  }

  public void setProductKey(String productKey) {
    this.productKey = ((productKey == null) ? null : productKey.trim());
  }

  public String getInventroyDate() {
    return this.inventroyDate;
  }

  public void setInventroyDate(String inventroyDate) {
    this.inventroyDate = ((inventroyDate == null) ? null : inventroyDate.trim());
  }

  public Integer getProductInto() {
    return this.productInto;
  }

  public void setProductInto(Integer productInto) {
    this.productInto = productInto;
  }

  public Integer getProductOut() {
    return this.productOut;
  }

  public void setProductOut(Integer productOut) {
    this.productOut = productOut;
  }

  public Integer getProductNum() {
    return this.productNum;
  }

  public void setProductNum(Integer productNum) {
    this.productNum = productNum;
  }

  public Double getMoney() {
    return this.money;
  }

  public void setMoney(Double money) {
    this.money = money;
  }

  public String toString()
  {
    return "Inventory [inventroyKey=" + this.inventroyKey + ", productKey=" + this.productKey + ", inventroyDate=" + 
      this.inventroyDate + ", productInto=" + this.productInto + ", productOut=" + this.productOut + ", productNum=" + 
      this.productNum + ", money=" + this.money + ", product=" + this.product + "]";
  }
}