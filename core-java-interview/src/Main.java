package src;

public class Main {

  public static void main(String[] args) {

    // https://stackoverflow.com/questions/13077009/java-difference-between-a-x-new-a-and-a-x-new-b-when-b-extends-a

    //Child child1 = new Parent(); - wrong
    //child1.get();

    Parent parent = new Child();  // Parent parent -  is static type, new Child() - is dynamic type.
    Child child = new Child();

    parent.call();
    parent.getParent();
    child.call();
    child.get();
  }

}
