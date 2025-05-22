package View;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import Controller.UserDao;
import Model.User;
import javax.swing.JOptionPane;

public class UpdateUserGUI extends JFrame { 
	private static final long serialVersionUID = 1L;
	private JLayeredPane jPanel1;
    private JTextField jTextFieldUsename;
    private JTextField jTextFieldName;
    private JTextField jPasswordField;
    private JRadioButton jRadioButtonMale;
    private JRadioButton jRadioButtonFemale;
    private JRadioButton jRadioButtonAdmin;
    private JRadioButton jRadioButtonCustomer;
    private int id;
	public UpdateUserGUI(int idUser) {
		this.id=idUser;
       
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationByPlatform(true);
			setUndecorated(true);
			
	        setBounds(0, 0, 324, 511);
	        getContentPane().setLayout(null);
	        
	        jPanel1 = new JLayeredPane();
	        jPanel1.setBackground(new Color(255, 255, 255));
	        jPanel1.setOpaque(true); 
	        jPanel1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	        jPanel1.setBounds(0, 0, 324, 511);
	        jPanel1.setLayout(null);
	        getContentPane().add(jPanel1);

	        JLabel jLabel5 = new JLabel("UPDATE USER");
	        jLabel5.setFont(new Font("Segoe UI", Font.BOLD, 24));
	        jLabel5.setBounds(80, 34, 200, 30);
	        jLabel5.setBackground(new Color(47, 60, 126));
	        jPanel1.add(jLabel5);

	        JLabel jLabel4 = new JLabel("Name");
	        jLabel4.setForeground(new Color(128, 128, 128));
	        jLabel4.setBounds(30, 93, 100, 25);
	        jPanel1.add(jLabel4);

	        jTextFieldName = new JTextField();
	        jTextFieldName.setBounds(30, 128, 250, 25);
	        jTextFieldName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
	        jTextFieldName.setOpaque(false);
	        jTextFieldName.setBackground(Color.WHITE);
	        jPanel1.add(jTextFieldName);

	        JLabel jLabel7 = new JLabel("Gender");
	        jLabel7.setForeground(new Color(128, 128, 128));
	        jLabel7.setBounds(30, 163, 100, 25);
	        jPanel1.add(jLabel7);

	        jRadioButtonMale = new JRadioButton("Male");
	        jRadioButtonFemale = new JRadioButton("Female");
	        ButtonGroup genderGroup = new ButtonGroup();
	        genderGroup.add(jRadioButtonMale);
	        genderGroup.add(jRadioButtonFemale);
	        jRadioButtonMale.setBounds(30, 194, 80, 25);
	        jRadioButtonFemale.setBounds(147, 194, 100, 25);
	        jPanel1.add(jRadioButtonMale);
	        jPanel1.add(jRadioButtonFemale);

	        JLabel jLabel14 = new JLabel("Role");
	        jLabel14.setForeground(new Color(128, 128, 128));
	        jLabel14.setBounds(30, 225, 100, 25);
	        jPanel1.add(jLabel14);

	        jRadioButtonAdmin = new JRadioButton("Admin");
	        jRadioButtonCustomer = new JRadioButton("Employee");
	        ButtonGroup roleGroup = new ButtonGroup();
	        roleGroup.add(jRadioButtonAdmin);
	        roleGroup.add(jRadioButtonCustomer);
	        jRadioButtonAdmin.setBounds(30, 256, 80, 25);
	        jRadioButtonCustomer.setBounds(147, 256, 100, 25);
	        jPanel1.add(jRadioButtonAdmin);
	        jPanel1.add(jRadioButtonCustomer);

	        JLabel jLabel8 = new JLabel("Username");
	        jLabel8.setForeground(new Color(128, 128, 128));
	        jLabel8.setBounds(30, 287, 100, 25);
	        jPanel1.add(jLabel8);

	        jTextFieldUsename = new JTextField();
	        jTextFieldUsename.setBounds(30, 322, 250, 25);
	        jTextFieldUsename.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
	        jTextFieldUsename.setOpaque(false);
	        jTextFieldUsename.setBackground(Color.WHITE);
	        jPanel1.add(jTextFieldUsename);

	        
	        JLabel jLabel6 = new JLabel("Password");
	        jLabel6.setForeground(new Color(128, 128, 128));
	        jLabel6.setBounds(30, 357, 100, 25);
	        jPanel1.add(jLabel6);

	        jPasswordField = new JPasswordField();
	        jPasswordField.setBounds(30, 392, 250, 25);
	        jPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
	        jPasswordField.setOpaque(false);
	        jPasswordField.setBackground(Color.WHITE);
	        jPanel1.add(jPasswordField);

	        JButton btnAdd = new JButton("ADD");
	        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 10));
	        btnAdd.setForeground(new Color(255, 255, 255));
	        btnAdd.setBounds(30, 444, 100, 40);
	        btnAdd.setBackground(new Color(47, 79, 150));
	        btnAdd.setBorderPainted(false);    
	        btnAdd.setOpaque(true);
	        jPanel1.add(btnAdd);
	        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	            	btnAddMouseClicked(evt);
	            }
	        });
	        
	        JButton btnBack = new JButton("BACK");
	        btnBack.setFont(new Font("Tahoma", Font.BOLD, 10));
	        btnBack.setForeground(new Color(255, 255, 255));
	        btnBack.setBounds(182, 444, 100, 40);
	        btnBack.setBackground(new Color(47, 79, 150));
	        btnBack.setBorderPainted(false);    
	        btnBack.setOpaque(true);
	        jPanel1.add(btnBack);
	        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	            	btnBackMouseClicked(evt);
	            }
	        });
	        
	        jRadioButtonCustomer.setSelected(false);
	        jRadioButtonAdmin.setSelected(false);
	        jRadioButtonMale.setSelected(false);
	        jRadioButtonFemale.setSelected(false);
	         UserDao dao=new UserDao();
	         User user=dao.getbyId(idUser);
	         user=new User(user.getId(),user.getName(),user.getUsername(),user.getPassword(),user.getSex(),user.getRole());
	         jTextFieldName.setText(user.getName());
	         jTextFieldUsename.setText(user.getUsername());
	         if (user.getRole()==1) {
	             jRadioButtonCustomer.setSelected(true);
	         }else if(user.getRole()==2){
	             jRadioButtonAdmin.setSelected(true);
	         }
	         if (user.getSex()=="Nam") {
	             jRadioButtonMale.setSelected(true);
	         }else if (user.getSex()=="Nữ"){
	             jRadioButtonFemale.setSelected(true);
	         }
	         
	        setLocationRelativeTo(null);
		
}
	
	private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {
        String name=jTextFieldName.getText();
        String username=jTextFieldUsename.getText();
        String password=jPasswordField.getText();
        String sexString=null;
        int role=0;
        if (jRadioButtonAdmin.isSelected()) {
           role=2;
       }else if(jRadioButtonCustomer.isSelected()){
           role=1;
       }
        if (jRadioButtonMale.isSelected()) {
           sexString="Nam";
       }else if (jRadioButtonFemale.isSelected()) {
           sexString="Nữ";
       }
       User user=new User(this.id,name,username,password,sexString,role);
       UserDao userDao=new UserDao();
       if (userDao.updateUser(user)>0) {
            JOptionPane.showMessageDialog(UpdateUserGUI.this, "Update Thành Công !!!");
            this.dispose();
       }
     
   }

   private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {
        this.dispose();
        
   }

}
