package corona;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class subwayData {
    public static void subway(String[] args) throws ParserConfigurationException, SAXException, IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/6850736555676764393646774a4b74"); /*URL*/
         
        urlBuilder.append("/xml");//dataform
        urlBuilder.append("/CardSubwayStatsNew");//serviceinfo
        urlBuilder.append("/" + 1);//first page
        urlBuilder.append("/" + 3);//last page
        urlBuilder.append("/" + 20200721);//date
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        
        OutputStream output = new FileOutputStream("C:\\Users\\buleb\\Desktop\\result1.xml");
        String str = sb.toString();
        byte[] by=str.getBytes();
	    output.write(by);
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    InputSource is = new InputSource(new StringReader(str));
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
	    Document document = documentBuilder.parse(is);
	    
	    Element element = document.getDocumentElement();
	    
	    NodeList n_list = element.getElementsByTagName("row");
	    Element el = null;
	    NodeList sub_n_list = null; //sub_n_list
	    Element sub_el = null; //sub_el
	    Node v_txt = null;
        String value="";
         
        String[] tagList = {"spot_num", "ymd", "hh","vol"};
        for(int i=0; i<n_list.getLength(); i++) {
            el = (Element) n_list.item(i);
            for(int k=0; k< tagList.length; k++) {
              sub_n_list = el.getElementsByTagName(tagList[k]);
              for(int j=0; j<sub_n_list.getLength(); j++) {
                sub_el = (Element) sub_n_list.item(j);
                v_txt = sub_el.getFirstChild();
                value = v_txt.getNodeValue();
                 
                System.out.println(sub_el.getNodeName() + "::::value="+value);
                if(sub_el.getAttributes().getNamedItem("id")!=null)
                System.out.println("id="+ sub_el.getAttributes().getNamedItem("id").getNodeValue() );
              }
            }
          }
	          System.out.println();
	        }
	      }
    }
}