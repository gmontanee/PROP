package presentacio.vistes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.util.*;

import presentacio.vistes.navFullesView;
import presentacio.vistes.menuBarView;
import presentacio.vistes.gridView;

public class mainView {

    static JFrame mainFrame;

    static String fullaActiva;

    //Frame que s'obre quan volem afegir una nova fulla passa el mateix que a dalt, no tenim volem popup menu que desapareixi
    static JFrame menuAfegirFulla;

    //top text field
    static JTextField topBarInp;

    //label top left
    static JLabel selectedCell;

    //es per tenir un map amb les fulles i no haverles de carregar cada cop pero serà més facil guardar la fulla amb el controlardor i fer un render cada cop que canviem la fulla.
    static HashMap<String, JScrollPane> fulles = new HashMap<>();

    /*
        funcio encarregada d'actualitzar el mainframe amb la nova fulla i amb les noves dades
    */
    public static void render(){
        //llegir el update del codi del controlador.
        mainFrame.revalidate();
    }

    /*
        Funcio encarregada d'inicialitzar el mainframe amb totes les seves propiestats.
    */
    public mainView() {
        mainFrame = new JFrame("test");
        mainFrame.getContentPane().setLayout(new GridBagLayout());
        
        setBackgroundColor();
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1500, 1000);
    
        mainFrame.setJMenuBar(new menuBarView().menuBar);

        GridBagConstraints gbc = new GridBagConstraints();


        JPanel topBar = topTextBar();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainFrame.add(topBar, gbc);


        JScrollPane grid = new JScrollPane();

        for(int i = 1; i <= 3; ++i){
            JScrollPane fulla = new gridView().mainGrid;

            fulles.put(""+i, fulla);

            grid.add(fulla);
        }

        
        /*
        1, 0, 0.3, 0.9, 1, 1, GridBagConstraints.BOTH
        */
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        fullaActiva = "1";

        mainFrame.add(fulles.get("1"), gbc);

        // 0, 1, 1.0, 0.1, 2, 1, GridBagConstraints.BOTH
        JScrollPane hojas = new gridView().mainGrid;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.005;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mainFrame.add(hojas, gbc);
    }

    /*
        Funcio auxiliar de prova que posa color al fons de la pantalla (no fa falta era a la fase d'apendre treure al passar al final)
    */
    private static void setBackgroundColor(){
        mainFrame.getContentPane().setBackground(new Color(254, 100, 100));
    }

    public void setMainMSVisible(Boolean bool) {
        mainFrame.setVisible(bool);
    }
    
    /*
        Funcio que inicialitza el submenu que s'obre al afegir una fulla.
    */
    public static void subMenuAfegirFulla(){
        menuAfegirFulla = new JFrame("Afegir Fulla");
        menuAfegirFulla.getContentPane().setLayout(new GridBagLayout());
            

        menuAfegirFulla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuAfegirFulla.setSize(500, 275);
        
        JPanel menu = new JPanel(new GridLayout(7, 1, 5, 5));

        JLabel nomFulla = new JLabel("Introdueix el nom de la Fulla");
        String numFulla = Integer.toString(fulles.size()+1);
        JTextField nom = new JTextField(numFulla);
        JLabel textFiles = new JLabel("Introdueix les files: ");
        JTextField files = new JTextField("100");
        JLabel textCol = new JLabel("Introdueix les columnes: ");
        JTextField col = new JTextField("26");
        JButton createBTN = new JButton("Afegir Fulla");

        JScrollPane auxiliar = new gridView().mainGrid;

        createBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                fulles.put("fulla " + (fulles.size()+1), auxiliar);

                System.out.println(fulles.size());
                menuAfegirFulla.setVisible(false);
            }
        });

        menu.add(nomFulla);
        menu.add(nom);
        menu.add(textFiles);
        menu.add(files);
        menu.add(textCol);
        menu.add(col);
        menu.add(createBTN);
        menuAfegirFulla.add(menu);
    }

    private JPanel topTextBar(){
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel cellContainer = new JPanel();

        selectedCell = new JLabel("A1");
        cellContainer.add(selectedCell);
        
        topBar.add(cellContainer);

        topBarInp = new JTextField("valor cella");
        topBarInp.setPreferredSize(new Dimension(mainFrame.getWidth() - 100, 30));

        topBar.add(topBarInp);

        return topBar;
    }
}
