package com.boc.igtb.java.check;

import java.io.*;

/**
 *This file is the sample code against we run our unit test.
 *It is placed src/test/files in order to not be part of the maven compilation.
 **/
class NewLineCheck {

    public void test() throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("test.txt"));
        StringBuilder sb  = new StringBuilder();
        StringBuilder sb2  = new StringBuilder();
        String s1 = "s1";
        sb.append(s1+"\r"); // Noncompliant {{please confirm using \r\n }}
        sb.append(s1+"\r"+s1); // Noncompliant {{please confirm using \r\n }}
        sb.append(s1+"\r"+sb2.append("12")); // Noncompliant {{please confirm using \r\n }}
        sb.append("\n"); // Noncompliant {{please confirm using \r\n }}

        fos.write(sb.toString().getBytes());
    }


}
