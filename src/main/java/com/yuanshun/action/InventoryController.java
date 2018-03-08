package com.yuanshun.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.yuanshun.entity.Inventory;
import com.yuanshun.service.InventoryService;
import com.yuanshun.util.TimeHelper;
import com.yuanshun.util.YNHJUUID;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
@RequestMapping({"/inventory"})
public class InventoryController
{
  private Logger logger = LoggerFactory.getLogger(super.getClass());

  @Autowired
  private InventoryService inventoryService;
  ObjectMapper toJson = new ObjectMapper();

  @RequestMapping(value={"/jsoninventorys"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private void jsonProdcut(Model model, HttpServletResponse response, HttpServletRequest request)
    throws IOException
  {
    PageInfo list;
    response.setContentType("text/plain;charset=utf-8");
    response.setCharacterEncoding("utf-8");

    if (StringUtils.isEmpty(request.getParameter("productkey")))
      list = this.inventoryService.queryByPage(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("limit")));
    else
      list = this.inventoryService.queryByPage(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("limit")), request.getParameter("productkey"));

    Map map = new HashMap();
    map.put("code", Integer.valueOf(0));
    map.put("count", Long.valueOf(list.getTotal()));
    map.put("data", list.getList());
    response.getWriter().write(this.toJson.writeValueAsString(map));
  }

  @RequestMapping(value={"/save"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  private void save(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    String mes;
    String productKey = request.getParameter("productKey");
    String changeInto = request.getParameter("into");
    String changeMoney = request.getParameter("money");
    try {
      Inventory inventory = this.inventoryService.getInventoryByProduct(productKey);
      if (inventory == null)
      {
        Inventory inventory2 = new Inventory();
        inventory2.setInventroyKey(YNHJUUID.getUUID());
        inventory2.setInventroyDate(TimeHelper.getCurrentDate());
        inventory2.setMoney(Double.valueOf(changeMoney));
        inventory2.setProductInto(Integer.valueOf(Integer.parseInt(changeInto)));
        inventory2.setProductNum(Integer.valueOf(Integer.parseInt(changeInto)));
        inventory2.setProductOut(Integer.valueOf(0));
        inventory2.setProductKey(productKey);
        this.inventoryService.insert(inventory2);
      }
      else {
        inventory.setMoney(Double.valueOf(inventory.getMoney().doubleValue() + Double.valueOf(changeMoney).doubleValue()));
        inventory.setProductInto(Integer.valueOf(inventory.getProductInto().intValue() + Integer.parseInt(changeInto)));
        inventory.setProductNum(Integer.valueOf(inventory.getProductNum().intValue() + Integer.parseInt(changeInto)));
        this.inventoryService.update(inventory);
      }
      mes = "success";
    } catch (Exception e) {
      e.printStackTrace();
      mes = "error";
    }
    response.getWriter().write(mes);
  }

  @RequestMapping(value={"/list"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private String list(Model model) {
    return "inventory-list";
  }
}