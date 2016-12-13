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

public class CreateRelation { 
    static class Process implements BatchCallback<Object> {   
    	private List<Map<String, Object>> params;
    	private static final String QUERY1 = "CREATE (m:Movie { title:{title},released:{released}})";
    	private static final String QUERY2 = "CREATE (p:Person {name:{name}, born:{born}})";  
    	
    	
    	private static final String QUERY3 = "match (a),(b) where a.name={p1} and b.name={p2} create (a)-[rd:ACTED_IN{rp:{rp}}]->(b)";
    	
//    	private static final String QUERY3 = "merge (p:Person {name:{name_src}}) on create set p.name={name_src}"
//    			+ "merge (p:Person {name:{name_dst}}) on create set p.name={name_dst}"
//    			+ "merge (p)-[r:roles]->(p) on create set r.p1={rp1}";
    	
    	private static final String QUERY4 = "CREATE (p1:Person{name:{name1}})-[rd:DIRECTED{rp:{rp}}]->(p2:Person{name:{name2}})";  
    	
    	 Process(final List<Map<String, Object>> params) {
 	        this.params = params;
 	    }

 	    @Override
 	    public Object recordBatch(final RestAPI restApi) {
 	        for (final Map<String, Object> param : params) {
 	            restApi.query(QUERY3, param);
 	        }
 	        return null;
 	    }  
    }
    
//    public static String getCurDateTime(String ptn) {  Date d = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat(ptn);
//        return sdf.format(d);
//    } 
    
    public static void main(String[] args ){  //System.setProperty("org.neo4j.rest.batch_transaction", "true");
    	final RestAPI restAPI = new RestAPIFacade("http://localhost:7474/db/data","neo4j","1234567");
    	int counter = 0;
    	List<Map<String, Object>> statements = new ArrayList<>();
    	File csv = new File("D:\\Neo4jFile\\LXRFile\\test.txt"); 
       BufferedReader br= null;
       long begintime = System.currentTimeMillis();
//       System.out.print("开始时间:" + getCurDateTime("yyyy-MM-dd hh:mm:ss")+"\n");
       try{   
    	   br = new BufferedReader(new FileReader(csv));
           String line = "";
          //2199660,19713972,WCCGN,65,rdbnhndjjj,VFCQN,YHCRP,QDHIH,QGMOW     
           while ((line = br.readLine()) != null && line.length()!=0) {      
        	   String mapresult[] = line.split(",", -1);
               Map<String,Object> properties= new HashMap<>();
               
               properties.put("p1", mapresult[0]);
               properties.put("rp", mapresult[1]);
               properties.put("p2", mapresult[2]);
               
               statements.add(properties);
              if (++counter % 2000 == 0) {
            	  restAPI.executeBatch(new Process(statements));
                  statements = new ArrayList<>();
               }   
           }   
           restAPI.executeBatch(new Process(statements));
           
           
           
           //System.out.print("总记录条数:" + counter+"\n");
//           System.out.print("完成时间:" + getCurDateTime("yyyy-MM-dd hh:mm:ss")+"\n");
//           System.out.println(" 用时(秒):"+(System.currentTimeMillis() - begintime)/1000+"\n");
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
    }