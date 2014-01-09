package de.csw.fub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import de.csw.fub.util.ProjectUtil;

/**
 * Servlet implementation class ReceiveExceptionServlet
 */
@WebServlet("/ReceiveExceptionServlet")
public class ReceiveExceptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReceiveExceptionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BufferedReader brd = request.getReader();

		String input = "";
		String message = "";

		while (!input.equals("</RuleML>")) {
			input = brd.readLine();
			message = message + input;
		}

		System.out.println("Received Exception Message: " + message);

		Document doc = null;
		try {
			doc = DocumentHelper.parseText(message);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Element msg = doc.getRootElement().element("Message");

		if (msg.attributeValue("directive").equalsIgnoreCase("inform")) {
			recordException(msg, msg.element("oid").elementText("Ind"));
		} else if (msg.attributeValue("directive").equalsIgnoreCase("request")) {

			Task task = new Task();
			task.setReceivedTime(new Date());

			task.setCid(msg.element("oid").elementText("Ind"));
			task.setId(java.util.UUID.randomUUID().toString());
			task.setSender(msg.element("sender").elementText("Ind"));
			task.setPayload(msg.element("content").asXML());
			task.setType(decideType(msg));
			task.setWorkflowName(msg.element("content").element("Atom")
					.elementText("Ind"));
			try {

				Document htsDoc = new SAXReader().read(new File(ProjectUtil
						.getHTSDBPath()));
				Element root = htsDoc.getRootElement();
				Element htElement = root.addElement("ht");
				htElement.addAttribute("type", task.getType());
				htElement.addElement("cid").setText(task.getCid());
				htElement.addElement("id").setText(task.getId());
				htElement.addElement("receivedTime").setText(
						task.getReceivedTime().toLocaleString());
				htElement.addElement("workflowName").setText(
						task.getWorkflowName());
				htElement.addElement("resolvedTime").setText(
						"3013-01-01 00:00:00");
				htElement.addElement("sender").setText(task.getSender());
				htElement.addElement("payload").setText(task.getPayload());
				htElement.addElement("solution").setText(task.getSolution());
				htElement.addElement("state").setText(task.getState());
				htElement.addElement("actualSolver").setText(
						task.getActualSolver());
				// htElement.addElement("expectedSolver").setText(
				// task.getExpectedSolver());
				write(htsDoc, ProjectUtil.getHTSDBPath());

			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String decideType(Element msg) {
		// TODO Auto-generated method stub
		String rel = msg.element("content").element("Atom").elementText("Rel");
		if (rel.equalsIgnoreCase("noServiceAvailable"))
			return "Exception";
		if (rel.equalsIgnoreCase("noAgentAvailable"))
			return "Exception";
		return "Human Task";
	}

	private void recordException(Element msg, String cid) {
		// TODO Auto-generated method stub

		Document exceptionDoc = null;
		try {
			exceptionDoc = new SAXReader().read(new File(ProjectUtil
					.getExceptionsPath()));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Element root = exceptionDoc.getRootElement();
		Element exceptionEle = root.addElement("exception");
		Element contentElement = msg.element("content");
		exceptionEle.addAttribute("type", contentElement.element("Atom")
				.element("Expr").elementText("Fun"));
		exceptionEle.addElement("id").setText(
				java.util.UUID.randomUUID().toString());
		exceptionEle.addElement("cid").setText(cid);
		exceptionEle.addElement("receivedTime").setText(
				new Date().toLocaleString());
		exceptionEle.addElement("workflowName").setText(
				contentElement.element("Atom").elementText("Rel"));
		exceptionEle.addElement("sender").setText(
				msg.element("sender").elementText("Ind"));
		exceptionEle.addElement("payload").setText(contentElement.asXML());

		write(exceptionDoc, ProjectUtil.getExceptionsPath());

	}

	public static void write(Document document, String filePath) {
		// XMLWriter writer = null;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(filePath), format);
			writer.write(document);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
