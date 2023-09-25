import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    Login(){

        JFrame frame = new JFrame();

        frame.getContentPane().setBackground(Color.white);
        frame.setLayout(null);

        JLabel username = new JLabel("Username");
        username.setBounds(40,20,100,30);
        frame.add(username);

        JTextField tfusername = new JTextField();
        tfusername.setBounds(150,20,150,30);
        frame.add(tfusername);

        JLabel password = new JLabel("Password");
        password.setBounds(40,70,100,30);
        frame.add(password);

        JPasswordField tfpassword = new JPasswordField();
        tfpassword.setBounds(150,70,150,30);
        frame.add(tfpassword);

        JButton login = new JButton("LOGIN");
        login.setBounds(150,140,150,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        frame.add(login);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350,0,200,200);
        frame.add(image);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == login) {
                    String username = tfusername.getText();
                    char[] passwordChars = tfpassword.getPassword();
                    String password = new String(passwordChars);

                    // Check login credentials
                    if (username.equals(Admin.admins[0]) && password.equals(Admin.passes[0])) {
                        JOptionPane.showMessageDialog(frame, "Login successful!");
                        frame.setVisible(false);
                        new AdminGui();
                    }
                    else if (username.equals(Admin.admins[1]) && password.equals(Admin.passes[1])) {
                        JOptionPane.showMessageDialog(frame, "Login successful!");
                        frame.setVisible(false);
                        new AdminGui();
                    }
                    else if (username.equals(Admin.admins[2]) && password.equals(Admin.passes[2])) {
                        JOptionPane.showMessageDialog(frame, "Login successful!");
                        frame.setVisible(false);
                        new AdminGui();
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Invalid username or password");
                    }
                }
            }
        });


        frame.setSize(600,300);
        frame.setLocation(450,200);
        frame.setVisible(true);
        frame.setResizable(false);
    }


}
