<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="/common/header :: commonHeader('列表')">  
</head>  
<body>

  <div class="layui-fluid">   
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label">登录名</label>
            <div class="layui-input-block">
              <input type="text" name="username" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="${table_name?lower_case}-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
        <div style="padding-bottom: 10px;">
          <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">删除</button>
          <button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
        </div>
        
        <table id="LAY-${table_name?lower_case}-back-manage" lay-filter="LAY-${table_name?lower_case}-back-manage"></table>  
        <script type="text/html" id="table-useradmin-admin">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
        </script>
      </div>
    </div>
  </div>
  
  <script>
  layui.config({
    base: layuiadmin //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', '${table_name?lower_case}', 'table'], function(){
    var $ = layui.$
    ,form = layui.form
    ,table = layui.table;
    
    //监听搜索
    form.on('submit(${table_name?lower_case}-search)', function(data){
      var field = data.field;
      
      //执行重载
      table.reload('LAY-${table_name?lower_case}-back-manage', {
        where: field
      });
    });
  
    //事件
    var active = {
      batchdel: function(){
        var checkStatus = table.checkStatus('LAY-${table_name?lower_case}-back-manage')
        ,checkData = checkStatus.data; //得到选中的数据

        if(checkData.length === 0){
          return layer.msg('请选择数据');
        }
      
        layer.confirm('确定删除吗？', function(index) {
        	var idArr = [];
        	for(i in checkData){
        		idArr.push(checkData[i].userId);
        	}
        	$.ajax({
          	  url:ctxPath+'/${table_name?lower_case}/logicDeleteBatch',
          	  contentType: 'application/json',
          	  data:JSON.stringify(idArr),
          	  dataType:'json',
          	  type:'post',
          	  success:function(result){
          		  table.reload('LAY-${table_name?lower_case}-back-manage');
                  layer.close(index); //关闭弹层
                  layer.msg('已删除');
          	  }            	  
            }); 
          });
      }
      ,add: function(){
        layer.open({
          type: 2
          ,title: '添加'
          ,content: ctxPath+'/${table_name?lower_case}/form'
          ,area: ['420px', '430px']
          ,btn: ['确定', '取消']
          ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
            ,submit = layero.find('iframe').contents().find('#LAY-${table_name?lower_case}-back-submit');
            //监听提交
            iframeWindow.layui.form.on('submit(LAY-${table_name?lower_case}-back-submit)', function(data){
              var field = data.field; //获取提交的字段
              //提交 Ajax 成功后，静态更新表格中的数据
               $.ajax({
	          	  url:ctxPath+'/${table_name?lower_case}/save',
	          	  data:field,
	          	  dataType:'json',
	          	  type:'post',
	          	  success:function(result){
	          		  table.reload('LAY-${table_name?lower_case}-back-manage');
	                  layer.close(index); //关闭弹层
	          	  }            	  
           		}); 
            });  
            
            submit.trigger('click');
          }
        }); 
      }
    }  
    $('.layui-btn.layuiadmin-btn-admin').on('click', function(){
      var type = $(this).data('type');
      active[type] ? active[type].call(this) : '';
    });
  });
  </script>
</body>
</html>

