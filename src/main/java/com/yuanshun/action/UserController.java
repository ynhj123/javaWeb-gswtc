package com.yuanshun.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.yuanshun.entity.User;
import com.yuanshun.service.UserService;
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
@RequestMapping({"/user"})
public class UserController
{
  private Logger logger = LoggerFactory.getLogger(super.getClass());

  @Autowired
  private UserService userService;
  ObjectMapper toJson = new ObjectMapper();

  @RequestMapping(value={"/jsonuser"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private void jsonBook(Model model, HttpServletResponse response, HttpServletRequest request)
    throws IOException
  {
    PageInfo list;
    response.setContentType("text/plain;charset=utf-8");
    response.setCharacterEncoding("utf-8");

    if (StringUtils.isEmpty(request.getParameter("userphone")))
      list = this.userService.queryByPage(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("limit")));
    else
      list = this.userService.queryByPage(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request.getParameter("limit")), request.getParameter("userphone"));

    Map map = new HashMap();
    map.put("code", Integer.valueOf(0));
    map.put("count", Long.valueOf(list.getTotal()));
    map.put("data", list.getList());
    response.getWriter().write(this.toJson.writeValueAsString(map)); }

  @RequestMapping(value={"/save"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  private void save(User user, HttpServletResponse response) throws IOException {
    String mes;
    try {
      if (StringUtils.isEmpty(user.getUserkey()))
      {
        user.setUserkey(YNHJUUID.getUUID());
        user.setCreatetime(TimeHelper.getCurrentTime());
        this.userService.insert(user);
      }
      else {
        this.userService.update(user);
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
      this.userService.deletes(ids);
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
    List users = this.userService.queryAll();
    response.getWriter().write(this.toJson.writeValueAsString(users)); }

  @RequestMapping(value={"/getaddress"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private void getaddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/plain;charset=utf-8");
    response.setCharacterEncoding("utf-8");
    String phone = request.getParameter("phone");
    User user = this.userService.queryByPhone(phone);
    response.getWriter().write(this.toJson.writeValueAsString(user));
  }

  @RequestMapping(value={"/list"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  private String list(Model model) {
    return "user-list";
  }
}