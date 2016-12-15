package UtilNeo4j;
//neo4j 
//neo4j-rest-graphdb-2.0.1.jar
//jersey-client-1.9.jar 


import java.io.BufferedReader; 
import java.io.File;  
import java.io.FileReader;  
import java.text.SimpleDateFormat; 
import java.util.ArrayList; 
import java.util.Date; 
import java.util.HashMap; 
import java.util.List; 
import java.util.Map;   

import org.neo4j.rest.graphdb.RestAPI; 
import org.neo4j.rest.graphdb.RestAPIFacade; 
import org.neo4j.rest.graphdb.batch.BatchCallback; 

public class testBatchImport { 
    private static final RestAPI restAPI = new RestAPIFacade("http://localhost:7474/db/data","neo4j","1234567");
    
    public static void main(String[] args ){  //System.setProperty("org.neo4j.rest.batch_transaction", "true");
    	String NodeType = "Card"; 
    	ArrayList<String> NodeProperty = new ArrayList<String>();

//    	NodeProperty.add("np1");  //节点属性1
//    	NodeProperty.add("np2");  //节点属性2
    	UtilQuery.CreateNode("EdgeFile\\Card.txt", NodeType, NodeProperty);
    	   
    	//CREATE (Node: Node{nid: {nid}})
    	
    	
    	String EdgeType = "TRANS"; 
    	ArrayList<String> EdgeProperty = new ArrayList<String>();
    	EdgeProperty.add("rp1");  //边的属性1
    	//EdgeProperty.add("rp2");  //边的属性2
    	UtilQuery.CreateEdge("EdgeFile\\staticInOut.txt", EdgeType, EdgeProperty);
    	 
    	
   //match (a),(b) where a.nid={nid1} and b.nid={nid2} create (a)-[TRANS: TRANS{rp1: {rp1}}]->(b) 
    	
    }
    
  
}