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
        QueryResult<Map<String, Object>> result = engine.query("start n=node(*) return count(n) as total ", Collections.EMPTY_MAP);
        
        
        
        
        Iterator<Map<String, Object>> iterator = result.iterator();
        if (iterator.hasNext())
        {
            Map<String, Object> row = iterator.next();
            System.out.print("Total nodes: " + row.get("total"));
        } 
    }
}





//        QueryResult<Map<String, Object>> result = engine.query("LOAD CSV WITH HEADERS FROM \"file:D:\\Neo4jFile\\persons.csv\" AS csvLine CREATE (p:Person { id: toInt(csvLine.id), name: csvLine.name }) ", Collections.EMPTY_MAP);


//
//match (p: Card {nid:"1"}) return p;
//match (p: Card) where p.nid =~ '.*1.*'  RETURN p  查找nid属性中带1的所有节点
//match (p: Card {nid:"2"})-[:TRANS]-(neighbors) RETURN p,neighbors  查找跟2有交易关系的节点   
//match (p: Card {nid:"2"})-[:TRANS*1..2]-(neighbors) RETURN p,collect(DISTINCT neighbors)     查找跟2有交易关系的节点  二层关系网络
//match (p: Card {nid:"1"})-[:TRANS*1..3]-(neighbors) RETURN p,collect(DISTINCT neighbors)      查找跟1有交易关系的节点   3层关系网络
//match p=allShortestPaths((a: Card {nid:"1919"})-[:TRANS*1..3]-(b: Card {nid:"2240"})) RETURN p   3层关系之内两个节点间的最短路劲

//MERGE (m:Movie { title:"Cloud Atlas" }) ON CREATE SET m.released = 2012 RETURN m

//MATCH (m:Movie { title:"Cloud Atlas" })
//MATCH (p:Person { name:"Tom Hanks" })
//MERGE (p)-[r:ACTED_IN]->(m)
//ON CREATE SET r.roles =['Zachry']
//RETURN p,r,m


//CREATE (y:Year { year:2014 })
//MERGE (y)<-[:IN_YEAR]-(m10:Month { month:10 })
//MERGE (y)<-[:IN_YEAR]-(m11:Month { month:11 })
//RETURN y,m10,m11


//MATCH (p:Person)-[r:ACTED_IN]->(m:Movie)
//WHERE p.name =~ "K.+" OR m.released > 2000 OR "Neo" IN r.roles
//RETURN p,r,m

//MATCH (p:Person)-[:ACTED_IN]->(m)
//WHERE NOT (p)-[:DIRECTED]->()
//RETURN p,m


//CREATE INDEX ON :Movie(title)
//DROP INDEX ON :Movie(title)