function changeOption(obj) {
        var pames = "?option=" + document.all.agent.value;
        var tourl = "hts.jsp" + pames;
        window.location = tourl;
}

var XMLHttpReq;

function createXMLHttpRequest() {
	if (window.XMLHttpRequest) {
		XMLHttpReq = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			}
			catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				}
				catch (e) {
				}
			}
		}
	}
}

function createHtml() {
	if (document.form1.workflow.value == "") {
		alert("The workflow cannot be null!");
		form1.workflow.focus();
		return;
	}

	var workflow = document.form1.workflow.value;
	createXMLHttpRequest();
	XMLHttpReq.open("GET", "GenerateHTMLServlet?workflow=" + workflow);
	XMLHttpReq.onreadystatechange = processResponse;
	XMLHttpReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	XMLHttpReq.send(null);
}

function processResponse() {
	if (XMLHttpReq.readyState == 4) {
		if (XMLHttpReq.status == 200) {
			document.getElementById("result").innerHTML = XMLHttpReq.responseText;
		} else {
			window.alert(XMLHttpReq.status);
			window.alert("Parsing Error!");
		}
	}
}

function analyse() {
	
	var rulemlIDL = document.form1.rulemlIDL.value;
	var muleAddress = document.form1.mule.value;
	
	createXMLHttpRequest();
	document.form1.submit.disabled=true;
	XMLHttpReq.open("GET", muleAddress + "?payload=" + rulemlIDL, true);
	document.getElementById("result").innerHTML = "Waiting for the results from the workflow engine...";
	XMLHttpReq.onreadystatechange = processAnalyseResponse;
	XMLHttpReq.setRequestHeader("If-Modified-Since","0"); 

	XMLHttpReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	XMLHttpReq.send(null);
}
function processAnalyseResponse() {
	if (XMLHttpReq.readyState == 4) {
		document.form1.submit.disabled=false;
		if (XMLHttpReq.status == 200) {
			document.getElementById("result").innerHTML= XMLHttpReq.responseText;
		} else {
			window.alert("Parsing Error!");
		}
	}
}


