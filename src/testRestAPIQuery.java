import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;
import org.neo4j.rest.graphdb.query.QueryEngine;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.QueryResult;

public class testRestAPIQuery
{
    public static void main(String args[])
    {
        RestAPI graphDb = new RestAPIFacade("http://localhost:7474/db/data/","neo4j","1234567");
        QueryEngine<Map<String, Object>> engine = new RestCypherQueryEngine(graphDb);
        QueryResult<Map<String, Object>> result = engine.query("start n=node(*) return count(n) as total", Collections.EMPTY_MAP);
        
        
        
        
        Iterator<Map<String, Object>> iterator = result.iterator();
        if (iterator.hasNext())
        {
            Map<String, Object> row = iterator.next();
            System.out.print("Total nodes: " + row.get("total"));
        } 
    }
}