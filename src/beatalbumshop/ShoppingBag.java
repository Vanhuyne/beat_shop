package beatalbumshop;

import beatalbumshop.componment.MyLabel;
import beatalbumshop.dao.AlbumDAO;
import beatalbumshop.dao.AlbumDAOImpl;
import beatalbumshop.dao.CustomerDAO;
import beatalbumshop.dao.CustomerDAOImpl;
import beatalbumshop.model.Album;
import beatalbumshop.model.Item;
import beatalbumshop.model.Customer;
import beatalbumshop.model.LoggedInUser;
import beatalbumshop.utils.OtherHelper;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShoppingBag extends javax.swing.JPanel {

    AlbumDAO albumDAO = new AlbumDAOImpl();
    CustomerDAO customerDAO = new CustomerDAOImpl();
    ArrayList<Item> lBagItem = new ArrayList<>();
    Customer customer;
    double subtotal = 0;
    
    public ShoppingBag() {
        initComponents();
    
        pnlListBag.setLayout(new BoxLayout(pnlListBag, BoxLayout.Y_AXIS));

        updateBag();
    }
    
    
    
    public ArrayList<Item> getlBagItem() {
        return lBagItem;
    }
    
    
    
    public void updateBag() {
        subtotal = 0;
        pnlListBag.removeAll();

        customer = (Customer) LoggedInUser.getCurrentUser();
        lBagItem = customer.getlBagItem();
              
        // bag empty
        if(lBagItem.isEmpty()) {
            pnlListBag.setLayout(new BorderLayout());
            MyLabel lbl = new MyLabel("Currently Empty");
            lbl.setHorizontalAlignment(JLabel.CENTER);
            pnlListBag.add(lbl);
            btnCheckout.setVisible(false);
        }
        else {
            for(Item item : lBagItem) {
                
                Album album = albumDAO.getByID(item.getAlbumID());
                
                if(album.getInStock() > 0) {
                    SelectionProduct sp = new SelectionProduct(album, item.getQuantity());

                    pnlListBag.add(sp);
                    subtotal += sp.getSubtotal();
                }
            }

            btnCheckout.setVisible(true);
            
            pnlListBag.revalidate();
            pnlListBag.repaint();
        }
        
        lblSubtotal.setText("$ " + subtotal);
        String shipping = lblShipping.getText().substring(lblShipping.getText().indexOf("$ ") + 2);
        double total = subtotal + Double.parseDouble(shipping);
        lblTotal.setText("$ " + total);
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
        btnClose = new beatalbumshop.componment.MyButton();
        lblYourSelection = new beatalbumshop.componment.MyLabel();
        myScrollPane1 = new beatalbumshop.componment.MyScrollPane();
        pnlListBag = new javax.swing.JPanel();
        pnlSummary = new javax.swing.JPanel();
        lblOrderSummary = new beatalbumshop.componment.MyLabel();
        lblSubtotal2 = new beatalbumshop.componment.MyLabel();
        lblShipping2 = new beatalbumshop.componment.MyLabel();
        lblTotal2 = new beatalbumshop.componment.MyLabel();
        lblSubtotal = new beatalbumshop.componment.MyLabel();
        lblShipping = new beatalbumshop.componment.MyLabel();
        lblTotal = new beatalbumshop.componment.MyLabel();
        btnCheckout = new beatalbumshop.componment.MyButton();

        setMaximumSize(new java.awt.Dimension(1030, 658));
        setMinimumSize(new java.awt.Dimension(1030, 658));
        setPreferredSize(new java.awt.Dimension(1030, 658));

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        btnClose.setBackground(null);
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/beatalbumshop/resources/images/icons/back.png"))); // NOI18N
        btnClose.setText(" Continue Shopping");
        btnClose.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnClose.setPreferredSize(new java.awt.Dimension(70, 50));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        lblYourSelection.setText("Your Selection");
        lblYourSelection.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N

        myScrollPane1.setBackground(null);
        myScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        myScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlListBag.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlListBagLayout = new javax.swing.GroupLayout(pnlListBag);
        pnlListBag.setLayout(pnlListBagLayout);
        pnlListBagLayout.setHorizontalGroup(
            pnlListBagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1030, Short.MAX_VALUE)
        );
        pnlListBagLayout.setVerticalGroup(
            pnlListBagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 605, Short.MAX_VALUE)
        );

        myScrollPane1.setViewportView(pnlListBag);

        pnlSummary.setBackground(new java.awt.Color(255, 255, 255));

        lblOrderSummary.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrderSummary.setText("Order Summary");
        lblOrderSummary.setFont(new java.awt.Font("Open Sans", 1, 16)); // NOI18N

        lblSubtotal2.setText("Subtotal:");

        lblShipping2.setText("Shipping:");

        lblTotal2.setText("Total:");

        lblSubtotal.setText("$ 0");

        lblShipping.setText("$ 0");

        lblTotal.setText("$ 0");
        lblTotal.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N

        btnCheckout.setBackground(new java.awt.Color(0, 0, 0));
        btnCheckout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCheckout.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckout.setText("Checkout");
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSummaryLayout = new javax.swing.GroupLayout(pnlSummary);
        pnlSummary.setLayout(pnlSummaryLayout);
        pnlSummaryLayout.setHorizontalGroup(
            pnlSummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOrderSummary, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
            .addComponent(btnCheckout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlSummaryLayout.createSequentialGroup()
                .addComponent(lblTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSummaryLayout.createSequentialGroup()
                .addComponent(lblShipping2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblShipping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSummaryLayout.createSequentialGroup()
                .addComponent(lblSubtotal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlSummaryLayout.setVerticalGroup(
            pnlSummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSummaryLayout.createSequentialGroup()
                .addComponent(lblOrderSummary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(pnlSummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubtotal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnlSummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblShipping2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblShipping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnlSummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 832, Short.MAX_VALUE))
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblYourSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlSummary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMainLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(myScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(419, Short.MAX_VALUE)))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(lblYourSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSummary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(313, Short.MAX_VALUE))
            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMainLayout.createSequentialGroup()
                    .addGap(105, 105, 105)
                    .addComponent(myScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        Main mainFrame = OtherHelper.getMainFrame(this);
        mainFrame.getBtnShop().doClick();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        JPanel pnlTabContent = (JPanel) getParent();
        pnlTabContent.add(new Checkout(), "checkout");
        CardLayout c = (CardLayout) pnlTabContent.getLayout();
        c.show(pnlTabContent, "checkout");
    }//GEN-LAST:event_btnCheckoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private beatalbumshop.componment.MyButton btnCheckout;
    private beatalbumshop.componment.MyButton btnClose;
    private beatalbumshop.componment.MyLabel lblOrderSummary;
    private beatalbumshop.componment.MyLabel lblShipping;
    private beatalbumshop.componment.MyLabel lblShipping2;
    private beatalbumshop.componment.MyLabel lblSubtotal;
    private beatalbumshop.componment.MyLabel lblSubtotal2;
    private beatalbumshop.componment.MyLabel lblTotal;
    private beatalbumshop.componment.MyLabel lblTotal2;
    private beatalbumshop.componment.MyLabel lblYourSelection;
    private beatalbumshop.componment.MyScrollPane myScrollPane1;
    private javax.swing.JPanel pnlListBag;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSummary;
    // End of variables declaration//GEN-END:variables
}
