package UtilNeo4j;
import java.util.ArrayList;

import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;
 
 

public class testFlow {
	  
	public static void main(String[] args){
		//UtilQuery installer = new UtilQuery(new RestAPIFacade("http://localhost:7474/db/data","neo4j","1234567"));
		UtilQuery.graphDb = new RestAPIFacade("http://localhost:7474/db/data","neo4j","1234567");
		UtilQuery.CleanAllGraph();
		UtilQuery.NumofNodes();
		
		String NodeType1 = "Card"; 
    	ArrayList<String> NodeProperty1 = new ArrayList<String>();
    	UtilQuery.CreateNode("EdgeFile\\Card.txt", NodeType1, NodeProperty1);
 
    	String NodeType2 = "Location"; 
    	ArrayList<String> NodeProperty2 = new ArrayList<String>();
    	UtilQuery.CreateNode("EdgeFile\\Location.txt", NodeType2, NodeProperty2);
    	
    	String EdgeType = "TRANS"; 
    	ArrayList<String> EdgeProperty = new ArrayList<String>();
    	EdgeProperty.add("rp1");  //边的属性1
    	UtilQuery.CreateEdge("EdgeFile\\trans.txt", EdgeType, EdgeProperty);
    	//match (a),(b) where a.nid={n1} and b.nid={n2} create (a)-[rt:TRANS{rp:{rp}}]->(b)
        //match (a),(b) where a.nid={nid1} and b.nid={nid2} create (a)-[TRANS: TRANS{rp1: {rp1}}]->(b)
    	
    	String EdgeType2 = "Quxian_At"; 
    	ArrayList<String> EdgeProperty2 = new ArrayList<String>();
    	EdgeProperty2.add("amount");  //边的属性1
    	EdgeProperty2.add("count");  //边的属性2
    	UtilQuery.CreateEdge("EdgeFile\\quxian_loc.txt", EdgeType2, EdgeProperty2);
    	
    	UtilQuery.NumofNodes();
		
		System.out.println("All flow done!");
    	
	}

}