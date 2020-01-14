    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8"%>
        <%@ page import="org.bluedon.oa.common.um.Uploader" %>
        <%@ page import="org.bluedon.oa.common.constants.PathConstants" %>
        <%@page import="org.bluedon.oa.common.util.SessionPropertyUtil"%>
		<%@page import="java.io.File"%>

            <%
    request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	
	StringBuilder path = new StringBuilder(PathConstants.UPLOAD_ROOT);
	path.append(File.separator).append(String.valueOf(SessionPropertyUtil.getCurrentYear()));
	path.append(PathConstants.UMEDITOR_IMAGE_PATH);
	//String savePath = request.getSession().getServletContext().getRealPath(path.toString());
	String savePath = path.toString();
	
    Uploader up = new Uploader(request);
    //up.setSavePath("/upload"); //upload
    up.setSavePath("../../../../../umeditorupload/Image/"+SessionPropertyUtil.getCurrentYear());
    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
    up.setAllowFiles(fileType);
    up.setMaxSize(10000); //单位KB
    up.upload();

    String callback = request.getParameter("callback");

	//String urlFix = up.getUrl().replaceAll("../../../../../", "");
    String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";

    result = result.replaceAll( "\\\\", "\\\\" );

    if( callback == null ){
        response.getWriter().print( result );
    }else{
        response.getWriter().print("<script>"+ callback +"(" + result + ")</script>");
    }
    %>
