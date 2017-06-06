
import java.util.ArrayList;

import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;

import UtilNeo4j.UtilQuery;
 
//MATCH (n)
//OPTIONAL MATCH (n)-[r]-()
//DELETE n,r

//MATCH (n) RETURN n 

//tips：如果发现插入的边数笔文件的行数要多了，可以尝试吧文件行数先删除一些，只留几条边然后对比发现问题 

public class MultiEvent {
	  
	public static void main(String[] args){
		//UtilQuery installer = new UtilQuery(new RestAPIFacade("http://localhost:7474/db/data","neo4j","1234567"));
	    UtilQuery.graphDb = new RestAPIFacade("http://localhost:7474/db/data","neo4j","1234567");
		UtilQuery.CleanAllGraph();
		UtilQuery.NumofNodes();
		
		String NodeType1 = "Card"; 
    	ArrayList<String> NodeProperty1 = new ArrayList<String>();
    	UtilQuery.CreateNode("EdgeFile\\0701\\MultiEvent\\cardNode.txt", NodeType1, NodeProperty1);               
    
    	String NodeType2 = "Region"; 
    	ArrayList<String> NodeProperty2 = new ArrayList<String>();
    	UtilQuery.CreateNode("EdgeFile\\0701\\MultiEvent\\regionNode.txt", NodeType2, NodeProperty2);
    	 
    	String EdgeType1 = "transfer"; 
    	ArrayList<String> EdgeProperty1 = new ArrayList<String>();
    	EdgeProperty1.add("amount");  //边的属性1
    	EdgeProperty1.add("time");  //边的属性1
    	UtilQuery.CreateEdge("EdgeFile\\0701\\MultiEvent\\transfer.csv", EdgeType1, EdgeProperty1);
      
    	String EdgeType2 = "chaxun"; 
    	ArrayList<String> EdgeProperty2 = new ArrayList<String>();
    	EdgeProperty2.add("amount");  //边的属性1
    	EdgeProperty2.add("time");  //边的属性1
    	UtilQuery.CreateEdge("EdgeFile\\0701\\MultiEvent\\chaxun.csv", EdgeType2, EdgeProperty2);
    	
    	String EdgeType3 = "cunqian"; 
    	ArrayList<String> EdgeProperty3 = new ArrayList<String>();
    	EdgeProperty3.add("amount");  //边的属性1
    	EdgeProperty3.add("time");  //边的属性1
    	UtilQuery.CreateEdge("EdgeFile\\0701\\MultiEvent\\cunqian.csv", EdgeType3, EdgeProperty3);
    	
    	String EdgeType4 = "quxian"; 
    	ArrayList<String> EdgeProperty4 = new ArrayList<String>();
    	EdgeProperty4.add("amount");  //边的属性1
    	EdgeProperty4.add("time");  //边的属性1
    	UtilQuery.CreateEdge("EdgeFile\\0701\\MultiEvent\\quxian.csv", EdgeType4, EdgeProperty4);
    	
    	String EdgeType5 = "xiaofei"; 
    	ArrayList<String> EdgeProperty5 = new ArrayList<String>();
    	EdgeProperty4.add("amount");  //边的属性1
    	EdgeProperty4.add("time");  //边的属性1
    	UtilQuery.CreateEdge("EdgeFile\\0701\\MultiEvent\\xiaofei.csv", EdgeType5, EdgeProperty5);
    	
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