package com.yuanshun.entity;

import java.io.Serializable;

public class Product
  implements Serializable
{
  private String productkey;
  private String productname;
  private String cteatetime;
  private static final long serialVersionUID = 1L;

  public String getProductkey()
  {
    return this.productkey;
  }

  public void setProductkey(String productkey) {
    this.productkey = ((productkey == null) ? null : productkey.trim());
  }

  public String getProductname() {
    return this.productname;
  }

  public void setProductname(String productname) {
    this.productname = ((productname == null) ? null : productname.trim());
  }

  public String getCteatetime() {
    return this.cteatetime;
  }

  public void setCteatetime(String cteatetime) {
    this.cteatetime = ((cteatetime == null) ? null : cteatetime.trim());
  }
}