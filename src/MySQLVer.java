/**
 * Created by jianhuizhu on 2016-02-29.
 */
import java.sql.*;
import java.util.Random;
public class MySQLVer {
    Connection connection=null;
    Random random=new Random(System.currentTimeMillis());
    String memberAndMemberRelation="CREATE TABLE IF NOT EXISTS USER_USER(" +
            "from_id INT NOT NULL ," +
            "to_id INT NOT NULL," +
            "relation VARCHAR(20),"  +
            "PRIMARY KEY(from_id,to_id,relation))ENGINE=InnoDB;";
    public MySQLVer(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
            connection.createStatement().execute("DROP TABLE IF EXISTS USER_USER;");
            connection.createStatement().execute(memberAndMemberRelation);
            long before=System.currentTimeMillis();
            for(int count=0;count<Constant.HUNDRED_THOUSAND;count++){
                connection.createStatement().execute("INSERT INTO USER_USER(from_id,to_id,relation) VALUES ("
                        +Math.abs(random.nextInt(Constant.HUNDRED_THOUSAND))+","+
                        +Math.abs(random.nextInt(Constant.HUNDRED_THOUSAND))+","
                        +MemberRelationCollection.getRandomRelation()
                        + ");");
            }
            long after=System.currentTimeMillis();
            System.out.println("MySQL Execution time (creation): "+(after-before)+" milliseconds");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getResults(int formID){
        ResultSet resultSet=null;
        try {
           resultSet=connection.createStatement().executeQuery("SELECT * FROM USER_USER WHERE from_id="+formID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  resultSet;
    }

}
