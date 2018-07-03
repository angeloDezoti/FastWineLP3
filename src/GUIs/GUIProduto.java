package GUIs;

import GUIs.GUIListagemProduto;
import DAOs.DAOProduto;
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

public class GUIProduto extends JFrame {

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
    private JPanel pnCentro = new JPanel(new GridLayout(7, 2));
    private JPanel pnSul = new JPanel(new GridLayout(1, 1));
    private JPanel pnE1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel pnE2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel pnE3 = new JPanel(new GridLayout(1, 1));
    private JLabel lbIdProduto = new JLabel("IdProduto");
    private JTextField tfIdProduto = new JTextField(10);
    private JLabel lbNomeProduto = new JLabel("NomeProduto");
    private JTextField tfNomeProduto = new JTextField(10);
    private JLabel lbPaisDeOrigem = new JLabel("PaisDeOrigem");
    private JTextField tfPaisDeOrigem = new JTextField(10);
    private JLabel lbTeorAlcoolico = new JLabel("TeorAlcoolico");
    private JTextField tfTeorAlcoolico = new JTextField(10);
    private JLabel lbVolumeProduto = new JLabel("VolumeProduto");
    private JTextField tfVolumeProduto = new JTextField(10);
    private JLabel lbPrecoProduto = new JLabel("PrecoProduto");
    private JTextField tfPrecoProduto = new JTextField(10);
    private JPanel pnIdTipo = new JPanel(new GridLayout(1, 2));
    private JLabel lbIdTipo = new JLabel("IdTipo");
    private JTextField tfIdTipo = new JTextField();
    private JButton btIdTipo = new JButton("Procurar");

    JTextField tfCaminho = new JTextField();
    ScrollPane scroll = new ScrollPane();
    JTextArea jTextArea = new JTextArea();
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String qualAcao = "";//variavel para facilitar insert e update
    DAOProduto daoProduto = new DAOProduto();
    Produto produto;
    private CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();
    private JPanel pnEsquerda = new JPanel(new BorderLayout());
    private JPanel pnDireita = new JPanel(new BorderLayout());
    private JLabel rotulo = new JLabel();
    private JButton btAbrirImagem = new JButton("Selecionar imagem");
    private String caminho;
    private Image imagemAux;
    private ImageIcon icone;

    public GUIProduto(Point posicao, Dimension dimensao) {
        setSize(900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CRUD - Produto");
        Container cp = getContentPane();
        cp = getContentPane();
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        cp.setLayout(new GridLayout(1, 2));
        cp.add(pnEsquerda);
        cp.add(pnDireita);
        try {
            String caminho = "";
            tfCaminho.setText(caminho);
            icone = new ImageIcon(getClass().getResource(caminho));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
            rotulo.setIcon(icone);
        } catch (Exception err) {
            System.out.println("erro " + err.getLocalizedMessage());
        }
        pnDireita.add(pnE1, BorderLayout.NORTH);
        pnE1.add(rotulo);
        pnDireita.add(pnE2, BorderLayout.CENTER);
        pnE2.add(btAbrirImagem);
        pnDireita.add(pnE3, BorderLayout.SOUTH);
        pnE3.add(tfCaminho);
        pnEsquerda.add(pnNorte, BorderLayout.NORTH);
        pnEsquerda.add(pnCentro, BorderLayout.CENTER);
        pnEsquerda.add(pnSul, BorderLayout.SOUTH);
        pnNorte.add(lbIdProduto);
        pnNorte.add(tfIdProduto);
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
        btAbrirImagem.setEnabled(false);
        tfCaminho.setEditable(false);
        pnCentro.add(lbNomeProduto);
        pnCentro.add(tfNomeProduto);
        pnCentro.add(lbPaisDeOrigem);
        pnCentro.add(tfPaisDeOrigem);
        pnCentro.add(lbTeorAlcoolico);
        pnCentro.add(tfTeorAlcoolico);
        pnCentro.add(lbVolumeProduto);
        pnCentro.add(tfVolumeProduto);
        pnCentro.add(lbPrecoProduto);
        pnCentro.add(tfPrecoProduto);
        pnCentro.add(lbIdTipo);
        pnCentro.add(pnIdTipo);
        pnIdTipo.add(tfIdTipo);
        pnIdTipo.add(btIdTipo);

        pnSul.setBackground(Color.red);
        scroll.add(jTextArea);
        pnSul.add(scroll);
        tfCaminho.setEditable(false);
        tfNomeProduto.setEditable(false);
        tfPaisDeOrigem.setEditable(false);
        tfTeorAlcoolico.setEditable(false);
        tfVolumeProduto.setEditable(false);
        tfPrecoProduto.setEditable(false);
        tfIdTipo.setEditable(false);
        btIdTipo.setEnabled(false);

        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tfIdProduto.setBackground(Color.white);
                    jTextArea.setText("");
                    produto = new Produto();
                    int identificador = Integer.valueOf(tfIdProduto.getText());
                    produto.setIdProduto(identificador);
                    produto = daoProduto.obter(produto.getIdProduto());
                    if (produto == null) {
                        pnNorte.setBackground(Color.red);
                        tfCaminho.setText("");
                        tfNomeProduto.setText("");
                        tfPaisDeOrigem.setText("");
                        tfTeorAlcoolico.setText("");
                        tfVolumeProduto.setText("");
                        tfPrecoProduto.setText("");
                        tfIdTipo.setText("");

                        btnCreate.setVisible(true);
                    } else {
                        pnNorte.setBackground(Color.green);
                        caminho = produto.getCaminhoImagemProduto();
                        tfCaminho.setText(caminho);
                        icone = new ImageIcon(caminho);
                        imagemAux = icone.getImage();
                        icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                        rotulo.setIcon(icone);
                        tfNomeProduto.setText(produto.getNomeProduto());
                        tfPaisDeOrigem.setText(produto.getPaisDeOrigemProduto());
                        tfTeorAlcoolico.setText(String.valueOf(produto.getTeorAlcoolicoProduto()));
                        tfVolumeProduto.setText(String.valueOf(produto.getVolumeProduto()));
                        tfPrecoProduto.setText(String.valueOf(produto.getPrecoProduto()));
                        String dao1 = String.valueOf(produto.getTipoVinhoidtipo());
                        String[] aux1 = dao1.split("-");
                        tfIdTipo.setText(aux1[0]);

                        btnUpdate.setVisible(true);
                        btnDelete.setVisible(true);
                        btnCreate.setVisible(false);
                    }
                    btAbrirImagem.setEnabled(false);
                    tfIdProduto.setEditable(false);
                    tfNomeProduto.setEditable(false);
                    tfPaisDeOrigem.setEditable(false);
                    tfTeorAlcoolico.setEditable(false);
                    tfVolumeProduto.setEditable(false);
                    tfPrecoProduto.setEditable(false);
                    btIdTipo.setEnabled(false);

                    tfIdProduto.selectAll();
                } catch (Exception erro) {
                    pnNorte.setBackground(Color.yellow);
                    tfIdProduto.requestFocus();
                    tfIdProduto.setBackground(Color.red);
                    jTextArea.setText("Erro... \n");
                    jTextArea.append(erro.getMessage());
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdProduto.setEditable(false);
                tfNomeProduto.requestFocus();
                btnCreate.setVisible(false);
                btnSave.setVisible(true);
                qualAcao = "incluir";
                produto = new Produto();
                tfNomeProduto.setEditable(true);
                tfPaisDeOrigem.setEditable(true);
                tfTeorAlcoolico.setEditable(true);
                tfVolumeProduto.setEditable(true);
                tfPrecoProduto.setEditable(true);
                btIdTipo.setEnabled(true);
                tfIdProduto.setEditable(false);
                btAbrirImagem.setEnabled(true);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    produto = new Produto();
                    produto.setIdProduto(Integer.valueOf(tfIdProduto.getText()));
                    produto.setNomeProduto(tfNomeProduto.getText());
                    produto.setPaisDeOrigemProduto(tfPaisDeOrigem.getText());
                    produto.setTeorAlcoolicoProduto(Double.valueOf(tfTeorAlcoolico.getText()));
                    produto.setVolumeProduto(Double.valueOf(tfVolumeProduto.getText()));
                    produto.setPrecoProduto(Double.valueOf(tfPrecoProduto.getText()));
                    String[] aux0 = tfIdTipo.getText().split("-");
                    DAOTipoVinho daoTipo = new DAOTipoVinho();
                    TipoVinho d0 = daoTipo.obter(Integer.valueOf(aux0[0]));
                    produto.setTipoVinhoidtipo(d0);

                    caminho = tfCaminho.getText();
                    produto.setCaminhoImagemProduto(caminho);
                    caminho = "";
                    icone = new ImageIcon(caminho);
                    rotulo.setIcon(icone);
                    if (qualAcao.equals("incluir")) {
                        daoProduto.inserir(produto);
                    } else {
                        daoProduto.atualizar(produto);
                    }
                    tfIdProduto.setEditable(true);
                    tfIdProduto.requestFocus();
                    tfNomeProduto.setText("");
                    tfPaisDeOrigem.setText("");
                    tfTeorAlcoolico.setText("");
                    tfVolumeProduto.setText("");
                    tfPrecoProduto.setText("");
                    tfIdTipo.setText("");

                    tfCaminho.setText("");
                    btnSave.setVisible(false);
                    pnNorte.setBackground(Color.green);
                    tfNomeProduto.setEditable(false);
                    tfPaisDeOrigem.setEditable(false);
                    tfTeorAlcoolico.setEditable(false);
                    tfVolumeProduto.setEditable(false);
                    tfPrecoProduto.setEditable(false);
                    btIdTipo.setEnabled(false);

                    btAbrirImagem.setEnabled(false);
                } catch (Exception erro) {
                    jTextArea.append("Erro............");
                    tfIdProduto.setEditable(true);
                    pnNorte.setBackground(Color.red);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
                btAbrirImagem.setEnabled(true);
                tfNomeProduto.requestFocus();
                btnSave.setVisible(true);
                qualAcao = "editar";
                tfNomeProduto.setEditable(true);
                tfPaisDeOrigem.setEditable(true);
                tfTeorAlcoolico.setEditable(true);
                tfVolumeProduto.setEditable(true);
                tfPrecoProduto.setEditable(true);
                btIdTipo.setEnabled(true);

            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + produto.getIdProduto() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    daoProduto.remover(produto);
                    tfIdProduto.requestFocus();
                    tfNomeProduto.setText("");
                    tfPaisDeOrigem.setText("");
                    tfTeorAlcoolico.setText("");
                    tfVolumeProduto.setText("");
                    tfPrecoProduto.setText("");
                    tfIdTipo.setText("");

                    String caminho = "";

                    icone = new ImageIcon(caminho);
                    rotulo.setIcon(icone);

                    tfCaminho.setText("");
                    tfIdProduto.setEditable(true);
                    btnUpdate.setVisible(false);
                    btnDelete.setVisible(false);
                }
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIListagemProduto guiListagem = new GUIListagemProduto(daoProduto.list());
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        btAbrirImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fc.showOpenDialog(pnDireita) == JFileChooser.APPROVE_OPTION) {
                    File img = fc.getSelectedFile();
                    String caminho = fc.getSelectedFile().getAbsolutePath();
                    try {
                        tfCaminho.setText(caminho);
                        icone = new javax.swing.ImageIcon(img.getAbsolutePath());
                        imagemAux = icone.getImage();
                        icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                        rotulo.setIcon(icone);
                    } catch (Exception ex) {
                        System.out.println("Erro: " + ex.getMessage());
                    }
                }
            }
        });
        DAOTipoVinho daoTipoVinho = new DAOTipoVinho();
        btIdTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoTipoVinho.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfIdTipo.setText(aux[0]);
                    } else {
                        jTextArea.setText("Nenhum dado adicionado!");
                    }
                }
            }
        });
        tfIdProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoProduto.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfIdProduto.setText(aux[0]);
                        btnRetrieve.doClick();

                    } else {
                        tfIdProduto.requestFocus();
                        tfIdProduto.selectAll();
                    }
                }
            }
        });
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUIProduto(new Point(880, 250), new Dimension(800, 600));
    }

}
