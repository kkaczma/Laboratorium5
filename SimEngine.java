public class SimEngine //Klasa reprezentuj�ca silnik symulacyjny
{
    //Prywatne pola parametr�w stmulacji wraz z kontrol� warto�ci
    private double masa;
    public void kontrolamasa(float masa)
    {
        if(masa>=0)
        {
            this.masa = masa;
        }
        else
        {
            System.out.println("Masa nie mo�e by� ujemna");
            System.exit(0);
        }
    }
    private double wspolczynnik_sprezystosci;
    private double wspolczynnik_tlumienia;
    private double dlugosc_swobodna_sprezyny;
    Vector2D predkosc_masy =new Vector2D();
    Vector2D polozenie_masy = new Vector2D();
    private double polozenie_punktu_zawieszenia;
    private double przyspieszenie_grawitacyjne;


    //Konstruktory z parametrami pozwalaj�cy na nadanie warto�ci wszystki parametrom symulacji

    public SimEngine(double m, double ws, double wt, double dss,double pm, double prm,double ppz, double pg)
    {
        this.masa = m;
        this.wspolczynnik_sprezystosci=ws;
        this.wspolczynnik_tlumienia=wt;
        this.dlugosc_swobodna_sprezyny=dss;
        this.polozenie_masy.y=pm;
        this.predkosc_masy.y=prm;
        this.polozenie_punktu_zawieszenia=ppz;
        this.przyspieszenie_grawitacyjne=pg;
    }

    //Zwracanie warto�ci parametr�w
    
    public double Polozenie_masy()
    { return polozenie_masy.y;}
    
    public double Predkosc_masy()
    { return predkosc_masy.y;}

    public double Dlugosc_swobodna_sprezyny()
    { return dlugosc_swobodna_sprezyny;}
    
    //Zmiana warto�ci parametru
    
    public Vector2D Masa(double pmasa)
    {
    	this.polozenie_masy.y=pmasa;
    	return this.polozenie_masy;
    }
    //Metoda z parametrem obliczaj�ca przebieg symulacji
    public void symulacja(double czas)
    {
        //Utworzenie wektorow sily i przyspieszenia
    	Vector2D sila=new Vector2D();
    	Vector2D przyspieszenie=new Vector2D();
    	sila.x=0;
    	// Wyznaczenie wartosci skladowej y sily
        sila.y =masa*przyspieszenie_grawitacyjne - wspolczynnik_tlumienia*predkosc_masy.y-wspolczynnik_sprezystosci*(polozenie_masy.y+polozenie_punktu_zawieszenia);
        przyspieszenie.x=0;
        //Wyznaczenie waro�ci sk�adowej y przypieszenia
        przyspieszenie.y=sila.y/masa;
        //Wyznaczenie warto�ci sk�adowej y pr�dko�ci masy 
        this.predkosc_masy.y = predkosc_masy.y+przyspieszenie.y*czas;
      //Wyznaczenie warto�ci sk�adowej y pr�dko�ci masy 
        this.polozenie_masy.y =polozenie_masy.y+ predkosc_masy.y*czas+przyspieszenie.y*(czas*czas)/2;}
    
    //Metoda zwracaj�ca sk�adowow� y si�y ci�ko�ci 
    public double sila_ciezkosci()
    {
        Vector2D sila_ciezkosci = new Vector2D(0, masa * przyspieszenie_grawitacyjne);
        return sila_ciezkosci.y;
    }

  
    //Metoda zwracaj�ca sk�adowow� y si�y spr�zysto�ci 
    public double sila_sprezystosci() {
        Vector2D sila_sprezystosci = new Vector2D(0, -wspolczynnik_sprezystosci * (polozenie_masy.y-polozenie_punktu_zawieszenia));
        return sila_sprezystosci.y;
    }

    //Metoda zwracaj�ca sk�adow� y si�y t�umienia z uwzgl�dnieniem kierunku dzia�ania
    public double sila_tlumienia() {
    	Vector2D sila_tlumienia = new Vector2D();

        if(predkosc_masy.y>0)
        {
        	sila_tlumienia.y=-wspolczynnik_tlumienia * predkosc_masy.y*predkosc_masy.y;
        }
        else
        {
        	sila_tlumienia.y=wspolczynnik_tlumienia * predkosc_masy.y*predkosc_masy.y;
        }
        return sila_tlumienia.y;
    }

    //Metoda bez parametru resetuj�ca symulacje

    public void reset()
    {
        this.predkosc_masy.y=0;
    
    }
}