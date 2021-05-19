package tools;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.*;

/*
    Klasse Compressor ()
    kopiert die erstellten Listen in eine Zip-Datei und
    löscht anschließend die alten Listen

    Verbesserungsvorschläge:
    anstatt ZIP nach der Listenerstellung erstellen,
    die Listen direkt in die ZIP reinschreiben
 */
public class Compressor {
    public void compress(String path, String name1, String name2){
        //erstelle Zip und kopiere die Listen da rein
        String zipName = "\\dieListen.zip";
        List<String> filePaths = Arrays.asList(path+name1, path+name2);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(path+zipName))) {
            for (String filePath : filePaths) {
                File fileToZip = new File(filePath);
                zipOut.putNextEntry(new ZipEntry(fileToZip.getName()));
                Files.copy(fileToZip.toPath(), zipOut);
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Datei nicht gefunden!");
        } catch (IOException e) {
            System.err.println("ERROR: IO EXCEPTION");
        }
        //Lösche alte Listen
        File f1 = new File(path+name1);
        File f2 = new File(path+name2);
        f1.delete();
        f2.delete();

        //Output Text
        System.out.println();
        System.out.println("Die Listen wurden komprimiert.");
        System.out.println();
    }
}
