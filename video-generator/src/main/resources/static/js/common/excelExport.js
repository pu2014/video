

/**
 * 模板导出
 * @param gridId
 * @param url
 */
function excelExport(gridId,url){
	
	var target = $("#" + gridId);
	var gridRows = target.datagrid("getRows");
	if(gridRows == 0){
		msgTip("无数据导出!");
		return;
	}
	
	layer.confirm('确定导出吗?', {icon: 3, title:'提示'}, function(index){
		//查询条件赋值
		var queryParams = target.datagrid("options").queryParams;
		var paramHtml = [];
		for(var param in queryParams){
			paramHtml.push("<input type='hidden' name='"+param+"' value='"+queryParams[param]+"'>");
		}
		$("#exportExcelForm").append(paramHtml.join(""));
		
		//表单提交
		$("#exportExcelForm").form("submit",{
			url : url
		});
		
		$("#exportExcelForm").html("");
		layer.close(index);
	});
	
}

/**
 * 后台导出
 * @param gridId
 * @param url
 * @param config

 	fileName : 默认(未命名)
	sheetName : 默认(未命名)
    colName : 默认(页面显示的列)
	dataIndex : 默认(页面显示的列)
	title ： 默认(空)
	width ： 默认(页面列宽度)
	height ： 默认(表头加高)
	numFormat : 1:4=0.00,5=0.##

使用示例

excelExportBack("grid","marketInfo/exportMarket.action",{
	fileName : "行业单位信息"
});


 */
function excelExportBack(gridId,url,config){
	
	var target = $("#" + gridId);
	var gridRows = target.datagrid("getRows");
	if(gridRows == 0){
		msgTip("无数据导出!");
		return;
	}
	
	layer.confirm('确定导出吗?', {icon: 3, title:'提示'}, function(index){
		//查询条件赋值
		var queryParams = target.datagrid("options").queryParams;
		var paramHtml = [];
		for(var param in queryParams){
			paramHtml.push("<input type='hidden' name='"+param+"' value='"+queryParams[param]+"'>");
		}
		
		//初始化配置参数
		var excelConfig = initConfig(config,target);
		
		for(var item in excelConfig){
			paramHtml.push("<input type='hidden' name='"+item+"' value='"+excelConfig[item]+"'>");
		}
		$("#exportExcelForm").append(paramHtml.join(""));
		
		//表单提交
		$("#exportExcelForm").form("submit",{
			url : url
		});
		
		$("#exportExcelForm").html("");
		layer.close(index);
	});
	
}


function initConfig(config,target){
	if(!config){
		config = {};
	}
	if(!config.fileName){
		config.fileName = "未命名Excel";
	}
	if(!config.sheetName){
		config.sheetName = "Sheet1";
	}
	
	//colName、dataIndex,width
	var colName = [];
	var dataIndex = [];
	var widthArr = [];
	var fields = target.datagrid ('getColumnFields', false);
	for(var i = 0; i < fields.length; i++){
		var col = target.datagrid ('getColumnOption', fields[i]);
		if(!col.checkbox && !col.hidden){
			colName.push(col.title);
			dataIndex.push(col.field);
			widthArr.push( (col.width / 6).toFixed(0) );
		}
	}
	if(!config.colName){
		config.colName = colName.join(",");
	}
	if(!config.dataIndex){
		config.dataIndex = dataIndex.join(",");
	}
	if(!config.width){
		config.width = widthArr.join(",");
	}
	
	return config;
}


document.write("<form id='exportExcelForm' method='post'></form>");


