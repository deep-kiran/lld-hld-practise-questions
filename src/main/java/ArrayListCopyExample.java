import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListCopyExample {
    public static void main(String[] args) {
        ArrayList<String> originalList = new ArrayList<>();
        originalList.add("apple");
        originalList.add("banana");
        originalList.add("cherry");

        CopyOnWriteArrayList<String> copyList = new CopyOnWriteArrayList<>();
        copyList.addAll(originalList);
        Iterator<String> it = copyList.iterator();
        while (it.hasNext()){
            it.next();
            copyList.add("papaya");
        }

        System.out.println("Original List: " + originalList);
        System.out.println("Copy List: " + copyList);
    }
}
