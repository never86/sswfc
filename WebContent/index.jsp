<%@ page language="java" import="java.util.*,de.csw.fub.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>Human Agent</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">

<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/ClassAll.css" rel="stylesheet" />
<script language="javascript" src="js/humanAgent.js"
	type="text/javascript" charset="gb2312"></script>

<style type="text/css">
<!--
body,td,th {
	font-size: 13px;
	font-family: Arial, Helvetica, sans-serif;
}

body {
	background-color: #ffffff;
}

.STYLE19 {
	font-family: Arial, Helvetica, sans-serif
}

.STYLE20 {
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
}

.STYLE22 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
}

.STYLE23 {
	font-size: 14px
}
-->
</style>
</head>

<body>

	<table width="1011" border="0" align="center" cellpadding="0"
		cellspacing="0" bgcolor="#FFFFFF">
		<tr>
			<td colspan="2"><table width="100%" height="120">
					<tr>
						<td width="15%"><div align="right">
								<a href="http://www.corporate-semantic-web.de/"><img
									src="logo.jpg" alt="Semantic Scientific Workflow" width="118"
									border="0"> </a>
							</div></td>
						<td height="70" width="85%">
							<div align="left">
								&nbsp;<a href="index.jsp"><img src="projectName.gif"
									alt="Corporate Semantic Web" width="442" height="73" border="0">
								</a>
							</div>
						</td>

					</tr>
				</table></td>
		</tr>
		<tr>
			<td height="1" colspan="2" bgcolor="#CCCCCC"></td>
		</tr>
		<tr>
			<td width="180" height="24" valign="top"
				style="border: 1px #CCCCCC solid; border-left: none; border-top: none; border-bottom: none"><br>
				<table width="90%" border="0" align="right">
					<tr>
						<td height="40" class="downline"><div align="center"
								class="STYLE20">
								<div align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;<img src="item.jpg" height="15">&nbsp;&nbsp;<a
										href="index.jsp" class="l-blue1">Home</a>
								</div>
							</div></td>
					</tr>
					<tr>
						<td height="40" class="downline"><div align="center"
								class="STYLE20">
								<div align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;<img src="item.jpg" height="15">&nbsp;&nbsp;<a
										href="client.jsp" class="l-blue1">Submission</a>
								</div>
							</div></td>
					</tr>
					<tr>
						<td height="40" class="downline"><div align="center"
								class="STYLE20">
								<div align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;<img src="item.jpg" height="15">&nbsp;&nbsp;<a
										href="http://grid.lzu.edu.cn:6060/openrdf-workbench/"
										class="l-blue1">Resources</a>
								</div>
							</div></td>
					</tr>
					<tr>
						<td height="40" class="downline"><div align="center"
								class="STYLE20">
								<div align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;<img src="item.jpg" height="15">&nbsp;&nbsp;<a
										href="hts.jsp" class="l-blue1">Human Tasks</a>
								</div>
							</div></td>
					</tr>
					<tr>
						<td height="40" class="downline"><div align="center"
								class="STYLE20">
								<div align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;<img src="item.jpg" height="15">&nbsp;&nbsp;<a
										href="exceptions.jsp" class="l-blue1">Exceptions</a>
								</div>
							</div></td>
					</tr>
					<tr>
						<td height="40" class="downline"><div align="center"
								class="STYLE20">
								<div align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;<img src="item.jpg" height="15">&nbsp;&nbsp;<a
										href="http://yawl.imp.fu-berlin.de/sciworkflows/"
										class="l-blue1">About Project</a>
								</div>
							</div></td>
					</tr>
				</table> <br>
			<br>
			<br></td>
			<td width="746" valign="top"><br>
				<table width="75%" border="0" align="center">
					<tr>
						<td><p align="justify" class="STYLE19 STYLE23"
								style="line-height: 1.5">Scientific workflows accelerate the
								pace of scientific progress in many scientific areas, such as:
								astronomy, ecology, bioinformatics and earth science.
								Scientists can benefit from an explicitly modeled and executed
								workflow not only because it utilizes various resources from
								different administrative domains and automates troublesome
								experimental process, but also automatically captures provenance
								information in detail, which is critical for further
								verification, analysis and new discovery.</p>
							<p align="center" class="STYLE19 STYLE23"
								style="line-height: 1.5">
								<img src="wscf.jpg"
									alt="Weakly-Structured Scientific Workflow Family" width="280">
							</p>
							<p align="justify" class="STYLE22" style="line-height: 1.5">Existing solutions for business workflows as well as scientific workflows mainly focus on the orchestrated and pre-structured execution of compute-intensive and data-oriented tasks. On the contrary, this project explicitly considers  weakly-structured scientific workflows, which contain goal-oriented tasks that have to make agile runtime decisions. They may involve interactions between multiple participants or have complicated logic to express scientific policies and cater to dynamic execution environments. In general, such weakly-structured scientific workflows not only need a rich process and (domain-specific) decision logic specification, but also require a flexible execution and human interaction.</p></td>
					</tr>
				</table>
				<p class="STYLE19">&nbsp;</p></td>
		</tr>
		<tr>
			<td height="1" colspan="2" bgcolor="#CCCCCC"></td>
		</tr>
		<tr>
			<td height="90" colspan="2"><div align="center" class="STYLE23">Supported
					by China Scholarship Council (2010-2014).</div></td>
		</tr>
	</table>

</body>
</html>
