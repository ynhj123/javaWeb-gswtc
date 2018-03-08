<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
	<style>  
          .all_tr td {
         	 text-align:center;
          }
          .alls_tr th {
         	 text-align:center;
          }
          
    </style>
</head>
<body>
 
  <div class="layui-form-item">
    <label class="layui-form-label">电话:</label>
    <div class="layui-input-block">
       <input type="text"  readonly class="layui-input" value="${sales.phone}">
    </div>
 </div>
 <div class="layui-form-item">
    <label class="layui-form-label">地址:</label>
    <div class="layui-input-block">
      <input type="text" id="address" readonly name="address" autocomplete="off"  class="layui-input" value="${sales.address}">
    </div>
   </div>
  
  <div class="layui-form-item">
 	<div class="layui-inline">
      <label class="layui-form-label">送货日期:</label>
      <div class="layui-input-block">
        <input type="text" name="sendTime" readonly id="date1" autocomplete="off" class="layui-input" value="${sales.sendTime}">
      </div>
    </div>
    <div class="layui-inline">
    <label class="layui-form-label">状态:</label>
    <div class="layui-input-block">
   <c:if test="${sales.state == 0}">
    <input type="text" id="address" readonly name="address" autocomplete="off"  class="layui-input" value="未发货">
	</c:if>
	<c:if test="${sales.state == 1}">
	 <input type="text" id="address" readonly name="address" autocomplete="off"  class="layui-input" value="已发货">
	</c:if>

     
    </div>
  </div>
  </div>
  <div class="layui-form-item">
   	<div class="layui-inline">
      <label class="layui-form-label">总价:</label>
      <div class="layui-input-block">
        <input type="text" readonly  name="sendTime" id="date1" autocomplete="off" class="layui-input" value="${sales.price}">
      </div>
    </div>
    <div class="layui-inline">
    <label class="layui-form-label">创建日期:</label>
    <div class="layui-input-block">
      <input type="text" readonly id="address" name="address" autocomplete="off"  class="layui-input" value="${sales.createDate}">
    </div>
  </div>
  </div>



 <table id="acsalesReload" class="layui-table" <%-- lay-data="{url:'${pageContext.request.contextPath}/sales/jsonAcSales?id=${id}'}" lay-filter="demoEvent" --%>>
	<tr class="alls_tr" >
		<th width="20%"><span>商品名称</span></th>
		<th width="20%"><span>数量</span></th>
		<th width="20%"><span>价格</span></th>
		<th width="20%"><span>总价</span></th>
		
	</tr>
	<c:forEach  items="${acSales}" var="acSale" begin="0" step="1">
		<tr height="45px" class="all_tr" id="${acSale.acSalesKey}" >
			<%-- <td  style="background:#fcf9f5"><input  class="layui-input"  style="width:100%;" name="productName"  value='${acSale.productName}' /></td> --%>
			<td  style="background:#fcf9f5">${acSale.productName}</td>
			<td  style="background:#fcf9f5">${acSale.number}</td>
			<td  style="background:#fcf9f5">${acSale.price}</td>
			<td  style="background:#fcf9f5">${acSale.total}</td>
		</tr>
	</c:forEach>
</table> 
           
  
  
  <div class="layui-form-item">
    <div class="layui-input-block">
     <!--  <button class="layui-btn" lay-submit lay-filter="mySubmit">立即提交</button> -->
    </div>
  </div>
</form>
          
<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
layui.use(['table','form', 'layedit', 'laydate'], function(){
	var table = layui.table
  ,form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  
  table.render({
	  
  });
  
  var $ = layui.$, active = {	   
  }
  
});
</script>
</body>
</html>