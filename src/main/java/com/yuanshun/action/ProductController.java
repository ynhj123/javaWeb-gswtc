package com.yuanshun.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.yuanshun.entity.Product;
import com.yuanshun.service.ProductService;
import com.yuanshun.util.TimeHelper;
import com.yuanshun.util.YNHJUUID;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
@RequestMapping({"/product"})
public class ProductController
{
  private Logger logger = LoggerFactory.getLogger(super.getClass());

  @Autowired
  private ProductService productService;
  ObjectMapper toJson = new ObjectMapper();

  @RequestMapping(value={"/jsonProducts"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private void jsonProdcut(Model model, HttpServletResponse response, HttpServletRequest request)
    throws IOException
  {
    PageInfo list;
    response.setContentType("text/plain;charset=utf-8");
    response.setCharacterEncoding("utf-8");

    if (StringUtils.isEmpty(request.getParameter("productname")))
      list = this.productService.queryByPage(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("limit")));
    else
      list = this.productService.queryByPage(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("limit")), request.getParameter("productname"));

    Map map = new HashMap();
    map.put("code", Integer.valueOf(0));
    map.put("count", Long.valueOf(list.getTotal()));
    map.put("data", list.getList());
    response.getWriter().write(this.toJson.writeValueAsString(map)); }

  @RequestMapping(value={"/save"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  private void save(Product product, HttpServletResponse response) throws IOException {
    String mes;
    try {
      if (StringUtils.isEmpty(product.getProductkey()))
      {
        product.setProductkey(YNHJUUID.getUUID());
        product.setCteatetime(TimeHelper.getCurrentTime());
        this.productService.insert(product);
      }
      else {
        this.productService.update(product);
      }
      mes = "success";
    } catch (Exception e) {
      e.printStackTrace();
      mes = "error";
    }
    response.getWriter().write(mes);
  }

  @RequestMapping(value={"/delete"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String mes;
    String[] ids = request.getParameter("ids").split(",");
    try {
      this.productService.deletes(ids);
      mes = "success";
    } catch (Exception e) {
      e.printStackTrace();
      mes = "error";
    }
    response.getWriter().write(mes); }

  @RequestMapping(value={"/getAll"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private void jsonProdcut(HttpServletResponse response) throws IOException {
    response.setContentType("text/plain;charset=utf-8");
    response.setCharacterEncoding("utf-8");
    List products = this.productService.queryAll();
    response.getWriter().write(this.toJson.writeValueAsString(products));
  }

  @RequestMapping(value={"/list"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private String list(Model model) {
    return "product-list";
  }
}