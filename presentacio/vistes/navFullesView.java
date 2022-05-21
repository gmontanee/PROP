package presentacio.vistes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.File;



public class navFullesView {

    public JPanel panelFulles;

    //es per contenir la fulla activa pero no fara falta a la part final ja que farem un render cada cop que canviem de fulla.
    static String fullaActiva;

    /*
        part de sota del mainframe que apareix amb els botons de canviar de fulla i amb els botons per afegir eliminar fulla
    */
    public navFullesView(){
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
