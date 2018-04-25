public class Vector2D //Klasa reprezentuj¹ca wektor dwuwymiarowy
{
    public double x;  //Publiczne pola wspó³rzêdnych (x,y)
    public double y;

    Vector2D()   //Konstruktor domyœlny inicjuj¹cy pola klasy zerami
    {
        x=0;
        y=0;
    }

    Vector2D(double wspolrzednax,double wspolrzednay) //Konstruktor z parametrem pozwalaj¹cy na nadanie dowolnych wartoœci polom klasy
    {
        x=wspolrzednax;
        y=wspolrzednay;
    }
    public Vector2D sumawektorow(Vector2D wektor)     //Metoda z parametrem podanego jako parametr, zwaracaj¹ca nowy obiekt
    {                                                 // klasy Vector2D (suma wektorów)
        Vector2D obiektsuma =new Vector2D(this.x + wektor.x, this.y+wektor.y);
        return obiektsuma;
    }
    public Vector2D roznicawetorow(Vector2D wektor)  //Metoda z parametrem podanego jako parametr, zwaracaj¹ca nowy obiekt
    {                                                //klasy Vector2D ( ró¿nica wektorów)
        Vector2D obiektroznica =new Vector2D(this.x-wektor.x,this.y-wektor.y);
        return obiektroznica;
    }
    public Vector2D stalawektor(double stala)   //Metoda z parametrem w postaci sta³ej przez któr¹ mno¿ymy wspo³rzêdne wektora
    {
        Vector2D obiektstala=new Vector2D(this.x *stala,this.y*stala);
        return obiektstala;
    }
    public double dlugoscwektora()            //Metoda bez parametru zwracaj¹ca d³ugoœc wektora
    {
        return Math.sqrt(this.x *this.x + this.y*this.y);

    }
    public Vector2D normalizacja()     //Metoda bez parametru zwracaj¹ca znormalizowany wektor
    {
        double dlugosc =dlugoscwektora();
        Vector2D normwektor=new Vector2D(this.x/dlugosc,this.y/dlugosc);
        return normwektor;
    }
}