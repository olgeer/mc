//表单校验
var validateExcel = function() {
	var form= $('#importForm');
    form.validate({
        errorElement: 'span', //default input error message container
        errorClass: 'help-block', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        ignore: "", // validate all fields including form hidden input
        rules: {
        	fileExt : {
				required: true,
				checkFileType: ".xls,.xlsx"
			},
			fileSize : {
				required: true,
				checkFileSize: 1024*1024 //1M
			}
        },
        messages : {
        	fileExt : {
				required: "请选择文件",
				checkFileType: "只能选择后缀名为.xls或.xlsx的文件"
			},
			fileSize : {
				required: "请选择文件",
				checkFileSize: "文件大小不能超过1M"
			}
		},
		invalidHandler : function(event, validator) {
			 var mg = '填写信息不完整或有误，请重新检查！';
   			 for(var obj in validator.errorMap)
			 {
   				mg=validator.errorMap[obj];
   				break;
			 }
   			 Metronic.alert({
         		type: 'danger', 
         		icon: 'warning', 
         		message: mg, 
         		container: form, 
         		place: 'prepend'
             });
		},
		errorPlacement : function(error, element) {
			var icon = $(element).parent(".input-icon")
					.children("i");
			icon.removeClass("fa-check").addClass("fa-warning");
			icon.attr("data-original-title", error.text()).tooltip(
				{
					"container" : "body"
				});
		},
		
		// hightlight error inputs
		highlight : function(element) {
			// set error class to the control group
			$(element).closest(".form-group") .removeClass("has-success") .addClass("has-error");
		},

		success : function(label, element) {
			var icon = $(element).parent(".input-icon") .children("i");
			$(element).closest(".form-group") .removeClass("has-error") .addClass("has-success");
			icon.removeClass("fa-warning").addClass("fa-check");
		}
    });
 };

//点击导入按钮校验
var initImportEvent = function(listInit) {	
	
	$('#submitImport').on('click', function(e){
		e.preventDefault();
		if($("#importForm").valid()){
			var formData = new FormData($("#importForm")[0]);  
		    $.ajax({  
		          url: $("#base").val()+'/import/importFile' ,  
		          type: 'POST',  
		          data: formData,  
		          async: false,  
		          cache: false,  
		          contentType: false,  
		          processData: false,  
		          success: function (data) {  
		        	  if(data!=null && data.sCount!=null){
		        		  $('#importModel').modal('hide');
						  dataTable=listInit.getDataTableObject();
						  dataTable.addAjaxParam("iTotalRows", '0');
						  dataTable.getDataTable().fnDraw();
						  
						  var msg = "成功导入";
						  var sCount = data.sCount;
						  if(sCount.length>0){
							  if(sCount.length>1){
								  msg +="：";
								  for(var s=0;s<sCount.length;s++){
									  msg +="第"+(parseInt(s)+1)+"个工作表["+sCount[s]+"]条记录，";
								  }
							  }else{
								  msg +="["+sCount[0]+"]条记录，";
							  } 
						  }
						  var eCount = data.eCount;
						  var error = false;
						  msg += "失败"
						  if(eCount.length>0){
							  if(eCount.length>1){
								  msg +="：";
								  for(var e=0;e<eCount.length;e++){
									  if(eCount[e]>0){error=true;}
									  msg +="第"+(parseInt(e)+1)+"个工作表["+eCount[e]+"]条记录，";
								  }
							  }else{
								  if(eCount[0]>0){error=true;}
								  msg +="["+eCount[0]+"]条记录。";
							  } 
						  }
						  msg = msg.substr(0,msg.length-1);
						  //msg +="["+data.sCount+"]条记录,失败["+data.eCount+"]条记录."
						  if(error && data.path!=null){
							  msg += "。请下载错误信息文件查看：<a href=\"javascript:void(0);\" onclick=\"showFile('"+$("#base").val()+"/upload/getFile?path="+data.path+"');\" style=\"font-size:14px;\">错误信息文件.xls</a>";
						  }
						  if(/*data.eCount==0*/!error){
							  Metronic.alert({
									type : 'success',
									icon : 'success',
									message : msg,
									container : dataTable.getTableWrapper(),
									place : 'prepend'
								});
						  }else{
							  Metronic.alert({
									type : 'danger',
									icon : 'warning',
									message : msg,
									container : dataTable.getTableWrapper(),
									place : 'prepend'
							 });
						  }	  
		        	  }else{
		        		  Metronic.alert({
			         		 type: 'danger', 
			         		 icon: 'warning', 
			         		 message: "导入失败,"+data.msg, 
			         		 container: $("#importForm"), 
			         		 place: 'prepend'
			             });
		        	  }
		          },  
		          error: function (returndata) {  
		        	  Metronic.alert({
		         		type: 'danger', 
		         		icon: 'warning', 
		         		message: "导入失败", 
		         		container: $("#importForm"), 
		         		place: 'prepend'
		             });
		          }  
		     }); 
		}
	});
};

//选择或改变文件
var changeFile = function(o){
	if(o.value !=null && o.value!="" && o.value.lastIndexOf('.') > -1){
 		var fileExt = o.value.substring(o.value.lastIndexOf('.'));
 		$("#fileExt").val(fileExt);
 		$("#fileSize").val(o.files[0].size);
	}else{
		$("#fileExt").val("");
 		$("#fileSize").val("");
 		$(".fileinput-filename").text("");
 		$("#fileinput").addClass('fileinput-new').removeClass('fileinput-exists');
	}
	
};

//打开文件
var showFile = function(path){
	window.open(path);
}

