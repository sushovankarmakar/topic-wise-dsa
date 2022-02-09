package src.scjp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class _14 {

  public static void main(String[] args) {

    String a1 = new String("durga");
    String a2 = new String("durga");
    System.out.println(a1 == a2);
    //System.out.println(a1.hashCode() + " " + a2.hashCode());
    //System.out.println(a1.equals(a2));

    String b1 = "durga";
    String b2 = "durga";
    System.out.println(b1 == b2);
    System.out.println(b1.hashCode() + " " + b2.hashCode());
    System.out.println(a1.equals(b1));
    System.out.println(a1 == b1);
    int seven = 7;
    //System.out.println(seven == 7);
    System.out.println(b1 == "durga");  // true
    System.out.println(a1 == "durga");  // false


    Integer c1 = new Integer(7);
    Integer c2 = new Integer(7);
    Integer c3 = (int) 7L;
    System.out.println(c1 == 7);  // true
    System.out.println(c1 == c2);  // false

    HashSet set = new HashSet();
    set.add(2);
    set.remove("1");
    System.out.println("_--------------");
    System.out.println(set.contains(2));  // wrapper class auto-boxing makes 2 primitive type as new Integer(2);
    System.out.println(set.contains(new Integer(2)));

    HashMap hashMap = new HashMap<>();
    hashMap.put("1", 2);

    // http://www.durgasoft.com/scjp_material_2.asp?chapter=10&page=3
    String s = "-";
    Integer x = 343;
    long L343 = 343L;
    if (x.equals(L343)) {
      s += ".e1 ";
    }
    if (x.equals(343)) {
      s += ".e2 ";
    }
    Short s1 = (short) ((new Short((short) 343)) / (new Short((short) 49)));
    if (s1 == 7) {
      s += "=s ";
    }
    if (s1 < new Integer(7 + 1)) {
      s += "fly ";
    }
    //System.out.println(s);

    // https://www.youtube.com/watch?v=X2AjBFZfFCY&ab_channel=CodingSimplified
    String random1 = new String();
    String random2 = new String();
    System.out.println(random1.equals(random2));

  }
}
