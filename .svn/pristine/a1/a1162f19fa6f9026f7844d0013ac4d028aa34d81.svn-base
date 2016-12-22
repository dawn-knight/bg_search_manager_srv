$(function() {
 	$('#tagQuery').click(function() {
 		var pageSize = $("#roundedTag").val();
 		var pageNo = '1';
 		var params = appendTag(pageSize,pageNo);
 		commonQueryTag(params);
 	});
 	
 	
 	$("#batchImportTag").click(function(){
 		divBatch('tabInfoContentTag','fadebatch','lightBatch');
 	});
 	
 	
});

function appendTag(pageSizeT,pageNoT){
	var tagName = "";
	 $(".tagName").each(function(){
		 tagName = tagName+"&tagName="+$(this).val();
	 });
	var pageSize = pageSizeT;
	var pageNo = pageNoT;
	var params = "pageSize="+pageSize+"&currentPage="+pageNo+""+tagName;
	return params;
}

function commonQueryTag(params){
 	$.ajax({
 	 	type : 'post',
 	     url : 'searchProductBytag',
 	     cache : true,
 	     data : params,
 	 	dataType : 'json',
 	 	success : function(result){
 	 		$("#tabInfoContentTag").html("");
 	 		var temp ='';
 	 		if(result["total"] == 0){
 	 			temp = temp +'<a style="font-size:14px;color:red;">找不到任何商品信息</a>';
 	 		}else{
 	 		var totalNum = '';
 	 		var totalPage = '';
 	 		var list = result["rows"];
 	 		totalNum = result["total"];
 	 		var pageSize = $("#roundedTag").val();
 	 		if(totalNum%pageSize != 0){
 	 			totalPage = parseInt(totalNum/pageSize) +1;
 	 		}else{
 	 			totalPage = totalNum/pageSize;
 	 		}
 	 		$("#totalNumTag").html(totalNum);
 	 		$("#totalPageTag").html(totalPage);
 	 		$("#currentPageTag").html(result["currentPage"]);
 	 		$("#hcurrentPageTag").val(result["currentPage"]);
 	 		for(var i = 0;i < list.length;i++){
 	 			if(i%2 != 0){
 	 			temp += appendLeftTag(list[i]["imgUrl"],list[i]["productCode"],list[i]["productName"],list[i]["tags"],list[i]["productId"]);
// 	 			temp += appendLeftTag(list[i]["imgUrl"],list[i]["productCode"],list[i]["productName"],list[i]["tags"],list[i]["productCode"]);
 	 			}else{
 	 			temp +=	appendRightTag(list[i]["imgUrl"],list[i]["productCode"],list[i]["productName"],list[i]["tags"],list[i]["productId"]);
// 	 			temp +=	appendRightTag(list[i]["imgUrl"],list[i]["productCode"],list[i]["productName"],list[i]["tags"],list[i]["productCode"]);
 	 			}
 	 		}
 	 		}
 	 		$(temp).appendTo($("#tabInfoContentTag"));
 	 	},
 	     error : function(result){
 	    	 alert("响应出错");
 	     }
 	});
 }

function appendLeftTag(url,goodSn,proName,tag,productId){
	var left = '';
	var tempSpan = '';
	var leftPre ='<div id="tabInfoL">'+
	  '<table width="100%" border="0"><tr> <td rowspan="2" width="81">'+
	  '<img height="90" width="80" src="http://img2.mbanggo.com/'+url+'"/></td>'+
	  '<td height="25">'+productId+'<a style="padding-left: 4px;"><input type="hidden" id="batchProductIdTag" name="batchProductIdTag" value='+productId+'><span style="padding-left: 10px;">'+proName+'</a></td>'+
	  '</tr><tr><td valign="top" style="padding-left: 4px;">';
	  for(var i = 0;i<tag.length;i++){
		  tempSpan += '<span id="tagValue">'+tag[i]+'<a onclick="deleteTag('+productId+',\''+tag[i]+'\');"><sup>x</sup></a></span>';
	  }
	 var leftNext = '<span onclick="hovercell('+productId+',this);" id="addBtn" class="addBtn"><a id="addBtn" >+</a></span></td></tr></table></div>';
	  left = leftPre + tempSpan + leftNext;
	  return left;
}

 function appendRightTag(url,goodSn,proName,tag,productId){
	 var right = '';
	 var tempSpan = '';
	 var rightPre ='<div id="tabInfoR">'+
	 '<table width="100%" border="0"><tr> <td rowspan="2" width="81">'+
	  '<img height="90" width="80" src="http://img2.mbanggo.com/'+url+'"/></td>'+
	  '<td height="25">'+productId+'<a style="padding-left: 4px;"><input type="hidden" id="batchProductIdTag" name="batchProductIdTag" value='+productId+'><span style="padding-left: 10px;">'+proName+'</a></td>'+
	  '</tr><tr><td valign="top" style="padding-left: 4px;">';
	  for(var i = 0;i<tag.length;i++){
		  tempSpan += '<span id="tagValue">'+tag[i]+'<a onclick="deleteTag('+productId+',\''+tag[i]+'\');"><sup>x</sup></a></span>';
	  }
	 var rightNext = '<span onclick="hovercell('+productId+',this);" id="addBtn" class="addBtn"><a id="addBtn">+</a></span></td></tr></table></div>';
	  right = rightPre + tempSpan + rightNext;
	  return right;
 }
 
 
 function pageTagNum(){
	    var pageSize = $("#roundedTag").val();
		var currentPage = $("#hcurrentPageTag").val();
		var params = appendTag(pageSize,currentPage);
		commonQueryTag(params);
}
 
    function firstTag(){
	    var pageSize = $("#roundedTag").val();
		var currentPage = 1;
		var params = appendTag(pageSize,currentPage);
		commonQueryTag(params);
	}
	function lastTag(){
		   var pageSize = $("#roundedTag").val();
		   var currentPage = $("#totalPageTag").html();
			var params = appendTag(pageSize,currentPage);
			commonQueryTag(params);
	}
	function prevTag(){
		var currentPage = parseInt($("#hcurrentPageTag").val())-1;
		if(currentPage >= 1){
		var pageSize = $("#roundedTag").val();
		var params = appendTag(pageSize,currentPage);
		commonQueryTag(params);
		}else{
			alert("已经是最前一页");
		}
	}
	function nextPageTag(){
		var pageSize = $("#roundedTag").val();
		var lastPage = $("#totalPageTag").html();
		var currentPage = parseInt($("#hcurrentPageTag").val()) + 1;
		if(currentPage <= lastPage){
			var params = appendTag(pageSize,currentPage);
			commonQueryTag(params);
		}else{
			alert("已经是最后页");
		}
	}  
	function refreshPageTag(){
		var pageSize = $("#roundedTag").val();
		var currentPage = parseInt($("#hcurrentPageTag").val());
			var params = appendTag(pageSize,currentPage);
			commonQueryTag(params);
	}  
	
	function tagquery(){
		var para = $("#searchTag").val();
		if(para != ""){
		$.ajax({
			type : 'post',
			url : 'queryTagsToSelect',
			cache : false,
			data : "para="+para,
			dataType : 'json',
			success : function(result) {
				$("#tagSearch").html("") ;
				var list = result;
				var tempAppend='';
				if(list.length>0){
					for(var i = 0;i<list.length;i++){
						var id = list[i]["id"];
						var word = list[i]["tagWord"];
//						tempAppend = tempAppend + '<span id="tagValue" class="tagSpan"  onclick="selectPara(\''+list[i]+'\')">'+list[i]+'</span>'+
//						 '<input type="hidden" id="realValue" name="realValue" value="'+list[i]+'"/>';
						tempAppend = tempAppend + '<span id="tagSpan" class="tagSpan" onclick="selectPara(this,\''+id+'\',\''+word+'\')">'+word+
						'<input type="hidden" value="'+id+'" class="tagId" /><input class="tagWord" type="hidden" value="'+word+'" /></span>';
					}
				$(tempAppend).appendTo($("#tagSearch"));
				drawTagSpan();
				}
			},
		    error : function(result) {
		    	alert("查询失败");
			}
		
		});
		}
	}
		


