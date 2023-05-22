package tacos.gc;

import java.util.function.Predicate;

public class Question_10_1 {
      public static void main(String[] args) {
           Predicate<String> p1 = t -> {
                System.out.print("p1");
                return t.startsWith(" ");
           };
           Predicate<String> p2 = t -> {
                System.out.print("p2");
                return t.length() > 5;
           };
           p1.and(p2).test("a question");
      }
}