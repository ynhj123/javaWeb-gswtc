package com.yuanshun.util;

import java.util.UUID;

public class YNHJUUID
{
  public static String getUUID()
  {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
}