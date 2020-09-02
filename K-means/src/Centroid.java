import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Centroid {

    String id;
    double [] wspolrzedne;
    List<Flower> tempFlowers;
    List<Flower> oldFlowers;
    Flower f;
    private List<Flower> list;
    List<String> setosy = new ArrayList<>();
    List<String> versicolor = new ArrayList<>();
    List<String> virginica = new ArrayList<>();


    public Centroid(String id, int iloscWspolrzednych){
        this.id=id;
        this.wspolrzedne = new double[iloscWspolrzednych];
        this.tempFlowers = new ArrayList<>();
        oldFlowers = new ArrayList<>();
        list = new ArrayList<>();



        try {
            Scanner s = new Scanner(new File("lib/iris_training.txt"));
            while(s.hasNext()){
                list.add(new Flower(s.nextLine(),false));
            }

            Scanner s2 = new Scanner(new File("lib/iris_test.txt"));
            while(s2.hasNext()){
                list.add(new Flower(s2.nextLine(),true));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        for(int i = 0 ;i < iloscWspolrzednych;i++){
         //   wspolrzedne[i]=(Math.random()*10);
            wspolrzedne[i] = list.get((int)(Math.random()*list.size())).wartosci[i];
        }
    }



}
