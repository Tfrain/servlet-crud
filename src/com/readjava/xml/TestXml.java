package com.readjava.xml;

import com.readjava.util.DBConnection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;


public class TestXml
{
    public static void sqlToXML(String select)
    {
        List result = new ArrayList();

        String line[]= null;
        String[] sql={};
        try{
            DBConnection con=new DBConnection();

            ResultSet rs = null;
            rs=con.sqlFirstUser();
            //获取两个表中的属性值
            for (int j = 0; j < 2; j++) {
                ResultSetMetaData metaData = rs.getMetaData();
                sql = new String[metaData.getColumnCount()];
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    // resultSet数据下标从1开始
                    String columnName = metaData.getColumnName(i + 1);
                    sql[i] = columnName;
                }
                result.add(sql);
                rs=con.sqlSecondUser();
            }
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            Document document=builder.newDocument();//Creat xml document
            document.setXmlVersion("1.0");

            Element root=document.createElement("chart");//rootname of xml document
            document.appendChild(root);//creat root of xml document
            // result.size()
            for(int k=1;k<=result.size();k++)
            {
                root.appendChild(document.createElement("set"));//elementname of xml document
            }
            NodeList nodeList=document.getElementsByTagName("set");
            int size=nodeList.getLength();
            for(int k=0;k<size;k++)
            {
                Node node=nodeList.item(k);
                String[] temp = (String[])result.get(k);
                Element elementNode=(Element)node;
                elementNode.appendChild(document.createElement("id")).appendChild(document.createTextNode(temp[0]));
                elementNode.appendChild(document.createElement("number")).appendChild(document.createTextNode(temp[1]));
                elementNode.appendChild(document.createElement("name")).appendChild(document.createTextNode(temp[2]));
                elementNode.appendChild(document.createElement("password")).appendChild(document.createTextNode(temp[3]));
                elementNode.appendChild(document.createElement("sex")).appendChild(document.createTextNode(temp[4]));
                elementNode.appendChild(document.createElement("database")).appendChild(document.createTextNode(temp[5]));
            }

            TransformerFactory transFactory=TransformerFactory.newInstance();
            Transformer transformer=transFactory.newTransformer();
            DOMSource domSource=new DOMSource(document);

            String name = select + ".xml";
            File file=new File(name);
            FileOutputStream out=new FileOutputStream(file);
            StreamResult xmlResult=new StreamResult(out);
            transformer.transform(domSource,xmlResult);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}