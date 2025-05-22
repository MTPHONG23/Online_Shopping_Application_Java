package View;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Controller.CategoryDao;
import Controller.ProductDao;
import Model.Category;
import Model.Product;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;


public class ProductGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable TBLProduct;
	 private JTextField jTextFieldKey;
	 private JComboBox<String> jComboBoxCategoty1;
	 private JButton btn_home, btn_productmanagement, btn_revenue, btn_administration, btn_exit; 

	public ProductGUI() throws Exception{
		
	       
        init();

         fillCategory();
         loadListProduct();
         
         jComboBoxCategoty1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductDao productDao=new ProductDao();
                 TBLProduct.removeAll();
                  TBLProduct.revalidate();
                  TBLProduct.repaint();
		 DefaultTableModel defaultTableModel=new DefaultTableModel();
		 TBLProduct.setModel(defaultTableModel);
                 defaultTableModel.addColumn("id");
		 defaultTableModel.addColumn("name");
		 defaultTableModel.addColumn("Category");
                 defaultTableModel.addColumn("quantity");
                 defaultTableModel.addColumn("price");
                ArrayList<Product> products=new ArrayList<>();
                if (jComboBoxCategoty1.getSelectedItem().equals("Food")){
                    try {
                        products=productDao.getAllRows(1);
                         for(Product product:products) {
                     
                            String name=product.getName();
                            int quantity=product.getQuantity();
                            int price=product.getPrice();
                            String categoryString=null;
                         if (product.getCategoryID()==1) {
                            categoryString="Food";
                         }else{
                          categoryString="Drink";
                         }
                          defaultTableModel.addRow(new Object[] { product.getId(),name,categoryString,quantity,price});
                     }
                    } catch (Exception ex) {
                        Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }else if(jComboBoxCategoty1.getSelectedItem().equals("Drink")){
                    try {
                          products=productDao.getAllRows(2);
                           for(Product product:products) {
                     
			 String name=product.getName();
                         int quantity=product.getQuantity();
                         int price=product.getPrice();
                         String categoryString=null;
                         if (product.getCategoryID()==1) {
                            categoryString="Food";
                         }else{
                          categoryString="Drink";
                         }
                          defaultTableModel.addRow(new Object[] { product.getId(),name,categoryString,quantity,price});
                     }
                    } catch (Exception ex) {
                        Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    try {
                        products=productDao.getAllRows();
                        loadListProduct();
                    } catch (Exception ex) {
                        Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        
            }
         });  
         setColor(btn_productmanagement); 
}
     
	 private void fillCategory() throws Exception {
	        CategoryDao categoryDao=new CategoryDao();
	        ArrayList<Category> listCat=categoryDao.getAllRows();
	         jComboBoxCategoty1.addItem("All");
	        for(int i=0;i<listCat.size();i++){
	            jComboBoxCategoty1.addItem(listCat.get(i).getNam());
	        }

	        
	    }
	 
	  
      public void loadListProduct() throws Exception{
                TBLProduct.removeAll();
                TBLProduct.revalidate();
                TBLProduct.repaint();
                ProductDao dao=new ProductDao();
		 DefaultTableModel defaultTableModel=new DefaultTableModel();
		 TBLProduct.setModel(defaultTableModel);
                 defaultTableModel.addColumn("ID");
		 defaultTableModel.addColumn("Name");
		 defaultTableModel.addColumn("Category");
                 defaultTableModel.addColumn("Quantity");
                 defaultTableModel.addColumn("Price");
		 List<Product> products=dao.getAllRows();
		 for(Product product:products) {
                     
			 String name=product.getName();
                         int quantity=product.getQuantity();
                         int price=product.getPrice();
                         String categoryString=null;
                         if (product.getCategoryID()==1) {
                            categoryString="Food";
                         }else{
                          categoryString="Drink";
                         }
                          defaultTableModel.addRow(new Object[] { product.getId(),name,categoryString,quantity,price});
                     }		 
}
	   
      private void init()  {
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
		
		JPanel jPanelProduct = new JPanel();
		jPanelProduct.setBackground(new Color(255, 255, 255));
		jPanelProduct.setBounds(250, 50, 690, 480);
		bg.add(jPanelProduct);
		jPanelProduct.setLayout(null);
		
		JButton btn_addProduct = new JButton("ADD");
		btn_addProduct.setForeground(new Color(47, 60, 126));
		btn_addProduct.setBackground(new Color(255, 214, 224));
		btn_addProduct.setBorderPainted(false);    
		btn_addProduct.setOpaque(true);
		btn_addProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_addProduct.setBounds(10, 21, 65, 30);
		jPanelProduct.add(btn_addProduct);
        btn_addProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btn_addProductActionPerformed(evt);
            }
        });
		
		JButton btn_updateProduct = new JButton("EDIT");
		btn_updateProduct.setForeground(new Color(47, 60, 126));
		btn_updateProduct.setBackground(new Color(255, 214, 224));
		btn_updateProduct.setBorderPainted(false);    
		btn_updateProduct.setOpaque(true);
		btn_updateProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_updateProduct.setBounds(85, 21, 65, 30);
		jPanelProduct.add(btn_updateProduct);
        btn_updateProduct.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btn_updateProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btn_updateProductActionPerformed(evt);
            }
        });
		
		JButton btn_delProduct = new JButton("DELETE");
		btn_delProduct.setForeground(new Color(47, 60, 126));
		btn_delProduct.setBackground(new Color(255, 214, 224));
		btn_delProduct.setBorderPainted(false);    
		btn_delProduct.setOpaque(true);
		btn_delProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_delProduct.setBounds(160, 21, 80, 30);
		jPanelProduct.add(btn_delProduct);
        btn_delProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delProductActionPerformed(evt);
            }
        });
		
		JButton btn_updateUser2 = new JButton("REFRESH");
		btn_updateUser2.setForeground(new Color(47, 60, 126));
		btn_updateUser2.setBackground(new Color(255, 214, 224));
		btn_updateUser2.setBorderPainted(false);    
		btn_updateUser2.setOpaque(true);
		btn_updateUser2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_updateUser2.setBounds(250, 21, 90, 30);
		jPanelProduct.add(btn_updateUser2);
        btn_updateUser2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateUser2ActionPerformed(evt);
            }
        });
		
        jComboBoxCategoty1 = new JComboBox<>();
		jComboBoxCategoty1.setFont(new Font("Tahoma", Font.BOLD, 11));
		jComboBoxCategoty1.setBounds(350, 21, 100, 30);
		jPanelProduct.add(jComboBoxCategoty1);
        jComboBoxCategoty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategoty1ActionPerformed(evt);
            }
        });
		
		JButton btn_findProduct = new JButton("FIND");
		btn_findProduct.setForeground(new Color(47, 60, 126));
		btn_findProduct.setBackground(new Color(255, 214, 224));
		btn_findProduct.setBorderPainted(false);    
		btn_findProduct.setOpaque(true);
		btn_findProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_findProduct.setBounds(460, 21, 65, 30);
		jPanelProduct.add(btn_findProduct);
        btn_findProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_findProductActionPerformed(evt);
            }
        });
        
		jTextFieldKey = new JTextField();
		jTextFieldKey.setFont(new Font("Tahoma", Font.BOLD, 11));
		jTextFieldKey.setBounds(535, 21, 150, 30);
		jPanelProduct.add(jTextFieldKey);
		jTextFieldKey.setColumns(10);
        jTextFieldKey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldKeyKeyPressed(evt);
            }
        });
		
		JScrollPane jScrollPane2 = new JScrollPane();
		jScrollPane2.setBounds(30, 80, 630, 375);
		jPanelProduct.add(jScrollPane2);
		 TBLProduct = new JTable();
	        TBLProduct.setModel(new javax.swing.table.DefaultTableModel(
	                new Object [][] {
	                    {null, null, null, null},
	                    {null, null, null, null},
	                    {null, null, null, null},
	                    {null, null, null, null}
	                },
	                new String [] {
	                    "Title 1", "Title 2", "Title 3", "Title 4"
	                }
	            ));
	            TBLProduct.setSelectionBackground(new Color(255, 204, 102));
	            TBLProduct.getTableHeader().setReorderingAllowed(false);
	            jScrollPane2.setViewportView(TBLProduct);
		
	}
	    
	    private void btn_addProductActionPerformed(java.awt.event.ActionEvent evt) {
		       new AddProductGUI().setVisible(true);
		    }

	    private void btn_delProductActionPerformed(java.awt.event.ActionEvent evt) {
	        int row = TBLProduct.getSelectedRow();
	        if (row == -1) {
	            JOptionPane.showMessageDialog(ProductGUI.this, "Bạn Chưa Chọn Hàng Cần Xóa !!!");
	        } else {
	            int confirm = JOptionPane.showConfirmDialog(ProductGUI.this, "Bạn Có Chắc Xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
	            if (confirm == JOptionPane.YES_OPTION) {
	                int id = Integer.parseInt(String.valueOf(TBLProduct.getValueAt(row, 0)));
	                ProductDao dao = new ProductDao();
	                if (dao.deleteProduct(id) > 0) {
	                    try {
	                        loadListProduct();
	                    } catch (Exception ex) {
	                        Logger.getLogger(ProductGUI.class.getName()).log(Level.SEVERE, null, ex);
	                    }
	                }
	            }
	        }
	    }

		    private void btn_updateProductActionPerformed(java.awt.event.ActionEvent evt) {
		        int row=TBLProduct.getSelectedRow();
		        if(row==-1){
		            JOptionPane.showMessageDialog(ProductGUI.this, "Bạn Chưa Chọn Hàng Cần Cập Nhật !!!");
		        }else{
		            int id= Integer.parseInt(String.valueOf(TBLProduct.getValueAt(row, 0)));
		           new UpdateProductGUI(id).setVisible(true);
		        }
		    }

		    private void btn_findProductActionPerformed(java.awt.event.ActionEvent evt) {
		         String keyString=jTextFieldKey.getText();
		         TBLProduct.removeAll();
		         TBLProduct.revalidate();
		         TBLProduct.repaint();
		         DefaultTableModel defaultTableModel=new DefaultTableModel();
		         TBLProduct.setModel(defaultTableModel);
		        ProductDao dao=new ProductDao();
		        try {
		            ArrayList<Product> products=dao.searchProduct(keyString);
		             defaultTableModel.addColumn("Id");
				 defaultTableModel.addColumn("Name");
				 defaultTableModel.addColumn("Category");
		                 defaultTableModel.addColumn("Quantity");
		                 defaultTableModel.addColumn("Price");
				 for(Product product:products) {
		                     
					 String name=product.getName();
		                         int quantity=product.getQuantity();
		                         int price=product.getPrice();
		                         String categoryString=null;
		                         if (product.getCategoryID()==1) {
		                            categoryString="Food";
		                         }else{
		                          categoryString="Drink";
		                         }
		                          defaultTableModel.addRow(new Object[] { product.getId(),name,categoryString,quantity,price});
		                     }
		        } catch (SQLException ex) {
		            Logger.getLogger(ProductGUI.class.getName()).log(Level.SEVERE, null, ex);
		        }
		        
		               
		    }

		    private void btn_homeMouseClicked(ActionEvent evt) {
		    	EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							
							HomeGUI frame = new HomeGUI();
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
		    
		    private void btn_productmanagementMouseClicked(ActionEvent evt) {
				
			}

		    private void jComboBoxCategoty1ActionPerformed(java.awt.event.ActionEvent evt) {
		        
		    }

		    private void btn_updateUser2ActionPerformed(java.awt.event.ActionEvent evt) {
		        jTextFieldKey.setText("");
		         TBLProduct.removeAll();
		        TBLProduct.revalidate();
		        TBLProduct.repaint();
		        try {
		            loadListProduct();
		            jComboBoxCategoty1.setSelectedIndex(0);
		        } catch (Exception ex) {
		            Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }

		    private void jTextFieldKeyKeyPressed(java.awt.event.KeyEvent evt) {
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

		    void resetColor(JButton btn){
		    	btn.setForeground(new Color(255, 255, 255));
				btn.setBackground(new Color(47, 60, 126));
		    }
		    
		    void setColor(JButton btn){
		    	btn.setForeground(new Color(47, 60, 126));
				btn.setBackground(new Color(255, 255, 255));
		    }
		 
	        
		    public static void main(String[] args) {
		        EventQueue.invokeLater(() -> {
		            try {
		                ProductGUI frame = new ProductGUI();
		                frame.setVisible(true);
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        });   
	   }
	}
