package printer;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
/*
    Klasse PrinterJson()
    - benutzt HashMaps von der Klasse tools.ListPrepper
    - druckt die HashMaps als Json Dateien aus
 */
public class PrinterJson {
    public void print(int aufgabe, String list, String path, String name){
        System.out.println("Starte PrinterJson.print() von Aufgabe "+aufgabe);
        //Variablen
        ListPrepper listPrepper = new ListPrepper();
        String header1;
        String header2;
        String header3;
        int startPos;
        char sz = '"';

    /*
        Header einstellen: ursprünglich wollte ich über die erste Zeile der gg.Liste
        die Header zuweisen - aber die Namen sollten angepasst werden.
        -> Deshalb manuell zugewiesen
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
        LinkedHashMap map = listPrepper.getHashmap(list, startPos);
        Iterator<Map.Entry<Integer, String>> entrySet = map.entrySet().iterator();
        Map.Entry<Integer, String> entry = entrySet.next();

        HashMap count = listPrepper.getCount();

        //druckt die Listen aus anhand HashMaps
        try{
            PrintWriter pw= new PrintWriter(path+name);
            StringBuilder sb= new StringBuilder();
            sb.append("[\n");
            while (entrySet.hasNext()) {
                sb.append(" {\n");
                sb.append("  "+sz+header1+sz+": "+sz+entry.getKey()+sz+",\n");
                if (startPos == 2){
                    sb.append("  "+sz+header2+sz+": "+sz+entry.getValue()+sz+"\n");
                    sb.append(" },\n");
                }else{
                    sb.append("  "+sz+header2+sz+": "+sz+entry.getValue()+sz+",\n");
                    sb.append("  "+sz+header3+sz+": "+count.get(entry.getKey())+"\n");
                    if (entrySet.hasNext()){
                        sb.append(" },\n");
                    }
                }
                entry = entrySet.next();
            }
            sb.append(" }\n");
            sb.append("]\n");
            pw.write(sb.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("ERROR: Could not open file!");
        }
    }
}