<!DOCTYPE html>

  <head>
	<base href="${base}/">
	<meta charset="utf-8" />
	<title>司务官管理后台</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!--[if IE 8]> <html lang="zh" class="ie8 no-js"> <![endif]-->
	<!--[if IE 9]> <html lang="zh" class="ie9 no-js"> <![endif]-->
	<!--[if !IE]><!-->
	<html lang="zh" class="no-js">
	<!--<![endif]-->
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />

	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<!-- <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" /> -->
	<link href="assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
	<!--[if lt IE 7]><link rel="stylesheet" href="http://blueimp.github.com/cdn/css/bootstrap-ie6.min.css"><![endif]-->
	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN THEME STYLES -->
	<link href="assets/global/css/components.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/css/plugins.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/layout/css/layout.css" rel="stylesheet" type="text/css" />
	<link id="style_color" href="assets/global/layout/css/themes/my_dark_blue.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/layout/css/custom.css" rel="stylesheet" type="text/css" />
	<link href="assets/global/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css"  />
    <link href="assets/global/plugins/data-tables/DT_bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="assets/global/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" type="text/css"/>
    <link href="assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
    <link href="assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
	<!-- END THEME STYLES -->
	<link rel="shortcut icon" href="./favicon.ico" />

  </head>
  <!-- END HEAD -->

  <!-- BEGIN BODY -->
  <body class="page-header-fixed page-sidebar-fixed page-footer-fixed">
    <#include "header.ftl">
	<div class="clearfix"></div>

	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<#include "sidebar.ftl">
		<div class="page-content-wrapper">
			<div class="page-content" style="position: relative;">
					<div id="page-content-body-tabs">
	                    <!-- Nav tabs -->
	                    <ul class="nav nav-tabs" role="tablist" style="position: fixed;top: 80px;left: 250px;right: 20px;z-index: 1;background: #fff;">
	                    </ul>
	                    <!-- Tab panes -->
	                    <div class="tab-content" style="padding-top: 50px;">
	                    </div>
	                </div>
						<!-- 存放动态加载内容 -->
			</div>
		</div>
	</div>
	<!-- END CONTAINER -->

	<#include "footer.ftl">


  	 <!--[if lt IE 9]>
  	<script src="assets/global/plugins/respond.min.js"></script>
  	<script src="assets/global/plugins/excanvas.min.js"></script>
  	<![endif]-->
  	<!-- Begin javascript -->
  	<!-- 将baseUrl设置到"scripts"目录，并且加载module ID为'main'的一个脚本'-->
    <script language="javascript" type="text/javascript" src="${base}/assets/global/plugins/My97DatePicker/WdatePicker.js"></script>
	<script data-main="assets/custom/scripts/common/index-main" defer async="true" src="assets/custom/plugins/requirejs/require.js"></script>
	<!-- End javascript -->

  </body>
  <!-- END BODY -->
</html>