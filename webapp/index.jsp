<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ucsmy.mc.module.message.util.MsgUtil"%>
<%@page import="com.ucsmy.mc.util.ApplicationContextHolder"%>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@page import="com.ucsmy.mc.module.activiti.util.FlowDataToCmdbUtil"%>
<html>
<body>
<h2>Hello World!</h2>
</body>
</html>

<%
// MsgUtil.sendMsg("123", "234", "123", "234", "fbadb4064a464839bee09a4bc259f7f9", "fbadb4064a464839bee09a4bc259f7f9", MsgUtil.Mstip.MESSAGE);
/* 
JdbcTemplate jdbcTemplate = ApplicationContextHolder.getBean(JdbcTemplate.class);
out.println(jdbcTemplate.queryForList("show table status")); 



String msg = "";

try {
	//boolean rs = FlowDataToCmdbUtil.insert("process:307:6b914f08-1563-11e7-beff-024208eaddca", "123");
	List<String> list = new ArrayList<String>();
	list.add("a584736b-241a-11e7-9e95-1c1b0d3002cc");
	
	boolean rs = FlowDataToCmdbUtil.insert("dnspod_record", "123123", list);
	if (rs) {
		msg = "success";
	} else {
		msg = "fail";
	}
} catch(Exception ex) {
	msg = ex.getMessage();
	ex.printStackTrace();
}

out.print(msg);
*/
%>
