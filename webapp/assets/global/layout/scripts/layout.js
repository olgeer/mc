/**
 * Core script to handle the entire theme and core functions
 */
var Layout = function() {
	// add by ylm ====================================
	var errorPage = '<div class="col-md-12 page-404">' +
						'<div class="number">404</div>' + 
						'<div class="details">' +
							'<h3>抱歉！页面出错了！>_<</h3>' +
							'<p>服务器好像出了些问题，请稍候再试试吧...<br>' +
								'您可以联系我们网站下方的客服电话来帮您解决问题<br>' +
								'您也可以在服务恢复之后在意见反馈版块中给我们提出您宝贵的意见哦，万分感谢！^_^<br><br>' +
							'</p>' +
						'</div>' +
					'</div>';

    var errorPage500 = '<div class="col-md-12 page-404">' +
        '<div class="number">500</div>' +
        '<div class="details">' +
        '<h3>抱歉！页面出错了！>_<</h3>' +
        '<p>服务器好像出了些问题，请稍候再试试吧...<br>' +
        '您可以联系我们网站下方的客服电话来帮您解决问题<br>' +
        '您也可以在服务恢复之后在意见反馈版块中给我们提出您宝贵的意见哦，万分感谢！^_^<br><br>' +
        '</p>' +
        '</div>' +
        '</div>';

    var errorPage403 = '<div class="col-md-12 page-404">' +
        '<div class="number">403</div>' +
        '<div class="details">' +
        '<h3>未授权访问改页面！>_<</h3>' +
        '</div>' +
        '</div>';

    function getErrorPage(status) {
        if (status == '404') {
            return errorPage;
        } else if (status == '500') {
            return errorPage500;
        } else if (status == '403') {
            return errorPage403;
        }

        return errorPage;
    }



	//=================================================
	//临时Tab页面ID（非模块ID）
	tempTabId=1;
	//用于存储module的name和描述
	moduleMap={};
	
	var layoutImgPath = 'assets/custom/img/';

	// * BEGIN:CORE HANDLERS *//
	// this function handles responsive layout on screen size resize or mobile
	// device rotate.

	// Set proper height for sidebar and content. The content and sidebar height
	// must be synced always.
	var handleSidebarAndContentHeight = function() {
		var content = $('.page-content');
		var sidebar = $('.page-sidebar');
		var body = $('body');
		var height;

		if (body.hasClass("page-footer-fixed") === true
				&& body.hasClass("page-sidebar-fixed") === false) {
			var available_height = $(window).height()
					- $('.page-footer').outerHeight()
					- $('.page-header').outerHeight();
			if (content.height() < available_height) {
				//content.attr('style', 'min-height:' + available_height + 'px');
                content.css('min-height', available_height);
			}
		} else {
			if (body.hasClass('page-sidebar-fixed')) {
				height = _calculateFixedSidebarViewportHeight();
				if (body.hasClass('page-footer-fixed') === false) {
					height = height - $('.page-footer').outerHeight();
				}
			} else {
				height = sidebar.height() + 20;
				var headerHeight = $('.page-header').outerHeight();
				var footerHeight = $('.page-footer').outerHeight();
				if ($(window).width() > 1024
						&& (height + headerHeight + footerHeight) < $(window)
								.height()) {
					height = $(window).height() - headerHeight - footerHeight;
				}
			}
			//content.attr('style', 'min-height:' + height + 'px');
            content.css('min-height', height);
		}
	}

	// Handle sidebar menu
	var handleSidebarMenu = function() {
		jQuery('.page-sidebar').on('click', 'li > a', function(e) {
			if ($(this).next().hasClass('sub-menu') == false) {
				if ($('.btn-navbar').hasClass('collapsed') == false) {
					$('.btn-navbar').click();
				}
				return;
			}
			var hasSubMenu = $(this).next().hasClass('sub-menu');

            if ($(this).parents('.page-sidebar-menu-hover-submenu').size() === 1) {
                return;
            }

            if (hasSubMenu === false) {
                if ( $('.page-sidebar').hasClass("in")) {
                    $('.page-header .responsive-toggler').click();
                }
                return;
            }
            
			if ($(this).next().hasClass('sub-menu always-open')) {
				return;
			}

			var parent = $(this).parent().parent();
			var the = $(this);
			var menu = $('.page-sidebar-menu');
			var sub = jQuery(this).next();

			var autoScroll = menu.data("auto-scroll") ? menu
					.data("auto-scroll") : true;
			var slideSpeed = menu.data("slide-speed") ? parseInt(menu
					.data("slide-speed")) : 200;

			parent.children('li.open').children('a').children('.arrow')
					.removeClass('open');
			parent.children('li.open').children('.sub-menu:not(.always-open)')
					.slideUp(200);
			parent.children('li.open').removeClass('open');

			var slideOffeset = -200;

			if (sub.is(":visible")) {
				jQuery('.arrow', jQuery(this)).removeClass("open");
				jQuery(this).parent().removeClass("open");
				sub.slideUp(slideSpeed, function() {
					if (autoScroll == true
							&& $('body').hasClass('page-sidebar-closed') == false) {
						if ($('body').hasClass('page-sidebar-fixed')) {
							menu.slimScroll({
										'scrollTo' : (the.position()).top
									});
						} else {
							Metronic.scrollTo(the, slideOffeset);
						}
					}
					handleSidebarAndContentHeight();
				});
			} else {
				jQuery('.arrow', jQuery(this)).addClass("open");
				jQuery(this).parent().addClass("open");
				sub.attr('style','');
				sub.slideDown(slideSpeed, function() {
					if (autoScroll == true
							&& $('body').hasClass('page-sidebar-closed') == false) {
						if ($('body').hasClass('page-sidebar-fixed')) {
							menu.slimScroll({
										'scrollTo' : (the.position()).top
									});
						} else {
							Metronic.scrollTo(the, slideOffeset);
						}
					}
					handleSidebarAndContentHeight();
				});
			}

			e.preventDefault();
		});

		// handle ajax links within sidebar menu
		jQuery('.page-sidebar').on('click', ' li > a.ajaxify', function(e, data) {
			if (data === 'close') return;
			e.preventDefault();
			Metronic.scrollTop();

			var url = $(this).attr("href");
			var menuContainer = jQuery('.page-sidebar ul');
			var pageContent = $('.page-content');
			
			menuContainer.children('li.active').removeClass('active');
			menuContainer.children('arrow.open').removeClass('open');

			$(this).parents('li').each(function() {
						$(this).addClass('active');
						$(this).children('a > span.arrow').addClass('open');
					});
			$(this).parents('li').addClass('active');

			Metronic.startPageLoading();

			if ($(window).width() <= 991 && $('.page-sidebar').hasClass("in")) {
				$('.navbar-toggle').click();
			}

			var the = $(this);

			$.ajax({
				type : "GET",
				cache : false, //防止IE缓存问题
				url : url,
				dataType : "html",
				success : function(res) {
					if (the.parents('li.open').size() === 0) {
						$('.page-sidebar-menu > li.open > a').trigger('click', ['close']);
					}

					Metronic.stopPageLoading();
					var tabId;
					if(typeof(the.attr("module_id"))=="undefined"){
						tabId=tempTabId++;
					}else{
						var paths=url.split('/');
						var moduleName = paths[paths.length-2];
						if (moduleName == 'manageUserAccount') {
							moduleName = paths[paths.length-2] + paths[paths.length-1];
						}
						tabId='module_'+moduleName;//使用模块名称作为id
					}
					console.log("230");
			        Layout.addTab({
			            id: tabId,
			            title: the.html(),
			            content: res,
			            url: url
			        });
			        
					Layout.fixContentHeight(); // fix content height
					Metronic.initAjax(); // initialize core stuff
					
				},
				error : function(xhr, ajaxOptions, thrownError) {
					Metronic.stopPageLoading();
					console.log("244");
					Layout.addTab({
				            id: the.attr("module_id")?the.attr("module_id"):tempTabId++,
				            title: the.html(),
                            content: getErrorPage(xhr.status),
				            url: url
				    });
				}
			});
		});
		
		var tabs=jQuery("#page-content-body-tabs");
		tabs.on('click', '.close-tab', function () {
	        var id = $(this).prev("a").attr("aria-controls");
	        Layout.closeTab(id);
	    });
	    //obj上禁用右键菜单
		tabs.on('contextmenu', 'li[role=presentation]', function () {
	        var id = $(this).children('a').attr('aria-controls');
	        Layout.popTab(id, $(this));
	        return false;
	    });

	    //刷新页面
		tabs.on('click', 'ul.rightMenu a[data-right=refresh]', function () {
	        var id = $(this).closest('ul').attr("aria-controls").substring(4);
	        var url=$(this).closest('ul').attr('aria-url');
			Metronic.scrollTop();
			Metronic.startPageLoading();

			if ($(window).width() <= 991 && $('.page-sidebar').hasClass("in")) {
				$('.navbar-toggle').click();
			}

			var the = $(this);

			$.ajax({
				type : "GET",
				cache : false, //防止IE缓存问题
				url : url,
				dataType : "html",
				success : function(res) {

					if (the.closest('li.open').size() === 0) {
						$('.page-sidebar-menu > li.open > a').click();
					}

					Metronic.stopPageLoading();
					console.log("292");
			        Layout.addTab({
			            id: id,
			            title: the.html(),
			            content: res,
			            url: url
			        });
			        
					Layout.fixContentHeight(); // fix content height
					Metronic.initAjax(); // initialize core stuff
					
				},
				error : function(xhr, ajaxOptions, thrownError) {
					Metronic.stopPageLoading();
					console.log("306");
					Layout.addTab({
				            id: the.attr("module_id")?the.attr("module_id"):tempTabId++,
				            title: the.html(),
                            content: getErrorPage(xhr.status),
				            url: url
				    });
				}
			});


			console.log("317");
	        Layout.addTab({'id':id,'url':url});
	        $('#popMenu').fadeOut();
	    });

	    //关闭自身
		tabs.on('click', 'ul.rightMenu a[data-right=remove]', function () {
	        var id = $(this).closest("ul").attr("aria-controls");
	        Layout.closeTab(id);
	        Layout.dropTab();
	        $('#popMenu').fadeOut();
	    });

	    //关闭其他
		tabs.on('click', 'ul.rightMenu a[data-right=remove-circle]', function () {
	        var tab_id = $(this).closest('ul').attr("aria-controls");
	        tabs.children('ul.nav').find('li').each(function () {
	            var id = $(this).attr('id');
	            if (id && id != 'tab_' + tab_id) {
	            	Layout.closeTab($(this).children('a').attr('aria-controls'));
	            }
	        });
	        Layout.dropTab();
	        $('#popMenu').fadeOut();
	    });

	    //关闭左侧
		tabs.on('click', 'ul.rightMenu a[data-right=remove-left]', function () {
	        var tab_id = $(this).closest('ul').attr("aria-controls");
	        $('#tab_' + tab_id).prevUntil().each(function () {
	            var id = $(this).attr('id');
	            if (id && id != 'tab_' + tab_id) {
	            	Layout.closeTab($(this).children('a').attr('aria-controls'));
	            }
	        });
	        Layout.dropTab();
	        $('#popMenu').fadeOut();
	    });

	    //关闭右侧
		tabs.on('click', 'ul.rightMenu a[data-right=remove-right]', function () {
	        var tab_id = $(this).closest('ul').attr("aria-controls");
	        $('#tab_' + tab_id).nextUntil().each(function () {
	            var id = $(this).attr('id');
	            if (id && id != 'tab_' + tab_id) {
	            	Layout.closeTab($(this).children('a').attr('aria-controls'));
	            }
	        });
	        Layout.dropTab();
	        $('#popMenu').fadeOut();
	    });

		/*tabs.on('mouseover', 'li[role = "presentation"]', function () {
	        $(this).find('.close-tab').show();
	    });

	    tabs.on('mouseleave', 'li[role = "presentation"]', function () {
	        $(this).find('.close-tab').hide();
	    });*/
		

		// handle ajax link within main content
		jQuery('.page-content').on('click', '.ajaxify', function(e) {
			e.preventDefault();
			Metronic.scrollTop();

			var url = $(this).attr("href");
			var pageContent = $('.page-content');

			Metronic.startPageLoading();

			if ($(window).width() <= 991 && $('.page-sidebar').hasClass("in")) {
				$('.navbar-toggle').click();
			}
			var the = $(this);
			//父模块ID
			var parentid=$('div[role = "tabpanel"].active').attr("id");
			
			//cmdb编辑添加回刷列表参数
			if (url.indexOf('configuration') == 0 || the.is('[refresh]')) {
				var fromId = the.closest('table').attr('id');
				if (!fromId) {
					fromId = the.closest('.table-toolbar').next().find('table').attr('id');
				}
				if (fromId) url = url + (url.indexOf('?') <= -1 ? '?': '&') + 'fromId=' +  fromId
			}
			
			$.ajax({
				type : "GET",
				cache : false,
				url : url,
				success : function(res) {
					Metronic.stopPageLoading();
					var html = the.data('html') ? the.next().html() : the.html();
					if(parentid.indexOf('module')>0){
						console.log("412");
						Layout.addTab({
							id: the.attr("module_id")?the.attr("module_id"):tempTabId++,
							title: html,
							content: res,
							parentid:parentid,
							url: url
						});
					}else{
						var targetid=$('div[role = "tabpanel"].active').attr("parentid").split('tab_')[1];
						console.log("422");
						Layout.addTab({
							id: targetid?targetid:tempTabId++,
				            title: html,
				            content: res,
				            closeCurrent:true,
				            url: url
				        });
					}
					
					Layout.fixContentHeight(); // fix content height
					Metronic.initAjax(); // initialize core stuff
				},
				error : function(xhr, ajaxOptions, thrownError) {
					console.log("436");
					Layout.addTab({
			            id: the.attr("module_id")?the.attr("module_id"):tempTabId++,
			            title: the.html(),
                        content: getErrorPage(xhr.status),
			            url: url
					});
					
					Metronic.stopPageLoading();
				}
			});
		});


		// handle ajax link within page header
		jQuery('.page-header').on('click', '.ajaxify', function(e) {
			e.preventDefault();
			Metronic.scrollTop();
			var url = $(this).attr("href");
			var pageContent = $('.page-content');
			var menuContainer = jQuery('.page-sidebar ul');
			menuContainer.children('li.active').removeClass('active');
			menuContainer.children('arrow.open').removeClass('open');
			var sidebar=$('.page-sidebar ul li ul li');
			sidebar.each(function() {
					var context=$(this).children("a");
					if(context.attr("href")==url){
						$(this).addClass('active');
						$(this).parents('li').each(function() {
						$(this).addClass('active');
						$(this).children('ul').css('display','block'); 
						$(this).children('a > span.arrow').addClass('open');
					});
						return false;
					}
					});
			Metronic.startPageLoading();

			if ($(window).width() <= 991 && $('.page-sidebar').hasClass("in")) {
				$('.navbar-toggle').click();
			}

			var the = $(this);
			$.ajax({
				type : "GET",
				cache : false,
				url : url,
				dataType : "html",
				success : function(res) {
					var html = the.data('html') ? the.next().html() : the.html();
					Metronic.stopPageLoading();
					console.log("487");
					Layout.addTab({
			            id: the.attr("module_id")?the.attr("module_id"):tempTabId++,
			            title: html,
			            content: res,
			            url: url
					});
					
					Layout.fixContentHeight(); // fix content height
					Metronic.initAjax(); // initialize core stuff
				},
				error : function(xhr, ajaxOptions, thrownError) {
					console.log("499");
					Layout.addTab({
			            id: $(this).attr("module_id")?$(this).attr("module_id"):tempTabId++,
			            title: $(this).html(),
                        content: getErrorPage(xhr.status),
			            url: url
					});
					Metronic.stopPageLoading();
				}
			});
		});
	}

	// Helper function to calculate sidebar height for fixed sidebar layout.
	var _calculateFixedSidebarViewportHeight = function() {
		var sidebarHeight = $(window).height()
				- $('.page-header').outerHeight();
		if ($('body').hasClass("page-footer-fixed")) {
			sidebarHeight = sidebarHeight - $('.page-footer').outerHeight();
		}

		return sidebarHeight;
	}

	// Handles fixed sidebar
	var handleFixedSidebar = function() {
		var menu = $('.page-sidebar-menu');

		if (menu.parent('.slimScrollDiv').size() === 1) { // destroy existing
															// instance before
															// updating the
															// height
			menu.slimScroll({
						destroy : true
					});
			menu.removeAttr('style');
			$('.page-sidebar').removeAttr('style')
					.removeAttr('data-initialized');
		}

		if ($('.page-sidebar-fixed').size() === 0) {
			handleSidebarAndContentHeight();
			return;
		}

		var viewport = Metronic.getViewPort();
		if (viewport.width >= 992) {
			var sidebarHeight = _calculateFixedSidebarViewportHeight();

			menu.slimScroll({
						size : '7px',
						color : '#a1b2bd',
						opacity : .3,
						position : Metronic.isRTL() ? 'left' : 'right',
						height : sidebarHeight,
						allowPageScroll : false,
						disableFadeOut : false
					});
			handleSidebarAndContentHeight();
		}
	}

	// Handles sidebar toggler to close/hide the sidebar.
	var _initFixedSidebarHoverEffect = function() {
		var body = $('body');
		if (body.hasClass('page-sidebar-fixed')) {
			$('.page-sidebar-menu').on('mouseenter', function() {
						if (body.hasClass('page-sidebar-closed')) {
							$(this).removeClass('page-sidebar-menu-closed');
						}
					}).on('mouseleave', function() {
						if (body.hasClass('page-sidebar-closed')) {
							$(this).addClass('page-sidebar-menu-closed');
						}
					});
		}
	}

	// Hanles sidebar toggler
	var handleSidebarToggler = function() {
		var viewport = Metronic.getViewPort();
		var body = $('body');
		if ($.cookie && $.cookie('sidebar_closed') === '1'
				&& viewport.width >= 992) {
			$('body').addClass('page-sidebar-closed');
			$('.page-sidebar-menu').addClass('page-sidebar-menu-closed');
		}

		// handle sidebar show/hide
		$('.page-sidebar, .page-header').on('click', '.sidebar-toggler',
				function(e) {
					var sidebar = $('.page-sidebar');
					var sidebarMenu = $('.page-sidebar-menu');
					$(".sidebar-search", sidebar).removeClass("open");

					if (body.hasClass("page-sidebar-closed")) {
						body.removeClass("page-sidebar-closed");
						sidebarMenu.removeClass("page-sidebar-menu-closed");
						if ($.cookie) {
							$.cookie('sidebar_closed', '0');
						}
					} else {
						body.addClass("page-sidebar-closed");
						sidebarMenu.addClass("page-sidebar-menu-closed");
						if (body.hasClass("page-sidebar-fixed")) {
							sidebarMenu.trigger("mouseleave");
						}
						if ($.cookie) {
							$.cookie('sidebar_closed', '1');
						}
					}

					$(window).trigger('resize');
				});

		_initFixedSidebarHoverEffect();

		// handle the search bar close
		$('.page-sidebar').on('click', '.sidebar-search .remove', function(e) {
					e.preventDefault();
					$('.sidebar-search').removeClass("open");
				});

		// handle the search query submit on enter press
		$('.page-sidebar .sidebar-search').on('keypress', 'input.form-control',
				function(e) {
					if (e.which == 13) {
						$('.sidebar-search').submit();
						return false; // <---- Add this line
					}
				});

		// handle the search submit(for sidebar search and responsive mode of
		// the header search)
		$('.sidebar-search .submit').on('click', function(e) {
					e.preventDefault();
					if ($('body').hasClass("page-sidebar-closed")) {
						if ($('.sidebar-search').hasClass('open') == false) {
							if ($('.page-sidebar-fixed').size() === 1) {
								$('.page-sidebar .sidebar-toggler').click(); // trigger
																				// sidebar
																				// toggle
																				// button
							}
							$('.sidebar-search').addClass("open");
						} else {
							$('.sidebar-search').submit();
						}
					} else {
						$('.sidebar-search').submit();
					}
				});
	}

	// Handles the horizontal menu
	var handleHorizontalMenu = function() {
		// handle tab click
		$('.page-header').on('click', '.hor-menu a[data-toggle="tab"]',
				function(e) {
					e.preventDefault();
					var nav = $(".hor-menu .nav");
					var active_link = nav.find('li.current');
					$('li.active', active_link).removeClass("active");
					$('.selected', active_link).remove();
					var new_link = $(this).parents('li').last();
					new_link.addClass("current");
					new_link.find("a:first")
							.append('<span class="selected"></span>');
				});

		// handle search box expand/collapse
		$('.page-header').on('click', '.search-form', function(e) {
			$(this).addClass("open");
			$(this).find('.form-control').focus();

			$('.page-header .search-form .form-control').on('blur',
					function(e) {
						$(this).closest('.search-form').removeClass("open");
						$(this).unbind("blur");
					});
		});

		// handle hor menu search form on enter press
		$('.page-header').on('keypress',
				'.hor-menu .search-form .form-control', function(e) {
					if (e.which == 13) {
						$(this).closest('.search-form').submit();
						return false;
					}
				});

		// handle header search button click
		$('.page-header').on('mousedown', '.search-form.open .submit',
				function(e) {
					e.preventDefault();
					e.stopPropagation();
					$(this).closest('.search-form').submit();
				});

		$(document).on('click', '.mega-menu-dropdown .dropdown-menu',
				function(e) {
					e.stopPropagation();
				});
	}

	// Handles Bootstrap Tabs.
	var handleTabs = function() {
		// fix content height on tab click
		$('body').on('shown.bs.tab', 'a[data-toggle="tab"]', function() {
					handleSidebarAndContentHeight();
				});
	}

	// Handles the go to top button at the footer
	var handleGoTop = function() {
		/* set variables locally for increased performance */
		jQuery('.page-footer').on('click', '.go-top', function(e) {
					Metronic.scrollTo();
					e.preventDefault();
				});
	}

	// Hanlde 100% height elements(block, portlet, etc)
	var handle100HeightContent = function() {

		// helper function to reinit slimscroll
		var reinitSlimscroll = function(target) {
			if (target.find(".slimScrollDiv").size() === 1) { // destroy
																// existing
																// instance
																// before
																// updating the
																// height
				target.find(".full-height-content-body").slimScroll({
							destroy : true
						});
				target.find(".full-height-content-body").removeAttr('style')
						.removeAttr('data-initialized');
			}
		}

		var target = $('.full-height-content');
		if (target.size() === 0 || $(window).width() < 992) {
			reinitSlimscroll(target); // reinit slimscroll
			return;
		}

		var height;
		var body = $('body');
		if (body.hasClass('page-footer-fixed')) {
			height = $(window).height() - $('.page-header').outerHeight()
					- $('.page-footer').outerHeight();
		} else {
			height = $(window).height() - $('.page-header').outerHeight()
					- $('.page-footer').outerHeight()
					- $('.page-title').outerHeight(true)
					- $('.page-breadcrumb').outerHeight(true);
		}

		reinitSlimscroll(target); // reinit slimscroll

		if (target.hasClass('portlet')) {
			var portletBody = target.find('.portlet-body');
			height = height
					- target.find('.portlet-title').outerHeight(true)
					- parseInt(target.find('.portlet-body').css('padding-top'))
					- parseInt(target.find('.portlet-body')
							.css('padding-bottom')) - 2;

			if (target.hasClass("full-height-content-scrollable")) {
				height = height - 20;
				portletBody.find('.full-height-content-body').css('height',
						height);
				Metronic.initSlimScroll(portletBody
						.find('.full-height-content-body'));
			} else {
				portletBody.css('min-height', height);
			}
		} else {
			if (target.hasClass("full-height-content-scrollable")) {
				height = height - 20;
				target.find('.full-height-content-body').css('height', height);
				Metronic.initSlimScroll(target
						.find('.full-height-content-body'));
			} else {
				target.css('min-height', height);
			}
		}
	}

	// Handle Theme Settings
	var handleTheme = function() {

		var panel = $('.theme-panel');

		if ($('body').hasClass('page-boxed') == false) {
			$('.layout-option', panel).val("fluid");
		}

		$('.sidebar-option', panel).val("default");
		$('.page-header-option', panel).val("fixed");
		$('.page-footer-option', panel).val("default");
		if ($('.sidebar-pos-option').attr("disabled") === false) {
			$('.sidebar-pos-option', panel).val(Metronic.isRTL()
					? 'right'
					: 'left');
		}

		// handle theme layout
		var resetLayout = function() {
			$("body").removeClass("page-boxed")
					.removeClass("page-footer-fixed")
					.removeClass("page-sidebar-fixed")
					.removeClass("page-header-fixed")
					.removeClass("page-sidebar-reversed");

			$('.page-header > .page-header-inner').removeClass("container");

			if ($('.page-container').parent(".container").size() === 1) {
				$('.page-container').insertAfter('body > .clearfix');
			}

			if ($('.page-footer > .container').size() === 1) {
				$('.page-footer').html($('.page-footer > .container').html());
			} else if ($('.page-footer').parent(".container").size() === 1) {
				$('.page-footer').insertAfter('.page-container');
			}

			$('body > .container').remove();
		}

		var lastSelectedLayout = '';

		var setLayout = function() {

			var layoutOption = $('.layout-option', panel).val();
			var sidebarOption = $('.sidebar-option', panel).val();
			var headerOption = $('.page-header-option', panel).val();
			var footerOption = $('.page-footer-option', panel).val();
			var sidebarPosOption = $('.sidebar-pos-option', panel).val();

			if (sidebarOption == "fixed" && headerOption == "default") {
				alert('Default Header with Fixed Sidebar option is not supported. Proceed with Fixed Header with Fixed Sidebar.');
				$('.page-header-option', panel).val("fixed");
				$('.sidebar-option', panel).val("fixed");
				sidebarOption = 'fixed';
				headerOption = 'fixed';
			}

			resetLayout(); // reset layout to default state

			if (layoutOption === "boxed") {
				$("body").addClass("page-boxed");

				// set header
				$('.page-header > .page-header-inner').addClass("container");
				var cont = $('body > .clearfix')
						.after('<div class="container"></div>');

				// set content
				$('.page-container').appendTo('body > .container');

				// set footer
				if (footerOption === 'fixed') {
					$('.page-footer').html('<div class="container">'
							+ $('.page-footer').html() + '</div>');
				} else {
					$('.page-footer').appendTo('body > .container');
				}
			}

			if (lastSelectedLayout != layoutOption) {
				// layout changed, run responsive handler:
				Metronic.runResizeHandlers();
			}
			lastSelectedLayout = layoutOption;

			// header
			if (headerOption === 'fixed') {
				$("body").addClass("page-header-fixed");
				$(".page-header").removeClass("navbar-static-top")
						.addClass("navbar-fixed-top");
			} else {
				$("body").removeClass("page-header-fixed");
				$(".page-header").removeClass("navbar-fixed-top")
						.addClass("navbar-static-top");
			}

			// sidebar
			if ($('body').hasClass('page-full-width') === false) {
				if (sidebarOption === 'fixed') {
					$("body").addClass("page-sidebar-fixed");
					$("page-sidebar-menu").addClass("page-sidebar-menu-fixed");
					$("page-sidebar-menu")
							.removeClass("page-sidebar-menu-default");
					_initFixedSidebarHoverEffect();
				} else {
					$("body").removeClass("page-sidebar-fixed");
					$("page-sidebar-menu")
							.addClass("page-sidebar-menu-default");
					$("page-sidebar-menu")
							.removeClass("page-sidebar-menu-fixed");
					$('.page-sidebar-menu').unbind('mouseenter')
							.unbind('mouseleave');
				}
			}

			// footer
			if (footerOption === 'fixed') {
				$("body").addClass("page-footer-fixed");
			} else {
				$("body").removeClass("page-footer-fixed");
			}

			// sidebar position
			if (Metronic.isRTL()) {
				if (sidebarPosOption === 'left') {
					$("body").addClass("page-sidebar-reversed");
					$('#frontend-link').tooltip('destroy').tooltip({
								placement : 'right'
							});
				} else {
					$("body").removeClass("page-sidebar-reversed");
					$('#frontend-link').tooltip('destroy').tooltip({
								placement : 'left'
							});
				}
			} else {
				if (sidebarPosOption === 'right') {
					$("body").addClass("page-sidebar-reversed");
					$('#frontend-link').tooltip('destroy').tooltip({
								placement : 'left'
							});
				} else {
					$("body").removeClass("page-sidebar-reversed");
					$('#frontend-link').tooltip('destroy').tooltip({
								placement : 'right'
							});
				}
			}

			handleSidebarAndContentHeight(); // fix content height
			handleFixedSidebar(); // reinitialize fixed sidebar
		}

		// handle theme colors
		var setColor = function(color) {
			var color_ = (Metronic.isRTL() ? color + '-rtl' : color);
			$('#style_color').attr("href",
					"assets/admin/layout/css/themes/" + color_ + ".css");
			if (color == 'light2') {
				$('.page-logo img').attr('src',
						'assets/admin/layout/img/logo-invert.png');
			} else {
				$('.page-logo img').attr('src',
						'assets/admin/layout/img/logo.png');
			}
			if ($.cookie) {
				$.cookie('style_color', color);
			}
		}

		$('.toggler', panel).on('click', function() {
					$('.toggler').hide();
					$('.toggler-close').show();
					$('.theme-panel > .theme-options').show();
				});

		$('.toggler-close', panel).on('click', function() {
					$('.toggler').show();
					$('.toggler-close').hide();
					$('.theme-panel > .theme-options').hide();
				});

		$('.theme-colors > ul > li', panel).on('click', function() {
					var color = $(this).attr("data-style");
					setColor(color);
					$('ul > li', panel).removeClass("current");
					$(this).addClass("current");
				});

		$(
				'.layout-option, .page-header-option, .sidebar-option, .page-footer-option, .sidebar-pos-option',
				panel).change(setLayout);

		if ($.cookie && $.cookie('style_color')) {
			setColor($.cookie('style_color'));
		}
	}

	//初始化补交modal确定的事件
	var submitFormId = null;
	var submitButtonId = null;
	var isInit = false;
	var initEvent = function() {
		if(!isInit) {
			$("body").on('click', '#delayFormSubmit', function() {
				var delayReason = $("#delayReason").val();
				$("<input>", {
					  type: "hidden",
					  name: "delayReason",
					  val: delayReason
				}).appendTo(submitFormId);
				Layout.ajaxSubmit(submitFormId, submitButtonId);
				$('.ajaxSubmitDelayModal').modal('hide');
			});
			isInit = true;
		}
	};
	
	//初始化模块对应的数据
	var  initModuleData=function () {
    	$('.page-sidebar li > a.ajaxify').each(function (i){
    		var paths=$(this).attr("href").split('/');
    		if(paths.length>2){
    			var key='tab_module_'+paths[paths.length-2];
    			var value=$(this).html();
    			moduleMap[key]=value;
    		}
    	});
	}
	// * END:CORE HANDLERS *//

	return {

		// main function to initiate the theme
		init : function() {
			// IMPORTANT!!!: Do not modify the core handlers call order.

			// reinitialize the layout on window resize
			Metronic.addResizeHandler(handleSidebarAndContentHeight); // recalculate
																		// sidebar
																		// &
																		// content
																		// height
																		// on
																		// window
																		// resize
			Metronic.addResizeHandler(handleFixedSidebar); // reinitialize
															// fixed sidebar on
															// window resize
			Metronic.addResizeHandler(handle100HeightContent); // reinitialize
																// content
																// height on
																// window resize

			// layout handlers
			handleFixedSidebar(); // handles fixed sidebar menu
			handleSidebarMenu(); // handles main menu
			handleHorizontalMenu(); // handles horizontal menu
			handleSidebarToggler(); // handles sidebar hide/show
			handle100HeightContent(); // handles 100% height elements(block,
										// portlet, etc)
			handleGoTop(); // handles scroll to top functionality in the footer
			handleTabs(); // handle bootstrah tabs
			handleTheme(); // handles style customer tool
			initEvent();
			initModuleData();
		},

		// public function to fix the sidebar and content height accordingly
		fixContentHeight : function() {
			handleSidebarAndContentHeight();
		},

		getLayoutImgPath : function() {
			return layoutImgPath;
		},

		//异步提交bootstrap_Wizard,并且不刷新页面
		ajaxSubmitWizard : function(formId, submitId,callback) {
			submitFormId = formId;
			submitButtonId = submitId;
			var url = $(submitId).attr("href");
			var pageContentBody = $('.page-content .page-content-body');

			Metronic.blockUI({
                target: formId,
                boxed: true,
                message: '请稍候...'
            });

			if ($(window).width() <= 991 && $('.page-sidebar').hasClass("in")) {
				$('.navbar-toggle').click();
			}
			var the = $(this);

			$.ajax({
				type : "post",
				cache : false,
				url : url,
				data : $(formId).serializeArray(),
				success : function(res) {
					Metronic.unblockUI(formId);
					if (res.sStatus == "true") {
						alert(res.sMessage);
                    }else if (res.sStatus == "false") {
                    	alert(res.sMessage);
                    }else{
                    	if( typeof callback == 'function')
                    		{
                    			callback(res);
                    		}
                    } 
				},
				error : function() {
                	//add by qinguidong 20160615 如果session超时  会返回的内容返回，则这里需要重新处理。跳转到登录页面
                	/*if (xhr.responseText.indexOf('window.parent.location.href')) {
                		PageEvent.toLoginPage();
                		return;
					}*/
                	//end by qinguidong 20160615
					Metronic.unblockUI(formId);
					Metronic.alert({
                		type: 'danger', 
                		icon: 'warning', 
                		message: '内部错误...', 
                		container: $(formId), 
                		place: 'prepend'
                	});
				}
			});
		},
		// myth.com-ylm:ajax submit method within form
		ajaxSubmit : function(formId, submitId,callback, dataCb) {

			submitFormId = formId;
			submitButtonId = submitId;
			
			var url = $(submitId).attr("href");
			var formData = dataCb ? dataCb(formId) : $(formId).serializeArray();

			Metronic.blockUI({
                target: formId,
                boxed: true,
                message: '请稍候...'
            });

			if ($(window).width() <= 991 && $('.page-sidebar').hasClass("in")) {
				$('.navbar-toggle').click();
			}
			var targetid;
			if(typeof($(submitId).attr("gotopage"))=="undefined"){
				targetid=$('div[role = "tabpanel"].active').attr("parentid").split('tab_')[1];
			}else{
				targetid='module_'+$(submitId).attr("gotopage");
			}
						
			$.ajax({
				type : "post",
				cache : false,
				url : url,
				data : formData,
				success : function(res) {
					Metronic.unblockUI(formId);
					if (res.sStatus == "true") {
						alert(res.sMessage);
//                    	Metronic.alert({
//                    		type: 'success', 
//                    		icon: 'check', 
//                    		message: res.sMessage, 
//                    		container: $(formId), 
//                    		place: 'prepend'
//                    	});
                    }else if (res.sStatus == "false") {
                    	alert(res.sMessage);
//                    	Metronic.alert({
//                    		type: 'danger', 
//                    		icon: 'warning', 
//                    		message: res.sMessage, 
//                    		container: $(formId), 
//                    		place: 'prepend'
//                    	});
                    }else if (res.length < 200) {
                    	Metronic.alert({
                    		type: 'danger', 
                    		icon: 'warning', 
                    		message: res, 
                    		container: $(formId), 
                    		place: 'prepend'
                    	});
                    } else {
                    	
                    	if( typeof callback == 'function')
                    	{
                    		callback();
                    	}
                    	//session 失效需要重新登录
                    	if(res.indexOf('copyright')> 0){
                    		window.location.href='';
                    		return;
                    	}else{
                    		Metronic.scrollTop();
							console.log("1192");
                    		Layout.addTab({
                    			id: targetid?targetid:tempTabId++,
                    					content: res,
                    					closeCurrent:true,
                    					url: url
                    		});
                    		
                    		Layout.fixContentHeight(); // fix content height
                    		Metronic.initAjax(); // initialize core stuff
                    	}
                    }
				},
				error : function(xhr, ajaxOptions, thrownError) {
					Metronic.unblockUI(formId);
					Metronic.alert({
                		type: 'danger', 
                		icon: 'warning', 
                		message: '内部错误...', 
                		container: $(formId), 
                		place: 'prepend'
                	});
				}
			});
		},
		ajaxSubmitModelToForm : function(formId, submitId,modeId,callback) {
			
			submitFormId = formId;
			submitButtonId = submitId;
			
			var url = $(submitId).attr("href");
			
			Metronic.blockUI({
				target: formId,
				boxed: true,
				message: '请稍候...'
			});
			
			if ($(window).width() <= 991 && $('.page-sidebar').hasClass("in")) {
				$('.navbar-toggle').click();
			}
			var the = $(this);
			$.ajax({
				type : "post",
				cache : false,
				url : url,
				data : $(formId).serializeArray(),
				success : function(res) {
					Metronic.unblockUI(formId);
					if (res.sStatus == "true") {
						alert(res.sMessage);
//                    	Metronic.alert({
//                    		type: 'success', 
//                    		icon: 'check', 
//                    		message: res.sMessage, 
//                    		container: $(formId), 
//                    		place: 'prepend'
//                    	});
					}else if (res.sStatus == "false") {
						alert(res.sMessage);
//                    	Metronic.alert({
//                    		type: 'danger', 
//                    		icon: 'warning', 
//                    		message: res.sMessage, 
//                    		container: $(formId), 
//                    		place: 'prepend'
//                    	});
					}else if (res.length < 200) {
						Metronic.alert({
							type: 'danger', 
							icon: 'warning', 
							message: res, 
							container: $(formId), 
							place: 'prepend'
						});
					} else {
						$(modeId).modal('hide');
						//session 失效需要重新登录
						if(res.indexOf('copyright')> 0){
                    		window.location.href='';
                    		return;
                    	}else{
                    		Metronic.scrollTop();
							console.log("1275");
    						Layout.addTab({
    				            id: the.attr("module_id")?the.attr("module_id"):tempTabId++,
    				            title: the.html(),
    				            content: res,
    				            url: url
    				        });
    						
    						callback();
    						Layout.fixContentHeight(); // fix content height
    						Metronic.initAjax(); // initialize core stuff
                    	}
						
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					Metronic.unblockUI(formId);
					Metronic.alert({
						type: 'danger', 
						icon: 'warning', 
						message: '内部错误...', 
						container: $(formId), 
						place: 'prepend'
					});
				}
			});
		},
		/**
		 * Model中的表单提交,callback为提交成功后回调函数
		 */
		ajaxSubmitModel : function(formId, submitId,callback) {

			var url = $(submitId).attr("href");
			Metronic.blockUI({
                target: formId,
                boxed: true,
                message: '请稍候...'
            });

			if ($(window).width() <= 991 && $('.page-sidebar').hasClass("in")) {
				$('.navbar-toggle').click();
			}
			var the = $(this);
			$.ajax({
				type : "post",
				cache : false,
				url : url,
				data : $(formId).serializeArray(),
				success : function(res) {
					Metronic.unblockUI(formId);
					if(res=='success'){
						callback();
					}else{
						Metronic.alert({
	                		type: 'danger', 
	                		icon: 'warning', 
	                		message: res, 
	                		container: $(formId),
	                		place: 'prepend',
	                		focus: false
	                	});
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
                	//add by qinguidong 20160615 如果session超时  会返回的内容返回，则这里需要重新处理。跳转到登录页面
                	if (xhr.responseText.indexOf('window.parent.location.href')>0) {
                		PageEvent.toLoginPage();
                		return;
					}
                	//end by qinguidong 20160615
					Metronic.unblockUI(formId);
					Metronic.alert({
                		type: 'danger', 
                		icon: 'warning', 
                		message: '内部错误...', 
                		container: $(formId), 
                		place: 'prepend',
	                	focus: false
                	});
				}
			});
		},
		
		ajaxToPage : function(url,callback){
			var the = $(this);
			Metronic.scrollTop();
			Metronic.startPageLoading();
			$.ajax({
				type : "GET",
				cache : false,
				url : url,
				dataType : "html",
				success : function(res) {
					
					if( typeof callback == 'function')
            		{
            			callback();
            		}
					
					//session 失效需要重新登录
					if(res.indexOf('copyright')> 0){
                		window.location.href='';
                		return;
                	}else{
                		Metronic.stopPageLoading();
    					var tabId;
    					var paths=url.split('/');
    					tabId='module_'+paths[paths.length-2];//使用模块名称作为id
						console.log("1383");
    					Layout.addTab({
    			            id: tabId,
    			            title: the.html(),
    			            content: res,
    			            url: url
    			        });
    					Layout.fixContentHeight(); // fix content height
    					Metronic.initAjax(); // initialize core stuff
                	}
					
					
				},
				error : function(xhr, ajaxOptions, thrownError) {
					console.log("1397");
					Layout.addTab({
			            id: the.attr("module_id")?the.attr("module_id"):tempTabId++,
			            title: the.html(),
			            content: res,
			            url: url
			        });
					Metronic.stopPageLoading();
				}
			});
		},
		
		
		addTab: function (opts) {
			
	        var id = 'tab_' + opts.id;
	        if(opts.closeCurrent){
	        	var currentId=$('div[role = "tabpanel"].active').attr("id");
	        	Layout.closeTab(currentId);
	        }
	        $('li[role = "presentation"].active').removeClass('active');
	        $('div[role = "tabpanel"].active').removeClass('active');
	        //如果TAB不存在，创建一个新的TAB
	        if(typeof(opts.title)=="undefined"){
	        	opts.title=moduleMap[id];
	        }
	        if (!$("#" + id)[0]) {
	            //创建新TAB的title

	            var title = $('<li>', {
	                'role': 'presentation',
	                'id': 'tab_' + id,
	                'aria-url':opts.url
	            }).append(
	                $('<a>', {
	                    'href': '#' + id,
	                    'aria-controls': id,
	                    'role': 'tab',
	                    'data-toggle': 'tab'
	                }).html(opts.title)
	            );

	            title.append($('<i>', {'class': 'close-tab glyphicon glyphicon-remove'}));
	            var content = $('<div>', {
	                'class': 'tab-pane',
	                'id': id,
	                'role': 'tabpanel'
	            });

	            //加入TABS
	            $('.page-content .nav-tabs').append(title);
	            $('.page-content > #page-content-body-tabs > .tab-content').append(content);
	        } else {
	            var content = $('#' + id);
	            content.html('');
	        }
	        
	        if(opts.parentid){
	        	$('#'+id).attr("parentid",opts.parentid);
	        }
	        //激活TAB
	        $('#tab_' + id).addClass('active');
	        $('#' + id).addClass('active');
	        $("#"+id).html(opts.content);
	        Layout.dropTab();
	    },
		
		popTab: function (id,e) {
	        $('body').find('#popMenu').remove();
	        var popHtml = $('<ul>', {'aria-controls': id, 'class': 'rightMenu list-group', id: 'popMenu','aria-url':e.attr('aria-url')})
	            .append(
	            '<a href="javascript:void(0);" class="list-group-item" data-right="refresh"><i class="glyphicon glyphicon-refresh"></i> 刷新此标签</a>' +
	            '<a href="javascript:void(0);" class="list-group-item" data-right="remove"><i class="glyphicon glyphicon-remove"></i> 关闭此标签</a>' +
	            '<a href="javascript:void(0);" class="list-group-item" data-right="remove-circle"><i class="glyphicon glyphicon-remove-circle"></i> 关闭其他标签</a>' +
	            '<a href="javascript:void(0);" class="list-group-item" data-right="remove-left"><i class="glyphicon glyphicon-chevron-left"></i> 关闭左侧标签</a>' +
	            '<a href="javascript:void(0);" class="list-group-item" data-right="remove-right"><i class="glyphicon glyphicon-chevron-right"></i> 关闭右侧标签</a>'
	        );

            popHtml.css({
                'top': '30px',
                'left': '0px',
                'fontSize': '13px'
            });
            $('i', popHtml).css({width: '1em'});
            popHtml.appendTo(jQuery(e)).fadeIn('slow');
	        popHtml.mouseleave(function () {
	            $(this).fadeOut('slow');
	        });
	    },
	    closeTab: function (id) {
	    	if (!id) {
	    		id = $("#page-content-body-tabs").find("li[role].active").attr('id');
	    		id = id.substring(4);
	    	}
	    	
	        //如果关闭的是当前激活的TAB，激活他的前一个TAB
	        if (jQuery("#page-content-body-tabs").find("li[role].active").attr('id') === "tab_" + id) {
	            $("#tab_" + id).prev().addClass('active');
	            $("#" + id).prev().addClass('active');
	        }
	        //关闭TAB
	        $("#tab_" + id).remove();
	        $("#" + id).remove();
	        Layout.dropTab();
	    },
	    closeTabAndBack: function (id) {
	    	if (!id) {
	    		id = $("#page-content-body-tabs").find("li[role].active").attr('id');
	    		id = id.substring(4);
	    	}
	    	var parentid = $("#" + id).attr('parentid');
	    	if (parentid && $("#" + parentid).length > 0) {
	    		$("#tab_" + parentid).addClass('active');
	            $("#" + parentid).addClass('active');
	    	} else if (jQuery("#page-content-body-tabs").find("li[role].active").attr('id') === "tab_" + id) {
	            $("#tab_" + id).prev().addClass('active');
	            $("#" + id).prev().addClass('active');
	        }
	        //关闭TAB
	        $("#tab_" + id).remove();
	        $("#" + id).remove();
	        Layout.dropTab();
	    },
	    closeAllTab: function () {
	        $.each(jQuery("#page-content-body-tabs").find('li[id]'), function () {
	            var id = $(this).children('a').attr('aria-controls');
	            $("#tab_" + id).remove();
	            $("#" + id).remove();
	        });
	        jQuery("#page-content-body-tabs").find('li[role = "presentation"]').first().addClass('active');
	        var firstID=jQuery("#page-content-body-tabs").find('li[role = "presentation"]').first().children('a').attr('aria-controls');
	        $('#'+firstID).addClass('active');
	        Layout.dropTab();
	    },
	    dropTab: function () {
	        element = jQuery("#page-content-body-tabs").find('.nav-tabs');
	        //创建下拉标签
	        var dropdown = $('<li>', {
	            'class': 'dropdown pull-right hide tabdrop tab-drop'
	        }).append(
	            $('<a>', {
	                'class': 'dropdown-toggle',
	                'data-toggle': 'dropdown',
	                'href': '#'
	            }).append(
	                $('<i>', {'class': "glyphicon glyphicon-align-justify"})
	            ).append(
	                $('<b>', {'class': 'caret'})
	            )
	        ).append(
	            $('<ul>', {'class': "dropdown-menu"})
	        )

	        //检测是否已增加
	        if (!$('.tabdrop').html()) {
	            dropdown.prependTo(element);
	        } else {
	            dropdown = element.find('.tabdrop');
	        }
	        //检测是否有下拉样式
	        if (element.parent().is('.tabs-below')) {
	            dropdown.addClass('dropup');
	        }
	        var collection = 0;

	        //检查超过一行的标签页
	        element.append(dropdown.find('li'))
	            .find('>li')
	            .not('.tabdrop')
	            .each(function () {
	                if (this.offsetTop > 0 || element.width() - $(this).position().left - $(this).width() < 83) {
	                    dropdown.find('ul.dropdown-menu').append($(this));
	                    collection++;
	                }
	            });

	        //如果有超出的，显示下拉标签
	        if (collection > 0) {
	            dropdown.removeClass('hide');
	            if (dropdown.find('.active').length == 1) {
	                dropdown.addClass('active');
	            } else {
	                dropdown.removeClass('active');
	            }
	        } else {
	            dropdown.addClass('hide');
	        }
	    },
		openTab: function openTab(url, html, module_id) {
            var the = $(this);

            Metronic.scrollTop();
            var pageContent = $('.page-content');
            Metronic.startPageLoading();
            if ($(window).width() <= 991 && $('.page-sidebar').hasClass("in")) {
                $('.navbar-toggle').click();
            }

            //父模块ID
            var parentid=$('div[role = "tabpanel"].active').attr("id");
            var id = module_id ? module_id : tempTabId++;

            $.ajax({
                type : "GET",
                cache : false,
                url : url,
                success : function(res) {
                    Metronic.stopPageLoading();
					console.log("1605");
					Layout.addTab({
						id: id,
						title: html,
						content: res,
						parentid: parentid,
						url: url
					});

                    Layout.fixContentHeight(); // fix content height
                    Metronic.initAjax(); // initialize core stuff
                },
                error : function(xhr, ajaxOptions, thrownError) {
					console.log("1618");
                    Layout.addTab({
                        id: id,
                        title: the.html(),
                        content: getErrorPage(xhr.status),
                        url: url
                    });

                    Metronic.stopPageLoading();
                }
            });
        }
	};

}();