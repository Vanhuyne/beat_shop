package beatalbumshop;

import beatalbumshop.componment.MyButton;
import beatalbumshop.componment.MyDialog;
import beatalbumshop.dao.CustomerDAO;
import beatalbumshop.dao.CustomerDAOImpl;
import beatalbumshop.dao.Firebase;
import beatalbumshop.dao.UserDAO;
import beatalbumshop.dao.UserDAOImpl;
import beatalbumshop.model.Customer;
import beatalbumshop.model.LoggedInUser;
import beatalbumshop.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class LogIn extends javax.swing.JFrame {
    UserDAO userDAO = new UserDAOImpl();
    CustomerDAO customerDAO = new CustomerDAOImpl();

    public LogIn() {
        initComponents();
        setLocationRelativeTo(null);
        
        //rounded frame
        setShape(new RoundRectangle2D.Double(0, 0, 1280, 720, 20, 20));
        setSize(1280, 720);
        setLocationRelativeTo(null);
        
        addPlaceholderText(txtEmail, "Email");
        addPlaceholderText(txtPassword, "Password");
        
        txtEmail.requestFocus();
    }
    
    
    
    private void addPlaceholderText(JTextField textField, String placeholderText) {
        // Save the default foreground color of the text field
        Color defaultColor = textField.getForeground();
        
        // Set the placeholder text
        textField.setText(placeholderText);

        // Add a focus listener to handle the placeholder text
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(defaultColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholderText);
                }
            }
        });
    }
    
    

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$";
        return email.matches(emailRegex);
    }


        
    public String validateFormSigup(String email, String password) {
        // Kiểm tra tính hợp lệ của email
        if (email.isEmpty()) {
            txtEmail.requestFocus();
            return "Vui lòng nhập Email";
        } else if (!isValidEmail(email)) {
            txtEmail.requestFocus();
            return "Email sai định dạng";

        }

        if (password.isEmpty()) {
            txtPassword.requestFocus();
            return "Vui lòng nhập Pasword";
        }
        else if(!password.matches("[a-zA-Z0-9]+")) {
            txtPassword.requestFocus();
            return "Password chỉ được chứa các kí tự [a-zA-Z0-9]";
        }
        
        return null; // Trả về null nếu form hợp lệ
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        windowTitleBar1 = new beatalbumshop.componment.WindowTitleBar();
        pnlContent = new javax.swing.JPanel();
        pnlForm = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogIn = new beatalbumshop.componment.MyButton();
        lblSignUp = new javax.swing.JLabel();
        lblContinueAs = new javax.swing.JLabel();
        lblForgotPassword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);

        pnlMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlMain.setLayout(new java.awt.BorderLayout());

        windowTitleBar1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        pnlMain.add(windowTitleBar1, java.awt.BorderLayout.PAGE_START);

        pnlContent.setBackground(new java.awt.Color(255, 255, 255));

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Log In");

        txtEmail.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(82, 82, 82));
        txtEmail.setText("Email");
        txtEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        txtEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txtPassword.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(82, 82, 82));
        txtPassword.setText("jPasswordField1");
        txtPassword.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.GRAY));

        btnLogIn.setBackground(new java.awt.Color(0, 0, 0));
        btnLogIn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnLogIn.setForeground(new java.awt.Color(255, 255, 255));
        btnLogIn.setText("Log in");
        btnLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogInActionPerformed(evt);
            }
        });

        lblSignUp.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        lblSignUp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSignUp.setText("CREATE AN ACCOUNT");
        lblSignUp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        lblSignUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSignUpMouseClicked(evt);
            }
        });

        lblContinueAs.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        lblContinueAs.setText("CONTINUE AS A GUEST");
        lblContinueAs.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        lblContinueAs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblContinueAs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblContinueAsMouseClicked(evt);
            }
        });

        lblForgotPassword.setBackground(new java.awt.Color(255, 255, 255));
        lblForgotPassword.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        lblForgotPassword.setText("FORGOT PASSWORD?");
        lblForgotPassword.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        lblForgotPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblForgotPasswordMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblForgotPassword))
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblTitle)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSignUp)
                            .addComponent(lblContinueAs))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnLogIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50)
                .addComponent(txtEmail)
                .addGap(30, 30, 30)
                .addComponent(txtPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblForgotPassword)
                .addGap(57, 57, 57)
                .addComponent(btnLogIn, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(50, 50, 50)
                .addComponent(lblSignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(lblContinueAs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGap(433, 433, 433)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(433, Short.MAX_VALUE))
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContentLayout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
        );

        pnlMain.add(pnlContent, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblContinueAsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContinueAsMouseClicked
        Window[] windows = Window.getWindows();
        if(windows.length <= 1) {
            dispose();
            new Main().setVisible(true);
        }
    }//GEN-LAST:event_lblContinueAsMouseClicked

    private void lblSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSignUpMouseClicked
        dispose();
        new SignUp().setVisible(true);
    }//GEN-LAST:event_lblSignUpMouseClicked

    private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogInActionPerformed
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());
        String errorMessage = validateFormSigup(email, password);
        
        // co loi
        if (errorMessage != null) {
            MyDialog.display(1, errorMessage);
        }
        else {
            if (customerDAO.checkExitByEmail(email) && userDAO.checkExitByEmail(email)) {
                txtEmail.requestFocus();
                MyDialog.display(1, "Email này chưa đăng ký");
            }
            else {
                if(customerDAO.authentication(email, password) != null) {
                    Customer customer = customerDAO.authentication(email, password);
                    LoggedInUser.setCurrentLoggedIn(customer);

                    Window[] windows = Window.getWindows();
                    if(windows.length <= 1) {
                        new Main().setVisible(true);
                    }
                    else {
                        // open from buy now button
                        for (Window window : windows) {
                            if (window instanceof Main) {System.out.println("co");
                                Main mainWindow = (Main) window;
                                
                                // Access btnLogIn in Main
                                MyButton btnLI = mainWindow.getBtnLogIn();
                                // Check login
                                if (LoggedInUser.isLoggedIn()) {
                                    btnLI.setText("Account");
                                }
                                
                                // Repaint the current Main window
                                mainWindow.revalidate();
                                mainWindow.repaint();
                                
                                break;
                            }
                        }
                    }

                    dispose();
                }
                else if(userDAO.authentication(email, password) != null) {
                    User user = userDAO.authentication(email, password);
                    LoggedInUser.setCurrentLoggedIn(user);
                    
                    //closing all
                    Window[] windows = Window.getWindows();
                    for (Window window : windows) {
                        window.dispose();
                    }
                    
                    new MainAdmin().setVisible(true);
                }
                else {
                    txtEmail.requestFocus();
                    MyDialog.display(1, "Sai Email hoặc Password");
                }
            }
//            // kiem tra ton tai
//            if (customerDAO.checkExitByEmail(email) && userDAO.checkExitByEmail(email)) {
//                Firestore db = Firebase.getFirestore("beat-75a88");
//                CollectionReference colRef = db.collection("customers");
////                customerDAO.add(new Customer(null, getMaxID(colRef, "customerID"), email, password));
//                dispose();
//                new LogIn2().setVisible(true);
//                MyDialog.display(0, "Đăng ký thành công!");
//            }
//            else {
//                txtEmail.requestFocus();
//                MyDialog.display(1, "Email đã tồn tại vui lòng thử lại");
//            }
        }
    }//GEN-LAST:event_btnLogInActionPerformed

    private void lblForgotPasswordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgotPasswordMousePressed

    }//GEN-LAST:event_lblForgotPasswordMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private beatalbumshop.componment.MyButton btnLogIn;
    private javax.swing.JLabel lblContinueAs;
    private javax.swing.JLabel lblForgotPassword;
    private javax.swing.JLabel lblSignUp;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    private beatalbumshop.componment.WindowTitleBar windowTitleBar1;
    // End of variables declaration//GEN-END:variables
}
