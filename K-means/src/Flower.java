public class Flower {

    String nazwa = "Brak nazwy";
    Double[] wartosci;


    public Flower(String linia,Boolean isTesting) {

        String[] tab = linia.substring(1).replace(",",".").split("    \t ");


        if(!isTesting) {
            wartosci = new Double[tab.length];
            nazwa = tab[tab.length - 1].split("    \t")[1];
            tab[tab.length - 1] = tab[tab.length - 1].split("    \t")[0];


            //uzupełnianie wartosci dla nowych elementów (learn)
            for (int i = 0; i < tab.length; i++) {
                this.wartosci[i] = (Double.parseDouble(tab[i]));
            }
        }
        else{
            //uzupełnianie wartosci dla testowanych elementów (test)
            nazwa=tab[tab.length-1].substring(0,tab[tab.length-1].length()-1);
            wartosci = new Double[tab.length-1];
            for (int i = 0; i < tab.length-1; i++) {
                this.wartosci[i] = (Double.parseDouble(tab[i]));
            }
        }


    }


}
