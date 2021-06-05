/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IT19099064;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import org.nlt.instance.DatabaseConnection;

/**
 *
 * @author ASUS
 */
public class ManageSessions extends javax.swing.JFrame {
private Connection con;
    /**
     * Creates new form ManageSessions
     */
    public ManageSessions() {
       
        initComponents();
        setsessionTable2Data();
        setsessionTableData();
        setparallelTableData();
        setoverTableData();
        setoverTable2Data();
        setparallelTable2Data();
        setavailableTableData();
        setavailableTable2Data();
    
    }
   
      public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }
      public void setsessionTableData(){
           try{  
//          DatabaseConnection dbc = DatabaseConnection.getDatabaseConnection();
//        con = dbc.getConnection();
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltdmgdb?zeroDateTimeBehavior=convertToNull","root","");
                String select = "select * from session";
                PreparedStatement pst = c.prepareStatement(select);

                DefaultTableModel model = (DefaultTableModel) sessionTable.getModel();
                model.setRowCount(0);

               

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                    //Data will be added until finish
                    String se_id = String.valueOf(rs.getInt("id"));
                    String lec1 = rs.getString("lecture1");
                    String lec2 = rs.getString("lecture2");
                    String scode = rs.getString("subject_code");
                    String ssubject = rs.getString("subject_name");
                    String sgroup = rs.getString("group_id");
                    String tag = rs.getString("tag");
               

                    //string array for store data into jtable
                    String tbData[] = {null, se_id, lec1, lec2,scode,ssubject,sgroup,tag};
                    DefaultTableModel tblModel = (DefaultTableModel) sessionTable.getModel();

                    tblModel.addRow(tbData);
                    
                    setsessionTable2Data();


                }
           }catch(SQLException e){
           
               System.out.println(e);
           }
            
    }
      
           private void setsessionTable2Data(){
            
    try{
        int rows=0;
        int rowIndex=0;
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltdmgdb?zeroDateTimeBehavior=convertToNull","root","");
         String sql = "select * from consecutive";
        PreparedStatement smt = con.prepareStatement(sql);
        ResultSet rs = smt.executeQuery(sql);

       
       if(rs.next())
       {
           rs.last();
           rows=rs.getRow();
           rs.beforeFirst();
       }
//       System.out.println(rows);

String[][] data= new String[rows][8];
while(rs.next())
{
   data[rowIndex][0]=rs.getInt(1)+"";
     data[rowIndex][1]=rs.getInt(2)+"";
   data[rowIndex][2]=rs.getString(3);
    data[rowIndex][3]=rs.getString(4);
     data[rowIndex][4]=rs.getString(5);
      data[rowIndex][5]=rs.getString(6);
      data[rowIndex][6]=rs.getString(7);
      data[rowIndex][7]=rs.getString(8);
   rowIndex++;
       
    }

String[] cols={"CID","ID","LECTURE ONE","LECTURE TWO","SUBJECT CODE","SUBJECT ONE","GROUP ID","TAG"};
DefaultTableModel model=new DefaultTableModel(data,cols);

sessionTable2.setModel(model);

rs.close();
smt.close();
    }catch(Exception ex){
       ex.printStackTrace();
    }
 
}
           
           
           
           //Parallal 
            public void setparallelTableData(){
           try{  
//          DatabaseConnection dbc = DatabaseConnection.getDatabaseConnection();
//        con = dbc.getConnection();
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltdmgdb?zeroDateTimeBehavior=convertToNull","root","");
                String select = "select * from session";
                PreparedStatement pst = c.prepareStatement(select);

                DefaultTableModel model = (DefaultTableModel) parallelTable1.getModel();
                model.setRowCount(0);

               

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                    //Data will be added until finish
                    String se_id = String.valueOf(rs.getInt("id"));
                     String lec1 = rs.getString("lecture1");
                    String lec2 = rs.getString("lecture2");
                    String scode = rs.getString("subject_code");
                    String ssubject = rs.getString("subject_name");
                    String sgroup = rs.getString("group_id");
                    String tag = rs.getString("tag");
               

                    //string array for store data into jtable
                    String tbData[] = {null, se_id, lec1, lec2,scode,ssubject,sgroup,tag};
                    DefaultTableModel tblModel = (DefaultTableModel) parallelTable1.getModel();

                    tblModel.addRow(tbData);
                    
                   


                }
           }catch(SQLException e){
           
               System.out.println(e);
           }
            
    }
            
                         private void setparallelTable2Data(){
            
    try{
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltdmgdb?zeroDateTimeBehavior=convertToNull","root","");
        int rows=0;
        int rowIndex=0;
         String sql = "select * from parallel";
        PreparedStatement smt = c.prepareStatement(sql);
        ResultSet rs = smt.executeQuery(sql);

       
       if(rs.next())
       {
           rs.last();
           rows=rs.getRow();
           rs.beforeFirst();
       }
//       System.out.println(rows);

String[][] data= new String[rows][8];
while(rs.next())
{
   data[rowIndex][0]=rs.getInt(1)+"";
     data[rowIndex][1]=rs.getInt(2)+"";
   data[rowIndex][2]=rs.getString(3);
    data[rowIndex][3]=rs.getString(4);
     data[rowIndex][4]=rs.getString(5);
      data[rowIndex][5]=rs.getString(6);
      data[rowIndex][6]=rs.getString(7);
      data[rowIndex][7]=rs.getString(8);
   rowIndex++;
       
    }

String[] cols={"PID","ID","LECTURE ONE","LECTURE TWO","SUBJECT CODE","SUBJECT ONE","GROUP ID","TAG"};
DefaultTableModel model=new DefaultTableModel(data,cols);

parallelTable2.setModel(model);

rs.close();
smt.close();
    }catch(Exception ex){
        JOptionPane.showMessageDialog(this, "Cannot retrieve data 1");
    }
 
}
                         
         //non overlapping
                         
           public void setoverTableData(){
           try{  
//          DatabaseConnection dbc = DatabaseConnection.getDatabaseConnection();
//        con = dbc.getConnection();
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltdmgdb?zeroDateTimeBehavior=convertToNull","root","");
                String select = "select * from session";
                PreparedStatement pst = c.prepareStatement(select);

                DefaultTableModel model = (DefaultTableModel) overTable1.getModel();
                model.setRowCount(0);

               

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                    //Data will be added until finish
                    String se_id = String.valueOf(rs.getInt("id"));
                     String lec1 = rs.getString("lecture1");
                    String lec2 = rs.getString("lecture2");
                    String scode = rs.getString("subject_code");
                    String ssubject = rs.getString("subject_name");
                    String sgroup = rs.getString("group_id");
                    String tag = rs.getString("tag");
               

                    //string array for store data into jtable
                    String tbData[] = {null, se_id, lec1, lec2,scode,ssubject,sgroup,tag};
                    DefaultTableModel tblModel = (DefaultTableModel) overTable1.getModel();

                    tblModel.addRow(tbData);
                    
                   


                }
           }catch(SQLException e){
           
               System.out.println(e);
           }
            
    }
            
                         private void setoverTable2Data(){
            
    try{
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltdmgdb?zeroDateTimeBehavior=convertToNull","root","");
        int rows=0;
        int rowIndex=0;
         String sql = "select * from non_overlapping";
        PreparedStatement smt = c.prepareStatement(sql);
        ResultSet rs = smt.executeQuery(sql);

       
       if(rs.next())
       {
           rs.last();
           rows=rs.getRow();
           rs.beforeFirst();
       }
//       System.out.println(rows);

String[][] data= new String[rows][8];
while(rs.next())
{
   data[rowIndex][0]=rs.getInt(1)+"";
     data[rowIndex][1]=rs.getInt(2)+"";
   data[rowIndex][2]=rs.getString(3);
    data[rowIndex][3]=rs.getString(4);
     data[rowIndex][4]=rs.getString(5);
      data[rowIndex][5]=rs.getString(6);
      data[rowIndex][6]=rs.getString(7);
      data[rowIndex][7]=rs.getString(8);
   rowIndex++;
       
    }

String[] cols={"OID","ID","LECTURE ONE","LECTURE TWO","SUBJECT CODE","SUBJECT ONE","GROUP ID","TAG"};
DefaultTableModel model=new DefaultTableModel(data,cols);

overTable2.setModel(model);

rs.close();
smt.close();
    }catch(Exception ex){
        JOptionPane.showMessageDialog(this, "Cannot retrieve data 2");
    }
 
}       
                         
                         
                          //not available
                         
           public void setavailableTableData(){
           try{  
//          DatabaseConnection dbc = DatabaseConnection.getDatabaseConnection();
//        con = dbc.getConnection();
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltdmgdb?zeroDateTimeBehavior=convertToNull","root","");
                String select = "select * from session";
                PreparedStatement pst = c.prepareStatement(select);

                DefaultTableModel model = (DefaultTableModel) availableTable3.getModel();
                model.setRowCount(0);

               

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                    //Data will be added until finish
                    String se_id = String.valueOf(rs.getInt("id"));
                     String lec1 = rs.getString("lecture1");
                    String lec2 = rs.getString("lecture2");
                    String scode = rs.getString("subject_code");
                    String ssubject = rs.getString("subject_name");
                    String sgroup = rs.getString("group_id");
                    String tag = rs.getString("tag");
               

                    //string array for store data into jtable
                    String tbData[] = {null, se_id, lec1, lec2,scode,ssubject,sgroup,tag};
                    DefaultTableModel tblModel = (DefaultTableModel) availableTable3.getModel();

                    tblModel.addRow(tbData);
                    
                   


                }
           }catch(SQLException e){
           
               System.out.println(e);
           }
            
    }
            
                         private void setavailableTable2Data(){
            
    try{
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltdmgdb?zeroDateTimeBehavior=convertToNull","root","");
        int rows=0;
        int rowIndex=0;
         String sql = "select * from not_available";
        PreparedStatement smt = c.prepareStatement(sql);
        ResultSet rs = smt.executeQuery(sql);

       
       if(rs.next())
       {
           rs.last();
           rows=rs.getRow();
           rs.beforeFirst();
       }
//       System.out.println(rows);

String[][] data= new String[rows][8];
while(rs.next())
{
   data[rowIndex][0]=rs.getInt(1)+"";
     data[rowIndex][1]=rs.getInt(2)+"";
   data[rowIndex][2]=rs.getString(3);
    data[rowIndex][3]=rs.getString(4);
     data[rowIndex][4]=rs.getString(5);
      data[rowIndex][5]=rs.getString(6);
      data[rowIndex][6]=rs.getString(7);
      data[rowIndex][7]=rs.getString(8);
   rowIndex++;
       
    }

String[] cols={"AID","ID","LECTURE ONE","LECTURE TWO","SUBJECT CODE","SUBJECT ONE","GROUP ID","TAG"};
DefaultTableModel model=new DefaultTableModel(data,cols);

availableTable4.setModel(model);

rs.close();
smt.close();
    }catch(Exception ex){
        JOptionPane.showMessageDialog(this, "Cannot retrieve data 3");
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

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        sessionTable2 = new javax.swing.JTable();
        jButtonAdd = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        sessionTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        parallelTable1 = new javax.swing.JTable();
        Add2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        parallelTable2 = new javax.swing.JTable();
        jInternalFrame3 = new javax.swing.JInternalFrame();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        overTable1 = new javax.swing.JTable();
        Add3 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        overTable2 = new javax.swing.JTable();
        jInternalFrame4 = new javax.swing.JInternalFrame();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        availableTable3 = new javax.swing.JTable();
        Add4 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        availableTable4 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jInternalFrame1.setToolTipText("");
        jInternalFrame1.setVisible(true);

        sessionTable2.setBackground(new java.awt.Color(255, 204, 204));
        sessionTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CID", "ID", "Lecture 1", "Lecture 2", "Subect code", "Subject", "Group ID", "Tag"
            }
        ));
        jScrollPane1.setViewportView(sessionTable2);

        jButtonAdd.setText("Add Session");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        sessionTable.setBackground(new java.awt.Color(255, 204, 204));
        sessionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Select", "ID", "Lecture 1", "Lecture 2", "Subect code", "Subject", "Group ID", "Tag"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sessionTable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                sessionTableAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        sessionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sessionTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(sessionTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Consecutive Sessions");

        jToggleButton1.setText("Dash Board");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(395, 395, 395)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton1))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(442, Short.MAX_VALUE))))
            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                    .addContainerGap(42, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(15, 15, 15)))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToggleButton1)
                    .addComponent(jLabel1))
                .addGap(225, 225, 225)
                .addComponent(jButtonAdd)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(253, Short.MAX_VALUE))
            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jInternalFrame1Layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(528, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Consecutive", jInternalFrame1);

        jInternalFrame2.setVisible(true);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Parallel Sessions");

        parallelTable1.setBackground(new java.awt.Color(255, 204, 204));
        parallelTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Select", "ID", "Lecture 1", "Lecture 2", "Subect code", "Subject", "Group ID", "Tag"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        parallelTable1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                parallelTable1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        parallelTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                parallelTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(parallelTable1);

        Add2.setText("Add Session");
        Add2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add2ActionPerformed(evt);
            }
        });

        parallelTable2.setBackground(new java.awt.Color(255, 204, 204));
        parallelTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "PID", "ID", "Lecture 1", "Lecture 2", "Subect code", "Subject", "Group ID", "Tag"
            }
        ));
        parallelTable2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                parallelTable2AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane4.setViewportView(parallelTable2);

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jInternalFrame2Layout.createSequentialGroup()
                            .addGap(405, 405, 405)
                            .addComponent(jLabel2))
                        .addGroup(jInternalFrame2Layout.createSequentialGroup()
                            .addGap(29, 29, 29)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jInternalFrame2Layout.createSequentialGroup()
                            .addGap(416, 416, 416)
                            .addComponent(Add2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(Add2)
                .addGap(50, 50, 50)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(279, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Parallel", jInternalFrame2);

        jInternalFrame3.setVisible(true);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Non Overlapping Sessions");

        overTable1.setBackground(new java.awt.Color(255, 204, 204));
        overTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Select", "ID", "Lecture 1", "Lecture 2", "Subect code", "Subject", "Group ID", "Tag"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        overTable1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                overTable1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        overTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                overTable1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(overTable1);

        Add3.setText("Add Session");
        Add3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add3ActionPerformed(evt);
            }
        });

        overTable2.setBackground(new java.awt.Color(255, 204, 204));
        overTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "OID", "ID", "Lecture 1", "Lecture 2", "Subect code", "Subject", "Group ID", "Tag"
            }
        ));
        overTable2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                overTable2AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane6.setViewportView(overTable2);

        javax.swing.GroupLayout jInternalFrame3Layout = new javax.swing.GroupLayout(jInternalFrame3.getContentPane());
        jInternalFrame3.getContentPane().setLayout(jInternalFrame3Layout);
        jInternalFrame3Layout.setHorizontalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame3Layout.createSequentialGroup()
                .addGap(383, 383, 383)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame3Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
            .addGroup(jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jInternalFrame3Layout.createSequentialGroup()
                    .addGap(418, 418, 418)
                    .addComponent(Add3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(419, Short.MAX_VALUE)))
        );
        jInternalFrame3Layout.setVerticalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
            .addGroup(jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jInternalFrame3Layout.createSequentialGroup()
                    .addGap(240, 240, 240)
                    .addComponent(Add3)
                    .addContainerGap(418, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Non Overlapping", jInternalFrame3);

        jInternalFrame4.setVisible(true);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Not available Sessions");

        availableTable3.setBackground(new java.awt.Color(255, 204, 204));
        availableTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Select", "ID", "Lecture 1", "Lecture 2", "Subect code", "Subject", "Group ID", "Tag"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        availableTable3.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                availableTable3AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        availableTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                availableTable3MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(availableTable3);

        Add4.setText("Add Session");
        Add4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add4ActionPerformed(evt);
            }
        });

        availableTable4.setBackground(new java.awt.Color(255, 204, 204));
        availableTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "AID", "ID", "Lecture 1", "Lecture 2", "Subect code", "Subject", "Group ID", "Tag"
            }
        ));
        availableTable4.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                availableTable4AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane8.setViewportView(availableTable4);

        javax.swing.GroupLayout jInternalFrame4Layout = new javax.swing.GroupLayout(jInternalFrame4.getContentPane());
        jInternalFrame4.getContentPane().setLayout(jInternalFrame4Layout);
        jInternalFrame4Layout.setHorizontalGroup(
            jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame4Layout.createSequentialGroup()
                .addGap(382, 382, 382)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame4Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jInternalFrame4Layout.createSequentialGroup()
                    .addGap(418, 418, 418)
                    .addComponent(Add4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(419, Short.MAX_VALUE)))
        );
        jInternalFrame4Layout.setVerticalGroup(
            jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(289, Short.MAX_VALUE))
            .addGroup(jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jInternalFrame4Layout.createSequentialGroup()
                    .addGap(240, 240, 240)
                    .addComponent(Add4)
                    .addContainerGap(418, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Not available times", jInternalFrame4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("tab 1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sessionTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sessionTableMouseClicked
        // TODO add your handling code here:
        sessionTable.getValueAt(sessionTable.getSelectedRow(), 0).toString();
         sessionTable.getValueAt(sessionTable.getSelectedRow(), 1).toString();
          sessionTable.getValueAt(sessionTable.getSelectedRow(), 2).toString();
           sessionTable.getValueAt(sessionTable.getSelectedRow(), 3).toString();
            sessionTable.getValueAt(sessionTable.getSelectedRow(), 4).toString();
             sessionTable.getValueAt(sessionTable.getSelectedRow(), 5).toString();
              sessionTable.getValueAt(sessionTable.getSelectedRow(), 6).toString();
    }//GEN-LAST:event_sessionTableMouseClicked

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        // TODO add your handling code here:
          // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) sessionTable.getModel();

       for (int i = 0; i < model.getRowCount(); i++) {
Boolean checked=(Boolean) model.getValueAt(i, 0);
//System.out.println(i+"th row: is selected? "+checked);
try {
if (checked!=null && checked == true) {
try {
//             DatabaseConnection dbc = DatabaseConnection.getDatabaseConnection();
                        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltdmgdb?zeroDateTimeBehavior=convertToNull","root","");

                        String value = model.getValueAt(i, 1).toString();

                        String update = "INSERT INTO consecutive(id,lecture_one,lecturetwo,subject_code,subject_one,group_id,tag) SELECT id,lecture1,lecture2,subject_code,subject_name,group_id,tag FROM session  where id=" + value;

                        PreparedStatement smt = c.prepareStatement(update);

                        
                        smt.execute();

                    setsessionTable2Data();

                    } catch (SQLException e) {
//call NOTNULL validation 
                                JOptionPane.showMessageDialog(this, e);
                   
                    }

                }
            } finally {
                System.out.println("success");
            }

        }
        JOptionPane.showMessageDialog(this, "Added Sucsessfully");

     
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void sessionTableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_sessionTableAncestorAdded
        // TODO add your handling code here:
      setsessionTableData();
    }//GEN-LAST:event_sessionTableAncestorAdded

    private void parallelTable1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_parallelTable1AncestorAdded
        // TODO add your handling code here:
        setparallelTableData();
    }//GEN-LAST:event_parallelTable1AncestorAdded

    private void parallelTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_parallelTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_parallelTable1MouseClicked

    private void Add2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add2ActionPerformed
        DefaultTableModel model = (DefaultTableModel) parallelTable1.getModel();

       for (int i = 0; i < model.getRowCount(); i++) {
Boolean checked=(Boolean) model.getValueAt(i, 0);
//System.out.println(i+"th row: is selected? "+checked);
try {
if (checked!=null && checked == true) {
try {
//             DatabaseConnection dbc = DatabaseConnection.getDatabaseConnection();
                        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltdmgdb?zeroDateTimeBehavior=convertToNull","root","");

                        String value = model.getValueAt(i, 1).toString();

                        String update = "INSERT INTO parallel(id,lecture_one,lecturetwo,subject_code,subject_one,group_id,tag) SELECT id,lecture1,lecture2,subject_code,subject_name,group_id,tag FROM session  where id=" + value;

                        PreparedStatement smt = c.prepareStatement(update);

                        
                        smt.execute();

                  setparallelTable2Data();

                    } catch (SQLException e) {
//call NOTNULL validation 
                                JOptionPane.showMessageDialog(this, e);
                   
                    }

                }
            } finally {
                System.out.println("success");
            }

        }
        JOptionPane.showMessageDialog(this, "Added Sucsessfully");

    }//GEN-LAST:event_Add2ActionPerformed

    
    
    private void parallelTable2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_parallelTable2AncestorAdded
//  setparallelTable2Data();
      
    }//GEN-LAST:event_parallelTable2AncestorAdded

    private void overTable1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_overTable1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_overTable1AncestorAdded

    private void overTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_overTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_overTable1MouseClicked

    private void Add3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add3ActionPerformed
         DefaultTableModel model = (DefaultTableModel) overTable1.getModel();

       for (int i = 0; i < model.getRowCount(); i++) {
Boolean checked=(Boolean) model.getValueAt(i, 0);
//System.out.println(i+"th row: is selected? "+checked);
try {
if (checked!=null && checked == true) {
try {
//             DatabaseConnection dbc = DatabaseConnection.getDatabaseConnection();
                        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltdmgdb?zeroDateTimeBehavior=convertToNull","root","");

                        String value = model.getValueAt(i, 1).toString();

                        String update = "INSERT INTO non_overlapping(id,lecture_one,lecturetwo,subject_code,subject_one,group_id,tag) SELECT id,lecture1,lecture2,subject_code,subject_name,group_id,tag FROM session  where id=" + value;

                        PreparedStatement smt = c.prepareStatement(update);

                        
                        smt.execute();

                  setoverTable2Data();

                    } catch (SQLException e) {
//call NOTNULL validation 
                                JOptionPane.showMessageDialog(this, e);
                   
                    }

                }
            } finally {
                System.out.println("success");
            }

        }
        JOptionPane.showMessageDialog(this, "Added Sucsessfully");
    }//GEN-LAST:event_Add3ActionPerformed

    private void overTable2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_overTable2AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_overTable2AncestorAdded

    private void availableTable3AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_availableTable3AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_availableTable3AncestorAdded

    private void availableTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_availableTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_availableTable3MouseClicked

    private void Add4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add4ActionPerformed
       DefaultTableModel model = (DefaultTableModel) availableTable3.getModel();

       for (int i = 0; i < model.getRowCount(); i++) {
Boolean checked=(Boolean) model.getValueAt(i, 0);
//System.out.println(i+"th row: is selected? "+checked);
try {
if (checked!=null && checked == true) {
try {
//             DatabaseConnection dbc = DatabaseConnection.getDatabaseConnection();
                        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltdmgdb?zeroDateTimeBehavior=convertToNull","root","");

                        String value = model.getValueAt(i, 1).toString();

                        String update = "INSERT INTO not_available(id,lecture_one,lecturetwo,subject_code,subject_one,group_id,tag) SELECT id,lecture1,lecture2,subject_code,subject_name,group_id,tag FROM session  where id=" + value;

                        PreparedStatement smt = c.prepareStatement(update);

                        
                        smt.execute();

                  setavailableTable2Data();

                    } catch (SQLException e) {
//call NOTNULL validation 
                                JOptionPane.showMessageDialog(this, e);
                   
                    }

                }
            } finally {
                System.out.println("success");
            }

        }
        JOptionPane.showMessageDialog(this, "Added Sucsessfully");
    }//GEN-LAST:event_Add4ActionPerformed

    private void availableTable4AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_availableTable4AncestorAdded
          // TODO add your handling code here:
        
//        DefaultTableModel model = (DefaultTableModel) availableTable3.getModel();

//       for (int i = 0; i < model.getRowCount(); i++) {
//Boolean checked=(Boolean) model.getValueAt(i, 0);
//System.out.println(i+"th row: is selected? "+checked);
//try {
//if (checked!=null && checked == true) {
//try {
//             DatabaseConnection dbc = DatabaseConnection.getDatabaseConnection();
//
//                        String value = model.getValueAt(i, 1).toString();
//
//                        String update = "INSERT INTO not_available(id,lecture_one,lecturetwo,subject_code,subject_one,group_id,tag) SELECT id,lecture_one,lecturetwo,subject_code,subject_one,group_id,tag FROM session  where id=" + value;
//
//                        PreparedStatement smt = con.prepareStatement(update);
//
//                        
//                        smt.execute();
//
//                    setavailableTable2Data();
//
//                    } catch (SQLException e) {
//call NOTNULL validation 
//                                JOptionPane.showMessageDialog(this, e);
//                   
//                    }
//
//                }
//            } finally {
//                System.out.println("success");
//            }
//
//        }
//        JOptionPane.showMessageDialog(this, "Added Sucsessfully");
    }//GEN-LAST:event_availableTable4AncestorAdded

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
         // Dash Board:
        if(evt.getActionCommand().equals("DASH BOARD")){
           System.out.println("Details Page of Dash Board");
        }
        close();
        Member1_Dash_Board nt = new Member1_Dash_Board();
        nt.setVisible(true);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    
    private void sessionTable2AncestorAdded(javax.swing.event.AncestorEvent evt) {                                           
        // TODO add your handling code here:
//      setsessionTable2Data();
    }  
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
            java.util.logging.Logger.getLogger(ManageSessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageSessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageSessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageSessions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageSessions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add2;
    private javax.swing.JButton Add3;
    private javax.swing.JButton Add4;
    private javax.swing.JTable availableTable3;
    private javax.swing.JTable availableTable4;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JInternalFrame jInternalFrame3;
    private javax.swing.JInternalFrame jInternalFrame4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable overTable1;
    private javax.swing.JTable overTable2;
    private javax.swing.JTable parallelTable1;
    private javax.swing.JTable parallelTable2;
    private javax.swing.JTable sessionTable;
    private javax.swing.JTable sessionTable2;
    // End of variables declaration//GEN-END:variables

  
}
