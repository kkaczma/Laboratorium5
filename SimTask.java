import java.util.TimerTask;

public class SimTask extends TimerTask {

    //Prywatne pole do przechowywania obiektu klasy SimEngine
    private SimEngine obiekt;
    //Prywatne pole do przechowywania obiektu klasy SpringApplet
    private SpringApplet obiekt1;
    //Prywatne pole do przechowywania odstêpu czasowego pomiêdzy kolejnymi krokami symulacji
    private double krok_czas;

    //Konstruktor z parametrami pozwalaj¹cy na przypisanie do pól klasy obiektu klasy SimEngine,
    //obiektu klasy SpringApplet i odstêpu czasowego pomiêdzy kolejnymi krokami symulacji
    public SimTask(SimEngine pierwszy,SpringApplet drugi, double czas)
    {
        obiekt=pierwszy;
        obiekt1=drugi;
        krok_czas=czas;
    }
    public void run()
    {
    	//Uruchomienie obliczenia kolejnego kroku symulacji
        obiekt.symulacja(krok_czas);
        //Odœwie¿enie wyœwietlania powierzchni appletu
        obiekt1.repaint();
    }
}