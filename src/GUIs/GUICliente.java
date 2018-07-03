package GUIs;

import Entidades.Cliente;
import DAOs.DAOCliente;
import Entidades.*;
import static com.sun.glass.ui.Cursor.setVisible;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import tools.*;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.Dimension;
import java.awt.Point;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.text.MaskFormatter;

public class GUICliente extends JFrame {

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
    private JLabel lbIdCliente = new JLabel("IdCliente");
    private JTextField tfIdCliente = new JTextField(10);
    private JLabel lbNomeCliente = new JLabel("NomeCliente");
    private JTextField tfNomeCliente = new JTextField(10);
    private JLabel lbEnderecoCliente = new JLabel("EnderecoCliente");
    private JTextField tfEnderecoCliente = new JTextField(10);
    private JLabel lbTelefoneCliente = new JLabel("TelefoneCliente");
    private JTextField tfTelefoneCliente = new JTextField(11);
    
//    MaskFormatter mascaraTelefone = new MaskFormatter("(##)####-####");

//    private JFormattedTextField tfTelefoneCliente = new JFormattedTextField(mascaraTelefone);
        
    
    ScrollPane scroll = new ScrollPane();
    JTextArea jTextArea = new JTextArea();
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String qualAcao = "";//variavel para facilitar insert e update
    DAOCliente daoCliente = new DAOCliente();
    Cliente cliente;
    private CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();

    public GUICliente(Point posicao, Dimension dimensao) throws ParseException {   
        
        setSize(900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CRUD - Cliente");
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
        pnNorte.add(lbIdCliente);
        pnNorte.add(tfIdCliente);
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
        pnCentro.add(lbNomeCliente);
        pnCentro.add(tfNomeCliente);
        pnCentro.add(lbEnderecoCliente);
        pnCentro.add(tfEnderecoCliente);
        pnCentro.add(lbTelefoneCliente);
        pnCentro.add(tfTelefoneCliente);
        pnSul.setBackground(Color.red);
        scroll.add(jTextArea);
        pnSul.add(scroll);
        tfNomeCliente.setEditable(false);
        tfEnderecoCliente.setEditable(false);
        tfTelefoneCliente.setEditable(false);
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tfIdCliente.setBackground(Color.white);
                    jTextArea.setText("");
                    cliente = new Cliente();
                    int identificador = Integer.valueOf(tfIdCliente.getText());
                    cliente.setIdCliente(identificador);
                    cliente = daoCliente.obter(cliente.getIdCliente());
                    if (cliente == null) {
                        pnNorte.setBackground(Color.red);
                        tfNomeCliente.setText("");
                        tfEnderecoCliente.setText("");
                        tfTelefoneCliente.setText("");
                        btnCreate.setVisible(true);
                    } else {
                        pnNorte.setBackground(Color.green);
                        tfNomeCliente.setText(cliente.getNomeCliente());
                        tfEnderecoCliente.setText(cliente.getEnderecoCliente());
                        tfTelefoneCliente.setText(String.valueOf(cliente.getTelefoneCliente()));
                        btnUpdate.setVisible(true);
                        btnDelete.setVisible(true);
                        btnCreate.setVisible(false);
                    }
                    tfIdCliente.setEditable(false);
                    tfNomeCliente.setEditable(false);
                    tfEnderecoCliente.setEditable(false);
                    tfTelefoneCliente.setEditable(false);
                    tfIdCliente.selectAll();
                } catch (Exception erro) {
                    pnNorte.setBackground(Color.yellow);
                    tfIdCliente.requestFocus();
                    tfIdCliente.setBackground(Color.red);
                    jTextArea.setText("Erro... \n");
                    jTextArea.append(erro.getMessage());
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdCliente.setEditable(false);
                tfNomeCliente.requestFocus();
                btnCreate.setVisible(false);
                btnSave.setVisible(true);
                qualAcao = "incluir";
                cliente = new Cliente();
                tfNomeCliente.setEditable(true);
                tfEnderecoCliente.setEditable(true);
                tfTelefoneCliente.setEditable(true);
                tfIdCliente.setEditable(false);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    cliente = new Cliente();
                    cliente.setIdCliente(Integer.valueOf(tfIdCliente.getText()));
                    cliente.setNomeCliente(tfNomeCliente.getText());
                    cliente.setEnderecoCliente(tfEnderecoCliente.getText());
//                    String a = tfTelefoneCliente.getText().replaceAll("[^0-9]", "");
//                    System.out.println(tfTelefoneCliente.getText().replaceAll("[^0-9]", ""));
//                    cliente.setTelefoneCliente(Integer.valueOf(a));
                    String telefonaae = String.valueOf(tfTelefoneCliente.getText());
                    int telefone = Integer.valueOf(telefonaae.replaceAll("[^0-9]", ""));
                    System.out.println(telefone);
                    cliente.setTelefoneCliente(telefone);
                    
                    
                    if (qualAcao.equals("incluir")) {
                        daoCliente.inserir(cliente);
                    } else {
                        daoCliente.atualizar(cliente);
                    }
                    tfIdCliente.setEditable(true);
                    tfIdCliente.requestFocus();
                    tfNomeCliente.setText("");
                    tfEnderecoCliente.setText("");
                    tfTelefoneCliente.setText("");
                    btnSave.setVisible(false);
                    pnNorte.setBackground(Color.green);
                    tfNomeCliente.setEditable(false);
                    tfEnderecoCliente.setEditable(false);
                    tfTelefoneCliente.setEditable(false);
                } catch (Exception erro) {
                    jTextArea.append("Erro............");
                    System.out.println(erro);
                    tfIdCliente.setEditable(true);
                    pnNorte.setBackground(Color.red);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
                tfNomeCliente.requestFocus();
                btnSave.setVisible(true);
                qualAcao = "editar";
                tfNomeCliente.setEditable(true);
                tfEnderecoCliente.setEditable(true);
                tfTelefoneCliente.setEditable(true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + cliente.getIdCliente() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    daoCliente.remover(cliente);
                    tfIdCliente.requestFocus();
                    tfNomeCliente.setText("");
                    tfEnderecoCliente.setText("");
                    tfTelefoneCliente.setText("");
                    tfIdCliente.setEditable(true);
                    btnUpdate.setVisible(false);
                    btnDelete.setVisible(false);
                }
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIListagemCliente guiListagem = new GUIListagemCliente(daoCliente.list());
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        tfIdCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoCliente.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfIdCliente.setText(aux[0]);
                        btnRetrieve.doClick();

                    } else {
                        tfIdCliente.requestFocus();
                        tfIdCliente.selectAll();
                    }
                }
            }
        });
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
//        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setVisible(true);
    }

    public static void main(String[] args) throws ParseException {
        new GUICliente(new Point(880, 250), new Dimension(800, 600));
    }


}
