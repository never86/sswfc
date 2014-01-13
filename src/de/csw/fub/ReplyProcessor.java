package de.csw.fub;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.oreilly.servlet.HttpMessage;

import de.csw.fub.util.ProjectUtil;

public class ReplyProcessor {
	private static Document confDoc = null;

	public boolean send(String finalMessage, String receiver) {
		boolean tag = true;
		URL receiverURL = getReceiverURL(receiver);
		HttpMessage msg = new HttpMessage(receiverURL);
		Properties props = new Properties();

		System.out.println("============Send message to " + receiver + ":"
				+ receiverURL + "===========");
		System.out.println(finalMessage);
		

		props.put("payload", finalMessage);
		try {
			msg.sendGetMessage(props);
		} catch (IOException e) {
			e.printStackTrace();
			System.out
			.println("=======================Sent failed=========================");
			tag = false;
		}
		
		System.out
		.println("=======================Sent successful=========================");

		return tag;
	}

	private URL getReceiverURL(String receiver) {
		String address = "";
		try {
			if (confDoc == null)
				confDoc = new SAXReader().read(new File(ProjectUtil
						.getConfigPath()));
			Element httpEndpoint = (Element) confDoc
					.selectSingleNode("//http-endpoint[@name='" + receiver
							+ "']");
			address = httpEndpoint.attributeValue("address");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		URL receiverURL = null;
		try {
			receiverURL = new URL(address);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return receiverURL;
	}

	 public String  messageGenerator(String id, String content) {

         String part1 = "<RuleML xmlns=\"http://www.ruleml.org/0.91/xsd\" xsi:schemaLocation=\"http://www.ruleml.org/0.91/xsd http://ibis.in.tum.de/research/ ReactionRuleML/0.2/rr.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> "+
                           "<Message mode=\"outbound\" directive=\"answer\">"+
                           "<oid><Ind>";
         String part2 = part1 + id.trim()+"</Ind></oid>"+"<protocol><Ind>esb</Ind></protocol>"+"<sender><Ind>";
         String part3 = part2 + "humanAgent</Ind></sender>"+"<content>";
         return  part3 + content.trim()+"</content></Message>"+"</RuleML>";
 }

}
