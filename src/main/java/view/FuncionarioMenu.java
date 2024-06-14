package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuncionarioMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	//private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FuncionarioMenu frame = new FuncionarioMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FuncionarioMenu() {
		getContentPane().setBackground(new Color(183, 183, 219));
		 setTitle("Menu Admin");
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setBounds(100, 100, 809, 494);
	        getContentPane().setLayout(null);
	        
	        JButton btnGerenciarUsuarios = new JButton("Visualizar Produtos");
	        btnGerenciarUsuarios.setBackground(new Color(191, 191, 223));
	        btnGerenciarUsuarios.setBounds(302, 298, 145, 46);
	        btnGerenciarUsuarios.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                gerenciarUser();
	            }
	        });
	        
	        JLabel iconCat = new JLabel("");
	        iconCat.setIcon(new ImageIcon("C:\\Users\\Cristiely\\Downloads\\download__18_-removebg-preview.png"));
	        iconCat.setBounds(115, 56, 540, 186);
	        getContentPane().add(iconCat);
	        
	      
	        getContentPane().add(btnGerenciarUsuarios);

	        JButton btnGerenciarProdutos = new JButton("Visualizar Vendas");
	        btnGerenciarProdutos.setBackground(new Color(191, 191, 223));
	        btnGerenciarProdutos.setBounds(488, 298, 145, 46);
	        btnGerenciarProdutos.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                gerenciarProdutos();
	            }
	        });
	        getContentPane().add(btnGerenciarProdutos);

	        JButton btnGerenciarVendas = new JButton("Realizar Venda");
	        btnGerenciarVendas.setBackground(new Color(191, 191, 223));
	        btnGerenciarVendas.setBounds(115, 298, 145, 46);
	        btnGerenciarVendas.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                gerenciarVendas();
	            }
	        });
	        getContentPane().add(btnGerenciarVendas);

	        JButton btnSair = new JButton("Sair");
	        btnSair.setBackground(new Color(191, 191, 223));
	        btnSair.setBounds(302, 368, 145, 46);
	        btnSair.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	                LoginFrame.main(null);
	            }
	        });
	        getContentPane().add(btnSair);
	        
	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(255, 255, 255));
	        panel.setBounds(0, 222, 793, 233);
	        getContentPane().add(panel);
	        	        
	        	  JLabel lblTitle = new JLabel("Menu Funcionario");
	        	  lblTitle.setBounds(295, 11, 166, 20);
	        	  getContentPane().add(lblTitle);
	        	  lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
	    }

	    private void gerenciarUser() {
	        // Implementar lógica para gerenciar usuários
	        JOptionPane.showMessageDialog(this, "Gerenciando usuários...");
	    }

	    private void gerenciarProdutos() {
	        // Implementar lógica para gerenciar produtos
	        JOptionPane.showMessageDialog(this, "Gerenciando produtos...");
	    }

	    private void gerenciarVendas() {
	        // Implementar lógica para gerenciar vendas
	        JOptionPane.showMessageDialog(this, "Gerenciando vendas...");
	    }
}
