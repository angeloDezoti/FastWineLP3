package GUIs;

import GUIs.GUIListagemUva;
import DAOs.DAOUva;
import Entidades.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Image;
import javax.swing.JTextField;
import tools.*;
import DAOs.*;
import java.awt.Dimension;
import java.awt.Point;

public class GUIUva extends JFrame {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);
    private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel(new GridLayout(3, 2));
    private JPanel pnSul = new JPanel(new GridLayout(1, 1));
    private JLabel lbIdUva = new JLabel("IdUva");
    private JTextField tfIdUva = new JTextField(10);
    private JLabel lbNomeUva = new JLabel("NomeUva");
    private JTextField tfNomeUva = new JTextField(10);
    private JLabel lbProdutorUva = new JLabel("ProdutorUva");
    private JTextField tfProdutorUva = new JTextField(10);
    private JLabel lbPaisDeOrigemUva = new JLabel("PaisDeOrigemUva");
    private JTextField tfPaisDeOrigemUva = new JTextField(10);
    ScrollPane scroll = new ScrollPane();
    JTextArea jTextArea = new JTextArea();
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String qualAcao = "";//variavel para facilitar insert e update
    DAOUva daoUva = new DAOUva();
    Uva uva;
    private CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();

    public GUIUva(Point posicao, Dimension dimensao) {
        setSize(900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CRUD - Uva");
        Container cp = getContentPane();
        cp = getContentPane();
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        cp.setLayout(new BorderLayout());
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        pnNorte.add(lbIdUva);
        pnNorte.add(tfIdUva);
        pnNorte.add(btnRetrieve);
        pnNorte.add(btnCreate);
        pnNorte.add(btnUpdate);
        pnNorte.add(btnDelete);
        pnNorte.add(btnSave);
        pnNorte.add(btnList);
        btnCancel.setVisible(false);
        btnDelete.setVisible(false);
        btnCreate.setVisible(false);
        btnSave.setVisible(false);
        btnUpdate.setVisible(false);
        pnCentro.add(lbNomeUva);
        pnCentro.add(tfNomeUva);
        pnCentro.add(lbProdutorUva);
        pnCentro.add(tfProdutorUva);
        pnCentro.add(lbPaisDeOrigemUva);
        pnCentro.add(tfPaisDeOrigemUva);
        pnSul.setBackground(Color.red);
        scroll.add(jTextArea);
        pnSul.add(scroll);
        tfNomeUva.setEditable(false);
        tfProdutorUva.setEditable(false);
        tfPaisDeOrigemUva.setEditable(false);
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tfIdUva.setBackground(Color.white);
                    jTextArea.setText("");
                    uva = new Uva();
                    int identificador = Integer.valueOf(tfIdUva.getText());
                    uva.setIdUva(identificador);
                    uva = daoUva.obter(uva.getIdUva());
                    if (uva == null) {
                        pnNorte.setBackground(Color.red);
                        tfNomeUva.setText("");
                        tfProdutorUva.setText("");
                        tfPaisDeOrigemUva.setText("");
                        btnCreate.setVisible(true);
                    } else {
                        pnNorte.setBackground(Color.green);
                        tfNomeUva.setText(uva.getNomeUva());
                        tfProdutorUva.setText(uva.getProdutorUva());
                        tfPaisDeOrigemUva.setText(uva.getPaisDeOrigemUva());
                        btnUpdate.setVisible(true);
                        btnDelete.setVisible(true);
                        btnCreate.setVisible(false);
                    }
                    tfIdUva.setEditable(false);
                    tfNomeUva.setEditable(false);
                    tfProdutorUva.setEditable(false);
                    tfPaisDeOrigemUva.setEditable(false);
                    tfIdUva.selectAll();
                } catch (Exception erro) {
                    pnNorte.setBackground(Color.yellow);
                    tfIdUva.requestFocus();
                    tfIdUva.setBackground(Color.red);
                    jTextArea.setText("Erro... \n");
                    jTextArea.append(erro.getMessage());
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdUva.setEditable(false);
                tfNomeUva.requestFocus();
                btnCreate.setVisible(false);
                btnSave.setVisible(true);
                qualAcao = "incluir";
                uva = new Uva();
                tfNomeUva.setEditable(true);
                tfProdutorUva.setEditable(true);
                tfPaisDeOrigemUva.setEditable(true);
                tfIdUva.setEditable(false);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    uva = new Uva();
                    uva.setIdUva(Integer.valueOf(tfIdUva.getText()));
                    uva.setNomeUva(tfNomeUva.getText());
                    uva.setProdutorUva(tfProdutorUva.getText());
                    uva.setPaisDeOrigemUva(tfPaisDeOrigemUva.getText());
                    if (qualAcao.equals("incluir")) {
                        daoUva.inserir(uva);
                    } else {
                        daoUva.atualizar(uva);
                    }
                    tfIdUva.setEditable(true);
                    tfIdUva.requestFocus();
                    tfNomeUva.setText("");
                    tfProdutorUva.setText("");
                    tfPaisDeOrigemUva.setText("");
                    btnSave.setVisible(false);
                    pnNorte.setBackground(Color.green);
                    tfNomeUva.setEditable(false);
                    tfProdutorUva.setEditable(false);
                    tfPaisDeOrigemUva.setEditable(false);
                } catch (Exception erro) {
                    jTextArea.append("Erro............");
                    tfIdUva.setEditable(true);
                    pnNorte.setBackground(Color.red);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
                tfNomeUva.requestFocus();
                btnSave.setVisible(true);
                qualAcao = "editar";
                tfNomeUva.setEditable(true);
                tfProdutorUva.setEditable(true);
                tfPaisDeOrigemUva.setEditable(true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + uva.getIdUva() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    daoUva.remover(uva);
                    tfIdUva.requestFocus();
                    tfNomeUva.setText("");
                    tfProdutorUva.setText("");
                    tfPaisDeOrigemUva.setText("");
                    tfIdUva.setEditable(true);
                    btnUpdate.setVisible(false);
                    btnDelete.setVisible(false);
                }
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIListagemUva guiListagem = new GUIListagemUva(daoUva.list());
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        tfIdUva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoUva.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfIdUva.setText(aux[0]);
                        btnRetrieve.doClick();

                    } else {
                        tfIdUva.requestFocus();
                        tfIdUva.selectAll();
                    }
                }
            }
        });
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUIUva(new Point(880, 250), new Dimension(800, 600));
    }

}
