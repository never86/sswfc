<%@ page language="java" import="java.util.*,de.csw.fub.*,org.dom4j.Element"
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

<title>Rule Responder eScientist</title>
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
	font-size: 14px;
	font-family: Arial, Helvetica, sans-serif;
}

body {
	background-color: #ffffff;
}

.STYLE18 {
	font-size: x-large;
	font-family: Georgia, "Times New Roman", Times, serif;
}

.STYLE19 {
	font-family: Arial, Helvetica, sans-serif
}

.STYLE20 {
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
}
-->
</style>
</head>

<body>
	<form name="form1" method="post" action="validation.jsp">
		<table width="1011" border="0" align="center" cellpadding="0"
			cellspacing="0" bgcolor="#FFFFFF">
			<tr>
				<td colspan="2"><table width="100%" height="120">
						<tr>
							<td width="15%"><div align="right">
									<a href="http://www.corporate-semantic-web.de/"><img
										src="logo.jpg" alt="Corporate Semantic Web" width="120"
										border="0"> </a>
								</div></td>
							<td height="70" width="85%">
								<div align="left">
									&nbsp;<a href="index.jsp"><img src="ruleml2011.jpg"
										width="442" height="73"
										border="0"> </a>
								</div>
							</td>

						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="1" colspan="2" bgcolor="#CCCCCC"></td>
			</tr>
			<tr>
				<td width="180" height="300" valign="top"
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
											href="http://localhost:8080/openrdf-workbench/"
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
				<td width="80%" valign="top">

					<div align="right" style="font-size: 10px">
						<br>
						<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="40"><div align="left">
									  <strong>&nbsp;&nbsp;Exception:</strong></div></td>
									</tr>
								</table>
						<table width="90%" border="0" align="center" cellspacing="0"
							bordercolor="#E8AB78" bgcolor="FFFFFF" class="table-4"
							style="border: 1px #000000 solid; border-right: none; border-bottom: none">

							<%
								String id = request.getParameter("id");
								WorkflowManagement wm = new WorkflowManagement();
										Element e = wm.getException(id);
										
										String temp = new WorkflowManagement().formatXml(e.elementText("payload"));
							%>
							
							<tr>
								<td height="30" class="downline-gud1"><div align="right">
										<strong>ID:&nbsp; </strong>
									</div></td>
								<td height="30" class="downline-gud1">&nbsp;&nbsp;<%=e.elementText("id")%></td>
							</tr>
							
							<tr>
								<td width="20%" height="30" class="downline-gud1"><div
										align="right">
										<strong>Conversation ID:&nbsp; </strong>
									</div></td>
								<td width="86%" height="30" class="downline-gud1">&nbsp;&nbsp;<%=e.elementText("cid")%></td>
							</tr>
							
							<tr>
								<td width="20%" height="30" class="downline-gud1"><div
										align="right">
										<strong>Type:&nbsp; </strong>
									</div></td>
								<td width="86%" height="30" class="downline-gud1">&nbsp;&nbsp;<%=e.attributeValue("type")%></td>
							</tr>
							

							<tr>
								<td height="30" class="downline-gud1"><div align="right">
										<strong>Workflow:&nbsp; </strong>
									</div></td>
								<td height="30" class="downline-gud1">&nbsp;&nbsp;<%=e.elementText("workflowName")%></td>
							</tr>
							
							<tr>
								<td height="30" class="downline-gud1"><div align="right">
										<strong>Sender:&nbsp; </strong>
									</div></td>
								<td height="30" class="downline-gud1">&nbsp;&nbsp;<%=e.elementText("sender")%></td>
							</tr>
							

							<tr>
								<td height="30" class="downline-gud1"><div align="right">
										<strong>Occurred at:&nbsp; </strong>
									</div></td>
								<td height="30" class="downline-gud1">&nbsp;&nbsp;<%=e.elementText("receivedTime")%></td>
							</tr>
							
							<tr>
								<td class="downline-gud1"><div align="right">
										<strong>Payload:&nbsp; </strong>
									</div></td>
								<td valign="top" class="downline-gud1"><xmp><%=temp%></xmp></xmp></td>
							</tr>
			
						</table>
						<br>
					</div>
				</td>
			</tr>
			<tr>
				<td height="1" colspan="2" bgcolor="#CCCCCC"></td>
			</tr>
			<tr>
				<td height="90" colspan="2"><div align="center">Supported
						by China Scholarship Council (2010-2014).</div></td>
			</tr>
		</table>
	</form>
</body>
</html>
