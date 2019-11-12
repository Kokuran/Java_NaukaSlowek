import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class NaukaSlowek {

    static List<String> ListaSlow = new ArrayList<String>();
    static List<String> NauczoneSlowa = new ArrayList<String>();
      static List<Integer> DzienNauczenia = new ArrayList<Integer>();

    public static void odczytZPliku(String nazwaPliku) {
        File file = new File(nazwaPliku);
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("1500.txt"));
            String linia = null;
            while (in.hasNextLine()) {
                ListaSlow.add(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Wystapil blad przy wczytywaniu danych");
            e.printStackTrace();
        }
    }

    public static void systemNaukiSlowek(int iloscDniNauki, int iloscDniZapominania){
        int x, bezPowtorzen=0;
        String newWords;
        float y;

        System.out.println(iloscDniNauki+" "+iloscDniZapominania);

        for(int i=1; i<=iloscDniNauki; i++){
            newWords = "New words: ";
            while(bezPowtorzen <2){
                x = (int) (Math.random() * ListaSlow.size()-1);
               // System.out.println(x);
                //System.out.println(ListaSlow.get(x).equals(NauczoneSlowa));
                if(NauczoneSlowa.indexOf(ListaSlow.get(x)) == -1){
                    NauczoneSlowa.add(ListaSlow.get(x));
                    DzienNauczenia.add(i);
                     bezPowtorzen ++;
                    newWords += ListaSlow.get(x) +" ";
                }
            }
           bezPowtorzen = 0;


            System.out.println("Day "+ i);
            System.out.println(newWords);
            ZapominanieSlow(i,iloscDniZapominania);
            newWords = "";
            System.out.println(NauczoneSlowa);
        }

    }

    public static void ZapominanieSlow(int dni, int zapomniane){
        int z,przedzial;
        String forgottenWord;
        float y;

        forgottenWord = "Forgotten words: ";

        for(int j=1;j<=2; j++){
                y = (float) (Math.random() * 1);
                if(y>= 0.5){
                    //Jeśli ilosc dni mniejsza od ilosc dni zapomniena losuj z przedziału [0,dniZapomnienia]
                    if(dni<=zapomniane){
                        z =  (int) (Math.random() * NauczoneSlowa.size()-1);
                        forgottenWord+=NauczoneSlowa.get(z) +" ";
                        NauczoneSlowa.remove(z);
                        DzienNauczenia.remove(z);
                    }
                    //Jeśli dniw większe od dni zapomnienia losuj z przedziału [dnie-dniZapomnienia, dni]
                    else{
                        //Sprawdzenie czy dzień dni-dniZapomnienia jest w tablicy
                        if(DzienNauczenia.indexOf(dni-zapomniane) == -1){
                            przedzial = (NauczoneSlowa.size()-1) - DzienNauczenia.indexOf(dni-zapomniane+1);
                        }
                        else{
                            przedzial = (NauczoneSlowa.size()-1) - DzienNauczenia.indexOf(dni-zapomniane) + 1;
                        }
                        z = (int) ((Math.random() * przedzial )+ DzienNauczenia.indexOf(dni-zapomniane-1));
                        System.out.println(DzienNauczenia.indexOf(dni-zapomniane-1)+" "+z+" ");
                        forgottenWord+=NauczoneSlowa.get(z) +" ";
                        NauczoneSlowa.remove(z);
                        DzienNauczenia.remove(z);
                    }
                }

         }
        System.out.println(forgottenWord);
    }

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        System.out.println("Ilosc dni nauki ");
        int wszystkieDni = myObj.nextInt();

        System.out.println("Ilosc dni zapominania: ");
        int dniZapomnienia = myObj.nextInt();
        if(dniZapomnienia > wszystkieDni){
            System.out.println("Podaj nowa liczbe dni zapominiania (0," + wszystkieDni +")");
            dniZapomnienia = myObj.nextInt();
        }

        odczytZPliku("1500.txt");

        systemNaukiSlowek(wszystkieDni,dniZapomnienia);
    }
}
