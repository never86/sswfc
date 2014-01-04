package de.csw.fub;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import com.oreilly.servlet.HttpMessage;

public class ReplyProcessor {
	

	public boolean send(String finalMessage) {
		boolean tag = true;
		URL sender = null;
		try {
			sender = new URL("http://localhost" + ":" + "9992");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpMessage msg = new HttpMessage(sender);
		Properties props = new Properties();

		System.out.println(finalMessage);

		props.put("text", finalMessage);
		try {
			InputStream in = msg.sendGetMessage(props);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tag = false;
		}

		return tag;
	}

	public String  messageGenerator(String id, String sender, String content) {

		String part1 = "<RuleML xmlns=\"http://www.ruleml.org/0.91/xsd\" xsi:schemaLocation=\"http://www.ruleml.org/0.91/xsd http://ibis.in.tum.de/research/ ReactionRuleML/0.2/rr.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> "+
				  "<Message mode=\"outbound\" directive=\"answer\">"+
				  "<oid><Ind>";
		String part2 = part1 + id.trim()+"</Ind></oid>"+"<protocol><Ind>esb</Ind></protocol>"+"<sender><Ind>";
		String part3 = part2 + sender.trim()+"</Ind></sender>"+"<content>";
		return  part3 + content.trim()+"</content></Message>"+"</RuleML>";
	}


}
