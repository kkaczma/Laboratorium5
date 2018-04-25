import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SpringApplet extends JApplet implements MouseListener, MouseMotionListener,ActionListener{
	private SimEngine obiekt;
    private SimTask obiekt11;
    private Timer timer;
    //Prywatne pola do przechowywania wartoœci wspó³rzêdnych punktu
    private int x,y;
    //Pola do przechowywani wartoœci masy
    int szerokoscmasy=24;
    int wysokoscmasy=20;
   
    
    //Prywatne pole do przechowywania wartoœci logicznej (przeci¹gniecie kursora)
    private boolean mysz;

    //Prywatne pola do przechowywania przycisków
      private Button reset;
     //Prywatne pola do przechowywania pola tekstowego
      private TextField masa,wspolczynnik_k,wspolczynnik_c,przyspieszenie,dlugosc;
      
      
      //Metody konieczne do implementacji interfejsu ActionListener
      
      @Override
  	  public void actionPerformed(ActionEvent e) {
     	//Sprawdzenie czy zród³em akcji jest stworzony przycisk
      	if(e.getSource()==reset)
      	{
      		timer.cancel();
      	    //Utworzenie obiektu SimEngine z ustawionymi parametrami
            obiekt=new SimEngine(Double.parseDouble(masa.getText()),Double.parseDouble(wspolczynnik_c.getText()),Double.parseDouble(wspolczynnik_c.getText()),
            		Double.parseDouble(dlugosc.getText()),1,10,50,Double.parseDouble(przyspieszenie.getText()));
      	     //Utowrzenie obiektu SimTask
            obiekt11 =new SimTask(obiekt,this,0.5);
            // Utworzenie obiektu klsay Timer
            timer = new Timer();
            //Uruchomienie timera
            timer.scheduleAtFixedRate(obiekt11,0,150);
      		repaint();
       }
  	}

    
   
    //Metody konieczne do implementacji interfejsów MouseMotionListener
    
   //Metoda wywo³ana,gdy wciœniemy przycisk i przeci¹gniemy kursor
    @Override
	public void mouseDragged(MouseEvent arg0) {
    	//Sprawdzenie czy nast¹pi³o przeciaganie kursora
    	if(mysz==true)
    	{
    		//Odczytanie pozycji kursora
    		x=arg0.getX();
    		y=arg0.getY();
    		//Ustawienie pozycji masy
    		obiekt.Masa(y-obiekt.Dlugosc_swobodna_sprezyny());
    		repaint();
    	}
    	arg0.consume();
	}
    //Metoda wywo³ana gdy poruszamy kursor w obszarze nas³uchuj¹cym zdarzenia
	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	//Metody konieczne do implementacji interfejsów MouseListener
	@Override
	//Metoda wywo³ana, gdy nastêpujê klikniêcie przycisku myszy
	public void mouseClicked(MouseEvent e) {
	}
	//Metoda wywo³ana,gdy zostaje wciœniety przycisk myszy
	@Override
	public void mousePressed(MouseEvent e) {
		//Odczytanie po³o¿enia kursora
		this.x=e.getX();
		this.y=e.getY();
		//Sprawdzenie czy kursor znajduje sie w poblizu elipsy
		if(x>290 && x<335 && y>((int)obiekt.Dlugosc_swobodna_sprezyny()+(int)obiekt.Polozenie_masy()-20) &&
				y<((int)obiekt.Dlugosc_swobodna_sprezyny()+50+(int)obiekt.Polozenie_masy()))
		{
			//Wy³¹czenie timera
			timer.cancel();
			//Zrestarowanie symulacji
			obiekt.reset();
			//Ustawienie wartoœci logicznej na 1
			mysz=true;
		}
		else {}
		//Wywo³anie metody consume() dla obiekty MouseEvent
		e.consume();
	}
	
	//Metoda wywo³ana gdy nastêpuje zwolnienie przycisku myszy
	@Override
	public void mouseReleased(MouseEvent e) {
		if(mysz==true)
		{
			
	        //Utowrzenie obiektu SimTask
	        obiekt11 =new SimTask(obiekt,this,0.5);
	        //Uruchomienie Timera
			timer=new Timer();
			timer.scheduleAtFixedRate(obiekt11,0,150);
			//Ustawienie wrtoœci logicznej na 0
			mysz=false;
			}
		e.consume();
	}
	
	//Metoda wywo³ana gdy kursor pojawia siê w obszarze nas³uchujacym na zdarzenia 
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	//Metoda wywo³ana gdy kursor opuszcza obszar nas³uchuj¹cy zdarzenia 
	@Override
	public void mouseExited(MouseEvent e) {
	}

	
    public void init()
    {
    	
    	//Ustawienie rozmiarów appletu
        setSize(600,540);
        //Nadanie wartoœci logicznej polu przechowuj¹cego stan myszy
        mysz=false;
        //Dodadnie nas³uchiwacza myszy
    	addMouseListener(this);
    	//Dodanie nas³uchiwacza ruchu myszy
    	addMouseMotionListener(this);
    	 //Inicjalizacja stworzonych pol nowymi obiektami
    	reset=new Button("Reset");
        masa=new TextField("10");
    	wspolczynnik_k=new TextField("0.1");
    	wspolczynnik_c=new TextField("0.5");
    	przyspieszenie=new TextField("10");
    	dlugosc=new TextField("100");
    	
    	
    	
    	//Dodanie elementów GUI do appletu
    	add(masa);
    	add(wspolczynnik_k);
    	add(wspolczynnik_c);
    	add(przyspieszenie);
    	add(dlugosc);
    	add(reset);
    	
    	 //Dodanie nas³uchiwacza od obiektu reset
    	 reset.addActionListener(this);
    	
        //Utworzenie obiektu SimEngine
        obiekt=new SimEngine(10,0.5,0.1,100,1,10,50,10);
        //Utowrzenie obiektu SimTask
        obiekt11 =new SimTask(obiekt,this,0.5);
        // Utworzenie obiektu klsay Timer
        timer = new Timer();
        //Uruchomienie timera
        timer.scheduleAtFixedRate(obiekt11,0,150);
       
       

    	
    }
    public void paint(Graphics g)
    {   
    //Wyczyszczenie powierzchni appletu
        g.setColor(Color.WHITE);
        g.fillRect(0,0,600,540);
        g.setColor(Color.BLACK);
    //Utworzenie siatki wspó³rzêdnych
        for(int y=0;y<9;y++)
        {
        	for(int x =0;x<10;x++)
        	{
        		g.drawRect(x,y,x*60+60,y*60+60);
        	}
        }
        
        //USTAWIENIA LINII
        //Tworzenie obiektu graficznego typu Graphics2D ( rozszerza klasê Graphics)
        Graphics2D g2=(Graphics2D)g;
       
        //Ustawnienie szerokoœci linii na 3 punkty
        BasicStroke grubaLinia= new BasicStroke(3.0f);
        g2.setStroke(grubaLinia);

        //Ustawienie uwierdzenia(linia,kreskowanie)
        g.drawLine(150,50,475,50);
        for(int i=0;i<14;i++)
        {
            g.drawLine(150+25*i,50,160+25*i,40);
        }
        //USTAWIENIA CZCIONKI
        //Nazwa czcionki,tekst pogrubiony,wielkoœæ
        Font czcionka = new Font( "Arial", Font.BOLD, 12 );
        g.setFont ( czcionka );

        g.setColor(Color.red);
        g.drawString("PARAMETRY", 20, 80);
        
        //Ustawienie koloru
        g.setColor(Color.green);
        //Ustawienie napisu
        g.drawString("Masa", 20,100);
        //Ustawienie po³o¿enia ramki
        masa.setSize(40, 20);
		masa.setLocation(110,85);
        
        g.drawString("Wspó³czynnik",20,120);
        g.drawString("sprê¿ystoœci",20,130);
        wspolczynnik_k.setSize(40, 20);
		wspolczynnik_k.setLocation(110,110);
        
        g.drawString("Wspó³czynnik",20,150);
        g.drawString("t³umienia",20,160);
        wspolczynnik_c.setSize(40, 20);
		wspolczynnik_c.setLocation(110,140);
        
        g.drawString("Przyspieszenie",20,180);
        g.drawString("grawitacyjne",20,190);
        przyspieszenie.setSize(40, 20);
		przyspieszenie.setLocation(110,170);
        
        g.drawString("D³ugoœæ",20,210);
        g.drawString("spoczynkowa",20,220);
        g.drawString("sprê¿yny",20,230);
        dlugosc.setSize(40, 20);
		dlugosc.setLocation(110,210);
        
        //Ustawienie przycisku
        reset.setSize(50, 50);
		reset.setLocation(80,250);
        
    
        ///Utworzenie liny
        g.setColor(Color.LIGHT_GRAY);
         g.drawLine(313,50, 313,(int)obiekt.Dlugosc_swobodna_sprezyny()+(int)obiekt.Polozenie_masy());
        //Utworzenie masy
         g.setColor(Color.blue);
        g.fillOval(313-szerokoscmasy/2,(int)obiekt.Dlugosc_swobodna_sprezyny()+(int)obiekt.Polozenie_masy()-wysokoscmasy/2, szerokoscmasy,wysokoscmasy);
       
   }
}