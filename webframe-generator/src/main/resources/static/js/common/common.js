//扩展根结点
function expandRoot(tree){
	var root = $(tree).tree("getRoot");
	if(root.state == "closed"){
		$(tree).tree("expand",root.target);
	}
}

//扩展所有
function expandAll(tree){
	$(tree).tree("expandAll");
}

//3秒后关闭
function toast(msg){
	$.messager.show({
		title: "提示",
		msg: msg,
		timeout : 3000,
		showType:'slide'
	});
}

//清空前后空格
function trimAll(formObj){
	$(formObj).find("input[data-options*=required]").each(function(index,obj){
		this.value = this.value.trim();
	});
}



//校验表单  
function validForm(obj){
	//必填项  去除前后空格
	trimAll(obj);
	
	return $(obj).form("enableValidation").form("validate");
}

$(function(){
	enterClick();
	/*$(".tree-icon,.tree-file").removeClass("tree-icon tree-file");
    $(".tree-icon,.tree-folder").removeClass("tree-icon tree-folder tree-folder-open tree-folder-closed");
    $('.tree-title').prev().removeClass("tree-folder-open");
 	$('.tree-checkbox,.tree-checkbox0').prev().removeClass("tree-folder-open");*/
});

//回车查询
function enterClick(){
	$("[enterClick]").keydown(function(e){
		if(e.keyCode == 13){
			eval( $(this).attr("enterClick") );
		}
	});
}

var layer
layui.use(['layer', 'form'], function(){
	layer = layui.layer,
	form = layui.form;
});

function save_form(form_id){
    var frameId = document.getElementById(form_id).getElementsByTagName("iframe")[0].id;
    $('#'+frameId)[0].contentWindow.save();
} 

function msgTip(msg, iconNum){
	if(!msg){
		msg = "操作成功!"
	}
	if(!iconNum){
		iconNum = 6
	}
	layer.msg(msg, {
	  icon: iconNum, 	//1表示成功，2表示失败
	  time: 3000 //3秒关闭（如果不配置，默认是3秒）
	}, function(){
		
	});
}

function closeForm(msg, iconNum){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
//	parent.toast(msg);
	parent.msgTip(msg, iconNum);
}
