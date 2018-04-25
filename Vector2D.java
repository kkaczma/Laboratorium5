public class Vector2D //Klasa reprezentuj�ca wektor dwuwymiarowy
{
    public double x;  //Publiczne pola wsp�rz�dnych (x,y)
    public double y;

    Vector2D()   //Konstruktor domy�lny inicjuj�cy pola klasy zerami
    {
        x=0;
        y=0;
    }

    Vector2D(double wspolrzednax,double wspolrzednay) //Konstruktor z parametrem pozwalaj�cy na nadanie dowolnych warto�ci polom klasy
    {
        x=wspolrzednax;
        y=wspolrzednay;
    }
    public Vector2D sumawektorow(Vector2D wektor)     //Metoda z parametrem podanego jako parametr, zwaracaj�ca nowy obiekt
    {                                                 // klasy Vector2D (suma wektor�w)
        Vector2D obiektsuma =new Vector2D(this.x + wektor.x, this.y+wektor.y);
        return obiektsuma;
    }
    public Vector2D roznicawetorow(Vector2D wektor)  //Metoda z parametrem podanego jako parametr, zwaracaj�ca nowy obiekt
    {                                                //klasy Vector2D ( r�nica wektor�w)
        Vector2D obiektroznica =new Vector2D(this.x-wektor.x,this.y-wektor.y);
        return obiektroznica;
    }
    public Vector2D stalawektor(double stala)   //Metoda z parametrem w postaci sta�ej przez kt�r� mno�ymy wspo�rz�dne wektora
    {
        Vector2D obiektstala=new Vector2D(this.x *stala,this.y*stala);
        return obiektstala;
    }
    public double dlugoscwektora()            //Metoda bez parametru zwracaj�ca d�ugo�c wektora
    {
        return Math.sqrt(this.x *this.x + this.y*this.y);

    }
    public Vector2D normalizacja()     //Metoda bez parametru zwracaj�ca znormalizowany wektor
    {
        double dlugosc =dlugoscwektora();
        Vector2D normwektor=new Vector2D(this.x/dlugosc,this.y/dlugosc);
        return normwektor;
    }
}