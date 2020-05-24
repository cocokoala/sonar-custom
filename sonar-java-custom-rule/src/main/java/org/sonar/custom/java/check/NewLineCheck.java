package org.sonar.custom.java.check;

import java.io.*;

/**
 *This file is the sample code against we run our unit test.
 *It is placed src/test/files in order to not be part of the maven compilation.
 **/
class NewLineCheck {

 public void test() throws IOException {
   FileOutputStream fos = new FileOutputStream(new File("test.txt"));
   StringBuilder sb  = new StringBuilder();
   String s1 = "s1";
   sb.append("\r"); // Noncompliant {{Avoid using  \r }}
   sb.append("\n"); // Noncompliant {{Avoid using \n }}

   fos.write(sb.toString().getBytes());
 }


}
