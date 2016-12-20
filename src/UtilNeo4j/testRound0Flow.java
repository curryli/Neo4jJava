package UtilNeo4j;
import java.util.ArrayList;

import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;
 
//tips：如果发现插入的边数笔文件的行数要多了，可以尝试吧文件行数先删除一些，只留几条边然后对比发现问题 

public class testRound0Flow {
	  
	public static void main(String[] args){
		//UtilQuery installer = new UtilQuery(new RestAPIFacade("http://localhost:7474/db/data","neo4j","1234567"));
		UtilQuery.graphDb = new RestAPIFacade("http://localhost:7474/db/data","neo4j","1234567");
		UtilQuery.CleanAllGraph();
		UtilQuery.NumofNodes();
		
		String NodeType1 = "Card"; 
    	ArrayList<String> NodeProperty1 = new ArrayList<String>();
    	UtilQuery.CreateNode("EdgeFile\\round0\\CardDict.txt", NodeType1, NodeProperty1);               //match (p: Card {nid:"1"}) return p;
  
    	
    	String NodeType2 = "Date"; 
    	ArrayList<String> NodeProperty2 = new ArrayList<String>();
    	UtilQuery.CreateNode("EdgeFile\\round0\\DateDicts.txt", NodeType2, NodeProperty2); 
    	
    	String EdgeType = "TRANS"; 
    	ArrayList<String> EdgeProperty = new ArrayList<String>();
    	//EdgeProperty.add("pair");  //边的属性1
    	UtilQuery.CreateEdge("EdgeFile\\round0\\MappedTrans0.txt", EdgeType, EdgeProperty);
 //match (a),(b) where a.nid={nid1} and b.nid={nid2} create (a)-[: TRANS{}]->(b)
    	
    	String EdgeType2 = "Quxian_At"; 
    	ArrayList<String> EdgeProperty2 = new ArrayList<String>();
    	//EdgeProperty2.add("time");  //边的属性1
 
    	UtilQuery.CreateEdge("EdgeFile\\round0\\MappedQuxian0.txt", EdgeType2, EdgeProperty2);
    	
    	String EdgeType3 = "Query_At"; 
    	ArrayList<String> EdgeProperty3 = new ArrayList<String>();
    	//EdgeProperty3.add("time");  //边的属性1
    	UtilQuery.CreateEdge("EdgeFile\\round0\\MappedQuery_0.txt", EdgeType3, EdgeProperty3);
    	
    	UtilQuery.NumofNodes();
		
		System.out.println("All flow done!");
    	
	}

}