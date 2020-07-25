package corona;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class trafficData {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        List<String> PlaceNum = Arrays.asList("A-01", "A-02", "A-03", "A-04", "A-05", "A-06", "A-07", "A-08", "A-09"
        		,"A-10", "A-11", "A-12", "A-13", "A-14", "A-15", "A-16", "A-17", "A-18", "A-19", "A-20", "A-21"
        		, "A-22", "A-23", "A-24", "B-01", "B-02", "B-03", "B-04", "B-05", "B-06", "B-07", "B-08", "B-09"
        		, "B-10", "B-11", "B-12", "B-13", "B-14", "B-15", "B-16", "B-17", "B-18", "B-19", "B-20", "B-21"
        		, "B-22", "B-23", "B-24", "B-25", "B-26", "B-27", "B-28", "B-29", "B-30", "B-31", "B-32", "B-33"
        		, "B-34", "B-35", "B-36", "B-37", "C-01", "C-02", "C-03", "C-04", "C-05", "C-06", "C-07", "C-08"
        		, "C-09", "C-10", "C-11", "C-12", "C-13", "C-14", "C-15", "C-16", "C-17", "C-18", "C-19", "C-20"
        		, "C-21", "D-01", "D-02", "D-03", "D-04", "D-05", "D-06", "D-07", "D-08", "D-09", "D-10", "D-11"
        		, "D-12", "D-13", "D-14", "D-15", "D-16", "D-17", "D-18", "D-19", "D-20", "D-21", "D-22", "D-23"
        		, "D-24", "D-25", "D-26", "D-27", "D-28", "D-29", "D-30", "D-31", "D-32", "D-33", "D-34", "D-35"
        		, "D-36", "D-37", "D-38", "D-39", "D-40", "D-41", "D-42", "D-43", "D-44", "D-45", "F-01", "F-02"
        		, "F-03", "F-04", "F-05", "F-06", "F-07", "F-08", "F-09");
    	
        for(String st : PlaceNum) {
    	StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/766a4a61656767643637547253554c"); /*URL*/
         
        urlBuilder.append("/xml");//dataform
        urlBuilder.append("/VolInfo");//serviceinfo
        urlBuilder.append("/" + 1);//first page
        urlBuilder.append("/" + 1);//last pag
        urlBuilder.append("/" + st);//region code
        urlBuilder.append("/" + 20200724);//date
        urlBuilder.append("/"+ 12 );//time
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
    	BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\buleb\\Desktop\\result1.xml",true));
        PrintWriter pw = new PrintWriter(bw,true);
        String str = sb.toString();
        
	    pw.write(str);
	    
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
	      // child node 가 1개 이상인 경우
	      pw.close();
        }
    }
}