/**
 * 
 */
package de.csw.fub;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import de.csw.fub.util.ProjectUtil;

/**
 * @author never86
 * 
 */
public class WorkflowManagement {

	public static WorkflowManagement INSTANCE = new WorkflowManagement();

	// XML namespace for Reaction RuleML
	public final String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";

	public final String RRML_NS = "http://www.ruleml.org/0.91/xsd";

	public final String SCHEMA_INSTANCE = "http://www.ruleml.org/0.91/xsd http://ibis.in.tum.de/research/ ReactionRuleML/0.2/rr.xsd";

	public List<AbstractWorkflow> getWorkflows() {
		List<AbstractWorkflow> workflows = new ArrayList();
		Document doc = getDocOfWF();
		List<Element> list = doc.getRootElement().elements();
		for (int i = 0; i < list.size(); i++) {
			Element ele = list.get(i);
			AbstractWorkflow wf = new AbstractWorkflow();
			wf.setName(ele.selectSingleNode("Fun").getText());

			wf.setDescription(ele.selectSingleNode("label/Expr/Ind").getText());

			workflows.add(wf);
		}
		return workflows;
	}

	private Document getDocOfWF() {
		try {
			return new SAXReader().read(new File(ProjectUtil.getWFSDBPath()));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String provaInterface2Html(String interfaceName) {
		Element interfaceElement = null;
		interfaceElement = (Element) getDocOfWF().selectSingleNode(
				"//interface[Fun='" + interfaceName + "']");

		if (interfaceElement == null)
			return "Interal Error!";
		String htmlContent = "<table cellpadding=\"0\" cellspacing=\"0\"><tr><td height=\"20\" valign=\"top\"><span class=\"STYLE4\">Workflow: "
				+ interfaceName
				+ ".</span></td></tr><tr><td height=\"20\"><span class=\"STYLE4\" style=\"line-height:1.2\">Description: "
				+ interfaceElement.selectSingleNode("label/Expr/Ind").getText()
				+ "</span></td></tr>";
		List<Element> list = interfaceElement.elements();
		for (int i = 0; i < list.size(); i++) {
			Element ele = list.get(i);
			if (ele.getName().equalsIgnoreCase("Expr"))
				htmlContent += exprElementToHtml(ele, "&nbsp;&nbsp;");
			else if (ele.getName().equalsIgnoreCase("Var"))
				htmlContent += indElementToHtml(ele, "&nbsp;&nbsp;");
		}

		htmlContent += "</table>";
		return htmlContent;
	}

	private String exprElementToHtml(Element element, String space) {
		String exprContent = space
				+ "<tr><td><table width=\"100%\"><tr><td  height=\"20\">"
				+ element.element("Fun").attributeValue("meta") + "</td></tr>";
		List<Element> list = element.elements();
		for (int i = 0; i < list.size(); i++) {
			Element ele = list.get(i);
			if (ele.getName().equalsIgnoreCase("Expr"))
				exprContent += exprElementToHtml(ele, space
						+ "&nbsp;&nbsp;");
			if (ele.getName().equalsIgnoreCase("Var"))
				exprContent += indElementToHtml(ele, space
						+ "&nbsp;&nbsp;");
		}
		exprContent += "</table></td></tr>";
		return exprContent;
	}

	private String indElementToHtml(Element ele, String space) {
		String indContent = "<tr><td height=\"20\">" + space;
		String value = "";
		if (ele.attribute("default") != null)
			value = ele.attributeValue("default");
		if (ele.attribute("candiates") == null) {
			indContent += ele.attributeValue("name").trim()
					+ ":&nbsp;&nbsp;<input class=\"input1\"  size=\"48\" type=\"text\" name=\""
					+ ele.attributeValue("name").trim() + "\" value=\"" + value
					+ "\"/>&nbsp;&nbsp;<span class=\"STYLE4\">("
					+ ele.attributeValue("meta") + ")</span>";
		} else {
			indContent += ele.getText().trim() + ":&nbsp;&nbsp;";
			indContent += "<select  class=\"input1\" name=\""
					+ ele.attributeValue("name").trim() + "\">";
			String[] candidates = ele.attributeValue("candiates").split(";");
			for (int i = 0; i < candidates.length; i++) {
				if (candidates[i].equals(ele.attributeValue("default"))) {
					if (candidates[i].indexOf(":") == -1)
						indContent += "<option value=\"" + candidates[i]
								+ "\" selected=\"selected\">" + candidates[i]
								+ "</option>";
					else {
						String[] temps = candidates[i].split(":");
						indContent += "<option value=\"" + temps[1]
								+ "\" selected=\"selected\">" + temps[0]
								+ "</option>";
					}
				} else {
					if (candidates[i].indexOf(":") == -1)
						indContent += "<option value=\"" + candidates[i]
								+ "\">" + candidates[i] + "</option>";
					else {
						String[] temps = candidates[i].split(":");
						indContent += "<option value=\"" + temps[1] + "\">"
								+ temps[0] + "</option>";
					}
				}
			}
			indContent += "</select>&nbsp;&nbsp;<span class=\"STYLE4\">("
					+ ele.attributeValue("meta") + ")</span>";
		}
		indContent += "</td></tr>";
		return indContent;
	}

	public String toRRuleML(HttpServletRequest request) {
		Document doc = getDocOfWF();

		Element interfaceElement = (Element) doc
				.selectSingleNode("//interface[Fun='"
						+ request.getParameter("workflow") + "']");

		List<Element> inputs = interfaceElement
				.selectNodes(".//Var[@mode='+']");

		for (int i = 0; i < inputs.size(); i++) {
			Element ind = inputs.get(i);
			ind.setText(request.getParameter(ind.attributeValue("name").trim()));
		}

		return formatXml(interfaceElement.asXML());

	}

	public String formatXml(Document doc) {
		StringWriter out = null;
		try {
			out = new StringWriter();
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(doc);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out.toString();

	}

	public String formatXml(String str) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(str);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = formatXml(doc);
		return s;
	}

	public List getExceptions() {
		Document exceptionDoc = null;
		try {
			exceptionDoc = new SAXReader().read(new File(ProjectUtil
					.getExceptionsPath()));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Element root = exceptionDoc.getRootElement();
		return root.elements();
	}
	
	
	public String userInput2Html(String interfaceDesc) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(interfaceDesc);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element interfaceElement = doc.getRootElement();
		String htmlContent = "<table cellpadding=\"3\" cellspacing=\"3\"  bgcolor=\"#EAEAEA\" style=\"border: 1px #000000 solid;\" width=\"99%\"><tr><td height=\"20\" valign=\"top\">Workflow: "
				+ interfaceElement.elementText("Fun")
				+ "</td></tr>";
		List<Element> list = interfaceElement.elements();
		for (int i = 1; i < list.size(); i++) {
			Element ele = list.get(i);
			if (ele.getName().equalsIgnoreCase("Expr"))
				htmlContent += exprElementToHtml1(ele, "&nbsp;&nbsp;");
			else if (ele.getName().equalsIgnoreCase("Var"))
				htmlContent += indElementToHtml1(ele, "&nbsp;&nbsp;");
		}

		htmlContent += "</table>";
		return htmlContent;
	}

	private String exprElementToHtml1(Element element, String space) {
		String exprContent = space
				+ "<tr><td><table width=\"100%\"><tr><td  height=\"20\">"
				+ element.element("Fun").attributeValue("meta") + ":</td></tr>";
		List<Element> list = element.elements();
		for (int i = 0; i < list.size(); i++) {
			Element ele = list.get(i);
			if (ele.getName().equalsIgnoreCase("Expr"))
				exprContent += exprElementToHtml1(ele, space
						+ "&nbsp;&nbsp;");
			if (ele.getName().equalsIgnoreCase("Var"))
				exprContent += indElementToHtml1(ele, space
						+ "&nbsp;&nbsp;");
		}
		exprContent += "</table></td></tr>";
		return exprContent;
	}

	private String indElementToHtml1(Element ele, String space) {
		String indContent = "<tr><td height=\"20\">" + space;
		String value = "";
		if (ele.attribute("default") != null)
			value = ele.attributeValue("default");
			indContent += ele.attributeValue("name").trim()
					+ ":&nbsp;&nbsp;" + value;
		indContent += "</td></tr>";
		return indContent;
	}

}
