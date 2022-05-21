package presentacio.vistes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.File;

public class gridView {

    public JScrollPane mainGrid;

    /*
        retorna la graella de texts principals.
    */
    public gridView() {
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

                    char c = int2char(grid.getSelectedColumn());
                    selectedCell.setText("" + c + (1 + grid.getSelectedRow()));

                    String out = "";
                    if(grid.getValueAt(grid.getSelectedRow(), grid.getSelectedColumn()) != null) out = "" + grid.getValueAt(grid.getSelectedRow(), grid.getSelectedColumn());
                    topBarInp.setText(out);
                }
            }
        });

        grid.add(menu);

        mainGrid = new JScrollPane(grid);
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

    private static char int2char(int i){
        return (char)(i + 64);
    }
}
