package presentacio.vistes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.File;

public class menuBarView {

    public JMenuBar menuBar;

    /*
        retorna el menu complet que s'afegira a dalt del mainframe.
    */
    public menuBarView() {
        menuBar = new JMenuBar();
        menuBar.add(DocumentMenu());
        menuBar.add(FullaMenu());
        menuBar.add(BlocMenu());
        menuBar.add(FuncionsMenu());
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

                // csv writer = new csv();

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
}
