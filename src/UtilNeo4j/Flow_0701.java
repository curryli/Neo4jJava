package UtilNeo4j;
import java.util.ArrayList;

import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;
 
//tips：如果发现插入的边数笔文件的行数要多了，可以尝试吧文件行数先删除一些，只留几条边然后对比发现问题 

public class Flow_0701 {
	  
	public static void main(String[] args){
		//UtilQuery installer = new UtilQuery(new RestAPIFacade("http://localhost:7474/db/data","neo4j","1234567"));
		UtilQuery.graphDb = new RestAPIFacade("http://localhost:7474/db/data","neo4j","1234567");
		UtilQuery.CleanAllGraph();
		UtilQuery.NumofNodes();
		
		String NodeType1 = "Card"; 
    	ArrayList<String> NodeProperty1 = new ArrayList<String>();
    	UtilQuery.CreateNode("EdgeFile\\0701\\cardNode.txt", NodeType1, NodeProperty1);               
    
    	String NodeType2 = "Region"; 
    	ArrayList<String> NodeProperty2 = new ArrayList<String>();
    	UtilQuery.CreateNode("EdgeFile\\0701\\regionNode.txt", NodeType2, NodeProperty2);
    	
    	String NodeType3 = "Hour"; 
    	ArrayList<String> NodeProperty3 = new ArrayList<String>();
    	UtilQuery.CreateNode("EdgeFile\\0701\\hourNode.txt", NodeType3, NodeProperty3);
    	
    	String NodeType4 = "trans_id"; 
    	ArrayList<String> NodeProperty4 = new ArrayList<String>();
    	UtilQuery.CreateNode("EdgeFile\\0701\\transidNode.txt", NodeType4, NodeProperty4);
    	
    	
    	String EdgeType2 = "trans_region"; 
    	ArrayList<String> EdgeProperty2 = new ArrayList<String>();
    	EdgeProperty2.add("amount");  //边的属性1
    	EdgeProperty2.add("trans_id");  //边的属性1
    	UtilQuery.CreateEdge("EdgeFile\\0701\\trans_region.txt", EdgeType2, EdgeProperty2);
    	
    	String EdgeType3 = "trans_hour"; 
    	ArrayList<String> EdgeProperty3 = new ArrayList<String>();
    	EdgeProperty3.add("amount");  //边的属性1
    	EdgeProperty3.add("trans_id");  //边的属性1
    	UtilQuery.CreateEdge("EdgeFile\\0701\\trans_hour.txt", EdgeType3, EdgeProperty3);
    	
    	String EdgeType4 = "trans_id"; 
    	ArrayList<String> EdgeProperty4 = new ArrayList<String>();
    	EdgeProperty4.add("amount");  //边的属性1
    	EdgeProperty4.add("hour");  //边的属性1
    	UtilQuery.CreateEdge("EdgeFile\\0701\\trans_transid.txt", EdgeType4, EdgeProperty4);
    	
    	UtilQuery.NumofNodes();
		
		System.out.println("All flow done!");
    	
	}
	
	
	//Cypher
	//match (p: Card {nid:"1"}) return p;
	//match (p: Card) where p.nid =~ '.*1.*'  RETURN p  查找nid属性中带1的所有节点
	//match (p: Card {nid:"2"})-[:TRANS]-(neighbors) RETURN p,neighbors  查找跟2有交易关系的节点   
	//match (p: Card {nid:"2"})-[:TRANS*1..2]-(neighbors) RETURN p,collect(DISTINCT neighbors)     查找跟2有交易关系的节点  二层关系网络
	//match (p: Card {nid:"1"})-[:TRANS*1..3]-(neighbors) RETURN p,collect(DISTINCT neighbors)      查找跟1有交易关系的节点   3层关系网络
    //match p=allShortestPaths((a: Card {nid:"1919"})-[:TRANS*1..3]-(b: Card {nid:"2240"})) RETURN p   3层关系之内两个节点间的最短路劲
}