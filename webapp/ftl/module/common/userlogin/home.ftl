<link rel="stylesheet" type="text/css" href="assets/custom/css/home/index.css">
<link rel="stylesheet" type="text/css" href="assets/custom/css/home/screen.css">
<script type="text/javascript">
	var global_usba_account="${Session.SPRING_SECURITY_CONTEXT.authentication.principal.usbaAccount}";
	var global_usba_token_id="${Session.SPRING_SECURITY_CONTEXT.authentication.principal.usbaCredentialToken}";
	//var cmdbHost = window.location.host == '10.1.21.123' ? 'http://10.1.21.123:8081': 'http://172.17.21.188:80';
	var cmdbHost = '${base}';
$(document).ready(function() {
	var monthNames = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ]; 
	var dayNames= ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"]

	var newDate = new Date();

	newDate.setDate(newDate.getDate());

	$('#Date').html(dayNames[newDate.getDay()] + " " + newDate.getDate() + ' ' + monthNames[newDate.getMonth()] + ' ' + newDate.getFullYear());

	setInterval(function() {
		var seconds = new Date().getSeconds();
		$("#sec").html(( seconds < 10 ? "0" : "" ) + seconds);
	},1000);
	
	setInterval( function() {
		var minutes = new Date().getMinutes();
		$("#min").html(( minutes < 10 ? "0" : "" ) + minutes);
    },1000);
	
	setInterval( function() {
		var hours = new Date().getHours();
		$("#hours").html(( hours < 10 ? "0" : "" ) + hours);
    }, 1000);
	
}); 
</script>
 
<div class="con-r"> 
    <div class="map"><img src="assets/custom/img/con-r-bg.png" width="100%"></div>
    <div class="dataBox">
        <div class="container">
            <div class="clock">
                <ul class="clockul">
                    <li id="hours"></li>
                    <li id="point">:</li>
                    <li id="min"></li>
                </ul>
            </div>
        </div>
        
        <div class="riqi">
            <span id="localtime"></span>
        </div> 
        
        <div class="zhouqi">
            <span id="localtime2"></span>
        </div>
        
        <div class="onlytext">欢迎登录司务官后台管理系统</div>
    </div> 
 </div>
   
<script type="text/javascript">
function showLocale(objD)
{
	var str,colorhead,colorfoot;
	
	var yy = objD.getYear();
	if(yy<1900) yy = yy+1900;
	var MM = objD.getMonth()+1;
	if(MM<10) MM = '0' + MM;
	var dd = objD.getDate();
	if(dd<10) dd = '0' + dd;
	var hh = objD.getHours();
	if(hh<10) hh = '0' + hh;
	var mm = objD.getMinutes();
	if(mm<10) mm = '0' + mm;
	var ss = objD.getSeconds();
	if(ss<10) ss = '0' + ss;
	
	var ww = objD.getDay();
	if  ( ww==0 )  colorhead="<font color=\"#FF0000\">";
	if  ( ww > 0 && ww < 6 )  colorhead="<font color=\"#ffffff\">";
	if  ( ww==6 )  colorhead="<font color=\"#008000\">";
	if  (ww==0)  ww="星期日";
	if  (ww==1)  ww="星期一";
	if  (ww==2)  ww="星期二";
	if  (ww==3)  ww="星期三";
	if  (ww==4)  ww="星期四";
	if  (ww==5)  ww="星期五";
	if  (ww==6)  ww="星期六";
	colorfoot="</font>"
	str = colorhead + yy + "年" + MM + "月" + dd + "日" +  colorfoot;	
	$("#localtime").html(str);
	str = colorhead + ww +  colorfoot;	
	$("#localtime2").html(str);
}
function tick()
{
	var today = new Date();
	showLocale(today);
	window.setTimeout("tick()", 1000);
}
tick();
</script>