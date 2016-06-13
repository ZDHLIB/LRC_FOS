<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="corpus.*"%>
<%@ page import="textBook.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>教材资源检索</title>
		<link href="<%=request.getContextPath()%>/lingdot_files/style.css"
			type="text/css" rel="stylesheet">
		<script type="text/javascript" src="FormCheck.js"></script>
	</head>

	<body>
		<form id="corpusSearchForm" name="corpusSearchForm" method="post"
			action="<%=request.getContextPath()%>/corpus/corpusServlet?method=searchTextDetail&type=1" onSubmit="return(FormCheck())">
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
									<strong>课本基本信息检索</strong>
								</center>
								<br />
							</caption>
							<tr>
								<td width="132">
									<div align="right">
										出版社：
									</div>
								</td>
								<td width="486">
									<label>
										<select name="publisher">
											<option selected="selected" value="-1">
												----
											</option>
											<%
												DealTextBookFunction dealTextDetailPublisher = new DealTextBookFunction();
												List<String> publisherList = new ArrayList<String>();
												publisherList = dealTextDetailPublisher.getTextDetailPublisher();
												if (publisherList.size() > 0) {
													for (int i = 0; i < publisherList.size(); i++) {
														out.print("<option value='"
																		+ publisherList.get(i)
																		+ "'>"
																		+ publisherList.get(i)
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
										小中高：
									</div>
								</td>
								<td>
									<label>
										<select name="period">
											<option selected="selected" value="-1">
												----
											</option>
											<%
												DealTextBookFunction dealTextDetailGrade = new DealTextBookFunction();
												List<String> gradeList = new ArrayList<String>();
												gradeList = dealTextDetailGrade.getTextDetailPeriod();
												if (gradeList.size() > 0) {
													for (int i = 0; i < gradeList.size(); i++) {
														out.print("<option value='"
																		+ gradeList.get(i)
																		+ "'>"
																		+ gradeList.get(i)
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
										册&nbsp; 数：
									</div>
								
								</td>
								<td>
									<label>
										<select name="volume">
											<option selected="selected" value="-1">
												----
											</option>
											<%
												DealTextBookFunction dealTextDetailVolume = new DealTextBookFunction();
												List<String> volumeList = new ArrayList<String>();
												volumeList = dealTextDetailVolume.getTextDetailVolume();
												if (volumeList.size() > 0) {
													for (int i = 0; i < volumeList.size(); i++) {
														out.print("<option value='"
																		+ volumeList.get(i)
																		+ "'>"
																		+ volumeList.get(i)
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
										语&nbsp; 言：
									</div>
								</td>
								<td>									
									<label>
										<select name="language">
											<option selected="selected" value="-1">
												----
											</option>
											<%
												DealTextBookFunction dealTextDetailLanguage = new DealTextBookFunction();
												List<String> languageList = new ArrayList<String>();
												languageList = dealTextDetailLanguage.getTextDetailLanguage();
												if (languageList.size() > 0) {
													for (int i = 0; i < languageList.size(); i++) {
														out.print("<option value='"
																		+ languageList.get(i)
																		+ "'>"
																		+ languageList.get(i)
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
