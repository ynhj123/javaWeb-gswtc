package com.yuanshun.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.yuanshun.entity.AcSales;
import com.yuanshun.entity.Inventory;
import com.yuanshun.entity.Sales;
import com.yuanshun.enums.SalesStateEnum;
import com.yuanshun.enums.SalesTypeEnum;
import com.yuanshun.service.AcSalesService;
import com.yuanshun.service.InventoryService;
import com.yuanshun.service.SalesService;
import com.yuanshun.util.TimeHelper;
import com.yuanshun.util.YNHJUUID;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/sales"})
public class SalesController
{
  private Logger logger = LoggerFactory.getLogger(super.getClass());

  @Autowired
  private SalesService salesService;

  @Autowired
  private AcSalesService acSalesService;

  @Autowired
  private InventoryService inventoryService;
  ObjectMapper toJson = new ObjectMapper();

  @RequestMapping(value={"/Slist"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private String list(Model model)
    throws IOException
  {
    return "sales-list";
  }

  @RequestMapping(value={"/input"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private String input(Model model, HttpServletRequest request) throws IOException {
    String id = request.getParameter("salesKey");
    if (!(StringUtils.isEmpty(id))) {
      Sales sales = (Sales)this.salesService.queryById(id);
      List acSales = this.acSalesService.getListBySales(id);
      model.addAttribute("id", id);
      model.addAttribute("sales", sales);
      model.addAttribute("acSales", acSales);
    }
    return "sales-input"; }

  @RequestMapping(value={"/view"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private String view(Model model, HttpServletRequest request) throws IOException {
    String id = request.getParameter("salesKey");
    Sales sales = (Sales)this.salesService.queryById(id);
    List acSales = this.acSalesService.getListBySales(id);
    model.addAttribute("sales", sales);
    model.addAttribute("acSales", acSales);
    return "sales-view"; }

  @RequestMapping(value={"/save"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private String save(Sales sales, String[] productName, String[] number, String[] prices, HttpServletRequest request) throws IOException {
    int i;
    AcSales acSales;
    Double total = Double.valueOf(0D);
    if (StringUtils.isEmpty(sales.getSalesKey()))
    {
      sales.setCreateDate(TimeHelper.getCurrentDate());
      sales.setSalesKey(YNHJUUID.getUUID());
      sales.setState(SalesStateEnum.COLLECT.getStateInfo());
      sales.setType(SalesTypeEnum.WHOLESALE.getTypeInfo());
      for (i = 0; i < productName.length; ++i) {
        acSales = new AcSales();
        acSales.setAcSalesKey(YNHJUUID.getUUID());
        acSales.setCreateDate(TimeHelper.getCurrentDate());
        acSales.setNumber(Integer.valueOf(Integer.parseInt(number[i])));
        acSales.setPrice(Double.valueOf(Double.parseDouble(prices[i])));
        acSales.setProductName(productName[i]);
        acSales.setSalesKey(sales.getSalesKey());
        acSales.setTotal(Double.valueOf(acSales.getNumber().intValue() * acSales.getPrice().doubleValue()));
        acSales.setType(sales.getType());
        total = Double.valueOf(total.doubleValue() + acSales.getTotal().doubleValue());
        this.acSalesService.insert(acSales);
      }
      sales.setPrice(total);
      this.salesService.insert(sales);
    }
    else
    {
      this.acSalesService.deletesBySalesKey(sales.getSalesKey());
      for (i = 0; i < productName.length; ++i) {
        acSales = new AcSales();
        acSales.setAcSalesKey(YNHJUUID.getUUID());
        acSales.setCreateDate(TimeHelper.getCurrentDate());
        acSales.setNumber(Integer.valueOf(Integer.parseInt(number[i])));
        acSales.setPrice(Double.valueOf(Double.parseDouble(prices[i])));
        acSales.setProductName(productName[i]);
        acSales.setSalesKey(sales.getSalesKey());
        acSales.setTotal(Double.valueOf(acSales.getNumber().intValue() * acSales.getPrice().doubleValue()));
        acSales.setType(sales.getType());
        total = Double.valueOf(total.doubleValue() + acSales.getTotal().doubleValue());
        this.acSalesService.insert(acSales);
      }
      sales.setPrice(total);
      this.salesService.update(sales);
    }
    return "sales-list"; }

  @RequestMapping(value={"/delete"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private String delete(Model model, HttpServletRequest request) throws IOException {
    String id = request.getParameter("salesKey");
    Sales sales = (Sales)this.salesService.queryById(id);

    this.salesService.delete(sales);
    this.acSalesService.deletesBySalesKey(id);
    return "sales-list"; }

  @RequestMapping(value={"/jsonSales"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private void jsonSales(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
    PageInfo list;
    response.setContentType("text/plain;charset=utf-8");
    response.setCharacterEncoding("utf-8");
    String phone = request.getParameter("phone");

    if (StringUtils.isEmpty(phone))
      list = this.salesService.queryByPage(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("limit")));
    else
      list = this.salesService.queryByPageAndPhone(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("limit")), phone);

    Map map = new HashMap();
    map.put("code", Integer.valueOf(0));
    map.put("count", Long.valueOf(list.getTotal()));
    map.put("data", list.getList());
    response.getWriter().write(this.toJson.writeValueAsString(map)); }

  @RequestMapping(value={"/jsonAcSales"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private void jsonAcSales(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
    response.setContentType("text/plain;charset=utf-8");
    response.setCharacterEncoding("utf-8");
    List list = new ArrayList();
    String id = request.getParameter("id");
    if (!(StringUtils.isEmpty(id)))
      list = this.acSalesService.getListBySales(id);

    Map map = new HashMap();
    map.put("code", Integer.valueOf(0));
    map.put("count", Integer.valueOf(list.size()));
    map.put("data", list);
    String json = this.toJson.writeValueAsString(map);
    response.getWriter().write(json); }

  @RequestMapping(value={"/jsonAcSalesReal"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private void jsonAcSalesReal(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
    PageInfo list;
    response.setContentType("text/plain;charset=utf-8");
    response.setCharacterEncoding("utf-8");

    String productname = request.getParameter("productname");
    if (StringUtils.isEmpty(productname))
    {
      list = this.acSalesService.getListByType(SalesTypeEnum.RETAIL.getTypeInfo(), Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("limit")));
    }
    else
      list = this.acSalesService.getListLikeType(productname, SalesTypeEnum.RETAIL.getTypeInfo(), Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("limit")));

    Map map = new HashMap();
    map.put("code", Integer.valueOf(0));
    map.put("count", Long.valueOf(list.getTotal()));
    map.put("data", list.getList());
    response.getWriter().write(this.toJson.writeValueAsString(map));
  }

  @RequestMapping(value={"/acSave"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  private void acSave(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    String mes;
    String productname = request.getParameter("productname");
    String changeOut = request.getParameter("out");
    String changeMoney = request.getParameter("money");
    try {
      Inventory inventory;
      AcSales acSales = this.acSalesService.getAcSalesByProduct(productname, SalesTypeEnum.RETAIL.getTypeInfo());

      if (acSales == null)
      {
        AcSales acSales2 = new AcSales();
        acSales2.setAcSalesKey(YNHJUUID.getUUID());
        acSales2.setCreateDate(TimeHelper.getCurrentDate());
        acSales2.setTotal(Double.valueOf(changeMoney));
        acSales2.setNumber(Integer.valueOf(Integer.parseInt(changeOut)));
        acSales2.setProductName(productname);
        acSales2.setType(SalesTypeEnum.RETAIL.getTypeInfo());
        this.acSalesService.insert(acSales2);
        inventory = this.inventoryService.getInventoryByProductName(productname);
        inventory.setProductOut(Integer.valueOf(inventory.getProductOut().intValue() + acSales2.getNumber().intValue()));
        inventory.setProductNum(Integer.valueOf(inventory.getProductNum().intValue() - acSales2.getNumber().intValue()));
      }
      else
      {
        acSales.setTotal(Double.valueOf(acSales.getTotal().doubleValue() + Double.valueOf(changeMoney).doubleValue()));
        acSales.setNumber(Integer.valueOf(acSales.getNumber().intValue() + Integer.parseInt(changeOut)));
        this.acSalesService.update(acSales);
        inventory = this.inventoryService.getInventoryByProductName(productname);
        inventory.setProductOut(Integer.valueOf(inventory.getProductOut().intValue() + acSales.getNumber().intValue()));
        inventory.setProductNum(Integer.valueOf(inventory.getProductNum().intValue() - acSales.getNumber().intValue()));
      }
      this.inventoryService.update(inventory);
      mes = "success";
    } catch (Exception e) {
      e.printStackTrace();
      mes = "error";
    }
    response.getWriter().write(mes); }

  @RequestMapping(value={"/Flist"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private String flist(Model model) throws IOException {
    return "salesA-list"; }

  @RequestMapping(value={"/addClum"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private void addClum(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String mes;
    String id;
    try {
      id = request.getParameter("salesKey");
      AcSales acSales = new AcSales();
      acSales.setAcSalesKey(YNHJUUID.getUUID());
      acSales.setCreateDate(TimeHelper.getCurrentDate());
      acSales.setSalesKey(id);
      acSales.setType(SalesTypeEnum.WHOLESALE.getTypeInfo());
      this.acSalesService.insert(acSales);
      mes = "success";
    } catch (Exception e) {
      e.printStackTrace();
      mes = "error";
    }
    response.getWriter().write(mes); }

  @RequestMapping(value={"/delClum"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private void delClum(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String mes;
    String id;
    try { id = request.getParameter("acSalesKey");
      AcSales acSales = this.acSalesService.getById(id);
      this.acSalesService.delete(acSales);
      mes = "success";
    } catch (Exception e) {
      e.printStackTrace();
      mes = "error";
    }
    response.getWriter().write(mes); }

  @RequestMapping(value={"/changeState"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private void changeState(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String mes;
    String id;
    try { id = request.getParameter("id");
      this.salesService.updateSate(id);

      List acSaless = this.acSalesService.getListBySales(id);
      for (Iterator localIterator = acSaless.iterator(); localIterator.hasNext(); ) { AcSales acSales = (AcSales)localIterator.next();
        Inventory inventory = this.inventoryService.getInventoryByProductName(acSales.getProductName());
        inventory.setProductOut(Integer.valueOf(inventory.getProductOut().intValue() + acSales.getNumber().intValue()));
        inventory.setProductNum(Integer.valueOf(inventory.getProductNum().intValue() - acSales.getNumber().intValue()));
        this.inventoryService.update(inventory);
      }
      mes = "success";
    } catch (Exception e) {
      e.printStackTrace();
      mes = "error";
    }
    response.getWriter().write(mes);
  }
}