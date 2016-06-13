<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="corpus.*"%>
<jsp:useBean id="corpusBean" class="corpus.CorpusBean"></jsp:useBean>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>语料资源检索</title>
		<link href="<%=request.getContextPath()%>/lingdot_files/style.css"
			type="text/css" rel="stylesheet">
		<script type="text/javascript" src="FormCheck.js"></script>
	</head>

	<body>
		<form id="corpusSearchForm" name="corpusSearchForm" method="post"
			action="<%=request.getContextPath()%>/corpus/corpusServlet?method=searchCorpus2" onSubmit="return(FormCheck())">
			<p>&nbsp; 
			</p>
			<p>
				&nbsp;
			</p>
			<p>
				&nbsp;
			</p>
			<table width="653" height="356" border="1" align="center"
				bordercolor="#66CCFF" bgcolor="#cccc33">
				<tr>
					<td width="553">
						<table width="628" border="0">
							<caption align="left">
								<br />
								<center>
									<strong>语料资源检索</strong>
								</center>
								<br />
							</caption>
							<tr>
								<td width="132">
									<div align="right">
										类别：
									</div>
								</td>
								<td width="486">
									<label>
										<select name="category">
											<option selected="selected" value="-1">
												----
											</option>
											<%
												List<CorpusCategory> corpusCategoryList = corpusBean.getAllCategory();
												if (corpusCategoryList.size() > 0) {
													for (int i = 0; i < corpusCategoryList.size(); i++) {
														out.print("<option value='"
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
								</td>
							</tr>
							<tr>
								<td>
									<div align="right">
										语种：
									</div>
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
									<div align="right">
										时间：
									</div>
								</td>
								<td>
									<label>
										<select name="year" id="year">
											<option selected="selected" value="----">
												----
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
										<select name="month" id="month">
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
										<select name="day" id="day">
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
									——
									<label>
										<select name="year2" id="year2">
											<option selected="selected" value="----">
												----
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
										<select name="month2" id="month2">
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
										<select name="day2" id="day2">
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
									<div align="right">
										来源：
									</div>
								</td>
								<td>
									<input type="radio" name="origin" id="origin" value="网络媒体" />
									网络媒体
									<label>
										<select name="net">
											<option selected="selected" value="----">
												----
											</option>
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
									&nbsp;&nbsp;&nbsp; <input type="radio" name="origin" id="origin" value="纸质媒体" />
									纸质媒体
									<label>
										<select name="paper">
											<option selected="selected" value="----">
												----
											</option>
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
									&nbsp;&nbsp;&nbsp;&nbsp;<br>
									<label>
										<input type = "radio" name = "origin" id = "origin" value = "人工语料"/>
										&nbsp;人工</input>
									</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label>
										<input type = "radio" name = "origin" id = "origin" value = "全部语料" checked/>所有来源</input>
									</label>
								</td>
							</tr>
							<tr>
								<td>
									<div align="right">
										标题：
									</div>
								</td>
								<td>
									<label>
										<input name="title" type="text" id="title" size="40"
											maxlength="80" />
									</label>
								</td>
							</tr>
							<tr>
								<td>
									<div align="right">
										作者：
									</div>
								</td>
								<td>
									<label>
										<input type="text" name="author" id="author" />
									</label>
								</td>
							</tr>
							<tr>
								<td>
									<div align="right">
										检索条件：
									</div>	
								</td>					
								<td>
									<labe>
										语料阈值
										<select name="factor1" id="factor1">
											<option selected="selected" value="9">----</option>
											<option value="4">无</option>
											<option value="5">低</option>
											<option value="6">中</option>
											<option value="7">高</option>
											
										</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										
										是否校验
										<select name="factor2" id="factor2">
											<option selected="selected" value="9">----</option>
											<option value="2">否</option>
											<option value="3">是</option>
										
										</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										
										是否加工
										<select name="factor3" id="factor3">
											<option selected="selected" value="9">----</option>
											<option value="0">否</option>
											<option value="1">是</option>
											
										</select>
									</labe>
								</td>
							</tr>
							<br>
							<br>
							<tr>
								<td>
									<div align="right">
										<label>
											<input type="submit" name="submit" id="submit" value="提交" />
										</label>
									</div>
								</td>
								<td>
									<label>
										<input type="reset" name="cancel" id="cancel" value="取消" />
									</label>
								</td>
							</tr>
						</table>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
					</td>
				</tr>
			</table>
			<p>
				&nbsp;
			</p>
			<p>
				&nbsp;
			</p>
		</form>
	</body>
