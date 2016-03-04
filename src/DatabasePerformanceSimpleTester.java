import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by jianhuizhu on 2016-02-29.
 */
public class DatabasePerformanceSimpleTester {
    public static void main(String[]args){

        Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                MySQLVer mySQLVer=new MySQLVer();
                long before=System.currentTimeMillis();
                mySQLVer.getResults(1);
                subscriber.onNext(before);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.newThread()).toBlocking()
        .subscribe(before -> {
            System.out.println("Mysql Execution time QUERY: "+(System.currentTimeMillis()-before)+" milliseconds");
        });

        Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                MongoDBVer mongoDBVer=new MongoDBVer();
                long before=System.currentTimeMillis();
                mongoDBVer.getRecords(1);
                subscriber.onNext(before);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.newThread()).toBlocking()
                .subscribe(before -> {
                    System.out.println("MongoDB Execution time QUERY: "+(System.currentTimeMillis()-before)+" milliseconds");
                });

        Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                CouchBaseVer couchBaseVer=new CouchBaseVer();
                long before=System.currentTimeMillis();
                couchBaseVer.getRecord(1);
                subscriber.onNext(before);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.newThread()).toBlocking()
                .subscribe(before -> {
                    System.out.println("CouchBase Execution time QUERY: "+(System.currentTimeMillis()-before)+" milliseconds");
                });

    }
}
