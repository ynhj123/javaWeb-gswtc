package com.yuanshun.entity;

import java.io.Serializable;

public class User
  implements Serializable
{
  private String userkey;
  private String userphone;
  private String useraddress;
  private String createtime;
  private static final long serialVersionUID = 1L;

  public String getUserkey()
  {
    return this.userkey;
  }

  public void setUserkey(String userkey) {
    this.userkey = ((userkey == null) ? null : userkey.trim());
  }

  public String getUserphone() {
    return this.userphone;
  }

  public void setUserphone(String userphone) {
    this.userphone = ((userphone == null) ? null : userphone.trim());
  }

  public String getUseraddress() {
    return this.useraddress;
  }

  public void setUseraddress(String useraddress) {
    this.useraddress = ((useraddress == null) ? null : useraddress.trim());
  }

  public String getCreatetime() {
    return this.createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = ((createtime == null) ? null : createtime.trim());
  }
}