package View;

import Controller.BillDao;
import Controller.BillDetailDao;
import Controller.ProductDao;
import Model.Bill;
import Model.BillDetail;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class PaymentGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable TBLPayment;
    private JTextField jTextFieldTotal;
    private JTextField jTextFieldRecieved;
    private JTextField jTextFieldRefund;
    private JButton btn_updatePayment1; 
    private JButton btn_updatePayment2; 
    private JButton btn_updatePayment3; 
    String recieved;
    String totalPayment="";
    JTable table;
    double bHeight=100;
    
    public PaymentGUI(JLabel totalJLabel, JTable TBLCart) {
    	getContentPane().setBackground(new Color(255, 255, 255));
    	table = TBLCart;
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setUndecorated(true);
		
        setBounds(0, 0, 724, 358);
        getContentPane().setLayout(null);
        
        JScrollPane jScrollPane1 = new JScrollPane();
        jScrollPane1.setBounds(10, 10, 450, 338);
        getContentPane().add(jScrollPane1);

        TBLPayment = new JTable();
        TBLPayment.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                },
                new String [] {
                    "id", "Name", "Quantity", "Price"
                }
            ));
            jScrollPane1.setViewportView(TBLPayment);

        JLabel jLabel1 = new JLabel("Total Price :");
        jLabel1.setForeground(new Color(128, 128, 128));
        jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jLabel1.setBounds(470, 42, 80, 20);
        getContentPane().add(jLabel1);

        jTextFieldTotal = new JTextField("<dynamic>");
        jTextFieldTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jTextFieldTotal.setEditable(false);
        jTextFieldTotal.setBounds(551, 38, 150, 33);
        getContentPane().add(jTextFieldTotal);
        
        JLabel jLabel2 = new JLabel("Received :");
        jLabel2.setForeground(new Color(128, 128, 128));
        jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jLabel2.setBounds(480, 107, 80, 20);
        getContentPane().add(jLabel2);

        jTextFieldRecieved = new JTextField();
        jTextFieldRecieved.setBounds(551, 103, 150, 33);
        getContentPane().add(jTextFieldRecieved);
        jTextFieldRecieved.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldRecievedKeyPressed(evt);
            }
        });
        
        btn_updatePayment1 = new JButton("CACULATOR");
        btn_updatePayment1.setForeground(new Color(255, 255, 255));
        btn_updatePayment1.setFont(new Font("Tahoma", Font.BOLD, 12));
        btn_updatePayment1.setBounds(516, 160, 150, 33);
        btn_updatePayment1.setBackground(new Color(47, 79, 150));
        btn_updatePayment1.setBorderPainted(false);    
        btn_updatePayment1.setOpaque(true);
        getContentPane().add(btn_updatePayment1);
        btn_updatePayment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updatePayment1ActionPerformed(evt);
            }
        });
        
        JLabel jLabel3 = new JLabel("Refund :");
        jLabel3.setForeground(new Color(128, 128, 128));
        jLabel3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jLabel3.setBounds(480, 231, 80, 20);
        getContentPane().add(jLabel3);

        jTextFieldRefund = new JTextField("0 VND");
        jTextFieldRefund.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jTextFieldRefund.setEditable(false);
        jTextFieldRefund.setBounds(551, 226, 150, 33);
        getContentPane().add(jTextFieldRefund);

        btn_updatePayment3 = new JButton("BACK");
        btn_updatePayment3.setForeground(new Color(255, 255, 255));
        btn_updatePayment3.setFont(new Font("Tahoma", Font.BOLD, 12));
        btn_updatePayment3.setBounds(480, 290, 100, 42);
        btn_updatePayment3.setBackground(new Color(47, 79, 150));
        btn_updatePayment3.setBorderPainted(false);    
        btn_updatePayment3.setOpaque(true);
        getContentPane().add(btn_updatePayment3);
        btn_updatePayment3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updatePayment3ActionPerformed(evt);
            }
        });
        
        btn_updatePayment2 = new JButton("PRINT");
        btn_updatePayment2.setFont(new Font("Tahoma", Font.BOLD, 12));
        btn_updatePayment2.setForeground(new Color(255, 255, 255));
        btn_updatePayment2.setBounds(601, 290, 100, 42);
        btn_updatePayment2.setBackground(new Color(47, 79, 150));
        btn_updatePayment2.setBorderPainted(false);    
        btn_updatePayment2.setOpaque(true);
        getContentPane().add(btn_updatePayment2);
        btn_updatePayment2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updatePayment2ActionPerformed(evt);
            }
        });
        
        jTextFieldTotal.setEditable(false);
        jTextFieldTotal.setText(totalJLabel.getText());
       
        jTextFieldRefund.setEditable(false);
      
         DefaultTableModel model=new DefaultTableModel();
          model=(DefaultTableModel) this.TBLPayment.getModel();
          TBLPayment.setDefaultEditor(Object.class, null);
           int rows = TBLCart.getRowCount();
           for (int row = 0; row < rows; row++) {
                  model.addRow(new Object[]{
                        TBLCart.getValueAt(row, 0),
                         TBLCart.getValueAt(row, 1),
                        TBLCart.getValueAt(row, 2),
                        TBLCart.getValueAt(row, 3),
                    });
                 
            }
           if(jTextFieldRecieved.getText().equals("")){
               btn_updatePayment2.setEnabled(false);
           }
           else{
               btn_updatePayment2.setEnabled(true);
           }
      
           setLocationRelativeTo(null);
    }
    public PageFormat getPageFormat(PrinterJob pj)
    {
        
                PageFormat pf = pj.defaultPage();
                Paper paper = pf.getPaper();    

                double bodyHeight = bHeight;  
                double headerHeight = 5.0;                  
                double footerHeight = 5.0;        
                double width = cm_to_pp(20); 
                double height = cm_to_pp(headerHeight+bodyHeight+footerHeight); 
                paper.setSize(width, height);
                paper.setImageableArea(0,10,width,height - cm_to_pp(1));  

                pf.setOrientation(PageFormat.PORTRAIT);  
                pf.setPaper(paper);    

                return pf;
    }
          protected static double cm_to_pp(double cm)
        {            
    	        return toPPI(cm * 0.393600787);            
        }
     
            protected static double toPPI(double inch)
        {            
    	        return inch * 72d;            
        }



    public class BillPrintable implements Printable {
            
    	public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
        {    
            ImageIcon icon=new ImageIcon("F:\\project swing\\FoodTSK\\src\\img\\tsk.PNG"); 
            int result = NO_SUCH_PAGE;    
              if (pageIndex == 0) {                    
              
                  Graphics2D g2d = (Graphics2D) graphics;                    
                  g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 

              try{
                  int y=30;
                  int yShift = 10;
                  int headerRectHeight=15;
                  DecimalFormat formatter = new DecimalFormat("###,###,###");
                  String RecievedString="0 VNĐ";
                  if (!jTextFieldRecieved.getText().equals("")) {
                       RecievedString= formatter.format(Float.parseFloat(jTextFieldRecieved.getText()))+" VNĐ";
                  }
               
                   
                      
                  g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
                  g2d.drawImage(icon.getImage(), 50, 20, 100, 50, rootPane);y+=yShift+30;
                  g2d.drawString("-----------------------------------------",12,y);y+=yShift;
                  g2d.drawString("                 AYA COFFEE              ",12,y);y+=yShift;
                  
                 
                  g2d.drawString("-----------------------------------------",12,y);y+=headerRectHeight;

                  g2d.drawString(" Item Name                    Price   ",10,y);y+=yShift;
                  g2d.drawString("-----------------------------------------",10,y);y+=headerRectHeight;
                  int rows = TBLPayment.getRowCount();
                  int tongsl = 0;
                  for(int row=0; row<rows; row++)
                  {
                  g2d.drawString(" "+TBLPayment.getValueAt(row, 1)+"                            ",10,y);y+=yShift;
                  g2d.drawString("        "+TBLPayment.getValueAt(row, 2)+" * "+Float.parseFloat(String.valueOf(TBLPayment.getValueAt(row, 3)))/ Float.parseFloat(String.valueOf(TBLPayment.getValueAt(row, 2))) ,10,y); 
                  g2d.drawString(String.valueOf(TBLPayment.getValueAt(row, 3)),170,y);y+=yShift;
                  tongsl+= Integer.parseInt(String.valueOf(TBLPayment.getValueAt(row, 2)));
                  }
                
                  g2d.drawString("-----------------------------------------",10,y);y+=yShift;
                  g2d.drawString(" Total amount:               "+jTextFieldTotal.getText()+"   ",10,y);y+=yShift;
                  g2d.drawString("-----------------------------------------",10,y);y+=yShift;
                  g2d.drawString(" Total of products:          "+tongsl+"   ",10,y);y+=yShift;
                  g2d.drawString("-----------------------------------------",10,y);y+=yShift;
                  g2d.drawString(" Recieved      :             "+RecievedString+"   ",10,y);y+=yShift;
                  g2d.drawString("-----------------------------------------",10,y);y+=yShift;
                  g2d.drawString(" Refund   :                  "+jTextFieldRefund.getText()+"   ",10,y);y+=yShift;
        
                  g2d.drawString("*****************************************",10,y);y+=yShift;
                  g2d.drawString("            THANK YOU COME AGAIN         ",10,y);y+=yShift;
          }
          catch(Exception e){
                  
          }

                    result = PAGE_EXISTS;    
                }    
                return result;    
            }
        

       }
   
    private void btn_updatePayment1ActionPerformed(java.awt.event.ActionEvent evt) {
    
    	String totalStr = jTextFieldTotal.getText().replaceAll("[^\\d]", ""); 
    	String receivedStr = jTextFieldRecieved.getText().replaceAll("[^\\d]", "");

    	if (!receivedStr.isEmpty() && !totalStr.isEmpty()) {
    	    int total = Integer.parseInt(totalStr);
    	    int received = Integer.parseInt(receivedStr);

    	    if (received >= total) {
    	        int refund = received - total;
    	        jTextFieldRefund.setText(refund + " VNĐ");
    	    } else {
    	        JOptionPane.showMessageDialog(this, "Không đủ tiền để thanh toán!");
    	    }
    	} else {
    	    JOptionPane.showMessageDialog(this, "Số tiền không hợp lệ!");
    	}

    }

    private void btn_updatePayment2ActionPerformed(java.awt.event.ActionEvent evt) {
    
        if(!jTextFieldRefund.getText().equals("")){
        	totalPayment = jTextFieldTotal.getText().replaceAll("[^\\d]", "");  // giữ lại chỉ số

        PrinterJob pj = PrinterJob.getPrinterJob();        
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
            BillDao bd=new BillDao();
            ProductDao pd = new ProductDao();
          String[] time=String.valueOf(java.time.LocalTime.now()).split("\\.");
          String [] time1=time[0].split("\\:");
          String timeNow=time1[0]+":"+time1[1];
            Bill b=new Bill(null,timeNow,String.valueOf(java.time.LocalDate.now()),Float.parseFloat(totalPayment),"Shipping");
            try {
                if (bd.insertBill(b)==true) {
                    try {
                        int idMax=bd.getBillMax();
                       int rows = TBLPayment.getRowCount();
                        BillDetailDao bdd=new BillDetailDao();
                        for (int row = 0; row<rows; row++) { 
                            Integer product=Integer.parseInt(String.valueOf(TBLPayment.getValueAt(row, 0)) ) ;
                            Integer quantity=Integer.parseInt(String.valueOf(TBLPayment.getValueAt(row, 2)) ) ;
                            Float price=Float.parseFloat(String.valueOf(TBLPayment.getValueAt(row, 3))) ;
                            BillDetail bd1=new BillDetail(null,product,idMax,quantity,price);
                           bdd.insertBillD(bd1);
                           pd.giamsoluong((int)TBLPayment.getValueAt(row, 2),(int)TBLPayment.getValueAt(row, 0));
                            
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PaymentGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pj.print();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PaymentGUI.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
          
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
            try {
                HomeGUI h = new HomeGUI();
                h.showPanel(1);
            } catch (Exception ex) {
                Logger.getLogger(PaymentGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(this,"Không thể in hoá đơn vì chưa tính tiền thừa!");
        }
    }

    private void jTextFieldRecievedKeyPressed(java.awt.event.KeyEvent evt) {
        char c=evt.getKeyChar();
        if (Character.isLetter(c)) {
            jTextFieldRecieved.setEditable(false);
        }else{
         jTextFieldRecieved.setEditable(true);
        }
        if(!jTextFieldRecieved.getText().equals("")){
               btn_updatePayment2.setEnabled(true);
           }
    }

    private void btn_updatePayment3ActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        
    }
}