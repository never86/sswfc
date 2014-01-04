<%@ page language="java" import="java.util.*,de.csw.fub.*"
	contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>Semantic Scientific Workflows</title>
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
.STYLE20 {font-family: Arial, Helvetica, sans-serif; font-weight: bold; }
.STYLE21 {font-size: 14px}
-->
</style>
</head>

	<body>

    <table width="1011" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td colspan="2"><table width="100%" height="120" >
          <tr>
		  <td  width="15%" ><div align="right"> <a href="http://www.corporate-semantic-web.de/"><img
						src="logo.jpg" alt="Semantic Scientific Workflow" width="118" border="0"> </a> </div></td>
            <td height="70"  width="85%"  >
              <div align="left"> &nbsp;<a href="index.jsp"><img
							src="ruleml2011.jpg" alt="Corporate Semantic Web" width="442" height="73" border="0"> </a>  </div></td>
          </tr>
        </table></td>
      </tr>
	   <tr><td height="1" colspan="2" bgcolor="#CCCCCC"></td></tr>
      <tr>
        <td width="180" height="300" valign="top" style="border: 1px #CCCCCC solid; border-left: none; border-top: none; border-bottom: none"><br>
          <table width="90%" border="0" align="right">
          <tr>
            <td height="40" class="downline"><div align="center" class="STYLE20">
              <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;<img src="item.jpg" height="15" >&nbsp;&nbsp;<a href="index.jsp" class="l-blue1">Home</a></div>
            </div></td>
          </tr>
          <tr>
            <td height="40" class="downline"><div align="center" class="STYLE20">
              <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;<img src="item.jpg" height="15" >&nbsp;&nbsp;<a href="client.jsp" class="l-blue1">Submission</a></div>
            </div></td>
          </tr>
          <tr>
            <td height="40" class="downline"><div align="center" class="STYLE20">
              <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;<img src="item.jpg" height="15" >&nbsp;&nbsp;<a href="http://grid.lzu.edu.cn:6060/sswf-3.0.owl" class="l-blue1">Resources</a></div>
            </div></td>
          </tr>
          <tr>
            <td height="40" class="downline"><div align="center" class="STYLE20">
              <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;<img src="item.jpg" height="15" >&nbsp;&nbsp;<a href="hts.jsp" class="l-blue1">Human Tasks</a></div>
            </div></td>
          </tr>
          <tr>
            <td height="40" class="downline"><div align="center" class="STYLE20">
              <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;<img src="item.jpg" height="15" >&nbsp;&nbsp;<a href="exceptions.jsp" class="l-blue1">Exceptions</a></div>
            </div></td>
          </tr>
          <tr>
            <td height="40" class="downline"><div align="center" class="STYLE20">
              <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;<img src="item.jpg" height="15" >&nbsp;&nbsp;<a href="http://yawl.imp.fu-berlin.de/sciworkflows/" class="l-blue1">Project</a> </div>
            </div></td>
          </tr>
        </table>
        <br><br><br></td>
        <td width="746" valign="top">
		<form name="form1" method="post" action="rruleml.jsp">
		<br>
		<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="82%" height="20" valign="middle">&nbsp;<strong><span class="STYLE21">Available workflows</span>:&nbsp;&nbsp; </strong> 
              <select name="workflow" class="input1" ONCHANGE="createHtml()">
							<option selected>
								Please select a workflow							</option>
							<%
								List<AbstractWorkflow> workflows = WorkflowManagement.INSTANCE
										.getWorkflows();
								for (int j = 0; j < workflows.size(); j++) {
							%>
							<option value='<%=workflows.get(j).getName()%>'><%=workflows.get(j).getName()%></option>
							<%
								}
							%>
		    </select></td>
          </tr>
          <tr>
            <td valign="top">&nbsp;&nbsp;<span id="result"></span><br></td>
          </tr>
        </table>
		</form></td>
      </tr>
       <tr><td height="1" colspan="2" bgcolor="#CCCCCC"></td></tr>
      <tr>
        <td height="90" colspan="2"><div align="center" class="STYLE21">Supported by China Scholarship Council (2010-2014).</div></td>
      </tr>
    </table>

    </body>
</html>
