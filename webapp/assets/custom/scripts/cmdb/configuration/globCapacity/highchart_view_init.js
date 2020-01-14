define(['plugins/vue/vue.min', '../utils','plugins/moment/moment','../../../../../custom/plugins/highcharts/js/highcharts','../../../../../custom/scripts/common/datatable-ajax-jsonp'], function(Vue, cmdbUtils, moment) {

	$(function(){
	var belongToDetailObjectId=	$("#belongToDetailObjectId").val();
		console.log(belongToDetailObjectId);
		 var chart = new Highcharts.Chart({
		chart: { 
		 renderTo: 'globCapacityCharts', //设置显示的页面块
				  //type:'line'    //设置显示的方式
		 type: 'line'
			   },
		title : {text : '产品使用情况图',x : -20},
		subtitle : {text : '',x : -20},
		xAxis : {categories : [  ]},
		yAxis : {title : {text : ''},plotLines : [ {value : 0,width : 1,color : '#808080'} ]},
		tooltip : {},
		series : [ {name : '使用率',data : [ ]} ]
		});
	cmdbUtils.getcmdbData({
				url: '/mc?_tablename=glob_capacity&_action=get&belong_to_detail_object_id='+belongToDetailObjectId,
				dataType: "json"
				
				   
			}).then(function(rs) {
				var datas = rs.returndata.data[0];
				 for(var i = 0; i<datas.length;i++){
					 var capacity_rate = datas[i].capacity_rate;
					  var modified_time = datas[i].modified_time;
					  chart.series[0].setData(capacity_rate);
					  chart.xAxis[0].setCategories(modified_time); 
					       }

			});

	})	
	
});


