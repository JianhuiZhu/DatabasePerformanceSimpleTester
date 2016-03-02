/**
 * Created by jianhuizhu on 2016-02-29.
 */
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import org.bson.BSON;
import org.bson.Document;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;
import static com.mongodb.client.model.Filters.*;
public class MongoDBVer {
    Random random=new Random(System.currentTimeMillis());
    MongoClient mongoClient = new MongoClient("localhost",27017);
    MongoDatabase db = mongoClient.getDatabase("test");
    public MongoDBVer(){
        //Class.forName("");
        db.getCollection("user_user").drop();
        db.createCollection("user_user");
        db.getCollection("user_user").createIndex(new Document("from_id",1).append("to_id",1).append("relation",1),new IndexOptions().unique(true));
        long before=System.currentTimeMillis();
        for(int count=0;count<Constant.HUNDRED_THOUSAND;count++){
            db.getCollection("user_user").insertOne(
                    new Document()
                    .append("from_id",Math.abs(random.nextInt(Constant.HUNDRED_THOUSAND)))
                    .append("to_id",Math.abs(random.nextInt(Constant.HUNDRED_THOUSAND)))
                    .append("relation",MemberRelationCollection.getRandomRelation()));
        }
        long after=System.currentTimeMillis();
        System.out.println("Mongodb Execution time (creation): "+(after-before)+" milliseconds");
    }
    public FindIterable<Document> getRecords(int fromID){
        return db.getCollection("user_user").find(eq("from_id",fromID));
    }
}
