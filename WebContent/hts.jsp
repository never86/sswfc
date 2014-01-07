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
.STYLE18 {
	font-size: x-large;
	font-family: Georgia, "Times New Roman", Times, serif;
}
.STYLE20 {font-family: Arial, Helvetica, sans-serif; font-weight: bold; }
-->
</style>
</head>

	<body>
	<form name="form1" method="post">
    <table width="1011" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#000000" bgcolor="#FFFFFF">
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
	  </tr>
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
              <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;<img src="item.jpg" height="15" >&nbsp;&nbsp;<a href="http://grid.lzu.edu.cn:6060/openrdf-workbench/" class="l-blue1">Resources</a></div>
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
              <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;<img src="item.jpg" height="15" >&nbsp;&nbsp;<a href="http://yawl.imp.fu-berlin.de/sciworkflows/" class="l-blue1">About Project</a> </div>
            </div></td>
          </tr>
        </table>
        <br><br><br></td>
        <td width="746" align="center" valign="top">
	
<br>
<br>
<table width="90%" border="0" align="center">
  <tr>
    <td><strong>Human Task Classification</strong>:
						&nbsp;&nbsp;&nbsp;
                        <select name="agent" class="input1"
							ONCHANGE="changeOption(this)">
                          <%
						int option = 0;
								String op = request.getParameter("option");
								if(op!=null)
								option = Integer.parseInt(op);
						String[] options = {"All tasks","Completed tasks","Incompleted tasks","All exceptions","Solved Exceptions","Pending Exceptions","Human tasks","Completed human tasks","Incompleted human tasks"}; 
						for(int i = 0; i<options.length;i++)
						if(i==option){
						%>
                          <option value="<%=i%>" selected><%=options[i]%></option>
                          <%}
							else{
							%>
                          <option value="<%=i%>"><%=options[i]%></option>
                          <%}%>
                        </select>
</td>
  </tr>
</table>
<br><br>


<table width="98%" border="0" align="center" cellpadding="3"
				cellspacing="0" bordercolor="#E8AB78"  bgcolor="FFFFFF" class="table-4"
				style="border: 1px #000000 solid; border-right: none; border-bottom: none">
			

				<tr class="table-wei3">
				  <td height="30" class="downline-gud1"><div align="center"><strong>XID</strong></div></td>
							<td   height="30" class="downline-gud1"><div align="center"><strong>Type</strong></div></td>
							<td  height="30" class="downline-gud1"><div align="center"><strong>Sender</strong></div></td>
							<td  height="30" class="downline-gud1"><div align="center"><strong>Workflow</strong></div></td>
							<td  height="30" class="downline-gud1"><div align="center"><strong>Received at </strong></div></td>
							<td height="30" class="downline-gud1"><div align="center"><strong>State</strong></div></td>
							<td  height="30" class="downline-gud1"><div align="center"><strong>Solved at </strong></div></td>
							<td  height="30" class="downline-gud1"><div align="center"><strong>Solver</strong></div></td>
		  </tr>
							
							
				<%
								
				HumanAgent ha = new HumanAgent();
								List list = new ArrayList();
								
								switch (option) {
			case 0:  list = ha.getAllTasks();
                     break;
            case 1:  list = ha.getAllCompletedTasks();
                     break;
            case 2:  list = ha.getAllIncompletedTasks();
                     break;
            case 3:  list = ha.getAllExceptions();
                     break;
            case 4:  list = ha.getSolvedExceptions();
                     break;
            case 5:  list = ha.getUnsolvedExceptions();
                     break;
            case 6:  list = ha.getAllHumanTasks();
                     break;
            case 7:  list = ha.getCompletedHumanTasks();
                     break;
            case 8:  list = ha.getIncompletedHumanTasks();
                     break;
            default: list = ha.getAllTasks();
                     break;
        }
								for (int i = 0; i < list.size(); i++) {
									Task task = (Task)list.get(i);
							%>
							<tr>
							<td height="38" class="downline-gud1"><div align="center"><a href="solution.jsp?id=<%=task.getId()%>"><%=task.getCid()%></a></div></td>
							<td height="38" class="downline-gud1"><div align="center"><%=task.getType()%></div></td>
							<td height="38" class="downline-gud1"><div align="center"><%=task.getSender()%></div></td>
							<td height="38" class="downline-gud1"><div align="center"><%=task.getWorkflowName()%></div></td>
							<td height="38" class="downline-gud1"><div align="center"><%=task.getReceivedTime()%></div></td>
							<td height="38" class="downline-gud1"><div align="center"><%=task.getState()%></div></td>
							<td height="38" class="downline-gud1"><div align="center"><%=task.getResolvedTime()%></div></td>
							<td height="38" class="downline-gud1"><div align="center"><%=task.getActualSolver()%></div></td>
							</tr>
							
							<%}%>
      </table>
       </td>
      </tr>
       <tr><td height="1" colspan="2" bgcolor="#CCCCCC"></td></tr>
      <tr>
        <td height="90" colspan="2"><div align="center">Supported by China Scholarship Council (2010-2014).</div></td>
      </tr>
    </table>
 </form>
    </body>
</html>
