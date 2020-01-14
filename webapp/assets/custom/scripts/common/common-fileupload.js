/**
 * uploadify上传图片
 * @param fileEleId 控件元素id
 * @param buttenName 按钮名称
 * @param maxSize 允许文件的最大容量
 * @param fileTypeExtsParam 允许的文件后缀
 * @param multi 是否可多附件上传
 * @param width 按钮的宽度
 * @param height 按钮的高度
 * @param module 上传的类型，主要用于不同模块确定放在不同的文件夹内
 * @param callBack 回调方法
 */
function initUploadImage(fileEleId,buttenName,maxSize,fileTypeExtsParam,multi,width,height,module,callBack){
	var fileTypeDesc;
	var fileTypeExts;
	if(fileTypeExtsParam){
		fileTypeExts = "";
		fileTypeDesc = "";
		for(var i=0;i<fileTypeExtsParam.length;i++){
			if(!fileTypeExts){
				fileTypeExts = "*."+fileTypeExtsParam[i];
				fileTypeDesc = fileTypeExtsParam[i];
			}else{
				fileTypeExts += ";"+"*."+fileTypeExtsParam[i];
				fileTypeDesc += ","+fileTypeExtsParam[i];
			}
		}
	}
	//调用uploadify的初始化方法
	$('#'+fileEleId).uploadify({
	    'swf':'assets/custom/plugins/uploadify/uploadify.swf',
	    'uploader':$("#base").val()+'/upload/uploadFile?type=image&module='+module,
	    'auto': true,
	    'width': width || 100,
	    'height': height || 34,
	    'buttonText':buttenName || '浏览',
	    'fileSizeLimit': maxSize || '10MB',
	    'fileTypeDesc': fileTypeDesc || '只能上传JPG,JPEG,png格式',
	    'fileTypeExts': fileTypeExts || '*.jpg;*.jpeg;*.png',
	    'multi' : multi || false,
	    'onUploadSuccess' : function(file, data, response) {
	    	if(callBack){
	    		callBack(file, data, response);
	    		return;
	    	}
	        if(response){
	 			var resObj = JSON.parse(data);
	            if(resObj.resultCode == 0){
	               console.debug(resObj.data);
	            }else{
	               alert("上传失败,失败原因："+resObj.msg);
	            }
	        }else {
	            alert("上传失败");
	        }
	
	    }
    });
}

/**
 * uploadify上传图片
 * @param fileEleId 控件元素id
 * @param buttenName 按钮名称
 * @param maxSize 允许文件的最大容量
 * @param fileTypeExtsParam 允许的文件后缀
 * @param multi 是否可多附件上传
 * @param width 预览图片展示的宽度
 * @param height 预览图片展示的高度
 * @param module 上传的类型，主要用于不同模块确定放在不同的文件夹内
 * @param callBack 回调方法
 */
function initUploadFile(fileEleId,buttenName,maxSize,fileTypeExtsParam,multi,width,height,module,callBack){
	var fileTypeDesc;
	var fileTypeExts;
	if(fileTypeExtsParam){
		fileTypeExts = "";
		fileTypeDesc = "";
		for(var i=0;i<fileTypeExtsParam.length;i++){
			if(!fileTypeExts){
				fileTypeExts = "*."+fileTypeExtsParam[i];
				fileTypeDesc = fileTypeExtsParam[i];
			}else{
				fileTypeExts += ";"+"*."+fileTypeExtsParam[i];
				fileTypeDesc += ","+fileTypeExtsParam[i];
			}
		}
	}
	//调用uploadify的初始化方法
	$('#'+fileEleId).uploadify({
		'swf':'assets/custom/plugins/uploadify/uploadify.swf',
	    'uploader':$("#base").val()+'/upload/uploadFile?type=file&module='+module,
	    'auto': true,
	    'width': width || 100,
	    'height': height || 34,
	    'buttonText':buttenName || '浏览',
	    'fileSizeLimit': maxSize || '10MB',
	    'fileTypeDesc': fileTypeDesc || '',
	    'fileTypeExts': fileTypeExts || '*.*',
	    'multi' : multi || false,
	    'onUploadSuccess' : function(file, data, response) {
	    	if(callBack){
	    		callBack(file, data, response);
	    		return;
	    	}
	        if(response){
	 			var resObj = JSON.parse(data);
	            if(resObj.resultCode == 0){
	               console.debug(resObj.data);
	            }else{
	                alert("上传失败,失败原因："+resObj.msg);
	            }
	        }else {
	           alert("上传失败");
	        }
	
	    }
    });
}

/**
 * 点击查看图片/下载文件
 * @param imagePath
 */
function showFile(path){
	window.open(path);
}
