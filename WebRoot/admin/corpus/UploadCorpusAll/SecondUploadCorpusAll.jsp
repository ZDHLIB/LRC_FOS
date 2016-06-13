<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="corpus.*" %>
<%@ include file="/admin/commons/pages/include.jsp" %>
<jsp:useBean id="corpusBean" class="corpus.CorpusBean"></jsp:useBean>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <title>批量上传预料</title>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/admin/corpus/js/FormCheck.js"></script>
   		<script type="text/javascript" src="../../../swfupload.js"></script>
		<script type="text/javascript" src="../../../handler.js"></script>
   		<script type="text/javascript">
			var swfu;
			window.onload = function () {  
				swfu = new SWFUpload({
					button_image_url : "../../../upload.png",//指向图片按钮的位置
					button_placeholder_id : "spanButtonPlaceholder",//该按钮的ID名字
					button_width: 100,//按钮的宽度
					button_height: 18,//按钮的高度
					button_text : '单击选择文件',//按钮中的文字
					flash_url : "../../../swfupload.swf"  ,//指向FLASH文件的位置
					upload_url: "Secondupload",//指向web项目下的名为upload的action
					upload_complete_handler:uploadComplete,//用于处理文件上传结束的事件
					file_dialog_complete_handler : fileDialogComplete,//用于处理选择文件后触发的事件
					file_queued_handler : fileQueued,//用于处理选择文件后触发的事件
					upload_error_handler:uploadError,//用于处理上传失败触发的事件
					upload_success_handler:uploadSuccess//用于处理上传成功触发的事件
					});
				};
				function   window.onbeforeunload(){   
 			 	 window.location.reload(true); 
 			} 

		</script>
  </head>
  
  <body>

    <form action="<%=request.getContextPath()%>/corpus/corpusServlet?method=uploadCourpus" method="post" enctype="multipart/form-data" name="corpusUploadForm" id="corpusUploadForm" onSubmit="return(FormCheck())">
     <center>
	 <br>
	 <br>
	 <br>
	 <br>
	 <br>
	 <br>
	 <br>
	 <tr>
	     <td>语料：</td>
	     <td><input  name="attachment" id="spanButtonPlaceholder"/>
	 </tr>
	 <br>
	 <br>
	 <br>
	 <tr>
	   	 <td><input type="button" name="button" id="button" value="批量上传"onclick="swfu.startUpload()"></td>	
		 <td><input type="reset" name="cancel" id="button" value="重置" onclick="location.replace('SecondUploadCorpusAll.jsp')"></td>
	 </tr>
	 <br>
	 <br>
	 <br>
	 <table style="width:60%; height:10%;" border="5" id="filesTable" style="display:block">
	 <th>name</th>
	 <th>size</th>
	 <th>status</th>
	 </table>
	  </center>
	 </table>
    </form>
  </body>
</html>
