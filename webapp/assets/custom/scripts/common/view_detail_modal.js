define(function() {
	//监听click事件，刷新DataTable，显示Modal
	var initStudentModal = function(DataTableId) {
        $(DataTableId).off("click", '.toViewStudentDetail').on("click", '.toViewStudentDetail', function() {
            $('body').modalmanager('loading');
            var stroId = $(this).attr('stroId');
            var $modal = $('.studentDetailModal');
            if ($modal.length == 0) {
                $('<div class="modal fade studentDetailModal" tabindex="-1" data-keyboard="false" data-width="960"></div>')
                    .appendTo($('body'));
                $modal = $('.studentDetailModal');
            }
            $modal.load('common/viewdetailmodal/viewStudentDetail?stroId='+stroId, '', function(){
                $modal.modal();
            });
        });
	};

    var initTeacherModal = function(DataTableId) {
        $(DataTableId).off("click", '.toViewTeacherDetail').on("click", '.toViewTeacherDetail', function() {
            $('body').modalmanager('loading');
            var teroId = $(this).attr('teroId');
            var $modal = $('.teacherDetailModal');
            if ($modal.length == 0) {
                $('<div class="modal fade teacherDetailModal" tabindex="-1" data-keyboard="false" data-width="960"></div>')
                    .appendTo($('body'));
                $modal = $('.teacherDetailModal');
            }
            $modal.load('common/viewdetailmodal/viewTeacherDetail?teroId='+teroId, '', function(){
                $modal.modal();
            });
        });
    };

    var initTopicLibraryModal = function(DataTableId) {
        $(DataTableId).off("click", '.toViewTopicLibraryDetail').on("click", '.toViewTopicLibraryDetail', function() {
            $('body').modalmanager('loading');
            var toliId = $(this).attr('toliId');
            var $modal = $('.toViewTopicLibraryDetailModel');
            if ($modal.length == 0) {
                $('<div class="modal fade toViewTopicLibraryDetailModel" tabindex="-1" data-keyboard="false" data-width="960"></div>')
                    .appendTo($('body'));
                $modal = $('.toViewTopicLibraryDetailModel');
            }
            $modal.load('common/viewdetailmodal/viewTopicLibraryDetail?toliId='+toliId, '', function(){
                $modal.modal();
            });
        });
    };
	
	return {
        initStudentModal : function(tableId){
            initStudentModal(tableId);
        },
        initTeacherModal : function(tableId){
            initTeacherModal(tableId);
        },
        initTopicLibraryModal : function(tableId){
            initTopicLibraryModal(tableId);
        }

	};
});