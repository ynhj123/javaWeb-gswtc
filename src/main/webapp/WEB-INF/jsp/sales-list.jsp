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
</head>
<style>
  	.layui-form-switch {
    width: "100%";
	}
	.layui-checkbox-disbaled span {
    background-color: #5FB878!important;
	}
  </style>
<body> 
 
<div class="salesTable">
  电话：
  <div class="layui-inline">
    <input class="layui-input"  id="salesReload" autocomplete="off">
  </div>
  <button class="layui-btn" data-type="reload">搜索</button>
  <button class="layui-btn layui-btn-normal" data-type="AddData">新增</button>
  <button class="layui-btn layui-btn-warm" data-type="ChangeData">修改</button>
  <button class="layui-btn layui-btn-danger" data-type="DeleteData">删除</button>
</div>
 
<table class="layui-hide" id="LAY_table_sales" lay-filter="sales">

</table> 
<script type="text/html" id="checkboxTpl">
  <input type="checkbox" name="{{d.salesKey}}" value="{{d.state}}" title="发货" lay-filter="lockDemo" {{ d.state == 1 ? 'checked' : '' }} {{ d.state == 1 ? 'class=\"layui-disabled\" disabled' : '' }}>
</script>
<script type="text/html" id="view">
  <a id="{{d.salesKey}}" class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看详请</a>
</script>
 
               
          
<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
 layui.use('table', function(){
  var table = layui.table
  ,form = layui.form;

  //方法级渲染
 table.render({
    elem: '#LAY_table_sales'
    ,url: '${pageContext.request.contextPath}/sales/jsonSales/'
    ,cols: [[
      {checkbox: true, fixed: true}
      ,{field:'address', title: '地址', align:'center', width:'25%', fixed: true}
      ,{field:'phone', title: '电话', align:'center', width:'15%',fixed: true}
      ,{field:'sendTime', title: '发送时间', align:'center', width:'15%', fixed: true}
      ,{field:'price', title: '金额', align:'center', width:'10%', fixed: true}
      ,{field:'createDate', title: '创建时间', align:'center', width:'15%' ,fixed: true}
      ,{field:'state', title: '状态', align:'center', width:'10%', fixed: true ,templet: '#checkboxTpl', unresize: true}
      ,{title: '操作', width:'10%', align:'center', toolbar: '#view',fixed: true}
    ]]
    ,id: 'salesReload'
    ,page: true
  });
//监听锁定操作
 form.on('checkbox(lockDemo)', function(obj){
	 var id = this.name  
	  if(this.value == '0') {
		  layer.confirm('确定发货？', {
              btn: ['发货','取消'] //按钮
          }, function(){
        	  $.ajax({
   		       type: "GET",//方法类型
   		       dataType: "html",//预期服务器返回的数据类型
   		       url: "${pageContext.request.contextPath}/sales/changeState" ,//url
   		       data:{
   		    	 id:id 
   		       },
   		       success: function (date) {
   		    	   
   		    	if (date== "success") {
            		layer.msg("发货成功",{
                		  icon:1
                	  })
            	} 
                if (date == "error") {
                	layer.msg("发货失败",{
                		  icon:7
                	  })
                }
                layer.close(layer.index-1);
                var salesReload = $('#salesReload');
      	      //执行重载
      	      table.reload('salesReload', {
      	        page: {
      	          curr: 1 //重新从第 1 页开始
      	        }
      	        ,where: {
      	            phone: salesReload.val()
      	        }
      	      })
   		       }
   		   }); 
                      
          }, function(){
        	  layer.closeAll();
        	  var salesReload = $('#salesReload');
    	      //执行重载
    	      table.reload('salesReload', {
    	        page: {
    	          curr: 1 //重新从第 1 页开始
    	        }
    	        ,where: {
    	            phone: salesReload.val()
    	        }
    	      })
             });
		 
		 
	  } 
	 
   
 });
 table.on('tool(sales)', function(obj){
	    var data = obj.data;
	    if(obj.event === 'detail'){
	   		var id = data.salesKey
	   		window.location.href="${pageContext.request.contextPath}/sales/view?salesKey="+id
	    } 
	  });

  var $ = layui.$, active = {
    reload: function(){
      var salesReload = $('#salesReload');
      //执行重载
      table.reload('salesReload', {
        page: {
          curr: 1 //重新从第 1 页开始
        }
        ,where: {
            phone: salesReload.val()
        }
      });
    }
   ,AddData: function(){ //获取选中数据
	   window.location.href="${pageContext.request.contextPath}/sales/input";
  	}
  	,ChangeData: function(){ //获取选中数据
      var checkStatus = table.checkStatus('salesReload')
      ,data = checkStatus.data;
      window.location.href="${pageContext.request.contextPath}/sales/input?salesKey="+data[0].salesKey
  	}
  	,DeleteData: function(){ //获取选中数据
        var checkStatus = table.checkStatus('salesReload')
        ,data = checkStatus.data;
        window.location.href="${pageContext.request.contextPath}/sales/delete?salesKey="+data[0].salesKey
    	}
  };
  
  $('.salesTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
}); 
</script>

</body>
</html>