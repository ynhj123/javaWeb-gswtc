package com.yuanshun.enums;

public enum SalesTypeEnum
{
  RETAIL, WHOLESALE, Welfare;

  private int type;
  private String typeInfo;

  public int getType()
  {
    return this.type; }

  public void setType(int type) {
    this.type = type; }

  public String getTypeInfo() {
    return this.typeInfo; }

  public void setTypeInfo(String typeInfo) {
    this.typeInfo = typeInfo; }

  public static SalesTypeEnum stateOf(int index) {
    SalesTypeEnum[] arrayOfSalesTypeEnum;
    int j = (arrayOfSalesTypeEnum = values()).length; for (int i = 0; i < j; ++i) { SalesTypeEnum state = arrayOfSalesTypeEnum[i];
      if (state.getType() == index)
        return state;
    }

    return null;
  }
}