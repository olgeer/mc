function locateMenu(ids){
	var idArr = ids.split('_');
	//移除原来的展开和选中情况
	$(".sub-menu").children('li.open').removeClass('open');
	for(var i=0;i<idArr.length;i++){
		if(i == idArr.length-1){//叶子节点
			$(".sub-menu").find("#li_"+idArr[i]+" a").click();
		}else{//非叶子节点
			$(".sub-menu").find("#li_"+idArr[i]).addClass("open");
		}
	}
}