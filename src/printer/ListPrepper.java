package printer;

import java.io.*;
import java.util.*;

/*
    Klasse tools.ListPrepper
    - bereitet die gegebene Liste vor
    - unterteilt Liste (trennt WebIds von Webname)
    - speichert jedes Element aus der Liste in LinkedHashMap check() ab
    - speichert Anzahl Werbeformate in HashMap count();
    - Duplikate werden nicht gespeichert und
    - Anzahl der Vorkommnisse werden in HashMap count() gezählt
    - int startPos: bestimmt, welche Reihe sie sich angucken soll

    Verbesserungsvorschläge:
    - Listen nach Key nummerisch sortieren mit TreeMap

    PROBLEM LOG:
    (14.05)
    - zuerst mit ArrayList gearbeitet, stieß aber zu einem Problem
      bei der Zusatzaufgabe: Anzahl Werbeformate
      -> gelöst indem ich zu HashMap gewechselt habe.
      -> und eine Count-HashMap erstellt

    (15.05)
    - 2.PROBLEM: HashMap kann nicht in der selben Reihenfolge
      aufgerufen werden, wie ich eingefügt habe. Die Liste
      war dadurch komplett durcheinander.
      -> gelöst indem ich zu LinkedHashMap gewechselt habe.
*/
public class ListPrepper {
    LinkedHashMap check = new LinkedHashMap();
    HashMap count = new HashMap();

    public LinkedHashMap getHashmap(String list, int startPos) {
        String line;
        int temp = 1;
    /*
            Geht Liste durch mit br()
            - wenn erstes Vorkommnis: speichern in check, count
              -> ansonsten nur count am aktuellen Key=values[startPos] erhöhen
    */
        try {
            BufferedReader br = new BufferedReader(new FileReader(list));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.replaceFirst("-", ";").split(";");

                if (!check.containsKey(values[startPos])) {
                    check.put(values[startPos], values[startPos+1]);
                    count.put(values[startPos], temp);
                }else{
                    temp = (int) (count.get(values[startPos]));
                    count.put(values[startPos], temp+1);
                }
                temp=1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR: Could not open file!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: IOException!");
        }
        return check;
    }

    public HashMap getCount(){
        return count;
    }
}