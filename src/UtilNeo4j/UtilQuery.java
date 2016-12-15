package UtilNeo4j;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;
import org.neo4j.rest.graphdb.batch.BatchCallback;
import org.neo4j.rest.graphdb.query.QueryEngine;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.QueryResult;



public class UtilQuery
{
	public static RestAPI graphDb = new RestAPIFacade("http://localhost:7474/db/data","neo4j","1234567");
	
	UtilQuery(RestAPI mygraphDb){
		this.graphDb = mygraphDb;
		
	}
	
    public static void main(String args[])
    { 
    	//CleanAllGraph();
    	NumofNodes();
    	
    }
    
    
    public static void CleanAllGraph(){
    	QueryEngine<Map<String, Object>> engine = new RestCypherQueryEngine(graphDb);
        QueryResult<Map<String, Object>> result = engine.query("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r", Collections.EMPTY_MAP);
        Iterator<Map<String, Object>> iterator = result.iterator();
        if (iterator.hasNext())
        {
            Map<String, Object> row = iterator.next();
        } 
        System.out.println("Clean All Graph done.");
    }
    
//    public static void ShowAllGraph(){
//    	QueryEngine<Map<String, Object>> engine = new RestCypherQueryEngine(graphDb);
//        QueryResult<Map<String, Object>> result = engine.query("MATCH (n) RETURN n", Collections.EMPTY_MAP);
//        Iterator<Map<String, Object>> iterator = result.iterator();
//        if (iterator.hasNext())
//        {
//            Map<String, Object> row = iterator.next();
//            System.out.print("Total nodes: " + row.get("total"));
//        } 
//    }
    
    public static void NumofNodes(){
    	QueryEngine<Map<String, Object>> engine = new RestCypherQueryEngine(graphDb);
        QueryResult<Map<String, Object>> result = engine.query("start n=node(*) return count(n) as total", Collections.EMPTY_MAP);
        Iterator<Map<String, Object>> iterator = result.iterator();
        if (iterator.hasNext())
        {
            Map<String, Object> row = iterator.next();
            System.out.println("Total nodes: " + row.get("total"));
        } 
    }
    
  
	public static void CreateNode(String filename, String NodeType, ArrayList<String> NodeProperty){
    	NodeProperty.add("nid");  //主键,  默认将节点号作为第一个属性： 主键nid
        String QueryCmd = "CREATE (";
    	QueryCmd = QueryCmd + NodeType + ": " + NodeType + "{";
    	int count=0;
    	for(String np: NodeProperty){
    		if(count==0)
    		  QueryCmd = QueryCmd + np + ": {" + np + "}";
    		else 
    		  QueryCmd = QueryCmd + ", " + np + ": {" + np + "}";
    		count++;
    	}
    	QueryCmd = QueryCmd + "})"; 
    	System.out.println(QueryCmd);
		
    	LoadFromFile(filename, NodeProperty, QueryCmd);
    	System.out.println("Batch Create Node Done."); 
       } 
	 
	
	public static void CreateEdge(String filename, String EdgeType, ArrayList<String> EdgeProperty){
		ArrayList<String> Property = new ArrayList<String>();
		Property.add("nid1"); //固定，根据节点主键属性在两个节点之间插入边
		Property.add("nid2");
    	for(String rp: EdgeProperty)
    		Property.add(rp);
    	
		String QueryCmd = String.format("match (a),(b) where a.nid={nid1} and b.nid={nid2} create (a)-[");
    	QueryCmd = QueryCmd + EdgeType + ": " + EdgeType + "{";
    	QueryCmd = QueryCmd + Property.get(2) + ": {" + Property.get(2) + "}";
    	int k=3;
    	while(k<Property.size()){
    		  QueryCmd = QueryCmd + ", " + Property.get(k) + ": {" + Property.get(k) + "}";
    		  k++;
    	}
    	
    	QueryCmd = QueryCmd + "}]->(b)"; 
    	System.out.println(QueryCmd);
    	
    	LoadFromFile(filename, Property, QueryCmd);
    	System.out.println("Batch Create Edges Done.");
		 
       } 
	
	
	public static void LoadFromFile(String filename, ArrayList<String> Property, String QueryCmd){
		int counter = 0;
    	List<Map<String, Object>> statements = new ArrayList<>();
     	File csv = new File(filename); 
 
        BufferedReader br= null;
        try{   
    	   br = new BufferedReader(new FileReader(csv));
           String line = "";
    
           while ((line = br.readLine()) != null && line.length()!=0) {      
        	   String mapresult[] = line.split("\\s+", -1);
               Map<String,Object> properties= new HashMap<>();
 
               int i = 0;
               for(String p: Property)
                 properties.put(p, mapresult[i++]);
                
               statements.add(properties);
               
              if (++counter % 10 == 0) {
            	  graphDb.executeBatch(new Process(statements, QueryCmd));
                  statements = new ArrayList<>();
               }   
           }   
           graphDb.executeBatch(new Process(statements, QueryCmd));
             
       } 
       catch (Exception e){e.printStackTrace();} 
       finally {
    	   try { 
                 br.close();
               } catch (Exception e) {    
            	 System.out.println("BR Close Exception");
               } 
               }       
	}
	
	static class Process implements BatchCallback<Object> {   
    	private List<Map<String, Object>> params;
    	private String QUERY = "";
   
    	
    	 Process(final List<Map<String, Object>> params, String Query) {
 	        this.params = params;
 	        this.QUERY = Query;
 	    }

 	    @Override
 	    public Object recordBatch(final RestAPI restApi) {
 	        for (final Map<String, Object> param : params) {
 	            restApi.query(QUERY, param);
 	        }
 	        return null;
 	    }  
    }
    
    

}