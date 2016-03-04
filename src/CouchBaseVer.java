import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.java.query.Index;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.mongodb.connection.QueryResult;
import rx.Observable;

import java.util.*;
import java.net.URI;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.x;

/**
 * Created by jianhuizhu on 2016-03-02.
 */
public class CouchBaseVer {
    private Random random=new Random(System.currentTimeMillis());
    private static final Bucket bucket = CouchbaseCluster.create(DefaultCouchbaseEnvironment.builder().
            bootstrapCarrierEnabled(false)
            .queryTimeout(100000)
            .kvTimeout(100000).queryPort(11211).build(), Arrays.asList("127.0.0.1")).openBucket("default");
    public CouchBaseVer(){
        Index.buildIndex().on("user_user").indexes("from_id","to_id","relation");
        long before=System.currentTimeMillis();
        for(int count=0;count<Constant.HUNDRED_THOUSAND;count++){
            JsonObject doc=JsonObject.empty()
                    .put("from_id",Math.abs(random.nextInt(Constant.HUNDRED_THOUSAND)))
                    .put("to_id",Math.abs(random.nextInt(Constant.HUNDRED_THOUSAND)))
                    .put("relation",MemberRelationCollection.getRandomRelation());
            bucket.insert(JsonDocument.create(UUID.randomUUID().toString(),doc));
        }
        long after=System.currentTimeMillis();
        System.out.println("CouchBase Execution time (Creation): "+(after-before)+" milliseconds");
    }
    public N1qlQueryResult getRecord(int fromID){
        return bucket.query(select("x").from("user_user").where(x("from_id").eq(fromID)));
    }
}
