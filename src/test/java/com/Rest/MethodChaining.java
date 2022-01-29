package com.Rest;

public class MethodChaining {

    public static void main(String[] args) {


        a1().a2().a3();


    }

    public static MethodChaining a1(){
        System.out.println("This is method a1");
        return new MethodChaining();
    }
    public static MethodChaining a2(){
        System.out.println("This is method a2");
        return new MethodChaining();
    }
    public static MethodChaining a3(){
        System.out.println("This is method a3");
        return new MethodChaining() ;
    }
}
