<div class="row" id="cmdbSupplier-view-content-${id}">
	<div class="col-md-8">
		<div class="portlet blue box">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-server"></i>供应商信息
				</div>
			</div>
			<div class="portlet-body" style="min-height: 485px;position: relative;">
			
		<div class="form-horizontal">
	   <div class="form-body">
		 <div class="form-group">
			<label class="col-md-3 control-label">供应商名称</label>
			<div class="col-md-8">
				<label class="control-label">{{cmdbSupplier.supplier_name}}</label>
			</div>
	 	</div>
	 	<div class="form-group">
			<label class="col-md-3 control-label">联系人</label>
			<div class="col-md-9">
				<label class="control-label">{{cmdbSupplier.supplier_marketing_representative}}</label>
			</div>
		</div>
		 <div class="form-group">
			<label class="col-md-3 control-label">联系方式</label>
			<div class="col-md-9">
				<label class="control-label">{{cmdbSupplier.supplier_contact_number}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">email</label>
			<div class="col-md-9">
				<label class="control-label">{{cmdbSupplier.email}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">地址</label>
			<div class="col-md-9">
				<label class="control-label">{{cmdbSupplier.supplier_adress}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">联系人(技术支持)</label>
			<div class="col-md-9">
				<label class="control-label">{{cmdbSupplier.contact_technical_support}}</label>
			</div>
		</div>
			<div class="form-group">
			<label class="col-md-3 control-label">联系人(技术支持)方式</label>
			<div class="col-md-9">
				<label class="control-label">{{cmdbSupplier.contact_technical_support_number}}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">供应商简介</label>
			<div class="col-md-9">
				<label class="control-label">{{cmdbSupplier.supplier_remark}}</label>
			</div>
		</div>
		
		</div>
		</div>
		</div>
		</div>
	</div>

</div>

<script type="text/javascript">
	requirejs.config({baseUrl : "assets/global"});
	require(['../custom/scripts/cmdb/configuration/cmdbSupplier/cmdbSupplier_view_init'], function(view) {
		view.init({
		  id:"${id}"
		});
	});
</script>