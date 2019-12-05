package com.readjava.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetXML {

    //打印表名
    public static String getDataBase(Connection conn ){
        DatabaseMetaData dbm = null;
        String dataBase = null;
        try {
            dbm = conn.getMetaData();
            ResultSet rs = dbm.getTables(null, null, "%student", null);
            while(rs.next()){
                //System.out.println(rs.getString(3)); //打印表名
                dataBase = rs.getString(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataBase;
    }


    public static List get() {
        List result = new ArrayList();
        //为什么下面这个注释会导致一直是第二个数据库的属性呢
        // 必须要在for循环里面，才不会才成引用变更的情况。因为String，多次之后，line里面只有第二个数据库的信息
        //String line[]= new String[6];
        StringBuilder mStringBuilder = new StringBuilder();
        try {
            // 创建文档构建器工厂(采用单例模式)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // 创建文档构建器
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 由xml文件创建文档对象
            org.w3c.dom.Document document = builder.parse("F:\\repositories\\servlet-crud\\property.xml");
            // 获得根节点
            org.w3c.dom.Element root = document.getDocumentElement();
            // 根节点名称
            String root_name = root.getNodeName();
            //System.out.println(root_name);
            mStringBuilder.append("<" + root_name + ">\n");
            // 获得一级子节点列表
            org.w3c.dom.NodeList first_level_list = root.getChildNodes();
            //System.out.println(first_level_list);
            for (int i = 0; i < first_level_list.getLength(); i++) {
                String line[]= new String[6];
                org.w3c.dom.Node first_level_node = first_level_list.item(i);
                //System.out.println(first_level_list.item(i));
                if (first_level_node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    // 判断一级子节点是否有属性
                    if (first_level_node.hasAttributes()) {
                        mStringBuilder.append("  <" + first_level_node.getNodeName() + " "
                                + first_level_node.getAttributes().item(0) + ">\n");
                        line[i] = first_level_node.getTextContent();
                    } else {
                        mStringBuilder.append("  <" + first_level_node.getNodeName() + ">\n");
                    }
                    // 获得二级子节点列表
                    org.w3c.dom.NodeList second_level_list = first_level_node.getChildNodes();
                    for (int j = 0; j < second_level_list.getLength(); j++) {
                        org.w3c.dom.Node second_level_node = second_level_list.item(j);
                        //System.out.println(second_level_node);
                        if (second_level_node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                            // 判断二级子节点是否有属性
                            if (second_level_node.hasAttributes()) {
                                mStringBuilder.append("    <" + second_level_node.getNodeName() + " "
                                        + second_level_node.getAttributes().item(0) + ">"
                                        + second_level_node.getTextContent() + "</" + second_level_node.getNodeName()
                                        + ">\n");
                                line[j] = second_level_node.getTextContent();
                                System.out.println(line[j]);
                            } else {
                                mStringBuilder.append("    <" + second_level_node.getNodeName() + ">"
                                        + second_level_node.getTextContent() + "</" + second_level_node.getNodeName()
                                        + ">\n");
                                line[j] = second_level_node.getTextContent();
                                // System.out.println(line[j]);
                            }
                        }
                    }
                    mStringBuilder.append("  </" + first_level_node.getNodeName() + ">\n");
                    result.add(line);
                }
            }
            mStringBuilder.append("</" + root_name + ">");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
