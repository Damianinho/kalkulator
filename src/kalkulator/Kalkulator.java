package kalkulator;
import java.awt.*;
import javax.swing.*;     //zaimportowanie potrzebnych bibliotek
import java.awt.event.*;

public class Kalkulator extends JFrame implements ActionListener { //deklaracja głównej klasy porgramu
    
    JPanel[] panel = new JPanel[5];      
    JButton[] button = new JButton[19];    //deklaracja tablic paneli i przycisków
    String[] przyciski = {"7", "8", "9", "+",
                             "4", "5", "6", "-",
                             "1", "2", "3", "*",    //deklaracja nazw przycisków
                             ".", "/", "C", "√",
                             "+/-", "=", "0"};
    int[] szerokosc = {285,55,65,112};       //ustawienie szerokosci i wysokosci elementów programu(przyciski, wyswietlacz)
    int[] wysokosc = {50, 50};
    Dimension ekranDimension = new Dimension(szerokosc[0], wysokosc[0]);
    Dimension podstawaDimension = new Dimension(szerokosc[1], wysokosc[1]);
    Dimension kolumnaDimension = new Dimension(szerokosc[2], wysokosc[1]);    //przydzielenie wartosci szer. i wys. poszczegolnym elementom
    Dimension zeroDimension = new Dimension(szerokosc[3], wysokosc[1]);
    boolean[] funkcje = new boolean[4];  //deklaracja tablicy tpyu boolean
    double[] przejscie = {0, 0}; //deklaracja tablicy typu double
    JTextArea ekran = new JTextArea();  //deklaracja obiektu JTextArea o nazwie ekran
    Font font = new Font("Times new Roman", Font.BOLD, 30); //ustawienia parametrów czcionki
    
    Kalkulator() {
        super("Kalkulator");
        setDesign();
        setSize(310, 300);
        setResizable(false);                        //ustawienia parametrów okna programu
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(5,5);
        setLayout(grid);
        
        for(int i = 0; i < 4; i++) //pętla for zawierająca 3 funkcje
            funkcje[i] = false;
        
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER,1,1); //zarządzanie układem elementów programu poprzez FlowLayout
        
        for(int i = 0; i < 5; i++)    
            panel[i] = new JPanel();  //inicjalizacja JPanelu poprzez użycie pętli for
        	panel[0].setLayout(f1);     
        for(int i = 1; i < 5; i++)
            panel[i].setLayout(f2);  //przydzielenie panelom odpowiednich elementów poprzez użcyie pętli for
        
        for(int i = 0; i < 19; i++) {
            button[i] = new JButton(); //tworzenie tablicy przycisków
            button[i].setText(przyciski[i]); //przydzielenie przyciskom kolejnych nazw z tablicy typu String
            button[i].setFont(font); //ustawienie wcześniej zadeklarowanej czcionki
            button[i].addActionListener(this);  //implementacja funkcji do obsługi zdarzeń
        }
        
        ekran.setFont(font);  //ustawienie czcionki ekranu
        ekran.setEditable(false); //zablokowanie możliwości zmiany rozmiaru ekranu
        ekran.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); //wyrównanie tekstu do prawej
        ekran.setPreferredSize(ekranDimension); //przydzielenie rozmiaru ekranu
        
        for(int i = 0; i < 14; i++)
            button[i].setPreferredSize(podstawaDimension);   
        for(int i = 14; i < 18; i++)
            button[i].setPreferredSize(kolumnaDimension);
        button[18].setPreferredSize(zeroDimension);       //przydzielenie odpowiednich rozmiarów przyciskom, poprzez pętle for
        
        panel[0].add(ekran);
        add(panel[0]);       //dodanie elementu JTextArea do panelu 0
        
        for(int i = 0; i < 4; i++)
            panel[1].add(button[i]);
        panel[1].add(button[14]);
        add(panel[1]);
        
        for(int i = 4; i < 8; i++)
            panel[2].add(button[i]);
        panel[2].add(button[15]);
        add(panel[2]);                        /*przyporządkowanie odpowiednich przycisków do konkretnych paneli
        									   * przy użyciu pętli for
                                              */
        
        for(int i = 8; i < 12; i++)
            panel[3].add(button[i]);
        panel[3].add(button[16]);
        add(panel[3]);
        
        panel[4].add(button[18]);
        for(int i = 12; i < 14; i++)
            panel[4].add(button[i]);
        panel[4].add(button[17]);
        add(panel[4]);
        
        
        setVisible(true); //ustawienie widoczności elementów na wartość "true"
    }
    
    public void czysc() {  //funkcja czysczaca zawartosc ekranu (przycisk C)
        try {
            ekran.setText(""); //wyswietlanie pustego tekstu na ekranie
            for(int i = 0; i < 4; i++)
                funkcje[i] = false;  // ustawienie wartosci funkcji na "false"  
            for(int i = 0; i < 2; i++)
                przejscie[i] = 0;  // ustawienie wartości zmiennej tymczasowej na 0 
        } catch(NullPointerException e) {  
        }
    }
    
    public void getSqrt() {
        try {
            double wartość = Math.sqrt(Double.parseDouble(ekran.getText()));
            //stworzenie zmiennej typu double dla wartości oraz użycie funkcji pierwiastka
            ekran.setText(Double.toString(wartość)); 
            //wyswietlenie wyniku na ekranie, poprzez konwersje zmiennej double do string
        } catch(NumberFormatException e) {
        }
    }
    
    public void getPosNeg() {
        try {
            double wartość = Double.parseDouble(ekran.getText()); //tworzenie zmiennej wartość typu double
            if(wartość != 0) { //jezeli wartość jest różna od 0
                wartość = wartość * (-1);//zmieniamy ją na ujemną, mnożąc przez (-1)
                ekran.setText(Double.toString(wartość));//przesłanie tekstu do nowej zmiennej
            }
            else {
            }
        } catch(NumberFormatException e) {
        }
    }
    
    public void getwynik() {
        double wynik = 0; //przypisanie zmiennej dla wyniku
        przejscie[1] = Double.parseDouble(ekran.getText());
        String zmiennik0 = Double.toString(przejscie[0]);
      //zmienna typu string dla tekstu pierwszej zmiennej typu string
        String zmiennik1 = Double.toString(przejscie[1]);
      //zmienna typu string dla tekstu drugiej zmiennej typu string
        try {
            if(zmiennik0.contains("-")) { //jesli pierwsza zmienna zawiera "-"
                String[] zmiennik00 = zmiennik0.split("-", 2);//dzielenie na 2 zmienne typu string
                przejscie[0] = (Double.parseDouble(zmiennik00[1]) * -1); //ponowne przydzielenie ciągowi typu double
            }
            if(zmiennik1.contains("-")) { //ta sama zasada działania, jak wyżej
                String[] zmiennik11 = zmiennik1.split("-", 2);
                przejscie[1] = (Double.parseDouble(zmiennik11[1]) * -1);
            }
        } catch(ArrayIndexOutOfBoundsException e) {
        }
        try {
            if(funkcje[2] == true)  //zaczynamy od mnożenia
                wynik = przejscie[0] * przejscie[1];//funkcja mnożenia
            else if(funkcje[3] == true)
                wynik = przejscie[0] / przejscie[1]; //dzielenie
            else if(funkcje[0] == true)
                wynik = przejscie[0] + przejscie[1]; //dodawanie
            else if(funkcje[1] == true)
                wynik = przejscie[0] - przejscie[1]; //odejmowanie
            ekran.setText(Double.toString(wynik));
            for(int i = 0; i < 4; i++)
                funkcje[i] = false;  //przydzielamy ponownie wszystkim funkcjom wartość false
        } catch(NumberFormatException e) {
        }
    }
    
    public final void setDesign() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");  //metoda setDesign, konieczna do wykonania programu
        } catch(Exception e) {   
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == button[0])
            ekran.append("7");
        if(ae.getSource() == button[1])
            ekran.append("8");                   
        if(ae.getSource() == button[2])
            ekran.append("9");                  // obsługa zdarzeń poszczególnych przycisków, przy użyciu metody getSource
        if(ae.getSource() == button[3]) {
            // funkcja dodawania
            przejscie[0] = Double.parseDouble(ekran.getText());
            funkcje[0] = true;
            ekran.setText("");
        }
        if(ae.getSource() == button[4])
            ekran.append("4");
        if(ae.getSource() == button[5])
            ekran.append("5");
        if(ae.getSource() == button[6])
            ekran.append("6");
        if(ae.getSource() == button[7]) {
            //funkcja  odejmowania
            przejscie[0] = Double.parseDouble(ekran.getText());
            funkcje[1] = true;
            ekran.setText("");
        }
        if(ae.getSource() == button[8])
            ekran.append("1");
        if(ae.getSource() == button[9])
            ekran.append("2");
        if(ae.getSource() == button[10])
            ekran.append("3");
        if(ae.getSource() == button[11]) {
            //funkcja mnozenia
            przejscie[0] = Double.parseDouble(ekran.getText());
            funkcje[2] = true;
            ekran.setText("");
        }
        if(ae.getSource() == button[12])
            ekran.append(".");
        if(ae.getSource() == button[13]) {
            //funkcja dzielenia
            przejscie[0] = Double.parseDouble(ekran.getText());
            funkcje[3] = true;
            ekran.setText("");
        }
        if(ae.getSource() == button[14])
            czysc();
        if(ae.getSource() == button[15])
            getSqrt();
        if(ae.getSource() == button[16])
            getPosNeg();
        if(ae.getSource() == button[17])
            getwynik();
        if(ae.getSource() == button[18])
            ekran.append("0");
    }
    
    public static void main(String[] arguments) {
        Kalkulator c = new Kalkulator();
    }
}

