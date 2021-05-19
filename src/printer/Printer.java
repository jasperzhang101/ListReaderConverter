package printer;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
/*
    Klasse Printer()
    - benutzt HashMaps von der Klasse tools.ListPrepper
    - druckt die HashMaps als CSV Dateien aus

    PROBLEM LOG:
    (13.05)
    -
 */
public class Printer {

    public void print(int aufgabe, String list, String path, String name){
        System.out.println("Starte Printer.print() von Aufgabe "+aufgabe);
        //Variablen
        String header1;
        String header2;
        String header3;
        int startPos;
    /*
        Header einstellen: ursprünglich wollte ich über die erste Zeile der gg.Liste
        die Header zuweisen - aber die Namen sollten angepasst werden.
        -> Deshalb manuell zugewiesen
        -> zusätzlich wird startPos eingestellt
    */
        if (aufgabe==1){
            startPos=2;
            header1 ="Werbeformat ID" ;
            header2 ="Werbeformat Name";
            header3 ="";
        }else{
            startPos=0;
            header1 ="Website ID" ;
            header2 ="Website Name";
            header3 ="buchbare Werbeformate";
        }
    /*
        Liste wird umgewandelt in LinkedHashMap und HashMap
        mit dem Iterator habe ich einen Zeiger, der durch die
        HashMap geht.
     */
        ListPrepper listPrepper = new ListPrepper();
        LinkedHashMap map = listPrepper.getHashmap(list, startPos);
        Iterator<Map.Entry<Integer, String>> entrySet = map.entrySet().iterator();
        Map.Entry<Integer, String> entry = entrySet.next();

        HashMap count = listPrepper.getCount();

    //druckt die Listen aus anhand der Maps
        try{
            PrintWriter pw= new PrintWriter(path+name);
            StringBuilder sb= new StringBuilder();
        //1.Individuelle Header zu sb hinzufügen
            sb.append(header1);
            sb.append(";");
            sb.append(header2);
            sb.append(";");
            sb.append(header3+"\n");
        /*
            2.Werte+Header drucken - je nach Input wird entweder Aufgabe 1
            oder Aufgabe 2 gedruckt.
         */
            while (entrySet.hasNext()) {
                sb.append(entry.getKey());
                sb.append(";");
                if (startPos == 2){
                    sb.append(entry.getValue()+"\n");
                }else{
                    sb.append(entry.getValue());
                    sb.append(";");
                    sb.append(count.get(entry.getKey())+"\n");
                }
                entry = entrySet.next();
            }
            pw.write(sb.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("ERROR: Could not open file!");
        }
    }
}