import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.event.MouseEvent;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.*;

import java.io.File;
import java.util.*;


/**
 * un cop clicat boto
 * try{
 *  reculls fila columna i nom i li passes al controlador que sigui i aquest al de domini.
 *  retorna o el fetch
 *  repaint revalidate.
 * } catch(e){
 *      pop up error visible
 * }
 */

class gui{
    //Frame principal que contindrà la pantalla on es veurà la graella el menu i el menu de seleccio de fulles
    static JFrame mainFrame;
    
    //Frame principal que veurem al executar la app, llavors aqui tindrem el menu de crear o exportar document.
    static JFrame welcomeScreen;

    //Frame que s'executa amb el menu de crear un nou document on tenim el numero de files i el numero de columnes d'aquests.
    //Ha de ser frame ja que si es popup menu al clicar un altre lloc es tancari i no volem aixo.
    static JFrame menuCrear;
    
    //Frame que s'obre quan volem afegir una nova fulla passa el mateix que a dalt, no tenim volem popup menu que desapareixi
    static JFrame menuAfegirFulla;
    
    //part auxiliar que ens ajuda a afegir una nova fulla, no se si farà falta.
    static JScrollPane auxiliar;
    
    //es per contenir la fulla activa pero no fara falta a la part final ja que farem un render cada cop que canviem de fulla.
    static String fullaActiva;

    //label top left
    static JLabel selectedCell;
    //top text field
    static JTextField topBarInp;
    
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
    public static void init(){
        mainFrame = new JFrame("test");
        mainFrame.getContentPane().setLayout(new GridBagLayout());
        
        setBackgroundColor();
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1500, 1000);
    
        mainFrame.setJMenuBar(createMenuBar());

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
            JScrollPane fulla = MainGrid();

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
        JPanel  hojas = panelFulles();
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
        Funcio encarregada d'inicilitzar la pantalla de benvinguda.
    */
    public static void initWelcome(){
        welcomeScreen = new JFrame("Welcome");
        welcomeScreen.getContentPane().setLayout(new GridBagLayout());
            

        welcomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeScreen.setSize(1000, 750);
        
        JPanel menu = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel text = new JLabel("Benvingut, Escull una opcio");
        JButton createBTN = new JButton("Crear Document");
        JButton importBTN = new JButton("Importar Document");

        createBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                menuCrear.setVisible(true);
            }
        });

        importBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                menuCrear.setVisible(false);

                JFileChooser fileInput = new JFileChooser();
                int returnValue = fileInput.showOpenDialog(null);
                // int returnValue = jfc.showSaveDialog(null);
        
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileInput.getSelectedFile();

                    // cvs reader = new cvs();

                    // reader.read(selectedFile.getAbsolutePath());

                    if(selectedFile.getAbsolutePath() != null){
                        menuCrear.setVisible(false);
                        welcomeScreen.setVisible(false);
                        mainFrame.setVisible(true);
                    }
                }
            }
        });

        menu.add(text);
        menu.add(createBTN);
        menu.add(importBTN);
        welcomeScreen.add(menu);
    }

    /*
        Funcio encarregada d'inicialitzar el submenu per crear el document.
    */
    public static void subMenuCrearDocument(){
        menuCrear = new JFrame("Crear");
        menuCrear.getContentPane().setLayout(new GridBagLayout());
            

        menuCrear.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuCrear.setSize(500, 250);
        
        JPanel menu = new JPanel(new GridLayout(5, 1, 10, 10));

        JLabel textFiles = new JLabel("Introdueix les files: ");
        JTextField files = new JTextField("100");
        JLabel textCol = new JLabel("Introdueix les columnes: ");
        JTextField col = new JTextField("26");
        JButton createBTN = new JButton("Crear Document");

        createBTN.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                menuCrear.setVisible(false);
                welcomeScreen.setVisible(false);
                mainFrame.setVisible(true);
            }
        });

        menu.add(textFiles);
        menu.add(files);
        menu.add(textCol);
        menu.add(col);
        menu.add(createBTN);
        menuCrear.add(menu);
    }
    
    /*
        Funcio auxiliar de prova que posa color al fons de la pantalla (no fa falta era a la fase d'apendre treure al passar al final)
    */
    private static void setBackgroundColor(){
        mainFrame.getContentPane().setBackground(new Color(254, 100, 100));
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

        JScrollPane auxiliar = MainGrid();

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

    /*
        main principal que posa inicialitza tots els frames i nomes posa en visible el welcome screen.
    */
    public static void main (String args[]){
        init();
        initWelcome();
        subMenuCrearDocument();
        subMenuAfegirFulla();

        welcomeScreen.setVisible(true);
        mainFrame.setVisible(false);
        menuCrear.setVisible(false);
        menuAfegirFulla.setVisible(false);
        
    }

    /*
        retorna la part de document del menu 
    */
    private static JMenu DocumentMenu() {
        JMenu fileMenu = new JMenu("Document");
   
        JMenuItem openItem = new JMenuItem(new AbstractAction("Guardar") {
            public void actionPerformed(ActionEvent e) {
                // Button pressed logic goes here
                JFileChooser fileInput = new JFileChooser();
                
                fileInput.showSaveDialog(fileInput);

                File f = fileInput.getSelectedFile();

                // cvs writer = new cvs();

                // writer.write(f.getAbsolutePath());
            }
        });
        
        fileMenu.add(openItem);
        return fileMenu;
    }

    /*
        retorna la part de fulla del menu
    */
    private static JMenu FullaMenu() {
        JMenu fileMenu = new JMenu("Fulla");
        JMenuItem newItem = new JMenuItem(new AbstractAction("Afegir Fulla") {
            public void actionPerformed(ActionEvent e) {
                menuAfegirFulla.setVisible(true);
            }
        });
        fileMenu.add(newItem);


        JMenuItem deleItem = new JMenuItem(new AbstractAction("Eliminar Fulla") {
            public void actionPerformed(ActionEvent e) {
                fulles.remove(fullaActiva);
                fullaActiva = "1";
                System.out.println(fulles.size());
            }
        });


        fileMenu.add(deleItem);
        JPopupMenu nom = menuCanviarNom();
        JMenuItem canviar = new JMenuItem(new AbstractAction("Canviar Nom Fulla") {
            public void actionPerformed(ActionEvent e) {
                int width = (int)(mainFrame.getSize().getWidth() / 2.4);
                int height = (int)(mainFrame.getSize().getHeight() / 3);

                nom.show(mainFrame, width, height);
            }
        });
        fileMenu.add(canviar);
        fileMenu.addSeparator();

        //APLICAR LA FUNCIÓ DE RENDER AGAIN UN COP EL CNTR DE DOMINI MODIFIQUI LA FULLA RECARREGAR LA VISTA.
        JMenuItem addCol = new JMenuItem("Afegir Columna");
        fileMenu.add(addCol);
        //APLICAR LA FUNCIÓ DE RENDER AGAIN UN COP EL CNTR DE DOMINI MODIFIQUI LA FULLA RECARREGAR LA VISTA.
        JMenuItem addRow = new JMenuItem("Afegir Fila");
        fileMenu.add(addRow);
        fileMenu.addSeparator();
        //APLICAR LA FUNCIÓ DE RENDER AGAIN UN COP EL CNTR DE DOMINI MODIFIQUI LA FULLA RECARREGAR LA VISTA.
        JMenuItem delCol = new JMenuItem("Eliminar Columna");
        fileMenu.add(delCol);
        //APLICAR LA FUNCIÓ DE RENDER AGAIN UN COP EL CNTR DE DOMINI MODIFIQUI LA FULLA RECARREGAR LA VISTA.
        JMenuItem delRow = new JMenuItem("Eliminar Fila");
        fileMenu.add(delRow);

        fileMenu.addSeparator();
        JPopupMenu jp = menuBuscar();
        JMenuItem buscar = new JMenuItem(new AbstractAction("Buscar Valor") {
            public void actionPerformed(ActionEvent e) {
                int width = (int)mainFrame.getSize().getWidth() - 275;

                jp.show(mainFrame, width,55);
            }
        });
        fileMenu.add(buscar);

        JPopupMenu rempl = menuRemplazar();
        JMenuItem rem = new JMenuItem(new AbstractAction("Remplasar Valor") {
            public void actionPerformed(ActionEvent e) {
                int width = (int)mainFrame.getSize().getWidth() - 300;

                rempl.show(mainFrame, width,55);
            }
        });
        fileMenu.add(rem);
        return fileMenu;
    }

    /*
        retorna el pop up menu per seleccionar el bloc aixo marxara si aconsegueixo fer lo de seleccionar bloc pero nidea de com polles es fa
    */
    private static JPopupMenu seleccionarBloc(){
        JPopupMenu menu = new JPopupMenu("menu");

        JLabel text = new JLabel("Introdueix les coordenades de la primera cela:");
        JTextField inp = new JTextField("A1");
        JLabel text2 = new JLabel("Introdueix les coordenades de la segona cela:");
        JTextField inp2 = new JTextField("B1");

        JPanel aux = new JPanel(new GridLayout(5, 1, 5, 5));

        inp.setPreferredSize(new Dimension(100, 20));

        aux.add(text);
        aux.add(inp);
        aux.add(text2);
        aux.add(inp2);

        menu.setLayout(new GridBagLayout());
        menu.setPreferredSize(new Dimension(350,210));

        JButton btn = new JButton("Seleccionar Bloc");
        aux.add(btn);
        
        menu.add(aux);
        return menu;
    }

    /*
        retorna la part de bloc del menu
    */
    private static JMenu BlocMenu() {
        JMenu fileMenu = new JMenu("Bloc");
        //ELIMINA EL DOCUMENT ACTUAL I TREU UN POP UP PER QUE ENTRI LES FILES COLUMNES ETC. treure pop up alertant.
        JPopupMenu seleccionar = seleccionarBloc();
        JMenuItem sel = new JMenuItem(new AbstractAction("Seleccionar Bloc") {
            public void actionPerformed(ActionEvent e) {
                int width = (int)(mainFrame.getSize().getWidth() - 375);
                int height = 55;

                seleccionar.show(mainFrame, width, height);
            }
        });
        fileMenu.add(sel);

        JMenuItem copiar = new JMenuItem("Copiar Bloc");
        fileMenu.add(copiar);

        JMenuItem eng = new JMenuItem("Enganxar Bloc");
        fileMenu.add(eng);

        JMenuItem el = new JMenuItem("Eliminar Bloc");
        fileMenu.add(el);

        JMenuItem ret = new JMenuItem("Retallar Bloc");
        fileMenu.add(ret);

        JMenuItem ord = new JMenuItem("Ordenar Bloc");
        fileMenu.add(ord);

        //POP UP AMB LLES 2 OPCIONS
        return fileMenu;
    }

    /*
        retorna la part de les funcions del menu.
    */
    private static JMenu FuncionsMenu() {
        //TOT AQUEST S'IMPLEMENTARA AMB EL CONTROLADOR DE DOMINI PASSANT ELS VALORS DE LA CEL·LA.
        JMenu fileMenu = new JMenu("Funcions");
        
        JMenu aritmeticMenu = new JMenu("Operacions aritmetiques");
        JMenuItem suma  = new JMenuItem("Suma");
        aritmeticMenu.add(suma);
        JMenuItem resta  = new JMenuItem("Resta");
        aritmeticMenu.add(resta);
        JMenuItem mult  = new JMenuItem("Multiplicacio");
        aritmeticMenu.add(mult);
        JMenuItem div  = new JMenuItem("Divisio");
        aritmeticMenu.add(div);
        JMenuItem arrel  = new JMenuItem("Arrel");
        aritmeticMenu.add(arrel);
        JMenuItem pot  = new JMenuItem("Potencia");
        aritmeticMenu.add(pot);
        JMenuItem valorAbs  = new JMenuItem("Valor Absolut");
        aritmeticMenu.add(valorAbs);
        JMenuItem truncar  = new JMenuItem("Truncar Valor");
        aritmeticMenu.add(truncar);
        fileMenu.add(aritmeticMenu);
        fileMenu.addSeparator();

        JMenu funcionsCalcul = new JMenu("Operacions estadistica");
        JMenuItem mitjana = new JMenuItem("Mitjana");
        funcionsCalcul.add(mitjana);
        JMenuItem mediana = new JMenuItem("Mediana");
        funcionsCalcul.add(mediana);
        JMenuItem variancia = new JMenuItem("Variancia");
        funcionsCalcul.add(variancia);
        JMenuItem covariancia = new JMenuItem("Covariancia");
        funcionsCalcul.add(covariancia);
        JMenuItem desvEstandard = new JMenuItem("Desviacio Estandard");
        funcionsCalcul.add(desvEstandard);
        JMenuItem pearson = new JMenuItem("Coeficient de Pearson");
        funcionsCalcul.add(pearson);
        fileMenu.add(funcionsCalcul);
        fileMenu.addSeparator();


        JMenu funcionsData = new JMenu("Operacions amb dates");
        JMenuItem any = new JMenuItem("Obtenir Any");
        funcionsData.add(any);
        JMenuItem mes = new JMenuItem("Obtenir Mes");
        funcionsData.add(mes);
        JMenuItem dia = new JMenuItem("Obtenir Dia");
        funcionsData.add(dia);
        JMenuItem diaSet = new JMenuItem("Obtenir Dia Setmana");
        funcionsData.add(diaSet);
        fileMenu.add(funcionsData);
        fileMenu.addSeparator();

        JMenu funcionsText = new JMenu("Operacions amb text");
        JMenuItem longitud = new JMenuItem("Obtenir Longitud");
        funcionsText.add(longitud);
        fileMenu.add(funcionsText); 
        
        return fileMenu;
    }

    /*
        retorna el menu complet que s'afegira a dalt del mainframe.
    */
    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(DocumentMenu());
        menuBar.add(FullaMenu());
        menuBar.add(BlocMenu());
        menuBar.add(FuncionsMenu());

        return menuBar;
    }

    /*
        pop up que surt quan volem canviar el nom de la fulla en questió
    */
    private static JPopupMenu menuCanviarNom(){
        JPopupMenu menu = new JPopupMenu("menu");
        JLabel text = new JLabel("Introdueix el nou nom de la fulla actual:");
        JTextField inp = new JTextField("Nou nom");
        JPanel aux = new JPanel(new GridLayout(3, 1, 5, 10));

        inp.setPreferredSize(new Dimension(100, 20));

        aux.add(text);
        aux.add(inp);

        menu.setLayout(new GridBagLayout());
        menu.setPreferredSize(new Dimension(250,110));

        JButton btn = new JButton("Canviar Nom");
        aux.add(btn);
        
        menu.add(aux);
        return menu;
    }

    /*
        menu que apareix al fer click dret a la zona de botons de la fulla.
    */
    private static JPopupMenu fullaClickDret(){
        JPopupMenu menu = new JPopupMenu("menu");
        
        JMenuItem afegir = new JMenuItem("Afegir Fulla");
        menu.add(afegir);

        JMenuItem eli = new JMenuItem("Eliminar Fulla");
        menu.add(eli);
        
        menu.addSeparator();

        JMenuItem eliFila = new JMenuItem("Eliminar Fila");
        menu.add(eliFila);

        JMenuItem eliCol = new JMenuItem("Eliminar Columna");
        menu.add(eliCol);

        menu.addSeparator();

        JPopupMenu nom = menuCanviarNom();
        JMenuItem canviar = new JMenuItem(new AbstractAction("Canviar Nom Fulla") {
            public void actionPerformed(ActionEvent e) {
                int width = (int)(mainFrame.getSize().getWidth() / 2.4);
                int height = (int)(mainFrame.getSize().getHeight() / 3);

                nom.show(mainFrame, width, height);
            }
        });
        menu.add(canviar);
        
        return menu;
    }

    /*
        part de sota del mainframe que apareix amb els botons de canviar de fulla i amb els botons per afegir eliminar fulla
    */
    private static JPanel panelFulles(){
        JPanel panel = new JPanel();

        for(int i = 1; i <= 3; ++i){
            JButton btn = new JButton("" + i);
            btn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 1;
                    gbc.gridy = 0;
                    gbc.weightx = 0.3;
                    gbc.weighty = 0.9;
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;
                    gbc.fill = GridBagConstraints.BOTH;

                    //mainFrame.remove(0);
                    mainFrame.add(fulles.get(btn.getText()), gbc);
                    mainFrame.revalidate();
                    fullaActiva = btn.getText();
                    System.out.println(btn.getText());
                }
            });

            panel.add(btn);
        }

        JButton addFulla = new JButton("Afegir Fulla");
        addFulla.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                menuAfegirFulla.setVisible(true);
            }
        });

        JButton eliminarFulla = new JButton("Eliminar Fulla");

        eliminarFulla.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                fulles.remove(fullaActiva);
                fullaActiva = "1";
                System.out.println(fulles.size());
            }
        });

        panel.add(addFulla);
        panel.add(eliminarFulla);

        JPopupMenu menu = fullaClickDret();    
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //right mouse click event
                if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1){
                    menu.show(panel , e.getX(), e.getY());
                }        
            }
        });
        
        panel.add(menu);



        JButton btn2 = new JButton("Repaint");
        btn2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                render();
            }
        });

        panel.add(btn2);
        return panel;
    }

    /*
        menu que apareix al fer click dret a qualsevol part de la pantalla que no sigui el menu de baix.
    */
    private static JPopupMenu menuClickDret(){
        JPopupMenu menu = new JPopupMenu("menu");
        
        JMenuItem copiar = new JMenuItem("Copiar");
        menu.add(copiar);

        JMenuItem enganxar = new JMenuItem("Enganxar");
        menu.add(enganxar);

        JMenuItem ret = new JMenuItem("Retallar");
        menu.add(ret);

        JMenuItem eli = new JMenuItem("Eliminar");
        menu.add(eli);

        menu.addSeparator();

        JMenuItem ordenar = new JMenuItem("Ordenar Bloc");
        menu.add(ordenar);

        JMenuItem copiarBloc = new JMenuItem("Retallar Bloc");
        menu.add(copiarBloc);

        JMenuItem enganxarBloc = new JMenuItem("Enganxar Bloc");
        menu.add(enganxarBloc);

        JMenuItem retBloc = new JMenuItem("Retallar Bloc");
        menu.add(retBloc);

        JMenuItem eliBloc = new JMenuItem("Eliminar Bloc");
        menu.add(eliBloc);
        
        menu.addSeparator();

        JMenuItem eliFila = new JMenuItem("Eliminar Fila");
        menu.add(eliFila);

        JMenuItem eliCol = new JMenuItem("Eliminar Columna");
        menu.add(eliCol);

        menu.addSeparator();

        JMenuItem buscar = new JMenuItem("Buscar Valor");
        menu.add(buscar);

        JMenuItem rempl = new JMenuItem("Remplazar Valor");
        menu.add(rempl);

        return menu;
    }

    /*
        menu que apareix al buscar una paraula dins de la fulla.
    */
    private static JPopupMenu menuBuscar(){
        JPopupMenu menu = new JPopupMenu("menu");
        JLabel text = new JLabel("Introdueix la paraula que vols buscar:");
        JTextField inp = new JTextField();
        JPanel aux = new JPanel(new GridLayout(3, 1, 5, 10));

        inp.setPreferredSize(new Dimension(100, 20));

        aux.add(text);
        aux.add(inp);

        menu.setLayout(new GridBagLayout());
        menu.setPreferredSize(new Dimension(250,110));

        JButton btn = new JButton("Buscar");
        aux.add(btn);
        
        menu.add(aux);
        return menu;
    }

    /*
        menu que apareix al buscar una paraula al voler remplezar.
    */
    private static JPopupMenu menuRemplazar(){
        JPopupMenu menu = new JPopupMenu("menu");

        JLabel text = new JLabel("Introdueix la paraula que vols buscar:");
        JTextField inp = new JTextField();
        inp.setPreferredSize(new Dimension(100, 20));

        JLabel text2 = new JLabel("Introdueix la paraula de remplas:");
        JTextField inp2 = new JTextField();
        inp2.setPreferredSize(new Dimension(100, 20));

        JPanel aux = new JPanel(new GridLayout(5, 1, 0, 0));

        aux.add(text);
        aux.add(inp);
        aux.add(text2);
        aux.add(inp2);

        JPanel btnContainer = new JPanel(new FlowLayout());
        JButton btn = new JButton("Remplasar");
        btnContainer.add(btn);
        JButton btnTot = new JButton("Remplasar Tot");
        btnContainer.add(btnTot);

        aux.add(btnContainer);

        menu.setLayout(new GridBagLayout());
        menu.setPreferredSize(new Dimension(275, 185));

        menu.add(aux);
        return menu;
    }

/** 
 *  MAY BE USED IN FUTURE NOT NOW
    private static char int2char(int i){
        return (char)(i + 64);
    }
    private static JTable leftGrid(){
        int files = 100;
        int col = 1;
        String mat[][] = new String[files][col];
        String names[] = new String[col];
        names[0] = "-";
        for(int i = 0; i < files; ++i){
            mat[i][0] = "" + (i + 1);
        }
        JTable res = new JTable(mat, names);
        res.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        res.getColumnModel().getColumn(0).setPreferredWidth(100);
        res.setRowHeight(25);
        return res;
    }*/

    /*
        retorna la graella de texts principals.
    */
    private static JScrollPane MainGrid(){
        int files = 100;
        int col = 26;
        
        String mat[][] = new String[files][col+1];
        
        //creacio contingut celes
        for(int i = 1; i <= files; ++i){
            mat[i-1][0] = "" + i;
        }
        String names[] = new String[col+1];
        char c = 'A';  
        
        names[0] = "-";
        for(int i = 1; i < 27; ++i){
            names[i] = ""+c;
            ++c;
        }

        //fem cel·les col0 no es puguin editar
        JTable grid = new JTable(mat, names){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 ? true : false;
            };
        };

        //posem el size de les cel·les.
        grid.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        grid.getTableHeader().setReorderingAllowed(false);

        grid.setRowHeight(25);
        
        for(int i = 0; i < col+1; ++i){
            if(i == 0) grid.getColumnModel().getColumn(i).setPreferredWidth(50);
            else grid.getColumnModel().getColumn(i).setPreferredWidth(100);
            
        }

        //canviar el color de la columna amb la classe de baix
        grid.getColumnModel().getColumn(0).setCellRenderer(new MyRenderer(Color.lightGray, Color.black));
        grid.getTableHeader().setBackground(Color.lightGray);

        //menu click dret
        JPopupMenu menu = menuClickDret();    
        grid.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //right mouse click event
                if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1){
                    menu.show(grid , e.getX(), e.getY());
                } else if(SwingUtilities.isLeftMouseButton(e)){
                    selectedCell.setText("" + grid.getSelectedRow() + grid.getSelectedColumn());
                    System.out.println(grid.getSelectedRow()); 
                    String out = "";
                    if(grid.getValueAt(grid.getSelectedRow(), grid.getSelectedColumn()) != null) out = "" + grid.getValueAt(grid.getSelectedRow(), grid.getSelectedColumn());
                    topBarInp.setText(out);
                }
            }
        });

        grid.add(menu);

        JScrollPane res = new JScrollPane(grid);

        return res;
    }


    private static JPanel topTextBar(){
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel cellContainer = new JPanel();

        selectedCell = new JLabel("A1");
        cellContainer.add(selectedCell);
        
        topBar.add(cellContainer);

        topBarInp = new JTextField("valor cella");
        topBarInp.setPreferredSize(new Dimension(mainFrame.getWidth() - 55, 30));

        topBar.add(topBarInp);

        return topBar;
    }

}

class MyRenderer extends DefaultTableCellRenderer {
    Color bg, fg;
    public MyRenderer(Color bg, Color fg) {
        super();
        this.bg = bg;
        this.fg = fg;
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, 
        isSelected, hasFocus, row, column);
        cell.setBackground(bg);
        cell.setForeground(fg);
        
        //centrar text columna dreta
        ((DefaultTableCellRenderer)cell).setHorizontalAlignment(JLabel.CENTER);

        return cell;
    }
}