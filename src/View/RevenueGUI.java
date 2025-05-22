package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Controller.BillDao;
import Controller.BillDetailDao;
import Model.Bill;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RevenueGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField jTextFieldRevenue;
	private JTable TBLHistory;
	private JComboBox<String> jComboBoxDate;
	private JButton btn_home, btn_productmanagement, btn_revenue, btn_administration, btn_exit;
	
	public RevenueGUI() throws Exception {
        
         Init();
        
         fillDate();
         LoadListBill();
     
       jComboBoxDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    TBLHistory.removeAll();
                    TBLHistory.revalidate();
                    TBLHistory.repaint();
                    
                     if (jComboBoxDate.getSelectedItem().equals("All Date")) {
                        try {
                            LoadListBill();
                        } catch (Exception ex) {
                            Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   }else{
                        DefaultTableModel defaultTableModel=new DefaultTableModel();
                        TBLHistory.setModel(defaultTableModel);
                        defaultTableModel.addColumn("ID Bill");
                        defaultTableModel.addColumn("Time");
                        defaultTableModel.addColumn("Date");
                        defaultTableModel.addColumn("Total");
                        defaultTableModel.addColumn("Status");
                        BillDao bd=new BillDao();
                        String date=String.valueOf(jComboBoxDate.getSelectedItem());
                    try {
                        ArrayList<Bill> bills=new ArrayList<Bill>();
                         bills=bd.getAllRowsDate(date);
                         /*System.out.println(bills.get(0).getTotal());*/
                        for(Bill bill:bills){
                             defaultTableModel.addRow(new Object[] { bill.getId(),bill.getTime(),bill.getDate(),bill.getTotal(), bill.getStatus()});
                        }
                        int rowHistory=TBLHistory.getRowCount();
                        if (rowHistory>0) {
                            double total=0;
                            for (int row = 0; row < rowHistory; row++) {
                               total+=Double.parseDouble(String.valueOf(TBLHistory.getValueAt(row, 3)) ) ;

                           }
            DecimalFormat formatter = new DecimalFormat("###,###,###");
              jTextFieldRevenue.setText(formatter.format(total)+" VNĐ");
        }
                    } catch (Exception ex) {
                        Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                         }

            }
       } );  
       setColor(btn_revenue);
   
}
	 public void LoadListBill() throws Exception{
         	TBLHistory.removeAll();
         	TBLHistory.revalidate();
         	TBLHistory.repaint();
         BillDao dao=new BillDao();
         DefaultTableModel defaultTableModel=new DefaultTableModel();
         TBLHistory.setModel(defaultTableModel);
         	defaultTableModel.addColumn("ID Bill");
         	defaultTableModel.addColumn("Time");
         	defaultTableModel.addColumn("Date");
           defaultTableModel.addColumn("Total");
           defaultTableModel.addColumn("Status");
           ArrayList<Bill> bills=dao.getAllRows();
              
            for(Bill bill:bills) {
                  
            	defaultTableModel.addRow(new Object[] { bill.getId(), bill.getTime(), bill.getDate(), bill.getTotal(), bill.getStatus() });
               }
            int rowHistory=TBLHistory.getRowCount();
            if (rowHistory>0) {
            	double total=0;
            	for (int row = 0; row < rowHistory; row++) {
            		total+=Double.parseDouble(String.valueOf(TBLHistory.getValueAt(row, 3)) ) ;
          
            	}
            	DecimalFormat formatter = new DecimalFormat("###,###,###");
            	jTextFieldRevenue.setText(formatter.format(total)+" VNĐ");
            }
	 }
	 private void fillDate() throws Exception{
		 BillDetailDao dao=new BillDetailDao();
		 ArrayList<String> listDate=dao.getDate();
		 jComboBoxDate.addItem("All Date");
		 for(int i=0;i<listDate.size();i++){
			 jComboBoxDate.addItem(listDate.get(i));

		 }
	 }

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
	    	
	    	JPanel jPanelRevenue = new JPanel();
	    	jPanelRevenue.setBackground(new Color(255, 255, 255));
	    	jPanelRevenue.setBounds(250, 50, 690, 480);
	    	bg.add(jPanelRevenue);
	    	jPanelRevenue.setLayout(null);
	    	
	    	jComboBoxDate = new JComboBox<>();
	    	jComboBoxDate.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	jComboBoxDate.setBounds(20, 60, 250, 30);
	    	jPanelRevenue.add(jComboBoxDate);
	    	
	    	JLabel jLabel15 = new JLabel("Revenue");
	    	jLabel15.setForeground(new Color(47, 60, 126));
	    	jLabel15.setHorizontalAlignment(SwingConstants.CENTER);
	    	jLabel15.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	jLabel15.setBounds(20, 285, 65, 30);
	    	jPanelRevenue.add(jLabel15);
	    	
	    	jTextFieldRevenue = new JTextField();
	    	jTextFieldRevenue.setBounds(100, 287, 170, 40);
	    	jPanelRevenue.add(jTextFieldRevenue);
	    	jTextFieldRevenue.setColumns(10);
	    	JButton btn_refresh = new JButton("Refresh");
	    	btn_refresh.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent evt) {
	    			btn_refreshActionPerformed(evt);
	    		}
	    	});
	    	
	    	btn_refresh.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	btn_refresh.setForeground(new Color(47, 60, 126));
	    	btn_refresh.setBackground(new Color(255, 214, 224));
	    	btn_refresh.setBorderPainted(false); 
	    	btn_refresh.setOpaque(true);
	    	btn_refresh.setBounds(20, 400, 120, 40);
	    	jPanelRevenue.add(btn_refresh);
	    	JScrollPane scrollPane = new JScrollPane();
	    	scrollPane.setBounds(0, 0, 2, 2);
	    	jPanelRevenue.add(scrollPane);
	    	JButton btn_printRevenue = new JButton("Print");
	    	btn_printRevenue.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent evt) {
	    			btn_printRevenueActionPerformed(evt);
	    		}
	    	});
	    	
	    	btn_printRevenue.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	btn_printRevenue.setForeground(new Color(47, 60, 126));
	    	btn_printRevenue.setBackground(new Color(255, 214, 224));
	    	btn_printRevenue.setBorderPainted(false); 
	    	btn_printRevenue.setOpaque(true);
	    	btn_printRevenue.setBounds(160, 400, 120, 40);
	    	jPanelRevenue.add(btn_printRevenue);
	    	
	    	JButton btnDetail = new JButton("Detail");
	    	btnDetail.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	btnDetail.setForeground(new Color(47, 60, 126));
	    	btnDetail.setBackground(new Color(255, 214, 224));
	    	btnDetail.setBorderPainted(false); 
	    	btnDetail.setOpaque(true);
	    	btnDetail.setBounds(518, 400, 120, 40);
	    	btnDetail.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent evt) {
	    			btnDetailActionPerformed(evt);
	    		}
	    	});
	    	
	    	jPanelRevenue.add(btnDetail);
	    	JButton btnChangeStatus = new JButton("Status");
	    	btnChangeStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	btnChangeStatus.setForeground(new Color(47, 60, 126));
	    	btnChangeStatus.setBackground(new Color(255, 214, 224));
	    	btnChangeStatus.setBorderPainted(false); 
	    	btnChangeStatus.setOpaque(true);
	    	btnChangeStatus.setBounds(345, 400, 120, 40);
	    	btnChangeStatus.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent evt) {
	    			btnChangeStatusActionPerformed(evt);
	    	}
	    	});
	    	jPanelRevenue.add(btnChangeStatus);
		
		JScrollPane jScrollPane3 = new JScrollPane();
		jScrollPane3.setBounds(300, 60, 370, 323);
		jPanelRevenue.add(jScrollPane3);
		
		TBLHistory = new JTable();
		TBLHistory.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"ID Bill", "Time", "Date", "Total", "Status"
			}
		));		
		TBLHistory.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	        	int selectedRow = TBLHistory.getSelectedRow();
	            if (selectedRow >= 0) {
	                btnDetail.setEnabled(true);
	                btnChangeStatus.setEnabled(true); 
	            }
	        }
	    });
		jScrollPane3.setViewportView(TBLHistory);
		
		JLabel jLabel19 = new JLabel("History");
		jLabel19.setForeground(new Color(47, 60, 126));
		jLabel19.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel19.setFont(new Font("Tahoma", Font.BOLD, 20));
		jLabel19.setBounds(448, 19, 100, 30);
		jPanelRevenue.add(jLabel19);
		
	}
	  
		private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {
	    	int row = TBLHistory.getSelectedRow();   
            try {
                int id=Integer.parseInt(String.valueOf(TBLHistory.getValueAt(row, 0)) ) ;
                new DetailBillGUI(id).setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
            }       
	    }
	    private void btnChangeStatusActionPerformed(java.awt.event.ActionEvent evt) {
	        int selectedRow = TBLHistory.getSelectedRow();
	        if (selectedRow != -1) {
	        	int IdBill = Integer.parseInt(TBLHistory.getValueAt(selectedRow, 0).toString());
	            String currentStatus = TBLHistory.getValueAt(selectedRow, 4).toString();
	            
	            String newStatus = currentStatus.equals("Delivered") ? "Shipping" : "Delivered";

	            try {
	                BillDao dao = new BillDao();
	                if (dao.updateStatus(IdBill, newStatus)) {
	                    // Cập nhật lại giá trị trong bảng
	                    TBLHistory.setValueAt(newStatus, selectedRow, 4);
	                    JOptionPane.showMessageDialog(this, "Cập nhật trạng thái thành công!");
	                } else {
	                    JOptionPane.showMessageDialog(this, "Không thể cập nhật trạng thái!");
	                }
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật trạng thái!");
	                ex.printStackTrace();
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn một đơn hàng!");
	        }
	    }

	    
	 private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {
	        try {
	            jComboBoxDate.setSelectedIndex(0);
	            LoadListBill();
	        } catch (Exception ex) {
	            Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
	        }
	 }
	 
	  protected void btn_revenueMouseClicked(ActionEvent evt) {
			
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
	 
	 
	 private void btn_printRevenueActionPerformed(java.awt.event.ActionEvent evt) {	        
	         MessageFormat header = new MessageFormat("Doanh Thu");
	        MessageFormat footer = new MessageFormat("Page(0, number, integer)");
	        try{
	            TBLHistory.print(JTable.PrintMode.FIT_WIDTH, header, footer);
	        }
	        catch(Exception e){
	            System.out.println(e);
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
	    
	    public static void main(String[] args) {
	        EventQueue.invokeLater(() -> {
	            try {
	                RevenueGUI frame = new RevenueGUI();
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });   
	}
}