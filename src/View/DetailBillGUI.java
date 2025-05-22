package View;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import Controller.BillDetailDao;
import Model.ProductPayment;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class DetailBillGUI extends JFrame {
	
    static final long serialVersionUID = 1L;

	public DetailBillGUI(int IdBill) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setUndecorated(true);
		
        setBounds(0, 0, 474, 203);
        getContentPane().setLayout(null);
        
        JLayeredPane jPanel1 = new JLayeredPane();
        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        jPanel1.setBounds(0, 0, 474, 203);
        jPanel1.setLayout(null);
        getContentPane().add(jPanel1);
        
        JLabel lbTitle = new JLabel("BILL", SwingConstants.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lbTitle.setBounds(0, 10, 460, 30);
        jPanel1.add(lbTitle);
        
		JScrollPane jScrollPane2 = new JScrollPane();
		jScrollPane2.setBounds(40, 51, 388, 106);
		jPanel1.add(jScrollPane2);

        // Nút Close
        JButton btnClose = new JButton("CLOSE");
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnClose.setForeground(new Color(255, 255, 255));
        btnClose.setBounds(200, 172, 67, 21);
        btnClose.setBackground(new Color(47, 79, 150));
        btnClose.setBorderPainted(false);    
        btnClose.setOpaque(true);
        btnClose.addActionListener(e -> dispose());
        jPanel1.add(btnClose);
        
        JTable jTable1 = new JTable();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ));
        jTable1.setSelectionBackground(new Color(255, 204, 102));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);
        
        setLocationRelativeTo(null);
        lbTitle.setText("#BILL"+IdBill);
      DefaultTableModel defaultTableModel=new DefaultTableModel();
      jTable1.setModel(defaultTableModel);
      BillDetailDao dao=new BillDetailDao();
      ArrayList<ProductPayment> details=new ArrayList<ProductPayment>();
       details=dao.getBillDetails(IdBill);
             defaultTableModel.addColumn("Name");
	 defaultTableModel.addColumn("Quantity");
             defaultTableModel.addColumn("Price");
	 defaultTableModel.addColumn("SubTotal");
              DecimalFormat formatter = new DecimalFormat("###,###,###");
	 for(int i=0;i<details.size();i++) {
                 
                      defaultTableModel.addRow(new Object[]{
                          details.get(i).getNameProduct(),
                          details.get(i).getQuantity(),
                          formatter.format(details.get(i).getPrice())+" VNĐ",
                          formatter.format(details.get(i).getTotal())+" VNĐ"
                      });
                 }
	}
        
    }
