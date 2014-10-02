/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcd;

import imageHelper.ImageProcessing;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author ucup_aw
 */
public class View extends javax.swing.JFrame {
    
    ImageProcessing testingImage = null;
    /**
     * Creates new form View
     */
    public View() {
        initComponents();
        setLocationRelativeTo(null);
        testingImage = new ImageProcessing();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        citraLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        pathCitraLabel = new javax.swing.JLabel();
        detailCitraLabel = new javax.swing.JLabel();
        citraGrayLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Program Pengolahan Citra Digital");

        citraLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        citraLabel.setText("Citra Awal");
        citraLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Citra Awal"));

        jButton1.setText("Cari Gambar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pathCitraLabel.setText("Path Citra");

        detailCitraLabel.setText("Detal Citra");

        citraGrayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        citraGrayLabel.setText("Citra Gray");
        citraGrayLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Citra Gray"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(citraLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(citraGrayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(detailCitraLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pathCitraLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(citraLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(citraGrayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pathCitraLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(detailCitraLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        jMenu1.setText("File");

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("About");

        jMenuItem1.setText("About");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        final String path = "./Citra Images";
        
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setCurrentDirectory(new File(path));
        
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public final static String jpeg = "jpeg";
            public final String jpg = "jpg";
            public final String gif = "gif";
            public final String tiff = "tiff";
            public final String tif = "tif";
            public final String png = "png";

            /*Get the extension of a file.*/
            public String getExtension(java.io.File f) {
                String ext = null;
                String s = f.getName();
                int i = s.lastIndexOf('.');

                if (i > 0 && i < s.length() - 1) {
                    ext = s.substring(i + 1).toLowerCase();
                }
                return ext;
            }

            @Override
            public String getDescription() {
                return "All Images extensions (jpg, gif, tiff, tif, png)";
            }

            @Override
            public boolean accept(java.io.File f) {
                if (f.isDirectory()) {
                    return true;
                }

                String extension = getExtension(f);
                if (extension != null) {
                    if (extension.equals(tiff)
                            || extension.equals(tif)
                            || extension.equals(gif)
                            || extension.equals(jpeg)
                            || extension.equals(jpg)
                            || extension.equals(png)) {
                        return true;
                    } else {
                        return false;
                    }
                }

                return false;
            }
        });

        if (chooser.showDialog(this, "  Open  ")
                == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File file = chooser.getSelectedFile();
            String filePath = file.toString();
            //String nama_file = file.getName().substring(5, 10);
            //jTextFieldactual_class_Testing.setText(nama_file);
            
            final ImageProcessing imageProTest;
            //ArrayDataTiapFitur nilaiFitur;
           
            
            try {               
                //jLabelCitraTesting.setText("");                        
                final BufferedImage bimg = ImageIO.read(new File(filePath));
                
                //imageProTest = dataPro.dataTraining(bimg, filePath);  // akan mereset data training awal
                //nilaiFitur = dataPro.getDataFitur();
                
                pathCitraLabel.setText("Nama Citra : " + file.getName());

                

                //Buat thread baru 
                Thread t = new Thread() {
                    public void run() {
                        testingImage.setImage(bimg);
                        citraLabel.setText("");
                        citraLabel.setIcon(new ImageIcon(bimg.getScaledInstance(300, 300, 0)));
                        //citraLabel.setIcon(new ImageIcon(imageProTest.getDiameterLine().getScaledInstance(220, 210, 0)));
                        
//                        jLabelCitraGrayTesting.setIcon(new ImageIcon(testingImage.getGrayImage().getScaledInstance(70, 60, 0)));
//                        jLabelCitraBinerTesting.setIcon(new ImageIcon(testingImage.getBinaryImage().getScaledInstance(70, 60, 0)));
//                        jLabelCitraMaxFilterTesting.setIcon(new ImageIcon(testingImage.getMaxImage().getScaledInstance(70, 60, 0)));
//                
                        detailCitraLabel.setText("Ukuran Citra : " + testingImage.getTinggi() + " x " + testingImage.getLebar() + "");
//                        
//
//                        jLabelCitraGrayTesting.setText("");
                        citraGrayLabel.setText("");
                        citraGrayLabel.setIcon(new ImageIcon(testingImage.getGrayImage().getScaledInstance(300, 300, 0)));
//
//                        jLabelCitraBinerTesting.setText("");
//                        //jLabelCitraBinerTesting.setIcon(new ImageIcon(testingImage.getBinaryImage().getScaledInstance(70, 60, 0)));
//
//                        jLabelCitraMaxFilterTesting.setText("");
//                        //jLabelCitraMaxFilterTesting.setIcon(new ImageIcon(testingImage.getMaxImage().getScaledInstance(70, 60, 0)));
//
//                        // format desimal
//                        DecimalFormat df = new DecimalFormat("#.###");
//
//
//                        jTextFieldvar_mean_Red_Testing.setText(df.format(testingImage.getR()));
//                        jTextFieldvar_mean_Green_Testing.setText(df.format(testingImage.getG()));
//                        jTextFieldvar_mean_Blue_Testing.setText(df.format(testingImage.getB()));
//                        jTextFielddiameter_Testing.setText(df.format(testingImage.getDiameter()));
//                        
//                        //String hasil="";
//                        String hasil = dataPro.dataTesting(testingImage.getImage());
//                        ArrayList<ArrayDataPosterior> allPosterior = dataPro.getSemuaPosterior(); 
//                        System.out.println("nilai post nipis" + allPosterior.get(0).getProbabilitas());
//                        jTextFieldposterior_kelas_jn.setText(""+allPosterior.get(0).getProbabilitas());
//                        jTextFieldposterior_kelas_jl.setText(allPosterior.get(1).getProbabilitas()+"");
//                        jTextFieldposterior_kelas_jm.setText(allPosterior.get(2).getProbabilitas()+"");
//                        jTextFieldpredicted_class_Testing.setText(hasil);   
                        
                    }
                };
                t.start();                
                
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(
                        this, "File Tidak Ditemukan");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        About about = new About(this, rootPaneCheckingEnabled);
        about.setVisible(true);
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        About about = new About(this, rootPaneCheckingEnabled);
        about.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel citraGrayLabel;
    private javax.swing.JLabel citraLabel;
    private javax.swing.JLabel detailCitraLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel pathCitraLabel;
    // End of variables declaration//GEN-END:variables
}
