package com.yuanshun.entity;

public class AcSales
{
  private String acSalesKey;
  private String salesKey;
  private String productName;
  private Integer number;
  private Double price;
  private Double total;
  private String createDate;
  private String type;

  public AcSales()
  {
  }

  public AcSales(String acSalesKey, String salesKey, String productName, Integer number, Double price, Double total, String createDate, String type)
  {
    this.acSalesKey = acSalesKey;
    this.salesKey = salesKey;
    this.productName = productName;
    this.number = number;
    this.price = price;
    this.total = total;
    this.createDate = createDate;
    this.type = type;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getAcSalesKey()
  {
    return this.acSalesKey; }

  public void setAcSalesKey(String acSalesKey) {
    this.acSalesKey = acSalesKey; }

  public String getSalesKey() {
    return this.salesKey; }

  public void setSalesKey(String salesKey) {
    this.salesKey = salesKey; }

  public String getProductName() {
    return this.productName; }

  public void setProductName(String productName) {
    this.productName = productName; }

  public Integer getNumber() {
    return this.number; }

  public void setNumber(Integer number) {
    this.number = number; }

  public Double getPrice() {
    return this.price; }

  public void setPrice(Double price) {
    this.price = price; }

  public Double getTotal() {
    return this.total; }

  public void setTotal(Double total) {
    this.total = total; }

  public String getCreateDate() {
    return this.createDate; }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
}