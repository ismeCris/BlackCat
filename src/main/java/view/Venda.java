package view;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.FormaPagamentoController;
import controller.ProdutoController;
import model.Entity.FormaPagamento;
import model.Entity.Produtos;
import model.Entity.Usuario;
import model.Entity.Vendas;
import model.Repository.ProdutoRepository;
import model.Repository.VendaRepository;
import model.Service.ProdutoService;
import model.Service.VendaService;

import java.awt.*;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

public class Venda extends JFrame {
	  private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoPie");
    private static EntityManager em = emf.createEntityManager();

    private static ProdutoRepository produtoRepository = new ProdutoRepository(em);
    private static ProdutoService produtoService = new ProdutoService(produtoRepository);
    private static ProdutoController produtoController = new ProdutoController(produtoService);
    private static FormaPagamentoController pagamentoController = new FormaPagamentoController(em);

    private static VendaRepository vendaRepository = new VendaRepository(em);
    private static VendaService vendaService = new VendaService(vendaRepository);

    private Usuario usuarioLogado;


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Usuario usuario = new Usuario(); 
                Venda frame = new Venda(usuario);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
  
    private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private int numeroItens = 0;
    private float totalVenda = 0;

    public Venda(Usuario usuario) {
        this.usuarioLogado = usuario;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 906, 509);
        contentPane = new JPanel();
        contentPane.setBackground(new java.awt.Color(208, 208, 232));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(99, 41, 584, 30);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Adicionar");
        btnNewButton.setBackground(new java.awt.Color(202, 247, 187));
        btnNewButton.setBounds(693, 40, 101, 30);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(e -> adicionarProduto());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(99, 81, 695, 242);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        tableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "ID", "Nome", "Valor Unitário", "Quantidade" });
        table.setModel(tableModel);

        JButton btnNewButton_5 = new JButton("Finalizar venda");
        btnNewButton_5.setBackground(new java.awt.Color(202, 247, 187));
        btnNewButton_5.setBounds(671, 377, 123, 35);
        contentPane.add(btnNewButton_5);
        btnNewButton_5.addActionListener(e -> finalizarVenda());

        JButton btnNewButton_3 = new JButton("Cancelar");
        btnNewButton_3.setBackground(new java.awt.Color(128, 128, 192));
        btnNewButton_3.setBounds(99, 377, 101, 35);
        contentPane.add(btnNewButton_3);
        btnNewButton_3.addActionListener(e -> cancelarVenda());

        lblNewLabel_2 = new JLabel("0");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(237, 322, 123, 43);
        contentPane.add(lblNewLabel_2);

        lblNewLabel_3 = new JLabel("R$ 0,00");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(680, 321, 145, 45);
        contentPane.add(lblNewLabel_3);

        JButton btnNewButton_3_1 = new JButton("Consultar Vendas");
        btnNewButton_3_1.addActionListener(e -> {
            ConsultaVendas consultaVendas = new ConsultaVendas(vendaRepository, vendaService);
            consultaVendas.setVisible(true);
        });
        btnNewButton_3_1.setBackground(new Color(128, 128, 192));
        btnNewButton_3_1.setBounds(224, 377, 123, 35);
        contentPane.add(btnNewButton_3_1);
    }

    private void adicionarProduto() {
        String idProdutoStr = textField.getText().trim();
        if (!idProdutoStr.isEmpty()) {
            try {
                Long idProduto = Long.valueOf(idProdutoStr);
                Produtos produto = produtoController.findProdutoByid(idProduto);

                if (produto != null) {
                    Object[] rowData = { produto.getId(), produto.getNome(), produto.getPreco(), 1 };
                    tableModel.addRow(rowData);

                    numeroItens++;
                    totalVenda += produto.getPreco();

                    lblNewLabel_2.setText(String.valueOf(numeroItens));
                    lblNewLabel_3.setText("R$ " + String.format("%.2f", totalVenda));
                } else {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado.");
                }

                textField.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Digite um ID válido para o produto.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Digite o ID do produto.");
        }
    }

    private void finalizarVenda() {
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Adicione produtos antes de finalizar a venda.");
            return;
        }

        String[] opcoesPagamento = { "Dinheiro", "Pix", "Cartão de Crédito", "Cartão de Débito" };
        String formaPagamentoSelecionada = (String) JOptionPane.showInputDialog(this, "Escolha a forma de pagamento:",
                "Forma de Pagamento", JOptionPane.PLAIN_MESSAGE, null, opcoesPagamento, opcoesPagamento[0]);

        if (formaPagamentoSelecionada == null) {
            return;
        }

        float totalFinal = totalVenda;

        int confirmacao = JOptionPane.showConfirmDialog(this, "Deseja gerar a Nota Fiscal Eletrônica (NFe)?",
                "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmacao != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Venda finalizada.");
            return;
        }

        try {
            String nfe = simularServicoEmissaoNFeString(totalFinal);

            if (nfe != null && !nfe.isEmpty()) {
                exibirNFe(nfe);
                System.out.println("NFe emitida com sucesso.");

                registrarVenda(formaPagamentoSelecionada, true); // Assuming emitirNFe should be true
            } else {
                registrarVenda(formaPagamentoSelecionada, false); // Assuming emitirNFe should be false
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao emitir NFe. Venda não concluída.");
        }
    }

    private String simularServicoEmissaoNFeString(float totalFinal) {
        return "Nota Fiscal Eletrônica\nNúmero: 12345\nData: " + LocalDateTime.now() + "\nTotal: R$ " + totalFinal;
    }

    private void exibirNFe(String nfeTexto) {
        JTextArea textArea = new JTextArea(nfeTexto);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Nota Fiscal Eletrônica", JOptionPane.INFORMATION_MESSAGE);
    }

    private Long simularServicoEmissaoNFe(float totalFinal) {
        return System.currentTimeMillis();
    }

    private void registrarVenda(String formaPagamentoSelecionada, boolean emitirNFe) {
       
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(this, "Usuário não logado. Venda não pode ser registrada.");
            return;
        }

       
        FormaPagamento formaPagamento = pagamentoController.findFormaPagamentoByNome(formaPagamentoSelecionada);
        if (formaPagamento == null) {
            JOptionPane.showMessageDialog(this, "Forma de pagamento não encontrada.");
            return;
        }
      
        Vendas venda = new Vendas();
        venda.setDataVenda(LocalDateTime.now());
        venda.setTotal(totalVenda);
        venda.setFormaPagamento(formaPagamento);
        venda.setUsuario(usuarioLogado); 

      
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Long produtoId = (Long) tableModel.getValueAt(i, 0);
            Produtos produto = produtoController.findProdutoByid(produtoId);
            if (produto != null) {
                venda.adicionarProduto(produto);
            }
        }

      
        if (emitirNFe) {
            Long nfe = simularServicoEmissaoNFe(totalVenda);
            venda.setNfe(nfe);
        }

        try {
            // Registra a venda
            vendaService.registrarVenda(venda, usuarioLogado.getId());
       
            if (emitirNFe) {
                JOptionPane.showMessageDialog(this, "Venda concluída e NFe gerada com sucesso.");
                exibirNFe("Conteúdo da NFe: " + venda.getNfe()); // Supondo que você tenha o conteúdo da NFe
            } else {
                JOptionPane.showMessageDialog(this, "Venda registrada sem emissão de NFe.");
            }
            // Reseta a venda atual
            cancelarVenda();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao registrar venda.");
        }

        // Reseta contadores e exibição
        numeroItens = 0;
        totalVenda = 0;
        lblNewLabel_2.setText("0");
        lblNewLabel_3.setText("R$ 0,00");
        tableModel.setRowCount(0);
    }



    private void cancelarVenda() {
        tableModel.setRowCount(0);
        numeroItens = 0;
        totalVenda = 0;
        lblNewLabel_2.setText("0");
        lblNewLabel_3.setText("R$ 0,00");
    }
}
