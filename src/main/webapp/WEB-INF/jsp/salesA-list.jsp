<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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
  	.layui-form-label  {
  		text-align: left !important;
  		width:80px;
  	}
  	.layui-input-block {
    	margin-left: 110px;
	}
  </style>
</head>
<body> 
 
<div class="acsalesTable" >
<form class="layui-form" >
<div class="layui-form-item">
     <div class="layui-inline">
      <label class="layui-form-label">商品名称:</label>
      <div class="layui-input-inline">
        <select id="productnameSL"   lay-search="">
          <option value=""></option>
        </select>
      </div>
    </div>
  <button class="layui-btn" lay-submit lay-filter="reload">搜索</button>
  <button class="layui-btn layui-btn-normal" lay-submit lay-filter="addAcSales">销售</button>
</div>
</form>
</div>
 
<table class="layui-hide" id="LAY_table_acSales" lay-filter="acSales">

</table> 
               
          
<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
 layui.use('table', function(){
  var table = layui.table
  ,form = layui.form;

  //方法级渲染
 table.render({
    elem: '#LAY_table_acSales'
    ,url: '${pageContext.request.contextPath}/sales/jsonAcSalesReal/'
    ,cols: [[
      {checkbox: true, fixed: true}
      ,{field:'productName', title: '商品名称', width:'33.3%', sort: true, fixed: true}
      ,{field:'number', title: '售出数量', width:'33.3%',fixed: true}
      ,{field:'total', title: '共收金额', width:'33.3%', sort: true, fixed: true}
    ]]
    ,id: 'acsalesReload'
    ,page: true
  });
 form.on('submit(addAcSales)', function(data){
	  layer.open({
		   type: 1
		   ,title: '销售'
		   ,offset: 't'
		   ,content: $('#acsalesAddTab') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		   ,btn: ['提交'] 
	   	   ,yes:function() {
	   		  $.ajax({
	                  type: "POST",//方法类型
	                  dataType: "html",//预期服务器返回的数据类型
	                  url: "${pageContext.request.contextPath}/sales/acSave" ,//url
	                  data: $('#acsalesForm').serialize(),
	                  success: function (date) {
	                      console.log(date);//打印服务端返回的数据(调试用)
	                    	if (date== "success") {
	                    		layer.msg("操作成功",{
			                		  icon:1
			                	  })
			                	  reloadTab();
	                    	} 
	                        if (date == "error") {
	                        	layer.msg("操作失败",{
			                		  icon:7
			                	  })
	                        }
	                        layer.close(layer.index-1);
	                      
	                  },
	                  error : function() {
	                	  layer.msg("异常！",{
	                		  icon:7
	                	  });
	                	  layer.close(layer.index-1);
	                  }
	              });
	   		
	   	   }
		 });
	    return false;
	  });
 form.on('submit(reload)', function(data){
	  	reloadTab();
	    return false;
	  });
 
  var $ = layui.$, active = {
   
  };
  
  $('.acSalesTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  function reloadTab() {
		 var productname = $('#productnameSL');
	     //执行重载
	     table.reload('acsalesReload', {
	       page: {
	         curr: 1 //重新从第 1 页开始
	       }
	       ,where: {
	    	   productname: productname.val()
	       }
	     });
	 }
  $(function(){ 
	   $.ajax({
       type: "GET",//方法类型
       dataType: "json",//预期服务器返回的数据类型
       url: "${pageContext.request.contextPath}/product/getAll" ,//url
       success: function (date) {
           var htm = "";
           for (var product in date) {
           	 htm+="<option value="+date[product].productname+">"+date[product].productname+"</option>" 
           }
           $("#productnameSL").append(htm)
           $("#productnameFL").append(htm)
           form.render('select')
       }
   });
	}); 
}); 
</script>
<div id="acsalesAddTab" style="padding: 10px; line-height: 22px; background-color: #393D49; color: gray; font-weight: 300;display: none;">
	<form method="post" class="layui-form" id="acsalesForm">
	  <div class="layui-form-item">
	   <div class="layui-inline">
      <label class="layui-form-label">商品名称</label>
      <div class="layui-input-inline">
        <select id="productnameFL" name="productname"   lay-search="">
          <option value=""></option>
        </select>
      </div>
     </div>
     </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label" sytle="text-align:left">销货量:</label>
	    <div class="layui-input-block">
	      <input type="text" id="productout" name="out"  autocomplete="off"  class="layui-input" >
	    </div>
	  </div>
	 <div class="layui-form-item">
	    <label class="layui-form-label" sytle="text-align:left">进价:</label>
	    <div class="layui-input-block">
	      <input type="text" id="money" name="money"  autocomplete="off"  class="layui-input" >
	    </div>
	  </div>
  </form>	
</div>
</body>
</html>