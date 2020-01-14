define(function() {	
	var loadMethod = function(){
		//add 20160129
		jQuery.validator.addMethod("checkIdCard", function(value, element) {       
			return this.optional(element) 
				|| /^(\d{6})(18|19|20)(\d{2})([0]\d|[1][012])([012]\d|[3][01])(\d{3})(\d|X)$/.test(value)
				|| /^(\d{6})(\d{2})([0]\d|[1][012])([012]\d|[3][01])(\d{3})$/.test(value);       
		}, "请输入正确的身份证号码"); 
		
		//联系电话校验
		jQuery.validator.addMethod("checkPhone", function(value, element) {       
			return this.optional(element) || /^(\d{3,4}-)?\d{7,8}$/.test(value) || /^^[1][358][0-9]{9}$/.test(value);       
		}, "请输入正确的联系电话");   
		
		//邮箱校验
		jQuery.validator.addMethod("checkEmail", function(value, element) {       
			return this.optional(element) || /^[a-z]([a-z0-9]*[-_]?[a-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\.][a-z]{2,3}([\.][a-z]{2})?$/.test(value);       
		}, "请输入正确的邮箱地址");  
		
		//文件类型校验
		jQuery.validator.addMethod("checkFileType", function(value, element, param) {  
			var arr = param.split(",");
			for(var i=0;i<=arr.length;i++){
				if(value == arr[i]){
					return true;
				}
			}
			return false;       
		}, "选择的文件类型不符合要求"); 
		
		//文件大小校验
		jQuery.validator.addMethod("checkFileSize", function(value, element, param) {  
			if(value <= param){
				return true;
			}else{
				return false;
			}  
		}, "选择的文件大小不符合要求"); 
	}	
	
	return {
		init : function(){
			loadMethod();
		}
	};
});
