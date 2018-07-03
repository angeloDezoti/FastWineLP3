package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import tools.CentroDoMonitorMaior;

public class GUIMenuPrincipal extends JFrame {

    private Container cp;
    private Point p;
    private JPanel pnNorte = new JPanel();
    private JPanel pnCentro = new JPanel();
    private JLabel lbTitulo = new JLabel("FastWine");
    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);
    private JLabel labelComImagemDeTamanhoDiferente = new JLabel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuCadastros = new JMenu("Cadastros");
//------------------------ Itens do Menu ----------------------------
    private JMenuItem crudCliente = new JMenuItem("Cliente");
    private JMenuItem crudProduto = new JMenuItem("Produto");
    private JMenuItem crudUva = new JMenuItem("Uva");
    private JMenuItem crudTipoVinho = new JMenuItem("Tipo Vinho");
    private JMenuItem crudVenda = new JMenuItem("Venda");
    

    public GUIMenuPrincipal(Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setTitle("FastWine");

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pnNorte.add(lbTitulo);
        lbTitulo.setFont(fonte);
        pnNorte.setBackground(Color.LIGHT_GRAY);
        pnCentro.add(labelComImagemDeTamanhoDiferente);
        pnCentro.setBackground(Color.BLACK);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        menuBar.add(menuCadastros);
        menuCadastros.add(crudCliente);
        menuCadastros.add(crudProduto);
        menuCadastros.add(crudUva);
        menuCadastros.add(crudTipoVinho);
        menuCadastros.add(crudVenda);
        
        crudCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GUICliente guiCliente = new GUICliente(p, dimensao);
                } catch (ParseException ex) {
                    Logger.getLogger(GUIMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }
        });

        crudProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIProduto guiProduto = new GUIProduto(p, dimensao);
            }
        });

        crudUva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIUva guiUva = new GUIUva(p, dimensao);
            }
        });
        
        crudTipoVinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUITipoVinho guiTipoVinho = new GUITipoVinho(p, dimensao);
            }
        });
        
        crudVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIVenda guiVenda = new GUIVenda(p, dimensao);
            }
        });

        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        setVisible(true);
    } //fecha o contrutor

    
    public static void main(String[] args) {
        new GUIMenuPrincipal(new Dimension(800, 600));
        
    }

 
    
    
}
