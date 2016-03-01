/**
 * Created by jianhuizhu on 2016-02-29.
 */
public class DatabasePerformanceSimpleTester {
    public static void main(String[]args){
        MySQLVer mySQLVer=new MySQLVer();

        MongoDBVer mongoDBVer=new MongoDBVer();
        long before=System.currentTimeMillis();
        mySQLVer.getResults(1);
        long after=System.currentTimeMillis();
        System.out.println("MySQL Execution time QUERY: "+(after-before)+" milliseconds");
        before=System.currentTimeMillis();
        mongoDBVer.getRecords(1);
        after=System.currentTimeMillis();
        System.out.println("MongoDB Execution time QUERY: "+(after-before)+" milliseconds");
    }
}
