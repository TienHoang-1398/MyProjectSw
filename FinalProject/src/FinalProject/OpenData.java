package FinalProject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class OpenData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
	try {
		DocumentBuilder builer = factory.newDocumentBuilder();
		
		StringBuffer pharm_url = new StringBuffer();
		pharm_url.append("http://apis.data.go.kr/B552584/EvCharger/getChargerInfo");
		pharm_url.append("?serviceKey=R1jof6NgKLyzzQUX349d9TdjNCYOlItxebh4QimXxAtbsQPVbj%2FtUZAyysKajJuX3e4z1tEAEmo3BN2a6s3S0Q%3D%3D"
				+ "&numOfRows=999&pageNo=1&dataType=XML");
		
		URL url = new URL(pharm_url.toString());
		 
		BufferedInputStream xmldata = new BufferedInputStream(url.openStream());
		
		Document document = builer.parse(xmldata);
		Element root = document.getDocumentElement();
		System.out.println(root.getTagName());
		NodeList list = root.getElementsByTagName("item");
		System.out.println(list.getLength());
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.print("\n시명 또는 지역 입력(X 종료): ");
			String str = s.nextLine();
			if (str.equals("X"))
				break;
			
			System.out.println("=========================");
			for(int i=0;i<list.getLength();i++) {
			Node node =list.item(i);
			NodeList item_childlist = node.getChildNodes();
				for(int j=0;j<item_childlist.getLength();j++) {
					Node item_child = item_childlist.item(j);
					System.out.print(item_child.getNodeName()+":"+item_child.getTextContent());
					System.out.print(",");
					System.out.println();
				}
			}
		
		}
	
	}catch (ParserConfigurationException e) {
		// TODO: handle exception
		e.printStackTrace();
	}catch (IOException e) {
		// TODO: handle exception
		e.printStackTrace();
	}catch (SAXException e) {
		// TODO: handle exception
		e.printStackTrace();
	}

	}
}

