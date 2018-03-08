package com.yuanshun.entity;

public class Sales
{
  private String salesKey;
  private String phone;
  private String address;
  private String type;
  private String state;
  private Double price;
  private String sendTime;
  private String createDate;

  public Sales()
  {
  }

  public Sales(String salesKey, String phone, String address, String type, String state, Double price, String sendTime, String createDate)
  {
    this.salesKey = salesKey;
    this.phone = phone;
    this.address = address;
    this.type = type;
    this.state = state;
    this.price = price;
    this.sendTime = sendTime;
    this.createDate = createDate; }

  public String getSalesKey() {
    return this.salesKey; }

  public void setSalesKey(String salesKey) {
    this.salesKey = salesKey; }

  public String getPhone() {
    return this.phone; }

  public void setPhone(String phone) {
    this.phone = phone; }

  public String getAddress() {
    return this.address; }

  public void setAddress(String address) {
    this.address = address; }

  public String getType() {
    return this.type; }

  public void setType(String type) {
    this.type = type; }

  public String getState() {
    return this.state; }

  public void setState(String state) {
    this.state = state; }

  public Double getPrice() {
    return this.price; }

  public void setPrice(Double price) {
    this.price = price; }

  public String getSendTime() {
    return this.sendTime; }

  public void setSendTime(String sendTime) {
    this.sendTime = sendTime; }

  public String getCreateDate() {
    return this.createDate; }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
}