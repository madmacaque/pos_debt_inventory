package backend;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class logEverything {


    public void logEverything (String type, String log){
        try{
            //getting log xml file
            File inputFile=new File("data/loggedActions.xml");
            DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
            Document doc=dBuilder.parse(inputFile);

            //log all actions
            Node rootLogs=doc.getFirstChild();

            Element logType=doc.createElement(type);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            logType.setTextContent(log + " on "+ dtf.format(now));
            rootLogs.appendChild(logType);



            //writing the modifications to loggedActions.xml file
            TransformerFactory transformerFactory= TransformerFactory.newInstance();
            Transformer transformer=transformerFactory.newTransformer();
            DOMSource source=new DOMSource(doc);
            StreamResult result= new StreamResult(new File("data/loggedActions.xml"));
            transformer.transform(source, result);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
