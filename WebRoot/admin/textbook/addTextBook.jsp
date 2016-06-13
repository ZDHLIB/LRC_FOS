<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <script type="text/javascript" src="<%=request.getContextPath()%>/swfupload/swfupload.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/swfupload/handlers.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
  <body style="font-size: 12px;background-color:#a7c0c5">
   <script type="text/javascript">
			var swfu;
			var allPath = [];
			var finalAllPath;
			var localObj = window.location;
			var contextPath = localObj.pathname.split("/")[1];			
			var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;		
			var server_context=basePath;
			function myUploadFile() {
				var mytext = document.getElementById("mytext").value;
				swfu = new SWFUpload({
					upload_url: server_context + "/textBookServelet?method=addTextBook&mytext="+mytext+"&finalAllPath="+finalAllPath,
					
					// File Upload Settings
					file_size_limit : "50 MB",	// 1000MB
					file_types : "*.*",//设置可上传的类型
					file_types_description : "所有文件",
					file_upload_limit : "999999",
									
					file_queue_error_handler : fileQueueError,//选择文件后出错
					file_dialog_complete_handler : fileDialogComplete,//选择好文件后提交
					file_queued_handler : fileQueued,
					upload_progress_handler : uploadProgress,
					upload_error_handler : uploadError,
					upload_success_handler : uploadSuccess,
					upload_complete_handler : uploadComplete,
	
					// Button Settings
					button_image_url : "<%=request.getContextPath()%>/images/SmallSpyGlassWithTransperancy_17x18.png",
					button_placeholder_id : "spanButtonPlaceholder",
					button_width: 100,
					button_height: 18,
					button_text : '<span class="button">添加附件</span>',
					button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
					button_text_top_padding: 0,
					button_text_left_padding: 18,
					button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
					button_cursor: SWFUpload.CURSOR.HAND,
					
					// Flash Settings
					flash_url : "<%=request.getContextPath()%>/swfupload/swfupload.swf",
	
					custom_settings : {
						upload_target : "divFileProgressContainer"
					},
					// Debug Settings
					debug: false  //是否显示调试窗口
				});
			};
			function startUploadFile(){
				swfu.startUpload();
			}

			function browseFolder(path) {
				try {
					var Message = "\u8bf7\u9009\u62e9\u6587\u4ef6\u5939"; //选择框提示信息 
					var Shell = new ActiveXObject("Shell.Application");
					var Folder = Shell.BrowseForFolder(0, Message, 64, 17);//起始目录为：我的电脑 
					//var Folder = Shell.BrowseForFolder(0,Message,0); //起始目录为：桌面 
					if (Folder != null) {
						Folder = Folder.items(); // 返回 FolderItems 对象 
						Folder = Folder.item(); // 返回 Folderitem 对象 
						Folder = Folder.Path; // 返回路径 
						if (Folder.charAt(Folder.length - 1) != "\\") {
							Folder = Folder + "\\";
						}
						document.getElementById("mytext").value = Folder;
						return Folder;
					}
				} catch (e) {
					alert(e.message);
				}
			};
			
			function ShowFolderFileList(FilePath) {
				var fso, f, fc, sf;
				fso = new ActiveXObject("Scripting.FileSystemObject");
				try {
					f = fso.GetFolder(FilePath);
				} catch (err) {
					alert("文件路径错误或者不存在!!");
					return false;
				}
				
				// 列出所有文件
				fc = new Enumerator(f.files);
			
				var filePath = "";	
				for (; !fc.atEnd(); fc.moveNext()) {
					filePath = fc.item().Path;
					//alert(filePath);
					allPath.push(filePath);
				}
				
				// 循环 递归 读取 文件夹的文件
				sf = new Enumerator(f.SubFolders);
				var folderPath = "";
			
				for (; !sf.atEnd(); sf.moveNext()) {
					folderPath = sf.item().Path;
					ShowFolderFileList(folderPath);
				}
				
			};
			
			function startUpload() {
				var s = document.getElementById("mytext").value;
				ShowFolderFileList(s);
				finalAllPath = allPath.join();
				myUploadFile();
				finaleAllPath = "";
			};
		</script>
		<form>
			<center>
				<h2>录入教材基本信息</h2>
				<br/>
				<br/>
				<br/>
				<input type="text" name="mytext" id="mytext"/>
				<input type="button" onclick="browseFolder('mytext')" value="选择文件夹" />
				<br/>	
				<br/>
				<br/>
				<br/>
				<span id="spanButtonPlaceholder"></span>
				<div id="divFileProgressContainer" style="width:200;display:none;"></div>
				<div id="thumbnails">
					<table id="infoTable" border="0" width="90%" style="border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;margin-top:8px;">
					</table>
				</div>
				<br/>
				<br/>
				<input type="button" name="button1" id="button1" value="确定" onclick="startUpload()"><br/>
			</center>
		</form>
  </body>
</html>
