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
  		width:40px;
  	}
  	.layui-input-block {
    	margin-left: 70px;
	}
  </style>
</head>
<body> 
 
<div class="userTable">
电话：
  <div class="layui-inline">
    <input class="layui-input" name="userPhone" id="userReload" autocomplete="off">
  </div>
  <button class="layui-btn" data-type="reload">搜索</button>
  <button class="layui-btn layui-btn-normal" data-type="addUser">新增</button>
  <button class="layui-btn layui-btn-warm" data-type="changeUser">修改</button>
  <button class="layui-btn layui-btn-danger" data-type="deleteUser">删除</button>
</div>
 
<table class="layui-hide" id="LAY_table_user" lay-filter="user">

</table> 
               
          
<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
 layui.use('table', function(){
  var table = layui.table;

  //方法级渲染
 table.render({
    elem: '#LAY_table_user'
    ,url: '${pageContext.request.contextPath}/user/jsonuser/'
    ,cols: [[
      {checkbox: true, fixed: true}
      ,{field:'userphone', title: '电话', width:'50%', fixed: true}
      ,{field:'useraddress', title: '地址', width:'50%',fixed: true}
    ]]
    ,id: 'userReload'
    ,page: true
   
  });
  
  var $ = layui.$, active = {
	//拦截按钮
    reload: function(){
    	reloadTab();
    }
   ,addUser: function(){ 
	   layer.open({
		   type: 1
		   ,title: '新增'
		   ,area: '500px'
		   ,content: $('#userAddTab') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		   ,btn: ['提交'] 
	   	   ,yes:function() {
	   		  $.ajax({
	                  type: "POST",//方法类型
	                  dataType: "html",//预期服务器返回的数据类型
	                  url: "${pageContext.request.contextPath}/user/save" ,//url
	                  data: $('#userForm').serialize(),
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
  	}
  	,changeUser: function(){ //获取选中数据
      var checkStatus = table.checkStatus('userReload')
      ,data = checkStatus.data;
  	if (data.length!=1) {
  		layer.msg("请选择一条数据进行编辑",{
  			icon:7
  		})
  	}else {
	  	$("#userkey").val(data[0].userkey);
	  	$("#createtime").val(data[0].createtime);
	  	$("#userphone").val(data[0].userphone);
	  	$("#useraddress").val(data[0].useraddress);
	      layer.open({
			   type: 1
			   ,title: '修改'
			   ,content: $('#userAddTab') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
			   ,btn: ['提交'] 
		   	   ,yes:function() {
		   		  $.ajax({
		                  type: "POST",//方法类型
		                  dataType: "html",//预期服务器返回的数据类型
		                  url: "${pageContext.request.contextPath}/user/save" ,//url
		                  data: $('#userForm').serialize(),
		                  success: function (date) {
		                    	if (date== "success") {
		                    		layer.msg("操作成功",{
				                		  icon:1
				                	  })
				                	  reloadTab()
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
      }
  	}
  	,deleteUser: function(){ //获取选中数据
  		var chk_value=[];
        var checkStatus = table.checkStatus('userReload')
        ,data = checkStatus.data;
        data.forEach(function(v,i,arr){
        	chk_value.push(v.userkey);
        });
        if (data.length<1) {
      		layer.msg("请至少选择一条数据进行删除",{
      			icon:7
      		})
      	}else {
      	  layer.confirm('确定删除数据？', {
              btn: ['删除','取消'] //按钮
          }, function(){
        	  $.ajax({
                  type: "POST",//方法类型
                  dataType: "html",//预期服务器返回的数据类型
                  url: "${pageContext.request.contextPath}/user/delete?ids="+chk_value ,//url
                  success: function (date) {
                      console.log(date);//打印服务端返回的数据(调试用)
                    	if (date== "success") {
                    		layer.msg("删除成功",{
		                		  icon:1
		                	  })
		                	  reloadTab()
                    	} 
                        if (date == "error") {
                        	layer.msg("删除失败",{
		                		  icon:7
		                	  })
                        }
                        layer.close(layer.index-1);
                      
                  },
                  error : function() {
                	  layer.msg("删除异常！",{
                		  icon:7
                	  });
                	  layer.close(layer.index-1);
                  }
              });
          }, function(){
        	  layer.closeAll();
             });

    	}
  	}
  };
  
  $('.userTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  function reloadTab() {
		 var userReload = $('#userReload');
	     //执行重载
	     table.reload('userReload', {
	       page: {
	         curr: 1 //重新从第 1 页开始
	       }
	       ,where: {
	           userphone: userReload.val()
	       }
	     });
	 }
}); 

</script>
<div id="userAddTab" style="padding: 10px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;display: none;">
	<form method="post" class="layui-form" id="userForm">
      <input  id="userkey" name="userkey" type="hidden"  />
      <input  id="createtime" name="createtime" type="hidden"  />
	  <div class="layui-form-item">
	    <label class="layui-form-label" sytle="text-align:left">电话:</label>
	    <div class="layui-input-block">
	      <input type="text" id="userphone" name="userphone"  autocomplete="off"  class="layui-input" >
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">地址:</label>
	    <div class="layui-input-block">
	      <input type="text" id="useraddress" name="useraddress"  autocomplete="off"  class="layui-input">
	    </div>
	  </div>
  </form>	
</div>
</body>

</html>