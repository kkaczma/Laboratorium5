import java.util.TimerTask;

public class SimTask extends TimerTask {

    //Prywatne pole do przechowywania obiektu klasy SimEngine
    private SimEngine obiekt;
    //Prywatne pole do przechowywania obiektu klasy SpringApplet
    private SpringApplet obiekt1;
    //Prywatne pole do przechowywania odst�pu czasowego pomi�dzy kolejnymi krokami symulacji
    private double krok_czas;

    //Konstruktor z parametrami pozwalaj�cy na przypisanie do p�l klasy obiektu klasy SimEngine,
    //obiektu klasy SpringApplet i odst�pu czasowego pomi�dzy kolejnymi krokami symulacji
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
        //Od�wie�enie wy�wietlania powierzchni appletu
        obiekt1.repaint();
    }
}