package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.CategoryDao;
import Controller.ProductDao;
import Model.Category;
import Model.Product;
import event.ClickAddCart;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class HomeGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTable jTableListCart;
	private JComboBox<String> jComboBoxCategoty;
	private JButton btn_home, btn_productmanagement, btn_revenue, btn_administration, btn_exit;
	private JLabel lbtotal;
	private JPanel jPanelListFood;
	public HomeGUI() throws Exception {
        
        Init();
        
         jTableListCart.getColumnModel().getColumn(0).setPreferredWidth(5);
         jTableListCart.getColumnModel().getColumn(3).setPreferredWidth(100);
         fillCategory();

         int id=1;
         showPanel(id);

         jComboBoxCategoty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jComboBoxCategoty.getSelectedItem().equals("Food")){
                    try {
                        showPanel(1);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }else if(jComboBoxCategoty.getSelectedItem().equals("Drink")){
                    try {
                        showPanel(2);
                    } catch (Exception ex) {
                        Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        
            }
         });
         
        setColor(btn_home);
}

    private void fillCategory() throws Exception {
        CategoryDao categoryDao=new CategoryDao();
        ArrayList<Category> listCat=categoryDao.getAllRows();
        for(int i=0;i<listCat.size();i++){
			jComboBoxCategoty.addItem(listCat.get(i).getNam());       
        }  
    }
   
    void showPanel(int id) throws Exception {
		jPanelListFood.removeAll();
        jPanelListFood.revalidate();
        jPanelListFood.repaint();
        ProductDao dao=new ProductDao();
        ArrayList<Product> list= dao.getAllRows(id);
        GridBagLayout layout=new GridBagLayout();
        jPanelListFood.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(2,10,0,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
      
        JButton[] button = new JButton[list.size()];
       for (int i=0;i<list.size();i++) {
           if(i % 7 ==0 ){
               gbc.gridx=0;
               gbc.gridy+=4;
           }

         Image image = ImageIO.read(this.getClass().getResource("/img/food.png"));
           if (id==2) {
               image = ImageIO.read(this.getClass().getResource("/img/drink.png"));
           }
                
           Image imageScaled = image.getScaledInstance(70, 50, Image.SCALE_SMOOTH);
           ImageIcon imageIcon = new ImageIcon(imageScaled);
            jPanelListFood.add(new JLabel(imageIcon), gbc);
            gbc.gridy++;
            JLabel nameLabel = new JLabel(list.get(i).getName(), SwingConstants.CENTER);     
            nameLabel.setPreferredSize(new Dimension(100, 20)); // có thể điều chỉnh lại nếu cần
            jPanelListFood.add(nameLabel, gbc);           
            gbc.gridy++; 
            JLabel priceLabel = new JLabel(String.valueOf(list.get(i).getPrice() + ".VND"), SwingConstants.CENTER);
            priceLabel.setPreferredSize(new Dimension(100, 20));
            jPanelListFood.add(priceLabel, gbc);
            gbc.gridy++; 
            button[i]=new JButton("Add Cart");
            button[i].setForeground(new Color(47, 60, 126));
            button[i].setBackground(new Color(255, 214, 224));
            button[i].setBorderPainted(false);    
            button[i].setOpaque(true);
            button[i].setFont(new Font("Tahoma", Font.BOLD, 11));
            button[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            jPanelListFood.add(button[i],gbc );
               gbc.gridy--; 
               gbc.gridy--; 
               gbc.gridy--;
	       gbc.gridx++; 
	       button[i].addActionListener(new ClickAddCart(new Product(list.get(i).getId(),
	    	        list.get(i).getName(), list.get(i).getCategoryID(), 1, list.get(i).getPrice()), jTableListCart, lbtotal));
        
        }       
    }

    @SuppressWarnings("serial")
	private void Init() {
		setSize(940, 530); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setUndecorated(true);
		setLocationRelativeTo(null);

		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		JPanel bg = new JPanel();
		bg.setBounds(0, 0, 940, 530);
		bg.setLayout(null);
		contentPane.add(bg);
		
		JPanel sidepane = new JPanel();
		sidepane.setBackground(new Color(47, 79, 150));
		sidepane.setBounds(0, 0, 250, 530);
		bg.add(sidepane);
		sidepane.setLayout(null);
		
		btn_home = new JButton("MENU");
		ImageIcon logoIcon = new ImageIcon(getClass().getResource("/img/menu.png"));
		Image scaledShopp = logoIcon.getImage().getScaledInstance(55, 50, Image.SCALE_SMOOTH);
		btn_home.setIcon(new ImageIcon(scaledShopp));
		btn_home.setIconTextGap(20); 
		btn_home.setForeground(new Color(255, 255, 255));
		btn_home.setBackground(new Color(47, 79, 150));
		btn_home.setBorderPainted(false);    
		btn_home.setOpaque(true);  
		btn_home.setHorizontalAlignment(SwingConstants.LEFT);
		btn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btn_homeMouseClicked(evt);
			}
		});
		btn_home.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_home.setBounds(0, 80, 250, 90);
		sidepane.add(btn_home); 
		
		btn_productmanagement = new JButton("PRODUCT");
		ImageIcon logoIcon1 = new ImageIcon(getClass().getResource("/img/product.png"));
		Image scaledShopp1 = logoIcon1.getImage().getScaledInstance(55, 50, Image.SCALE_SMOOTH);
		btn_productmanagement.setIcon(new ImageIcon(scaledShopp1));
		btn_productmanagement.setIconTextGap(20); 
		btn_productmanagement.setHorizontalAlignment(SwingConstants.LEFT);
		btn_productmanagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btn_productmanagementMouseClicked(evt);
			}
		});
		btn_productmanagement.setForeground(new Color(255, 255, 255));
		btn_productmanagement.setBackground(new Color(47, 79, 150));
		btn_productmanagement.setBorderPainted(false);    
		btn_productmanagement.setOpaque(true);
		btn_productmanagement.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_productmanagement.setBounds(0, 170, 250, 90);
		sidepane.add(btn_productmanagement);
		
		btn_revenue = new JButton("REVENUE");
		ImageIcon logoIcon2 = new ImageIcon(getClass().getResource("/img/revenue.png"));
		Image scaledShopp2 = logoIcon2.getImage().getScaledInstance(55, 50, Image.SCALE_SMOOTH);
		btn_revenue.setIcon(new ImageIcon(scaledShopp2));
		btn_revenue.setIconTextGap(20); 
		btn_revenue.setHorizontalAlignment(SwingConstants.LEFT);
		btn_revenue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btn_revenueMouseClicked(evt);
			}
		});
		btn_revenue.setForeground(new Color(255, 255, 255));
		btn_revenue.setBackground(new Color(47, 79, 150));
		btn_revenue.setBorderPainted(false);    
		btn_revenue.setOpaque(true);
		btn_revenue.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_revenue.setBounds(0, 260, 250, 90);
		sidepane.add(btn_revenue);
		
		btn_administration = new JButton("ACCOUT");
		ImageIcon logoIcon3 = new ImageIcon(getClass().getResource("/img/user.png"));
		Image scaledShopp3 = logoIcon3.getImage().getScaledInstance(55, 50, Image.SCALE_SMOOTH);
		btn_administration.setIcon(new ImageIcon(scaledShopp3));
		btn_administration.setIconTextGap(20); 
		btn_administration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btn_administrationMouseClicked(evt);
			}
		});
		btn_administration.setForeground(new Color(255, 255, 255));
		btn_administration.setBackground(new Color(47, 79, 150));
		btn_administration.setBorderPainted(false);    
		btn_administration.setOpaque(true); 
		btn_administration.setHorizontalAlignment(SwingConstants.LEFT);
		btn_administration.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_administration.setBounds(0, 350, 250, 90);
		sidepane.add(btn_administration);
		
		btn_exit = new JButton("LOGOUT");
		ImageIcon logoIcon4 = new ImageIcon(getClass().getResource("/img/logout.png"));
		Image scaledShopp4 = logoIcon4.getImage().getScaledInstance(55, 50, Image.SCALE_SMOOTH);
		btn_exit.setIcon(new ImageIcon(scaledShopp4));
		btn_exit.setIconTextGap(20);
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btn_exitMouseClicked(evt);
			}
		});
		btn_exit.setForeground(new Color(255, 255, 255));
		btn_exit.setBackground(new Color(47, 79, 150));
		btn_exit.setBorderPainted(false);    
		btn_exit.setOpaque(true); 
		btn_exit.setHorizontalAlignment(SwingConstants.LEFT);
		btn_exit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_exit.setBounds(0, 440, 250, 90);
		sidepane.add(btn_exit);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText("WELCOME");
        ImageIcon logoIcon5 = new ImageIcon(getClass().getResource("/img/login.png"));
        Image scaledShopp5 = logoIcon5.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);
        lblNewLabel.setIcon(new ImageIcon(scaledShopp5));
        lblNewLabel.setIconTextGap(20);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(30, 0, 250, 80);
        sidepane.add(lblNewLabel);
        

		JPanel jpanel1 = new JPanel();
		jpanel1.setBackground(new Color(227, 227, 227));
		jpanel1.setBounds(250, 0, 690, 50);
		bg.add(jpanel1);
		jpanel1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("AYA COFFEE");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 0, 121));
		lblNewLabel_1.setBounds(0, 0, 690, 50);
		jpanel1.add(lblNewLabel_1);
		
		JPanel jPanelHome = new JPanel();
		jPanelHome.setBackground(new Color(255, 255, 255));
		jPanelHome.setBounds(250, 50, 690, 480);
		bg.add(jPanelHome);
		jPanelHome.setLayout(null);
		
		jPanelListFood = new JPanel();
		jPanelListFood.setBackground(new Color(255, 255, 255));
		jPanelListFood.setBounds(0, 0, 690, 224);
		jPanelHome.add(jPanelListFood);
		jPanelListFood.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel JPanelCart = new JPanel();
		JPanelCart.setBackground(new Color(255, 255, 255));
		JPanelCart.setBounds(0, 224, 690, 250);
		jPanelHome.add(JPanelCart);
		JPanelCart.setLayout(null);
		
		JScrollPane jScrollPane5 = new JScrollPane();
		jScrollPane5.setBounds(10, 10, 330, 230);
		JPanelCart.add(jScrollPane5);
		
		jTableListCart = new JTable();
		jTableListCart.setModel(new DefaultTableModel(
	            new Object [][] {
	            },
	            new String [] {
	                "ID", "Name", "Quantity", "Price"
	            }
	        )
	        {
	            public boolean isCellEditable(int row,int column){
	                return false;
	            }
	        }
	    );
		jScrollPane5.setViewportView(jTableListCart);
		
		jComboBoxCategoty = new JComboBox<>();
		jComboBoxCategoty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategotyActionPerformed(evt);
            }
        });
		jComboBoxCategoty.setBounds(360, 10, 140, 35);
		JPanelCart.add(jComboBoxCategoty);
		
		JButton btn_updatePayment = new JButton("Payment");
		btn_updatePayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btn_updatePaymentActionPerformed(evt);
			}
		});
		btn_updatePayment.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_updatePayment.setForeground(new Color(47, 60, 126));
		btn_updatePayment.setBackground(new Color(255, 214, 224));
		btn_updatePayment.setBorderPainted(false);    
		btn_updatePayment.setOpaque(true);
		btn_updatePayment.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_updatePayment.setBounds(530, 10, 140, 35);
		JPanelCart.add(btn_updatePayment);
		
		JButton btn_updatePayment1 = new JButton("Delete Item");
		btn_updatePayment1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btn_updatePayment1ActionPerformed(evt);
			}
		});
		btn_updatePayment1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_updatePayment1.setForeground(new Color(47, 60, 126));
		btn_updatePayment1.setBackground(new Color(255, 214, 224));
		btn_updatePayment1.setBorderPainted(false);    
		btn_updatePayment1.setOpaque(true);
		btn_updatePayment1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_updatePayment1.setBounds(360, 107, 140, 35);
		JPanelCart.add(btn_updatePayment1);
		
		JButton refreshbtn = new JButton("Refresh");
		refreshbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				refreshbtnActionPerformed(evt);
			}
		});
		refreshbtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		refreshbtn.setForeground(new Color(47, 60, 126));
		refreshbtn.setBackground(new Color(255, 214, 224));
		refreshbtn.setBorderPainted(false);    
		refreshbtn.setOpaque(true);
		refreshbtn.setBounds(578, 110, 90, 30);
		JPanelCart.add(refreshbtn);
		
		JLabel lbpri1 = new JLabel("Price Total :");
		lbpri1.setForeground(new Color(47, 60, 126));
		lbpri1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbpri1.setBounds(360, 198, 130, 35);
		JPanelCart.add(lbpri1);
		
		lbtotal = new JLabel(" 0");
		lbtotal.setForeground(new Color(47, 60, 126));
		lbtotal.setHorizontalAlignment(SwingConstants.CENTER);
		lbtotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbtotal.setBounds(503, 200, 150, 35);
		JPanelCart.add(lbtotal);
	}
    
    private void btn_productmanagementMouseClicked(ActionEvent evt) {
       	EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					
    					ProductGUI frame = new ProductGUI();
    					frame.setVisible(true);
    					dispose();
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
       
    }

    private void btn_administrationMouseClicked(ActionEvent evt) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdministrationGUI frame = new AdministrationGUI();
                    frame.setVisible(true);
                    dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private void btn_revenueMouseClicked(ActionEvent evt) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RevenueGUI frame = new RevenueGUI();
                    frame.setVisible(true);
                    dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

	private void btn_homeMouseClicked(ActionEvent evt) {
		
	}
	
    private void btn_exitMouseClicked(ActionEvent evt) {
    	EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SignUpGUI frame = new SignUpGUI();
                    frame.setVisible(true);
                    dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
    
    private void btn_updatePaymentActionPerformed(java.awt.event.ActionEvent evt) {
        int rows=jTableListCart.getRowCount();
        if(rows>0){
            new PaymentGUI(lbtotal,jTableListCart).setVisible(true);
        }
        if(rows == 0){
            JOptionPane.showMessageDialog(null, "Không có món để thanh toán,hãy chọn món!");
        }
    }
    
    private void btn_updatePayment1ActionPerformed(java.awt.event.ActionEvent evt) {
        int rowSelect=jTableListCart.getSelectedRow();
        DefaultTableModel model=new DefaultTableModel();
       model=(DefaultTableModel) jTableListCart.getModel();
       
        if(rowSelect < 0){
            JOptionPane.showMessageDialog(HomeGUI.this, "Bạn Chưa Chọn Item Cần Xóa !!!");
        }else{
             model.removeRow(rowSelect);
              int rows = jTableListCart.getRowCount();
            double total=0;
            for (int row = 0; row < rows; row++) {
                total+=Double.parseDouble(String.valueOf(jTableListCart.getValueAt(row, 3)) ) ;
                
            }
            DecimalFormat formatter = new DecimalFormat("###,###,###");


              lbtotal.setText(formatter.format(total)+" VNĐ");
        }
    }
    
    private void jComboBoxCategotyActionPerformed(java.awt.event.ActionEvent evt) {
    }
    
    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {
        try{
            showPanel(1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    void resetColor(JButton btn){
    	btn.setForeground(new Color(255, 255, 255));
		btn.setBackground(new Color(47, 60, 126));
    }
    
    void setColor(JButton btn){
    	btn.setForeground(new Color(47, 60, 126));
		btn.setBackground(new Color(255, 255, 255));
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new HomeGUI().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
