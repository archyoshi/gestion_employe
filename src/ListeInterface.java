/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
//import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Souna
 */
public class ListeInterface extends javax.swing.JFrame {

    private Object[][] data;

    /**
     * Creates new form ListTest2
     */
    public ListeInterface() {
        initComponents();

        afficher_liste();

       /* jButton1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }

            private void jButton1ActionPerformed(ActionEvent evt) {

            }
        });*/
    }

    public ListeInterface(int num_dir) {
        this.num_dir = num_dir;
        initComponents();

        afficher_liste();
    }

    private void afficher_liste() {
        //Debut fontion d'affichage de la liste
        this.data = null;

        JPanel p = new JPanel (new BorderLayout ());
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            this.data,
            new String[] {
                "ID", "Nom", "Prenom", "Photo", "Supprimer"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.awt.Image.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

String url = "jdbc:mysql://localhost:3306/employe";
String user = "root";
String pswd = "";

try {
	Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, pswd);
        int j=0;
//	String sql = "SELECT ID, nom, prenom, photo FROM employe";
        String sql = "SELECT ID, nom, prenom, photo FROM employe WHERE id_directeur = "+num_dir;
	java.sql.Statement state = conn.createStatement();

    //L'objet ResultSet contient le r�sultat de la requ�te SQL
    ResultSet result = state.executeQuery(sql);

                this.data = new Object[100][5];
                Blob blob;
            while (result.next()) {
		this.data[j][0]= result.getInt("ID");
		this.data[j][1]= result.getString("nom");
		this.data[j][2]= result.getString("prenom");
                blob = result.getBlob("photo");
                ImageIcon icon = new ImageIcon(blob.getBytes(1, (int)blob.length()));
                Image img = icon.getImage();
                img = img.getScaledInstance(50, 50, img.SCALE_DEFAULT);
                icon.setImage(img);
                this.data[j][3]= icon;
                this.data[j][4]= false;
                model.addRow(this.data[j]);
		j++;
		}

jTable1.setModel(model);
	jScrollPane1 = new JScrollPane(p,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	this.getContentPane().add(jScrollPane1);
	} catch(Exception e){e.printStackTrace();}
//Fin fonction d'affichage de la liste

    }

    private void supprimer_de_liste(int ID) {

        String url = "jdbc:mysql://localhost:3306/employe";
        String user = "root";
        String pswd = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pswd);

            String sql = "DELETE FROM employe WHERE ID = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ID);
            System.out.println("\nsupprimer ID = "+ID+"\n");
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("\n"+row+" employ�s ont �t� supprim�s.");
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ListeInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(EmployeInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            /* JTable a un renderer par defaut pour les icons.
            * On a just besoin de specifier�la table quel type de donn�e
            * est dans quele colonne pour choisir le renderer appropri�.
            * Pour ce on r��crit la m�thode getColumnClass*/
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Actualiser la liste");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [100][4],
            new String [] {
                "ID", "Nom", "Prenom", "Supprimer"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setRowHeight(50);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Supprimer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Retour");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(143, 143, 143)
                .addComponent(jButton3)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    for(int i=0; i<100;  i++) {
        System.out.println("\n----------- ID["+i+"]="+data[i][0]+"\n");
        if(data[i][0]!=null){
    System.out.println("\navant IF data["+i+"][3]="+data[i][4]+" ValueAt(i, 4)="+jTable1.getValueAt(i, 4)+"\n");
    if(Boolean.parseBoolean((String) jTable1.getValueAt(i, 4)) == true) {
        System.out.println("\nApres IF data["+i+"][3]="+data[i][4]+" ValueAt(i, 4)="+jTable1.getValueAt(i, 4)+"\n");
        supprimer_de_liste((int)data[i][0]);
            }
        }
    }
    afficher_liste(); //Actualiser le tableau apr�s les supressions
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        afficher_liste();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       if(evt.getClickCount() == 2) {
        for(int i=0; i<100;  i++) {
            if((data[i][0]!=null) && jTable1.isRowSelected(i) && jTable1.isColumnSelected(3))
            {
                    try {
                        System.out.println("\nApres IF data["+i+"][3]="+data[i][4]+" ValueAt(i, 4)="+jTable1.getValueAt(i, 4)+"\n");
                        ProfilInterface profil_i = new ProfilInterface(Integer.valueOf(data[i][0].toString()));
                        profil_i.setLocation(this.getLocation());
                        profil_i.setVisible(true);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ListeInterface.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ListeInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

            MenuInterface menu_i = new MenuInterface(num_dir);
            menu_i.setVisible(true);
            menu_i.setLocation(this.getLocation());
            this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListeInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListeInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListeInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListeInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new ListeInterface().setVisible(true);
            }
        });
    }
    private int num_dir;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
