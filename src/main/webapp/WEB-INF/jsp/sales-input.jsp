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
 
<form class="layui-form" action="${pageContext.request.contextPath}/sales/save">
   <input type="hidden" name="salesKey"   value="${sales.salesKey}">
   <input type="hidden" name="type"   value="${sales.type}">
   <input type="hidden" name="state"   value="${sales.state}">
   <input type="hidden" name="price"   value="${sales.price}">
   <input type="hidden" name="createDate"   value="${sales.createDate}">
   <input type="hidden" id="phoneVal" name="phone"   value="${sales.phone}">
  <div class="layui-form-item">
    <label class="layui-form-label">电话:</label>
    <div class="layui-input-block">
       <select id="phone"   lay-filter='phone' lay-search="" >
          <option value=""></option>
        </select>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">地址:</label>
    <div class="layui-input-block">
      <input type="text" id="address" name="address" autocomplete="off"  class="layui-input" value="${sales.address}">
    </div>
  </div>
  
  <div class="layui-form-item">
      <label class="layui-form-label">送货日期:</label>
      <div class="layui-input-block">
        <input type="text" name="sendTime" id="date1" autocomplete="off" class="layui-input" value="${sales.sendTime}">
      </div>
    </div>



 <table id="acsalesReload" class="layui-table" <%-- lay-data="{url:'${pageContext.request.contextPath}/sales/jsonAcSales?id=${id}'}" lay-filter="demoEvent" --%>>
	 <div class="layui-form-item">
	 <div class="acSalesTable">
	  <button class="layui-btn " lay-submit lay-filter="addAcSales">新增</button>
	  <button class="layui-btn " lay-submit lay-filter="deleteAcSales">删除</button>
	</div>
	</div>
	<tr class="alls_tr" >
		<th width="3%"><input   type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
		<th width="20%"><span>商品名称</span></th>
		<th width="20%"><span>数量</span></th>
		<th width="20%"><span>价格</span></th>
		<th width="20%"><span>总价</span></th>
		
	</tr>
	<c:forEach  items="${acSales}" var="acSale" begin="0" step="1">
		<tr height="45px" class="all_tr" id="${acSale.acSalesKey}" >
			<td align="center" style="width:2%;">
				<input type="hidden" name="ids" id="id" value="${acSale.acSalesKey}" >
				<input type="checkbox" name="flag_id" lay-skin="primary" value="${acSale.acSalesKey}"  >
			</td>
			<%-- <td  style="background:#fcf9f5"><input  class="layui-input"  style="width:100%;" name="productName"  value='${acSale.productName}' /></td> --%>
			<td  style="background:#fcf9f5">
			       <select id="productName"  name="productName" lay-search="" >
			          <option value=""></option>
			        </select>
			        <input type="hidden"  name="lastPro" value="${acSale.productName}" >
			</td>
			<td  style="background:#fcf9f5"><input  class="layui-input"  style="width:100%;" name="number"  value='${acSale.number}' /></td>
			<td  style="background:#fcf9f5"><input  class="layui-input"  style="width:100%;" name="prices"  value='${acSale.price}' /></td>
			<td  style="background:#fcf9f5"><input  class="layui-input" readonly style="width:100%;" name="total"  value='${acSale.total}' /></td>
		</tr>
	</c:forEach>
</table> 
           
  
  
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="mySubmit">立即提交</button>
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
  
  //日期
  laydate.render({
    elem: '#date'
  });
  laydate.render({
    elem: '#date1'
  });
  table.render({
	  
  });
  
 
  //自定义验证规则
  form.verify({
    title: function(value){
      if(value.length < 1){
        return '标题至少得1个字符啊';
      }
    }
    ,pass: [/(.+){6,12}$/, '密码必须6到12位']
    ,content: function(value){
      layedit.sync(editIndex);
    }
  });
  //监听指定开关
  form.on('switch(switchTest)', function(data){
    layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
      offset: '6px'
    });
    layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
  });
  form.on('checkbox(allChoose)', function(data){
      var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
      child.each(function(index, item){
          item.checked = data.elem.checked;
      });
      form.render('checkbox');
  }); 
  form.on('select(phone)',function(data) {
	  $("#phoneVal").val(data.value)
	  var phone = data.value
	   $.ajax({
	       type: "GET",//方法类型
	       dataType: "json",//预期服务器返回的数据类型
	       url: "${pageContext.request.contextPath}/user/getaddress" ,//url
	       data:{
	    	   phone:phone
	       },
	       success: function (date) {
	    	   $("#address").val(date.useraddress)
	       }
  	   });
  })
  form.on('submit(addAcSales)', function(data){
	    var id=uuid()
	 	var htm = ""
	 		htm += "<tr height='45px' class='all_tr' id="+id+" >"
	 		htm += "<td align='center' style='width:2%;'>"
			htm += "<input type='hidden' name='ids' id='id' value="+id+" >"
			htm += "<input type='checkbox' name='flag_id' lay-skin='primary' value="+id+"  ></td>"
			htm += "<td  style='background:#fcf9f5'>"
		    htm += "<select id='productName'  name='productName' lay-search=''>"
	        htm += "<option value=''></option>"
	        for (var product in products) {
	        htm+="<option value="+products[product].productname+">"+products[product].productname+"</option>" 
	        }
	        htm += "</select>"
			htm += "</td>"
			htm += "<td  style='background:#fcf9f5'><input  class='layui-input'  style='width:100%;' name='number'  ></td>"
			htm += "<td  style='background:#fcf9f5'><input  class='layui-input'  style='width:100%;' name='prices'   ></td>"
			htm += "<td  style='background:#fcf9f5'><input  class='layui-input'  style='width:100%;' name='total'  readonly ></td>"
	 		htm += "</tr>"
	 	  $("#acsalesReload").append(htm)	
	 	  form.render('checkbox')
	 	  form.render('select')
	    return false;
	  });
  form.on('submit(deleteAcSales)', function(data){
	    var ids=[];
		$("input[name='flag_id']:checked").each(function(){
			ids.push($(this).val());
		});
		if(""!=ids){
			layer.confirm('确认删除数据？',{
				btn:['确认','取消'],
					btn1:function(index,layro){
						for (var x in ids){
							$('tr#'+ids[x]).remove();	
						}
						layer.msg("删除成功", { icon : 1 });
					},
					btn2:function(index,layro){
						layer.msg('取消成功',{icon:7});
					}
			});
		}else{
			layer.msg('请至少选择一条数据进行删除!',{icon:7});
			return ;
		} 
	 
	    return false;
	  });
  form.on('submit(mySubmit)', function(data){
	    return true;
	  });
  var $ = layui.$, active = {	   
  }
  var products;
  $(function(){ 
	   $.ajax({
       type: "GET",//方法类型
       dataType: "json",//预期服务器返回的数据类型
       url: "${pageContext.request.contextPath}/user/getAll" ,//url
       success: function (date) {
           var htm = "";
           for (var user in date) {
           	 htm+="<option value="+date[user].userphone+">"+date[user].userphone+"</option>" 
           }
           $("#phone").append(htm)
           $("#phone").val($("#phoneVal").val())
           form.render('select')
           
       }
   });
		   
		   $.ajax({
		        type: "GET",//方法类型
		        dataType: "json",//预期服务器返回的数据类型
		        url: "${pageContext.request.contextPath}/product/getAll" ,//url
		        success: function (date) {
		        	
		             var htm = "";
		            for (var product in date) {
		            	 htm+="<option value="+date[product].productname+">"+date[product].productname+"</option>" 
		            }
		            var productArr = $("select[name='productName']")
		            productArr.append(htm)
		            var proArr = $("input[name='lastPro']");
		            for(var i = 0;i<productArr.size();i++) {
		        	   productArr[i].value = proArr[i].value
		            }
		            products = date
		            form.render('select')
		        }
		    });
		   
	});
  function uuid() {
	    var s = [];
	    var hexDigits = "0123456789abcdef";
	    for (var i = 0; i < 36; i++) {
	        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
	    }
	    s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
	    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
	    s[8] = s[13] = s[18] = s[23] = "-";
	 
	    var uuid = s.join("");
	    return uuid;
	}
});
</script>
</body>
</html>