import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchInserters;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;


public class batchInsert {
	
	//enum Relationships implements RelationshipType { KNOWS, INLOVE, HAS_CODED, MATRIX };

	public static void main(String [] args) throws IOException{
		BatchInserter inserter = null;
		try
		{  
		    inserter = BatchInserters.inserter( new File("D:\\MyNeo4jFile\\BIdb"));
	
		    Label personLabel = Label.label( "id" );
		    inserter.createDeferredSchemaIndex( personLabel ).on( "name" ).create();

		    Map<String, Object> properties = new HashMap<>();

		    properties.put( "name", "Mattias" );
		    long mattiasNode = inserter.createNode( properties, personLabel );

		    properties.put( "name", "Chris" );
		    long chrisNode = inserter.createNode( properties, personLabel );

		    RelationshipType knows = RelationshipType.withName( "KNOWS" );
		    
		    inserter.createRelationship( mattiasNode, chrisNode, knows, null );
		}
		finally
		{
		    if ( inserter != null )
		    {
		        inserter.shutdown();
		    }
		}
	}
}
