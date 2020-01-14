var AddValiMethod = function() {
	 //添加校验规则
	var init = function(){
	 jQuery.validator.addMethod("trim",function(value,element){  
		 return this.optional(element) || value.indexOf(" ")<=0;  },$.validator.format("输入内容存在空格")  
		);
	 jQuery.validator.addMethod("chineseCharacter",function(value,element){  
		 return this.optional(element) || /^[\u4E00-\u9FA5]+$/.test(value);
		 },$.validator.format("请输入汉字"));
	 jQuery.validator.addMethod("numberAndLettersVal",function(value,element){  
		 return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);  
	 },$.validator.format("请输入字母或数字"));
	 
	 jQuery.validator.addMethod("idCardValidate",function(value,element){  
		 return this.optional(element) || /^(\d{14}|\d{17})(\d|[xX])$/.test(value);  
	 },$.validator.format("请输入合法身份证号"));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
	};
	return{
		init:function(){
			init();
		}		
	};
}();