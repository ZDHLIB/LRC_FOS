<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="corpus.*"%>
<%@ include file="/admin/commons/pages/include.jsp"%>
<jsp:useBean id="corpusBean" class="corpus.CorpusBean"></jsp:useBean>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>上传语料</title>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/admin/corpus/js/FormCheck.js">
</script>
		<script type="text/javascript">
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
			document.getElementById("path").value = Folder;
			return Folder;
		}
	} catch (e) {
		alert(e.message);
	}
}
</script>
	</head>

	<body>
		<form action="<%=request.getContextPath()%>/SecondUploadCorpusFloder.do" method="post"  name="corpusUploadForm" id="corpusUploadForm" onSubmit="return(FormCheck())">
			
			<table width="582" border="0">
				<tr>
					<td width="54">
						类别：
					</td>
					<td width="518">
						<label>
							<select name="category">
								<%
									List<CorpusCategory> corpusCategoryList = corpusBean
											.getAllCategory();
									if (corpusCategoryList.size() > 0) {
										for (int i = 0; i < corpusCategoryList.size(); i++) {
											out
													.print("<option value='"
															+ corpusCategoryList.get(i).getType_id()
															+ "'>"
															+ corpusCategoryList.get(i).getType()
															+ "</option>");
										}
									} else {
										out.print("<option>暂无分类</option>");
									}
								%>
							</select>
						</label>
						&nbsp;&nbsp;&nbsp;&nbsp;分类可信度：
						<select name="quality">
							<option selected="selected" value="0">
								无
							</option>
							<option value="1">
								低
							</option>
							<option value="2">
								中
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						语种：
					</td>
					<td>
						<label>
							<select name="language">
								<option selected="selected" value="----">
									----
								</option>
								<%
									List<CorpusLanguage> corpusLanguageList = corpusBean
											.getAllLanguage();
									if (corpusLanguageList.size() > 0) {
										for (int i = 0; i < corpusLanguageList.size(); i++) {
											out
													.print("<option value='"
															+ corpusLanguageList.get(i).getType()
															+ "'>"
															+ corpusLanguageList.get(i).getType()
															+ "</option>");
										}
									} else {
										out.print("<option>暂无分类</option>");
									}
								%>
							</select>
						</label>
					</td>
				</tr>
				<tr>
					<td>
						时间：
					</td>
					<td>
						<label>
							<select name="year">
								<option selected="selected" value="----">
									----
								</option>
								<option value="2012">
									2012
								</option>
								<option value="2011">
									2011
								</option>
								<option value="2010">
									2010
								</option>
								<option value="2009">
									2009
								</option>
								<option value="2008">
									2008
								</option>
								<option value="2007">
									2007
								</option>
								<option value="2006">
									2006
								</option>
								<option value="2005">
									2005
								</option>
								<option value="2004">
									2004
								</option>
								<option value="2003">
									2003
								</option>
								<option value="2002">
									2002
								</option>
								<option value="2001">
									2001
								</option>
								<option value="2000">
									2000
								</option>
							</select>
							年
							<select name="month">
								<option selected="selected" value="----">
									----
								</option>
								<option value="01">
									01
								</option>
								<option value="02">
									02
								</option>
								<option value="03">
									03
								</option>
								<option value="04">
									04
								</option>
								<option value="05">
									05
								</option>
								<option value="06">
									06
								</option>
								<option value="07">
									07
								</option>
								<option value="08">
									08
								</option>
								<option value="09">
									09
								</option>
								<option value="10">
									10
								</option>
								<option value="11">
									11
								</option>
								<option value="12">
									12
								</option>
							</select>
							月
							<select name="day">
								<option selected="selected" value="----">
									----
								</option>
								<option value="01">
									01
								</option>
								<option value="02">
									02
								</option>
								<option value="03">
									03
								</option>
								<option value="04">
									04
								</option>
								<option value="05">
									05
								</option>
								<option value="06">
									06
								</option>
								<option value="07">
									07
								</option>
								<option value="08">
									08
								</option>
								<option value="09">
									09
								</option>
								<option value="10">
									10
								</option>
								<option value="11">
									11
								</option>
								<option value="12">
									12
								</option>
								<option value="13">
									13
								</option>
								<option value="14">
									14
								</option>
								<option value="15">
									15
								</option>
								<option value="16">
									16
								</option>
								<option value="17">
									17
								</option>
								<option value="18">
									18
								</option>
								<option value="19">
									19
								</option>
								<option value="20">
									20
								</option>
								<option value="21">
									21
								</option>
								<option value="22">
									22
								</option>
								<option value="23">
									23
								</option>
								<option value="24">
									24
								</option>
								<option value="25">
									25
								</option>
								<option value="26">
									26
								</option>
								<option value="27">
									27
								</option>
								<option value="28">
									28
								</option>
								<option value="29">
									29
								</option>
								<option value="30">
									30
								</option>
								<option value="31">
									31
								</option>
							</select>
							日
						</label>
					</td>
				</tr>
				<tr>
					<td>
						来源：
					</td>
					<td>
						<input type="radio" name="origin" id="origin" value="网络媒体" checked>
						网络媒体
						</input>
						<label>
							<select name="net">
								<%
									List<CorpusNetMedia> corpusNetMediaList = corpusBean
											.getAllNetMedia();
									if (corpusNetMediaList.size() > 0) {
										for (int i = 0; i < corpusNetMediaList.size(); i++) {
											out
													.print("<option value='"
															+ corpusNetMediaList.get(i).getType()
															+ "'>"
															+ corpusNetMediaList.get(i).getType()
															+ "</option>");
										}
									} else {
										out.print("<option>暂无分类</option>");
									}
								%>
							</select>
						</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="origin" id="origin" value="纸质媒体">
						纸质媒体
						</input>
						<label>
							<select name="paper">
								<%
									List<CorpusPaperMedia> corpusPaperMediaList = corpusBean
											.getAllPaperMedia();
									if (corpusPaperMediaList.size() > 0) {
										for (int i = 0; i < corpusPaperMediaList.size(); i++) {
											out.print("<option value='"
													+ corpusPaperMediaList.get(i).getType() + "'>"
													+ corpusPaperMediaList.get(i).getType()
													+ "</option>");
										}
									} else {
										out.print("<option>暂无分类</option>");
									}
								%>
							</select>
						</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<label>
							<input type="radio" name="origin" id="origin" value="人工语料" />
							人工
							</input>
						</label>
					</td>
				</tr>

				<tr>
					<td>
						语料：
					</td>
					<td>
						<input type="text" name="path" />
						<input type="button" onclick="browseFolder('path')" value="选择文件夹" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 是否已加工
						<select name="tag" id="tag">
							<option selected="selected" value="0">
								否
							</option>
							<option value="1">
								是
							</option>
						</select>
						&nbsp;&nbsp;&nbsp;

					</td>
				</tr>
				<tr>
					<td>
						<label>
							<input type="submit" name="submit" id="submit" value="提交" />
						</label>
					</td>
					<td>
						<label>
							<input type="reset" name="cancel" id="cancel" value="取消" />
						</label>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>