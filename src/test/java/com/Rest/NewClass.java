package com.Rest;

import java.util.ArrayList;

public class NewClass {
    public NewClass() {
    }

    public static void main(String[] arg) {
        int a = 0;
        ArrayList M = new ArrayList();

        for(int i = 0; i < 100; ++i) {
            ArrayList subset = new ArrayList();
            subset.add(a);
            subset.add(a + 1);
            M.add(subset);
            ++a;
        }

        System.out.println(M);
    }

}
