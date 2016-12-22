function tagged(){
	document.getElementById('light').style.display='block';
	document.getElementById('fade').style.display='block';
}


function closeDiv() {
	document.getElementById('light').style.display='none';
	document.getElementById('fade').style.display='none';
}
function first(){
    var pageSize = $("#rounded").val();
	var currentPage = 1;
	var params = appendParams(pageSize,currentPage);
	commonQuery(params);
}
function last(){
	   var pageSize = $("#rounded").val();
	   var currentPage = $("#totalPage").html();
	   var params = appendParams(pageSize,currentPage);
		commonQuery(params);
}
function prev(){
	var currentPage = parseInt($("#hcurrentPage").val())-1;
	if(currentPage >= 1){
	var pageSize = $("#rounded").val();
	var params = appendParams(pageSize,currentPage);
	commonQuery(params);
	}else{
		alert("已经是最前一页");
	}
}
function nextPage(){
	var pageSize = $("#rounded").val();
	var lastPage = $("#totalPage").html();
	var currentPage = parseInt($("#hcurrentPage").val()) + 1;
	if(currentPage <= lastPage){
		var params = appendParams(pageSize,currentPage);
		commonQuery(params);
	}else{
		alert("已经是最后页");
	}
}
function refreshPage(){
	var pageSize = $("#rounded").val();
	var currentPage = parseInt($("#hcurrentPage").val());
		var params = appendParams(pageSize,currentPage);
		commonQuery(params);
}

function appendLeft(url,goodSn,proName,tag,productId){
	var left = '';
	var tempSpan = '';
	var leftPre ='<div id="tabInfoL">'+
	  '<table width="100%" border="0"><tr> <td rowspan="2" width="81">'+
	  '<img height="90" width="80" src="http://img2.mbanggo.com/'+url+'"/></td>'+
	  '<td height="25" style="padding-left: 4px;">'+productId+' <a style="padding-left: 4px;"><input type="hidden" id="batchProductId" name="batchProductId" value='+productId+'>'+proName+'</a></td>'+
	  '</tr><tr><td valign="top" style="padding-left: 4px;">';
	  for(var i = 0;i<tag.length;i++){
		  tempSpan += '<span id="tagValue">'+tag[i]+'<a onclick="deleteTag('+productId+',\''+tag[i]+'\');"><sup>x</sup></a></span>';
	  }
	 var leftNext = '<span onclick="hovercell('+productId+',this);" id="addBtn" class="addBtn"><a id="addBtn">+</a></span></td></tr></table></div>';
	  left = leftPre + tempSpan + leftNext;
	  return left;
}

 function appendRight(url,goodSn,proName,tag,productId){
	 var right = '';
	 var tempSpan = '';
	 var rightPre ='<div id="tabInfoR">'+
	 '<table width="100%" border="0"><tr> <td rowspan="2" width="81">'+
	  '<img height="90" width="80" src="http://img2.mbanggo.com/'+url+'"/></td>'+
	  '<td height="25" style="padding-left: 4px;">'+productId+'<a style="padding-left: 4px;"><input type="hidden" id="batchProductId" name="batchProductId" value='+productId+'>'+proName+'</a></td>'+
	  '</tr><tr><td valign="top" style="padding-left: 4px;">';
	  for(var i = 0;i<tag.length;i++){
		  tempSpan += '<span id="tagValue">'+tag[i]+'<a onclick="deleteTag('+productId+',\''+tag[i]+'\');"><sup>x</sup></a></span>';
	  }
	 var rightNext = '<span onclick="hovercell('+productId+',this);" id="addBtn" class="addBtn"><a id="addBtn">+</a></span></td></tr></table></div>';
	  right = rightPre + tempSpan + rightNext;
	  return right;
 }
 
 function pageNum(){
	     var pageSize = $("#rounded").val();
		var currentPage = $("#hcurrentPage").val();
		var params = appendParams(pageSize,currentPage);
		commonQuery(params);
 }
 function appendParams(pageSize,currentPage){
	    //关键字
		var word = $("#word").textbox('getValue');
		//平台商品id
		var productId= $("#productId").textbox('getValue');
		//色系
		var color = $("#color").textbox('getValue');
		//品牌
		var brand = $("#brand").textbox('getValue');
		//店铺ID
		var storeId = $("#storeId").textbox('getValue');
		//分类id
		var cid = $("#cid").textbox('getValue');
		//自定义价格段
		var prMin = $("#prMin").textbox('getValue');
		var prMax = $("#prMax").textbox('getValue');
		//自定义折扣
		var disMin = $("#disMin").textbox('getValue');
		var disMax = $("#disMax").textbox('getValue');
		//最低库存值
		var sortCol = $("#stock").textbox('getValue');
		//尺寸code
		var sizeCode = $("#sizeCode").textbox('getValue');
		//搜索类型
		var searchType = $("#searchType").combobox('getValue');
		//条件优先级
		var andFirst = $("#andFirst").combobox('getValue');
		//排序类型
		var sortType = $("#sortType").combobox('getValue');
		//排序字段
		var sortField = $("#sortField").combobox('getValue');
		var params = "pageSize="+pageSize+"&currentPage="+currentPage+"&word="+word+"&productId="+productId+"&color="+color+"&brand="+brand+"&storeId="+storeId+"&cid="+cid+"&prMin="+prMin+"&disMin="+disMin+"&disMax="+disMax+"&sortCol="+sortCol+"&sizeCode="+sizeCode+"&searchType="+searchType+"&andFirst="+andFirst+"&sortType="+sortType+"&sortField="+sortField;
		return params;
 }
 
 
 $(function() {
 	$('#confirm').click(function() {
 		var pageSize = $("#rounded").val();
 		var pageNo = '1';
 		var params = appendParams(pageSize,pageNo);
 		commonQuery(params);
 	});
 	
 	$("#reset").click(function(){
 		$('#query').form('clear');
 	});
 	$("#batchImportCon").click(function(){
 		divBatch('tabInfoContent','fadebatch','lightBatch');
 	});
 	
 });
 
 
//查询商品
 function commonQuery(params){
 	$.ajax({
 	 	type : 'post',
 	     url : 'searchProduct',
 	     cache : true,
 	     data : params,
 	 	dataType : 'json',
 	 	success : function(result){
 	 		$("#tabInfoContent").html("");
 	 		var temp ='';
 	 		if(result["total"] == 0){
 	 			temp = temp +'<span style="font-size:14px;color:red;">找不到任何商品信息</span>';
 	 		}else{
 	 		var totalNum = '';
 	 		var totalPage = '';
 	 		var list = result["rows"];
 	 		totalNum = result["total"];
 	 		var pageSize = $("#rounded").val();
 	 		if(totalNum%pageSize != 0){
 	 			totalPage = parseInt(totalNum/pageSize) +1;
 	 		}else{
 	 			totalPage = totalNum/pageSize;
 	 		}
 	 		$("#totalNum").html(totalNum);
 	 		$("#totalPage").html(totalPage);
 	 		$("#currentPage").html(result["currentPage"]);
 	 		$("#hcurrentPage").val(result["currentPage"]);
 	 		for(var i = 0;i < list.length;i++){
 	 			if(i%2 == 0){
 	 			temp += appendLeft(list[i]["imgUrl"],list[i]["productCode"],list[i]["productName"],list[i]["tags"],list[i]["productId"]);
// 	 				temp += appendLeft(list[i]["imgUrl"],list[i]["productCode"],list[i]["productName"],list[i]["tags"],list[i]["productCode"]);
 	 			}else{
 	 			temp +=	appendRight(list[i]["imgUrl"],list[i]["productCode"],list[i]["productName"],list[i]["tags"],list[i]["productId"]);
// 	 				temp +=	appendRight(list[i]["imgUrl"],list[i]["productCode"],list[i]["productName"],list[i]["tags"],list[i]["productCode"]);
 	 				
 	 			}
 	 		}
// 	 		var page = appendPage(totalNum,totalPage);
//  	 		temp += page;
 	 		}
 	 		$(temp).appendTo($("#tabInfoContent"));
 	 	},
 	     error : function(result){
 	    	 alert("响应出错");
 	     }
 	});
 }

	
	//通过excel打标签，提交表单
	function checkForm(){
		var ivalue = $(':input').filter('[id="ivalue"]');
		if(ivalue.length <= 0){
			alert("请选择标签");
			return false;
		}
		var v = $("#filename").val();
		if(v && v.length > 1){
			if(v.indexOf('.') > 0 && (v.indexOf('.xls') == v.length - 4 ||  v.indexOf('.xlsx') == v.length - 5)){
				return true;
			}else {
					alert("请选择正确格式的表格文件");
				return false;
			}
		}
		alert("请选择文件");
		return false;
	}
	
