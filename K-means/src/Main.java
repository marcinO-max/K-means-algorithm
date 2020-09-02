import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Wprowadz k: ");
        Scanner s = new Scanner(System.in);

        int k=s.nextInt();
        int counter = 0;
        List<Flower> list = new ArrayList<>();
        List<Centroid> listC = new ArrayList<>();
        boolean isChanged = true;
        try {


            Scanner scanner = new Scanner(new File("lib/iris_training.txt"));
            while (scanner.hasNext()) {
                list.add(new Flower(scanner.nextLine(), false));
            }
            Scanner s2 = new Scanner(new File("lib/iris_test.txt"));
            while(s2.hasNext()){
                list.add(new Flower(s2.nextLine(),true));
            }

            for (int i = 0; i < k; i++) {
                listC.add(new Centroid(("id " + i), list.get(0).wartosci.length));

             //   listC.add(new Centroid("id " + i,list.get((int)Math.random()*list.size()).wartosci.length));
            }

            mainloop:
            while(true){
                System.out.println("Aktualizacja nr. "+ counter);




            for (Flower f : list) {
                Centroid najblizszyCentroid = listC.get(0);
                double odleglosc = getDistance(listC.get(0), f);

                double temp;
                for (int i = 1; i < listC.size(); i++) {
                    temp = getDistance(listC.get(i), f);
                    System.out.print("["+temp+"]");
                    if (odleglosc > temp) {
                        odleglosc = temp;
                        najblizszyCentroid = listC.get(i);
                    }
                }





                najblizszyCentroid.tempFlowers.add(f);

            }
                System.out.println();

            for (Centroid c : listC) {
                for (int i = 0; i < c.wspolrzedne.length; i++) {
                    Double srednia = c.wspolrzedne[i];
                    for (Flower f : c.tempFlowers) {
                        srednia += f.wartosci[i];
                    }
                    srednia /= c.tempFlowers.size() + 1;
                    c.wspolrzedne[i] = srednia;

                }
            }

            isChanged = false;
            for (Centroid c : listC) {
                if (!c.tempFlowers.equals( c.oldFlowers)) {
               //     System.out.println("-------->");
                  //  System.out.println(c.tempFlowers.size()+ " != " + c.oldFlowers.size());
                    isChanged = true;
                    break;
                }

            }





            for (Centroid c : listC) {
            //    c.oldFlowers = c.tempFlowers;
//                Collections.copy(c.oldFlowers,c.tempFlowers);
//                System.out.println("Size of " + c.oldFlowers);
//                System.out.println("Size of " + c.tempFlowers);
                c.oldFlowers=new ArrayList<>(c.tempFlowers);
                c.tempFlowers.clear();
                System.out.println(c.id + " ma " +c.oldFlowers.size() + " elementow");
            }


                if(!isChanged){
                    break mainloop;
                }


            counter++;


        }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Entropia(list,listC);

    }


    public static double getDistance(Centroid c,Flower f){

        double distance=0;

        for(int i=0;i<f.wartosci.length;i++){
            distance+=Math.pow(f.wartosci[i]-c.wspolrzedne[i],2);
         //   distance+=Math.pow(c.wspolrzedne[i]-f.wartosci[i],2);
        }

        return Math.sqrt(distance);
    }



    public static void Entropia(List<Flower> list, List<Centroid> listC){


        List<String> setosy = new ArrayList<>();
        List<String> versicolor = new ArrayList<>();
        List<String> virginica = new ArrayList<>();
        for(Centroid c : listC) {
            for (int i = 0; i < c.oldFlowers.size(); i++) {
                if (c.oldFlowers.get(i).nazwa.equals("Iris-setosa")) {
                    c.setosy.add(c.oldFlowers.get(i).nazwa);
                }

                if (c.oldFlowers.get(i).nazwa.equals("Iris-versicolor")) {
                    c.versicolor.add(c.oldFlowers.get(i).nazwa);
                }

                if (c.oldFlowers.get(i).nazwa.equals("Iris-virginica")) {
                    c.virginica.add(c.oldFlowers.get(i).nazwa);
                }

            }

        }



        for(Centroid c : listC){
            double iloscWszystkich = c.oldFlowers.size();
            double iloscSetos = c.setosy.size();
            double iloscVirginic = c.virginica.size();
            double iloscVersicolor = c.versicolor.size();
            double entropia = (-1)*((iloscSetos/iloscWszystkich)*log2(iloscSetos/iloscWszystkich)+
                        (iloscVersicolor/iloscWszystkich)*log2(iloscVersicolor/iloscWszystkich)+
                        (iloscVirginic/iloscWszystkich)*log2(iloscVirginic/iloscWszystkich));
            if(entropia==0){
                System.out.println("Entropia rowna " + 0 + " "+ c.id);
            }else {
                System.out.println("Entropia rowna " + entropia +" "+ c.id);
            }
//            System.out.println("--------------------");
//            System.out.println("-------> "+iloscSetos);
//            System.out.println("-------> "+iloscVirginic);
//            System.out.println("-------> "+iloscVersicolor);

        }



    }

    public static double log2(double x){
        if(x==0){
            return 0;
        }
        return Math.log(x)/Math.log(2);
    }



}
