package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.UserDao;
import Model.User;

public class AdministrationGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable TBLUser;
	private JButton btn_home, btn_productmanagement, btn_revenue, btn_administration, btn_exit;

	public AdministrationGUI() throws Exception {
        
         Init();
         loadListUser();
         setColor(btn_administration);
   
	}
	
	public void loadListUser() throws Exception{
        UserDao dao=new UserDao();
		 DefaultTableModel defaultTableModel=new DefaultTableModel();
		 TBLUser.setModel(defaultTableModel);
               TBLUser.setDefaultEditor(Object.class, null);
               defaultTableModel.addColumn("ID");
		 defaultTableModel.addColumn("Name");
		 defaultTableModel.addColumn("Username");
               defaultTableModel.addColumn("Sex");
               defaultTableModel.addColumn("Role");
		 List<User> users=dao.getAllRows();
		 for(User user:users) {
                   
			 String name=user.getName();
                       String username=user.getUsername();
                       String sex=user.getSex();
                       String role=null;
                       if (user.getRole()==2) {
                       role="Admin";
                       }else{
                        role="Employee";
                       }
                        defaultTableModel.addRow(new Object[] { user.getId(),name,username,sex,role});
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
		
		JPanel jPanelUser = new JPanel();
		jPanelUser.setBackground(new Color(255, 255, 255));
		jPanelUser.setBounds(250, 50, 690, 480);
		bg.add(jPanelUser);
		jPanelUser.setLayout(null);
		
		JButton btn_addUser = new JButton("ADD");
		btn_addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				 btn_addUserActionPerformed(evt);
			}
		});
		btn_addUser.setForeground(new Color(47, 60, 126));
		btn_addUser.setBackground(new Color(255, 214, 224));
		btn_addUser.setBorderPainted(false);    
		btn_addUser.setOpaque(true);
		btn_addUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_addUser.setBounds(30, 35, 120, 40);
		jPanelUser.add(btn_addUser);
		
		JButton btn_updateUser = new JButton("EDIT");
		btn_updateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				 btn_updateUserActionPerformed(evt);
			}
		});
		btn_updateUser.setForeground(new Color(47, 60, 126));
		btn_updateUser.setBackground(new Color(255, 214, 224));
		btn_updateUser.setBorderPainted(false);    
		btn_updateUser.setOpaque(true);
		btn_updateUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_updateUser.setBounds(200, 35, 120, 40);
		jPanelUser.add(btn_updateUser);
		
		JButton btn_delUser = new JButton("DELETE");
		btn_delUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btn_delUserActionPerformed(evt);
			}
		});
		btn_delUser.setForeground(new Color(47, 60, 126));
		btn_delUser.setBackground(new Color(255, 214, 224));
		btn_delUser.setBorderPainted(false);    
		btn_delUser.setOpaque(true);
		btn_delUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_delUser.setBounds(360, 35, 120, 40);
		jPanelUser.add(btn_delUser);
		
		JButton btn_updateUser1 = new JButton("REFRESH");
		btn_updateUser1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				 btn_updateUser1ActionPerformed(evt);			
			}
		});
		btn_updateUser1.setForeground(new Color(47, 60, 126));
		btn_updateUser1.setBackground(new Color(255, 214, 224));
		btn_updateUser1.setBorderPainted(false);    
		btn_updateUser1.setOpaque(true);
		btn_updateUser1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_updateUser1.setBounds(530, 35, 120, 40);
		jPanelUser.add(btn_updateUser1);
		
		JScrollPane jScrollPane1 = new JScrollPane();
		jScrollPane1.setBounds(15, 110, 660, 350);
		jPanelUser.add(jScrollPane1);
		
		TBLUser = new JTable();
		TBLUser.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
					"ID", "Name", "Username", "Password", "Sex", "Role"
			}
		));
		jScrollPane1.setViewportView(TBLUser);
	}
	
	private void btn_updateUserActionPerformed(java.awt.event.ActionEvent evt) {
        int row=TBLUser.getSelectedRow();
        if(row==-1){
            JOptionPane.showMessageDialog(AdministrationGUI.this, "Bạn Chưa Chọn Hàng Cần Cập Nhật !!!");
        }else{
            int idUser= Integer.parseInt(String.valueOf(TBLUser.getValueAt(row, 0)));
           new UpdateUserGUI(idUser).setVisible(true);
        }
    }
	
	private void btn_delUserActionPerformed(java.awt.event.ActionEvent evt) {
        int row=TBLUser.getSelectedRow();
        if(row==-1){
            JOptionPane.showMessageDialog(AdministrationGUI.this, "Bạn Chưa Chọn Hàng Cần Xóa !!!");
        }else{
            int a = JOptionPane.showConfirmDialog(AdministrationGUI.this, "Bạn Có Chắc Xóa ","Xác nhận xoá",JOptionPane.YES_NO_OPTION);
            if(a == JOptionPane.YES_OPTION){
                        int idUser= Integer.parseInt(String.valueOf(TBLUser.getValueAt(row, 0)));
                     UserDao dao=new UserDao();
                     if (dao.deleteUser(idUser)>0) {
                        JOptionPane.showMessageDialog(AdministrationGUI.this, "Xoá thành công!");
                         try {
                             loadListUser();
                         } catch (Exception ex) {
                             Logger.getLogger(AdministrationGUI.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     } 
            }
            
        }
    }
	
	private void btn_addUserActionPerformed(java.awt.event.ActionEvent evt) {
        new AddUserGUI().setVisible(true);
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
	 private void btn_administrationMouseClicked(ActionEvent evt) {
			
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
	 
	 private void btn_updateUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateUser1ActionPerformed
        TBLUser.removeAll();
        TBLUser.revalidate();
        TBLUser.repaint();
        try {
            loadListUser();
        } catch (Exception ex) {
            Logger.getLogger(HomeGUI.class.getName()).log(Level.SEVERE, null, ex);
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
	            java.util.logging.Logger.getLogger(AdministrationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(AdministrationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(AdministrationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(AdministrationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    new AdministrationGUI().setVisible(true);
	                } catch (Exception ex) {
	                    Logger.getLogger(AdministrationGUI.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        });
	    }
}
