//导出文件
function exportFile(table,url,queryId,type){
	if(queryId == null || queryId ==""){
		alert("queryId参数必传！");
	}
	var paramStr = "";
 
	$('textarea.form-filter, select.form-filter, input.form-filter:not([type="radio"],[type="checkbox"])', table).each(function(){
		paramStr += "" + $(this).attr("name") + "=" + encodeURIComponent($(this).val()) + "&";
	});

	$('input.form-filter[type="checkbox"]:checked, input.form-filter[type="radio"]:checked', table).each(function(){
		paramStr += "" + $(this).attr("name") + "=" + encodeURIComponent($(this).val()) + "&";
	});
 
	if(paramStr.length>1){
		paramStr = paramStr.substring(0, (paramStr.length - 1));
	}

	if(url.indexOf("?")<0){
		url += "?";
	}else{
		url += "&";
	}
	//弹出导出
	var filetype = "excel";
	if(type!=null && type!=""){
		//待补充

	}
	window.open(url+"exportFile="+filetype+"&sAction=filter&queryId="+encodeURIComponent(queryId)+"&"+paramStr);
}


//导出文件
function exportFileBySelect(idArray,url,queryId,sGroupActionName,type){
	if(queryId == null || queryId ==""){
		alert("queryId参数必传！");
	}

	if(url.indexOf("?")<0){
		url += "?";
	}else{
		url += "&";
	}
	//弹出导出
	var filetype = "excel";
	if(type!=null && type!=""){
		//待补充

	}
	window.open(url+"exportFile="+filetype+"&sAction=group_action&queryId="+encodeURIComponent(queryId)+"&sGroupActionName="+sGroupActionName+"&idArray="+encodeURIComponent(idArray));
}
