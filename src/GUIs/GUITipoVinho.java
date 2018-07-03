package GUIs;

import GUIs.GUIListagemTipoVinho;
import DAOs.DAOTipoVinho;
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

public class GUITipoVinho extends JFrame {

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
    private JPanel pnCentro = new JPanel(new GridLayout(2, 2));
    private JPanel pnSul = new JPanel(new GridLayout(1, 1));
    private JLabel lbIdTipo = new JLabel("IdTipo");
    private JTextField tfIdTipo = new JTextField(10);
    private JLabel lbNomeTipo = new JLabel("NomeTipo");
    private JTextField tfNomeTipo = new JTextField(10);
    private JPanel pnIdUva = new JPanel(new GridLayout(1, 2));
    private JLabel lbIdUva = new JLabel("IdUva");
    private JTextField tfIdUva = new JTextField();
    private JButton btIdUva = new JButton("Procurar");
    ScrollPane scroll = new ScrollPane();
    JTextArea jTextArea = new JTextArea();
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String qualAcao = "";//variavel para facilitar insert e update
    DAOTipoVinho daoTipoVinho = new DAOTipoVinho();
    TipoVinho tipoVinho;
    private CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();

    public GUITipoVinho(Point posicao, Dimension dimensao) {
        setSize(900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CRUD - TipoVinho");
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
        pnNorte.add(lbIdTipo);
        pnNorte.add(tfIdTipo);
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
        pnCentro.add(lbNomeTipo);
        pnCentro.add(tfNomeTipo);
        pnCentro.add(lbIdUva);
        pnCentro.add(pnIdUva);
        pnIdUva.add(tfIdUva);
        pnIdUva.add(btIdUva);
        pnSul.setBackground(Color.red);
        scroll.add(jTextArea);
        pnSul.add(scroll);
        tfNomeTipo.setEditable(false);
        tfIdUva.setEditable(false);
        btIdUva.setEnabled(false);
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tfIdTipo.setBackground(Color.white);
                    jTextArea.setText("");
                    tipoVinho = new TipoVinho();
                    int identificador = Integer.valueOf(tfIdTipo.getText());
                    tipoVinho.setIdTipo(identificador);
                    tipoVinho = daoTipoVinho.obter(tipoVinho.getIdTipo());
                    if (tipoVinho == null) {
                        pnNorte.setBackground(Color.red);
                        tfNomeTipo.setText("");
                        tfIdUva.setText("");
                        btnCreate.setVisible(true);
                    } else {
                        pnNorte.setBackground(Color.green);
                        tfNomeTipo.setText(tipoVinho.getNomeTipo());
                        String dao1 = String.valueOf(tipoVinho.getUvaiduva());
                        String[] aux1 = dao1.split("-");
                        tfIdUva.setText(aux1[0]);
                        btnUpdate.setVisible(true);
                        btnDelete.setVisible(true);
                        btnCreate.setVisible(false);
                    }
                    tfIdTipo.setEditable(false);
                    tfNomeTipo.setEditable(false);
                    btIdUva.setEnabled(false);
                    tfIdTipo.selectAll();
                } catch (Exception erro) {
                    pnNorte.setBackground(Color.yellow);
                    tfIdTipo.requestFocus();
                    tfIdTipo.setBackground(Color.red);
                    jTextArea.setText("Erro... \n");
                    jTextArea.append(erro.getMessage());
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdTipo.setEditable(false);
                tfNomeTipo.requestFocus();
                btnCreate.setVisible(false);
                btnSave.setVisible(true);
                qualAcao = "incluir";
                tipoVinho = new TipoVinho();
                tfNomeTipo.setEditable(true);
                btIdUva.setEnabled(true);
                tfIdTipo.setEditable(false);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    tipoVinho = new TipoVinho();
                    tipoVinho.setIdTipo(Integer.valueOf(tfIdTipo.getText()));
                    tipoVinho.setNomeTipo(tfNomeTipo.getText());
                    String[] aux0 = tfIdUva.getText().split("-");
                    DAOUva daoUva = new DAOUva();
                    Uva d0 = daoUva.obter(Integer.valueOf(aux0[0]));
                    tipoVinho.setUvaiduva(d0);
                    if (qualAcao.equals("incluir")) {
                        daoTipoVinho.inserir(tipoVinho);
                    } else {
                        daoTipoVinho.atualizar(tipoVinho);
                    }
                    tfIdTipo.setEditable(true);
                    tfIdTipo.requestFocus();
                    tfNomeTipo.setText("");
                    tfIdUva.setText("");
                    btnSave.setVisible(false);
                    pnNorte.setBackground(Color.green);
                    tfNomeTipo.setEditable(false);
                    btIdUva.setEnabled(false);
                } catch (Exception erro) {
                    jTextArea.append("Erro............");
                    tfIdTipo.setEditable(true);
                    pnNorte.setBackground(Color.red);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
                tfNomeTipo.requestFocus();
                btnSave.setVisible(true);
                qualAcao = "editar";
                tfNomeTipo.setEditable(true);
                btIdUva.setEnabled(true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + tipoVinho.getIdTipo() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    daoTipoVinho.remover(tipoVinho);
                    tfIdTipo.requestFocus();
                    tfNomeTipo.setText("");
                    tfIdUva.setText("");
                    tfIdTipo.setEditable(true);
                    btnUpdate.setVisible(false);
                    btnDelete.setVisible(false);
                }
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIListagemTipoVinho guiListagem = new GUIListagemTipoVinho(daoTipoVinho.list());
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        DAOUva daoUva = new DAOUva();
        btIdUva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoUva.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfIdUva.setText(aux[0]);
                    } else {
                        jTextArea.setText("Nenhum dado adicionado!");
                    }
                }
            }
        });
        tfIdTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoTipoVinho.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfIdTipo.setText(aux[0]);
                        btnRetrieve.doClick();

                    } else {
                        tfIdTipo.requestFocus();
                        tfIdTipo.selectAll();
                    }
                }
            }
        });
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUITipoVinho(new Point(880, 250), new Dimension(800, 600));
    }

}
