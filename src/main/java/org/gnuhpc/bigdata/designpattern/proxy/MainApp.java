package org.gnuhpc.bigdata.designpattern.proxy;
/*
静态代理：
RealSubject 是原对象（本文把原对象称为"委托对象"），Proxy 是代理对象。
Subject 是委托对象和代理对象都共同实现的接口。
Request() 是委托对象和代理对象共同拥有的方法。

 */
public class MainApp{
    public static void main(String args[]){
        RealSubject subject = new RealSubject();
        Proxy p = new Proxy(subject);
        p.request();
    }
}

interface Subject{
    void request();
}

class RealSubject implements Subject{
    public void request(){
        System.out.println("request");
    }
}

class Proxy implements Subject{
    private Subject subject;
    public Proxy(Subject subject){
        this.subject = subject;
    }
    public void request(){
        System.out.println("PreProcess");
        subject.request();
        System.out.println("PostProcess");
    }
}
