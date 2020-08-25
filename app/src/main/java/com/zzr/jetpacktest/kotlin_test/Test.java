package com.zzr.jetpacktest.kotlin_test;

import com.zzr.jetpacktest.bean.Human;
import com.zzr.jetpacktest.bean.Person;
import com.zzr.jetpacktest.bean.Worker1;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        //Java泛型 协变：？ extends  逆变：？super

        List<Human> humans = new ArrayList<>();
        humans.add(new Human());

        List<Worker1> workers = new ArrayList<>();
        workers.add(new Worker1());

        setWorker(humans);
        setWorker(workers);

        setWorker2(humans);
        setWorker2(workers);
    }

    //协变 上界通配符
    private static void setWorker(List<? extends Human> worker) {
        //可以get，因为？代表的类型是Human的子类型 根据多态的特性可以赋值给Human
        //不能add，因为？代表的是未知类型
        Human man = worker.get(0);
//        worker.add(new Human());
//        worker.add(new Worker1());
    }

    //逆变 下界通配符
    private static void setWorker2(List<? super Worker1> worker) {
        //？虽然是未知类型，但worker一定是它的子类型，根据多态特性，可以add
        //Object是任何对象的子类，所以可以赋值给Object
        worker.add(new Worker1());
        Object human = worker.get(0);
    }
}
