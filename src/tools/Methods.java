package tools;
import printer.Printer;
import printer.PrinterJson;
import java.util.Scanner;
/*
    Klasse Methods()
    - Set-Methoden mit console Input
    - start(): erstellt Objekt Printer und
      gibt die Infos an diese weiter

    Verbesserungsmöglichkeiten:
    Input Klasse verwenden und dort alle Setter schreiben
 */
public class Methods implements Control {
    protected String datentyp;
    protected String sonderzeichen = "\\";
    protected String name1;
    protected String name2;
    protected String path;
    protected String list;


    //start() setzt Reihenfolge fest, wie das Programm arbeiten soll
    @Override
    public void start(){
        if (!useConfig()){
            setName1();
            setName2();
            setList();
            setPath();
        }
        if (useConvert()){
            PrinterJson printer = new PrinterJson();
            printer.print(1,list,path,name1);
            System.out.println(" ");
            printer.print(2,list,path,name2);
        }else{
            Printer printer = new Printer();
            printer.print(1,list,path,name1);
            System.out.println(" ");
            printer.print(2,list,path,name2);
        }
        System.out.println(" ");
        if(useCompress()){
            Compressor compressor = new Compressor();
            compressor.compress(path, name1, name2);
        }
    }

    /*
        Ab hier:
        Alle Getter/Setter Methoden, sowie booleans
        für Abfragen
     */

//TODO: INPUT KLASSE
    @Override
    public void setName1() {
        Scanner scan = new Scanner(System.in);
        System.out.println("-Name fuer die 1.Liste: ");
        name1 = scan.next();
        System.out.println();
    }

    @Override
    public void setName2() {
        Scanner scan = new Scanner(System.in);
        System.out.println("-Name fuer die 2.Liste: ");
        name2 = scan.next();
        System.out.println();

        while (name2.equals(name1)){
            System.out.println("-Bitte einen anderen Namen verwenden: ");
            name2 = scan.next();
            System.out.println();
        }
    }

    @Override
    public void setList() {
        Scanner scan = new Scanner(System.in);
        System.out.println("-Listenpfad angeben:");
        list = scan.next();
        System.out.println();
    }

    @Override
    public void setPath() {
        Scanner scan = new Scanner(System.in);
        System.out.println("-Wo sollen die Listen gespeichert werden?: ");
        path = scan.next();
        path += sonderzeichen;
        System.out.println();
    }

    @Override
    public boolean useConfig(){
        Scanner scan = new Scanner(System.in);
        System.out.println("-Config benutzen? (y/n)");
        String input = scan.next();
        if (input.equals("y")){
            Config config = new Config();
            name1 = config.name1;
            name2 = config.name2;
            path = config.path;
            list = config.list;
            System.out.println();
            return true;
        }else System.out.println();return false;
    }

    @Override
    public boolean useConvert() {
        Scanner scan = new Scanner(System.in);
        System.out.println("-zu JSON konvertieren? (y/n)");
        String input = scan.next();
        if (input.equals("y")){
            datentyp = ".json";
            name1 += datentyp;
            name2 += datentyp;
            System.out.println();
            return true;
        }else{
            datentyp = ".csv";
            name1 += datentyp;
            name2 += datentyp;
            System.out.println();
        } return false;
    }

    @Override
    public boolean useCompress() {
        Scanner scan = new Scanner(System.in);
        System.out.println("-Listen komprimieren? (y/n)");
        String input = scan.next();
        return input.equals("y");
    }
}
