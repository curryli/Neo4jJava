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

public class CreateNode {
    static class Process implements BatchCallback<Object> {

	   // private static final String QUERY = "MERGE (a{main:{val1},prop2:{val2}}) MERGE (b{main:{val3}}) CREATE UNIQUE (a)-[r:relationshipname]-(b);";
	    private static final String QUERY = "CREATE (p:Person { id: {id}, name: {name}}) return p";
	    private List<Map<String, Object>> params;

	    Process(final List<Map<String, Object>> params) {
	        this.params = params;
	    }

	    @Override
	    public Object recordBatch(final RestAPI restApi) {
	        for (final Map<String, Object> param : params) {
	            restApi.query(QUERY, param);
	        }
	        return null;
	    }    
	}
    public static String getCurDateTime(String ptn) {
	Date d = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat(ptn);
	return sdf.format(d);
}
    public static void main(String[] args ){
	//System.setProperty("org.neo4j.rest.batch_transaction", "true");
	final RestAPI restAPI = new RestAPIFacade("http://localhost:7474/db/data","neo4j","1234567");
	int counter = 0;
	List<Map<String, Object>> statements = new ArrayList<>();
	 File csv = new File("D:\\Neo4jFile\\LXRFile\\test.txt"); 
	 BufferedReader br= null;
	 long begintime = System.currentTimeMillis();
	 System.out.print("开始时间:" + getCurDateTime("yyyy-MM-dd hh:mm:ss")+"\n");
	 try{
	 br = new BufferedReader(new FileReader(csv)); 
			String line = "";
			while ((line = br.readLine()) != null && line.length()!=0) { 
				 String mapresult[] = line.split("\\t");
					Map<String,Object> properties= new HashMap<>(); 
					properties.put("id", mapresult[0]);
					properties.put("name", mapresult[1]);
					
				  statements.add(properties)  ;
				 
//				   if (++counter % 2000 == 0) {
//				        restAPI.executeBatch(new Process(statements));
//				        statements = new ArrayList<>();
//				     }
			} 
			restAPI.executeBatch(new Process(statements));
			System.out.print("总记录条数:" + counter+"\n");
			System.out.print("完成时间:" + getCurDateTime("yyyy-MM-dd hh:mm:ss")+"\n");
			System.out.println(" 用时(秒):"+(System.currentTimeMillis() - begintime)/1000+"\n");
	  
	 } catch (Exception e){e.printStackTrace();} finally {try {
	     
			br.close();
		} catch (Exception e) {
			System.out.println("BR Close Exception");
		}
	 }
	
    }
}
