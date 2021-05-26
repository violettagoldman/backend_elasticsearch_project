package other;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
        List<String> list1,list2;
        list1=new ArrayList<String>();
        list2=new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("8");
        for(int i=0;i<list1.size();i++)
        {
            System.out.print(list1.get(i)+" ");
        }
        System.out.println();
        for(int i=0;i<list2.size();i++)
        {
            System.out.print(list2.get(i)+" ");
        }

        System.out.println();
        //first Solution
        List<String> resultList1 = Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
        //first Solution Prints
        System.out.println("first Solution Prints");
        for(int i=0;i<resultList1.size();i++)
        {
            System.out.print(resultList1.get(i)+" ");
        }

        System.out.println();
    }
}
