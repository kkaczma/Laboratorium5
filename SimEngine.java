public class SimEngine //Klasa reprezentuj¹ca silnik symulacyjny
{
    //Prywatne pola parametrów stmulacji wraz z kontrol¹ wartoœci
    private double masa;
    public void kontrolamasa(float masa)
    {
        if(masa>=0)
        {
            this.masa = masa;
        }
        else
        {
            System.out.println("Masa nie mo¿e byæ ujemna");
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


    //Konstruktory z parametrami pozwalaj¹cy na nadanie wartoœci wszystki parametrom symulacji

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

    //Zwracanie wartoœci parametrów
    
    public double Polozenie_masy()
    { return polozenie_masy.y;}
    
    public double Predkosc_masy()
    { return predkosc_masy.y;}

    public double Dlugosc_swobodna_sprezyny()
    { return dlugosc_swobodna_sprezyny;}
    
    //Zmiana wartoœci parametru
    
    public Vector2D Masa(double pmasa)
    {
    	this.polozenie_masy.y=pmasa;
    	return this.polozenie_masy;
    }
    //Metoda z parametrem obliczaj¹ca przebieg symulacji
    public void symulacja(double czas)
    {
        //Utworzenie wektorow sily i przyspieszenia
    	Vector2D sila=new Vector2D();
    	Vector2D przyspieszenie=new Vector2D();
    	sila.x=0;
    	// Wyznaczenie wartosci skladowej y sily
        sila.y =masa*przyspieszenie_grawitacyjne - wspolczynnik_tlumienia*predkosc_masy.y-wspolczynnik_sprezystosci*(polozenie_masy.y+polozenie_punktu_zawieszenia);
        przyspieszenie.x=0;
        //Wyznaczenie waroœci sk³adowej y przypieszenia
        przyspieszenie.y=sila.y/masa;
        //Wyznaczenie wartoœci sk³adowej y prêdkoœci masy 
        this.predkosc_masy.y = predkosc_masy.y+przyspieszenie.y*czas;
      //Wyznaczenie wartoœci sk³adowej y prêdkoœci masy 
        this.polozenie_masy.y =polozenie_masy.y+ predkosc_masy.y*czas+przyspieszenie.y*(czas*czas)/2;}
    
    //Metoda zwracaj¹ca sk³adowow¹ y si³y ciê¿koœci 
    public double sila_ciezkosci()
    {
        Vector2D sila_ciezkosci = new Vector2D(0, masa * przyspieszenie_grawitacyjne);
        return sila_ciezkosci.y;
    }

  
    //Metoda zwracaj¹ca sk³adowow¹ y si³y sprêzystoœci 
    public double sila_sprezystosci() {
        Vector2D sila_sprezystosci = new Vector2D(0, -wspolczynnik_sprezystosci * (polozenie_masy.y-polozenie_punktu_zawieszenia));
        return sila_sprezystosci.y;
    }

    //Metoda zwracaj¹ca sk³adow¹ y si³y t³umienia z uwzglêdnieniem kierunku dzia³ania
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

    //Metoda bez parametru resetuj¹ca symulacje

    public void reset()
    {
        this.predkosc_masy.y=0;
    
    }
}