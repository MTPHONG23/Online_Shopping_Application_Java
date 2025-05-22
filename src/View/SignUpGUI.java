package View;

import Model.User;

import javax.swing.*;

import Controller.UserDao;

import java.awt.*;
import java.awt.event.*;

public class SignUpGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;
    private UserDao userDao;

    public SignUpGUI() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setUndecorated(true); 

        JPanel panel = new JPanel();
        panel.setBackground(new Color(47, 79, 150));
        panel.setLayout(null);
        getContentPane().add(panel);

        JLabel closeLabel = new JLabel("X");
        closeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        closeLabel.setBounds(470, 5, 20, 20);
        closeLabel.setForeground(new Color(255, 255, 255));
        closeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        panel.add(closeLabel);

        JLabel userLabel = new JLabel("Username");
        userLabel.setForeground(new Color(255, 255, 255));
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        userLabel.setBounds(35, 143, 150, 25);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(200, 144, 200, 30);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(new Color(255, 255, 255));
        passLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        passLabel.setBounds(35, 208, 150, 25);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 203, 200, 30);
        panel.add(passwordField);

        JButton loginBtn = new JButton("Sign In");
        loginBtn.setBackground(new Color(255, 255, 255));
        loginBtn.setForeground(new Color(47, 60, 126));
        loginBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        loginBtn.setBounds(172, 276, 150, 40);
        loginBtn.addActionListener(e -> handleLogin());
        panel.add(loginBtn);
        
        JLabel lblNewLabel = new JLabel("WELCOME");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(80, 27, 334, 80);
        panel.add(lblNewLabel);
    }

    private void handleLogin() {
        userDao = new UserDao();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ username và password");
            return;
        }

        if (userDao.login(username, password)) {
            try {
                User user = userDao.getUserLogined(username, password);
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công! Xin chào " + user.getUsername());
                new HomeGUI().setVisible(true);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi lấy thông tin người dùng");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignUpGUI().setVisible(true));
    }
}
