var PageEvent = function() {
	return{
		toPage:function(url){
			window.parent.location.href=url;
		},
		toLoginPage:function(){
			window.parent.location.href='';
		}
	};
}();