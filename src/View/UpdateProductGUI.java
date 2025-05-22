package View;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import Controller.ProductDao;
import Model.Product;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

public class UpdateProductGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLayeredPane jPanel1;
    private JTextField jTextFieldNameProduct;
    private JTextField jTextFieldQuantity;
    private JTextField jTextFieldPrice;
    private JRadioButton jRadioButtonFood;
    private JRadioButton jRadioButtonDrink;
    private int id;

	public UpdateProductGUI(int id) {
		this.id=id;
	    jTextFieldNameProduct = new JTextField();
	    jTextFieldPrice = new JTextField();
	    jTextFieldQuantity = new JTextField();
	    jRadioButtonFood = new JRadioButton("Food");
	    jRadioButtonDrink = new JRadioButton("Drink");

        ProductDao productDao=new ProductDao();
        Product product=productDao.getbyId(id);
        jTextFieldNameProduct.setText(product.getName());
        jTextFieldPrice.setText(String.valueOf(product.getPrice()));
        jTextFieldQuantity.setText(String.valueOf(product.getQuantity()));
        if (product.getCategoryID()==1) {
            jRadioButtonFood.isSelected();
        }else{
            jRadioButtonDrink.isSelected();
        }
 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setUndecorated(true);
		
        setBounds(0, 0, 565, 510);
        getContentPane().setLayout(null);
        
        jPanel1 = new JLayeredPane();
        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setOpaque(true); 
        jPanel1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        jPanel1.setBounds(0, 0, 565, 510);
        jPanel1.setLayout(null);
        getContentPane().add(jPanel1);
        
        JLabel jLabel5 = new JLabel("UPDATE PRODUCT");
        jLabel5.setFont(new Font("Tahoma", Font.BOLD, 24));
        jLabel5.setBounds(164, 32, 240, 30);
        jLabel5.setBackground(new Color(47, 60, 126));
        jPanel1.add(jLabel5);
        
        JLabel jLabel4 = new JLabel("Name");
        jLabel4.setForeground(new Color(128, 128, 128));
        jLabel4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jLabel4.setBounds(34, 116, 80, 20);
        jPanel1.add(jLabel4);

        jTextFieldNameProduct.setBounds(34, 146, 214, 25);
        jTextFieldNameProduct.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
        jTextFieldNameProduct.setOpaque(false);
        jTextFieldNameProduct.setBackground(Color.WHITE);
        jPanel1.add(jTextFieldNameProduct);

        JLabel jLabel9 = new JLabel("Price");
        jLabel9.setForeground(new Color(128, 128, 128));
        jLabel9.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jLabel9.setBounds(319, 116, 80, 20);
        jPanel1.add(jLabel9);

        jTextFieldQuantity.setBounds(34, 281, 214, 25);
        jTextFieldQuantity.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
        jTextFieldQuantity.setOpaque(false);
        jTextFieldQuantity.setBackground(Color.WHITE);
        jPanel1.add(jTextFieldQuantity);
        jTextFieldQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldQuantityKeyPressed(evt);
            }
        });

        JLabel jLabel6 = new JLabel("Quantity");
        jLabel6.setForeground(new Color(128, 128, 128));
        jLabel6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jLabel6.setBounds(34, 251, 80, 20);
        jPanel1.add(jLabel6);

        jTextFieldPrice.setBounds(319, 146, 214, 25);
        jTextFieldPrice.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
        jTextFieldPrice.setOpaque(false);
        jTextFieldPrice.setBackground(Color.WHITE);
        jPanel1.add(jTextFieldPrice);
        jTextFieldPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPriceKeyPressed(evt);
            }
        });
        
        
        JLabel jLabel7 = new JLabel("Category");
        jLabel7.setForeground(new Color(128, 128, 128));
        jLabel7.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jLabel7.setBounds(319, 251, 80, 20);
        jPanel1.add(jLabel7);

        jRadioButtonFood.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jRadioButtonFood.setBounds(319, 294, 70, 25);
        jRadioButtonFood.setSelected(true);
        jPanel1.add(jRadioButtonFood);

        jRadioButtonDrink.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jRadioButtonDrink.setBounds(399, 294, 70, 25);
        jPanel1.add(jRadioButtonDrink);
        
        ButtonGroup categoryGroup = new ButtonGroup();
        categoryGroup.add(jRadioButtonFood);
        categoryGroup.add(jRadioButtonDrink);

        JButton btnAdd = new JButton("ADD");
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnAdd.setBackground(new Color(47, 79, 150));
        btnAdd.setBounds(72, 413, 176, 66);
        btnAdd.setBorderPainted(false);    
        btnAdd.setOpaque(true);
        jPanel1.add(btnAdd);
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });
        
        JButton btnBack = new JButton("BACK");
        btnBack.setForeground(new Color(255, 255, 255));
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnBack.setBackground(new Color(47, 79, 150));
        btnBack.setBounds(309, 413, 176, 66);
        btnBack.setBorderPainted(false);    
        btnBack.setOpaque(true);
        jPanel1.add(btnBack);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	btnBackMouseClicked(evt);
            }
        });
 
        setLocationRelativeTo(null);
	}
	
    private void jTextFieldQuantityKeyPressed(java.awt.event.KeyEvent evt) {
        char c=evt.getKeyChar();
        if (Character.isLetter(c)) {
            jTextFieldQuantity.setEditable(false);
            JOptionPane.showMessageDialog(UpdateProductGUI.this, "Bạn vui lòng nhập số !!!");
        }else{
            jTextFieldQuantity.setEditable(true);
        }
    }

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {
        String name=jTextFieldNameProduct.getText();
         int quantity=Integer.parseInt(jTextFieldQuantity.getText());
         int price=Integer.parseInt(jTextFieldPrice.getText());
         int cat=0;
         if (jRadioButtonFood.isSelected()) {
            cat=1;
        }else if(jRadioButtonDrink.isSelected()){
            cat=2;
        }
        Product product=new Product(this.id,name,cat,quantity,price);
        ProductDao productDao=new ProductDao();
        if (productDao.updateProduct(product)>0) {
             JOptionPane.showMessageDialog(UpdateProductGUI.this, "Update Thành Công !!!");
             this.dispose();
        }
      
        
    }
    
    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {
        this.dispose();
    }

    private void jTextFieldPriceKeyPressed(java.awt.event.KeyEvent evt) {
        char c=evt.getKeyChar();
        if (Character.isLetter(c)) {
            jTextFieldPrice.setEditable(false);
            JOptionPane.showMessageDialog(UpdateProductGUI.this, "Bạn vui lòng nhập số !!!");
        }else{
            jTextFieldPrice.setEditable(true);
        }
    }
    

}
