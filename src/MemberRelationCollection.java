import java.util.Random;

/**
 * Created by jianhuizhu on 2016-03-01.
 */
public  final class MemberRelationCollection {
    public static  final String[] relations={"\"private coach\"","\"same interest\"","\"leader in club\"" +
            ""};
    public static String getRandomRelation(){
        Random random=new Random(System.currentTimeMillis());
        return relations[Math.abs(random.nextInt(3))];
    }
}
