package com.yuanshun.enums;

public enum SalesStateEnum {
  COLLECT(0,"0"), RECEIVED(1,"1");

  private int state;
  private String stateInfo;
  
  private SalesStateEnum(int state,String stateInfo) {
	  this.state = state;
	  this.stateInfo = stateInfo;
  }
  
  public int getState()
  {
    return this.state; }

  public void setState(int state) {
    this.state = state; }

  public String getStateInfo() {
    return this.stateInfo; }

  public void setStateInfo(String stateInfo) {
    this.stateInfo = stateInfo; }

  public static SalesStateEnum stateOf(int index) {
    SalesStateEnum[] arrayOfSalesStateEnum;
    int j = (arrayOfSalesStateEnum = values()).length; for (int i = 0; i < j; ++i) { SalesStateEnum state = arrayOfSalesStateEnum[i];
      if (state.getState() == index)
        return state;
    }

    return null;
  }
}