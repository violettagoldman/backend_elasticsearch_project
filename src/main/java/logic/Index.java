// package logic;
//
// import java.util.HashMap;
// import java.util.Map;
// import java.util.TreeMap;
//
// public class Index
// {
//     public static void main(String[] argv)
//     {
// //        Index test = new Index("testnomIndex", "testnomTable", new HashMap<Integer, String>());
// //        addColumn(1,"testNomColumn1", test.getNameColumns());
// //        addColumn(2,"testNomColumn2", test.getNameColumns());
// //        addColumn(3,"testNomColumn3", test.getNameColumns());
// //        System.out.println(test.getIndex());
// //        deleteColumn(3, test.getNameColumns());
// //        System.out.println(test.getIndex());
//     }
//
// //    private String nameIndex;
// //    private String nameTable;
//     private Map<String, Integer> dataMap;
//     private int keyData;
//
//     public Index() {
//         this.dataMap = new HashMap<>();
//         keyData = 0;
//     }
//
//     //sauvegarder aussi le nombre d'occurence
//     public String addDataValue(String value){
//         int i = dataMap.getOrDefault(value,0);
//         i++;
//         dataMap.put(value, i);
//     }
//
//     public static HashMap<Integer, String> addColumn(Integer columnNumber, String nomColumnToAdd, HashMap<Integer, String> hashMap){
//         hashMap.put(columnNumber, nomColumnToAdd);
//         return hashMap;
//     }
//
//     public static HashMap<Integer, String> deleteColumn(Integer columnNumber, HashMap<Integer, String> hashMap){
//         hashMap.remove(columnNumber);
//         return hashMap;
//     }
//
//     public String getData(int value) {
//         return dataMap.get(value);
//     }
// //    public HashMap<Integer, String> getNameColumns() {
// //        return nameColumns;
// //    }
//
// //    public String getNameIndex() {
// //        return nameIndex;
// //    }
// //
// //    public String getNameTable() {
// //        return nameTable;
// //    }
//
// //    public String getIndex() {
// //        return "Index{" +
// //                "nomIndex=" + getNameIndex() +
// //                ", nomTable='" + getNameTable() +
// //                ", nomColumn='" + getNameColumns() +
// //                '}';
// //    }
// }
//
//
