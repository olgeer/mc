/**
 * @license Copyright (c) 2003-2013; CKSource - Frederico Knabben. All rights reserved.
 * For licensing; see LICENSE.html or http=//ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example=
	 config.language = 'zh-cn';
	// config.uiColor = '#AADC6E';
	//config.skin = 'bootstrapck';
	 config.skin = 'office2013';
	 config.filebrowserBrowseUrl = CKEDITOR.basePath+'../ckfinder/ckfinder.html';
	 config.filebrowserImageBrowseUrl = CKEDITOR.basePath+'../ckfinder/ckfinder.html?type=Images';
	 config.filebrowserFlashBrowseUrl = CKEDITOR.basePath+'../ckfinder/ckfinder.html?type=Flash';
	 config.filebrowserUploadUrl = CKEDITOR.basePath+'../ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
	 config.filebrowserImageUploadUrl = CKEDITOR.basePath+'../ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
	 config.filebrowserFlashUploadUrl = CKEDITOR.basePath+'../ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
	 
	 
	 config.toolbar_Announcement=[
	 	['Preview','-'],
		['Cut','Copy','-','Print'],
		['Undo','Redo','-','RemoveFormat'],
		['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		['NumberedList','BulletedList','-','Outdent','Indent','-'],
		['Image','Table','HorizontalRule','PageBreak'],
		['Link','Unlink'],
		'/',
		['Styles','Format','Font','FontSize'],
		['Bold','Italic','Underline','-','Subscript','Superscript','-'],
		['TextColor','BGColor'],
		 ['Maximize', '-']
	 ];
	 
};
