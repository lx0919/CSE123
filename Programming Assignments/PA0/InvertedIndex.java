import java.util.*;

public class InvertedIndex {
    public static void main(String[] args) {
        List<String> docs = new ArrayList<>();
        docs.add("Raiders of the Lost Ark");
        docs.add("The Temple of Doom");
        docs.add("The Last Crusade");
        
        Map<String, Set<String>> result = createIndex(docs);
        System.out.println(docs);
        System.out.println();
        System.out.println(result);
    }

    // TODO: Write and document your createIndex method here
    //pre: takes a list of strings representating titles of books
    //post: creates a case-insentitive sorted index map for each individual word from all the titles
    //  and returns the map
    public static  Map<String, Set<String>> createIndex (List<String> docs)
    {
        Map<String, Set<String>> indexMap = new TreeMap<>();
        
        for (String title : docs)
        {
            String titleL = title.toLowerCase();
            String[] words = titleL.split(" ");
            for (String word : words)
            {
                if (!indexMap.containsKey(word))
                {
                    indexMap.put(word, new HashSet<>());
                }

                indexMap.get(word).add(title);
                
            }
            
        }

        return indexMap;
    }

}