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
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的
  	未来会添加修改按钮，可以整体修改库存数据
   -->
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
 
<div class="inventoryTable" >
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
  <button class="layui-btn layui-btn-normal" lay-submit lay-filter="addInventory">进货</button>
    </div>
  </form>
</div>
 
<table class="layui-hide" id="LAY_table_inventory" lay-filter="inventory">

</table> 
               
          
<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>

 layui.use('table', function(){
  var table = layui.table
  ,form = layui.form;
  //方法级渲染
 table.render({
    elem: '#LAY_table_inventory'
    ,url: '${pageContext.request.contextPath}/inventory/jsoninventorys/'
    ,cols: [[
      {checkbox: true, fixed: true}
      ,{field:'productname', title: '商品名称', width:'20%', fixed: true,templet: function(d){
        return d.product.productname
      }}
      ,{field:'productInto', title: '进货量', width:'20%',fixed: true}
      ,{field:'productOut', title: '出货量', width:'20%',fixed: true}
      ,{field:'productNum', title: '剩余量', width:'20%',fixed: true}
      ,{field:'money', title: '进价', width:'20%',fixed: true}
    ]]
    ,id: 'inventoryReload'
    ,page: true
   
  });
  form.on('submit(reload)', function(data){
	  	reloadTab();
	    return false;
	  });
  form.on('submit(addInventory)', function(data){
	  layer.open({
		   type: 1
		   ,title: '进货'
		   ,area: '500px'
		   ,content: $('#inventoryAddTab') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		   ,btn: ['提交'] 
	   	   ,yes:function() {
	   		  $.ajax({
	                  type: "POST",//方法类型
	                  dataType: "html",//预期服务器返回的数据类型
	                  url: "${pageContext.request.contextPath}/inventory/save" ,//url
	                  data: $('#inventoryForm').serialize(),
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
   var $ = layui.$, active = {}
  
  $('.inventoryTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  function reloadTab() {
		 var productkey = $('#productnameSL');
	     //执行重载
	     table.reload('inventoryReload', {
	       page: {
	         curr: 1 //重新从第 1 页开始
	       }
	       ,where: {
	    	   productkey: productkey.val()
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
            	 htm+="<option value="+date[product].productkey+">"+date[product].productname+"</option>" 
            }
            $("#productnameSL").append(htm)
            $("#productnameFL").append(htm)
            form.render('select')
        }
    });
	}); 
}); 

</script>
<div id="inventoryAddTab" style="padding: 10px; line-height: 22px; background-color: #393D49; color: gray; font-weight: 300;display: none;">
	<form method="post" class="layui-form" id="inventoryForm">
	  <div class="layui-form-item">
	   <div class="layui-inline">
      <label class="layui-form-label">商品名称</label>
      <div class="layui-input-inline">
        <select id="productnameFL" name="productKey"   lay-search="">
          <option value=""></option>
        </select>
      </div>
     </div>
     </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label" sytle="text-align:left">进货量:</label>
	    <div class="layui-input-block">
	      <input type="text" id="productInto" name="into"  autocomplete="off"  class="layui-input" >
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