/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Vinitha
 */
public class Pass extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4,l5,l6,l7;
    JTextField t1,t2;
    JPasswordField t3,t4;
    JButton b1,b2,b3, b4;
    Connection con=null;
    Statement st=null;
    Pass(){
        try{
            //pack();
            setSize(1000,1000);
        setTitle("Passport Registration");
        l1=new JLabel("Are you registered user to apply passport?");
        b1=new JButton("Yes");
        l6=new JLabel("Registration for new user");
        l2=new JLabel("Username");
        l3=new JLabel("Email");
        l4=new JLabel("Password");
        l5=new JLabel("Confirm Password");
        b2=new JButton("Submit");
        l7=new JLabel("To view date wise applicants");
        b4=new JButton("Click Here");
        t1=new JTextField(15);
        t2=new JTextField(15);
        t3=new JPasswordField(15);
        t4=new JPasswordField(15);
        setLayout(null);
        l1.setBounds(50,50,800,30);
        b1.setBounds(100,100,100,30);
        l6.setBounds(100,200,200,30);
        l2.setBounds(100,230,200,30);
        l3.setBounds(100,260,200,30);
        l4.setBounds(100,290,200,30);
        l5.setBounds(100,320,200,30);
        l7.setBounds(650,100,300,30);
        b4.setBounds(750,150,200,30);
        t1.setBounds(300,230,200,30);
        t2.setBounds(300,260,200,30);
        t3.setBounds(300,290,200,30);
        t4.setBounds(300,320,200,30);
        b2.setBounds(200,400,200,30);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l7);
        add(t1); add(t3);add(t2);add(t4);
        add(b1);
        add(b2);
        add(b4);
        setVisible(true);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b4.addActionListener(this);
        b4.setActionCommand("rep");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","vinitha");
        }catch(SQLException e){e.printStackTrace();}
        catch(Exception ex){ex.printStackTrace();}
        
    }

    
    public void actionPerformed(ActionEvent e) {
       if(e.getActionCommand()=="Yes")
           fun1();
       if(e.getActionCommand()=="Submit")
           update1();
       if(e.getActionCommand()=="rep")
           report();
    }
    void report()
    {
        String g=JOptionPane.showInputDialog("Enter the application date to view no of applicants");
        try{
        JFrame f=new JFrame("Report");
       // f.pack();
        f.setSize(1000,1000);
        JLabel l=new JLabel("Report");
        JTextArea ta=new JTextArea(1000,1000);
        f.setLayout(null);
        ta.setEditable(false);
        l.setBounds(430,20,300,50);
        ta.setBounds(100,130,1000,1000);
        st=con.createStatement();
        f.add(l);
        f.add(ta);
        ResultSet rs1=st.executeQuery("select * from application where adate='"+g+"'");
        ta.append("\n");
        ta.append("DETAILS OF THE APPLICANT\n");
        if(rs1!=null)
        {
        while(rs1.next())
        {
                 ta.append("   "+rs1.getString(1).trim()+"\n"+rs1.getString(2).trim()+"\n"+rs1.getString(3).trim()+"\n"+rs1.getString(4).trim()+"\n"+rs1.getString(5).trim()+"\n"+rs1.getString(6).trim()+"\t\n");
        }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"No records found for the date");
        }
        f.setVisible(true);
        }catch(SQLException ed){ed.printStackTrace();}
    }

    void update1()
    {
        try{
        String u,e,p,cp;
        char[] p1=t3.getPassword();
        char[] p2=t4.getPassword();
        u=t1.getText();
        e=t2.getText();
        p=new String(p1);
        cp=new String(p2);
        st=con.createStatement();
        if(cp.equals(p))
        {
            st.executeUpdate("insert into login(uname,email,password) values('"+u+"','"+e+"','"+p+"')");
            JOptionPane.showMessageDialog(null,"User created");
        }
        else
        {
            JFrame f1=new JFrame("Alert");
            f1.setSize(400,300);
            JOptionPane.showMessageDialog(null,"Password do not match");
            //f1.setSize(1000,1000);
            //f1.setLayout(new GridLayout());
            //JLabel l=new JLabel("Passwords dont match");
            add(l);
            f1.setVisible(true);
        }
        }
        catch(SQLException e){e.printStackTrace();}
        
    }
    void fun1()
    {
        new form2();
    }
    public static void main(String[] args)
    {
        new Pass();
    }
                
}
class form2 implements ActionListener
{
    
    JLabel em,pas,q;
    JTextField e;
    JPasswordField p;
    JButton b1;    
    Connection con=null;
    Statement st=null;
    form2()
    {
        try{
            JFrame f=new JFrame("Login Page");
        //f.pack();
        f.setSize(900,900);
        //f.setVisible(true);
        f=new JFrame("Login page");
        q=new JLabel("Enter the following details");
        em=new JLabel("Email id");
        pas=new JLabel("Password");
        e=new JTextField(15);
        p=new JPasswordField(15);
        b1=new JButton("Login");
        q.setFont(new Font("Serif", Font.PLAIN, 30));
         f.setLayout(null);
        q.setBounds(400,80,500,40);
        em.setBounds(400,200,230,30);
        pas.setBounds(400,240,230,30);
        p.setBounds(500,240,230,30);
        e.setBounds(500,200,230,30);
        b1.setBounds(400,300,230,30);
        f.add(q);
        f.add(em);
        f.add(pas);
        f.add(e);
        f.add(p);
        f.add(b1);
        f.setVisible(true);
        b1.addActionListener(this);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","vinitha");
        }catch(SQLException e){e.printStackTrace();}
        catch(Exception ex){ex.printStackTrace();}
    }

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand()=="Login")
                    appform();
                    }
            void appform()
            {
                try{
                String str1=e.getText();
                char[] p1=p.getPassword();
                String str2=new String(p1);
               
                    st=con.createStatement();
                    ResultSet rs=st.executeQuery("select uname from login where email='"+str1+"' and password='"+str2+"'");
                    if(rs.next()){
                        appform_f();
                        System.out.println("works");
                    }
                        else
                        {
                                JOptionPane.showMessageDialog(null,"Incorrect email id or password try with correct details");
                        }
                    }catch(SQLException e){e.printStackTrace();}
                    
                    }
            void appform_f()
            {
                new Application1().setVisible(true);
            }
                
            
       }
   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vinitha
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vinitha
 */
class Application1 extends javax.swing.JFrame implements ActionListener{
    Connection con=null;
    Statement st=null;
    @Override
   public void actionPerformed(ActionEvent e){
        try{
        //if("sub".equals(e.getActionCommand()))
        {
        String adate, fname, lname, sex, dob, address, state, nationality, father, mother;
        int contact;
        adate=jTextField1.getText();
        fname=jTextField2.getText();
        lname=jTextField3.getText();
        sex=jTextField4.getText();
        dob=jTextField5.getText();
        address=jTextField6.getText();
        contact=Integer.parseInt(jTextField7.getText());
        state=jTextField8.getText();
        nationality=jTextField9.getText();
        father=jTextField10.getText();
        mother=jTextField11.getText();
        st=con.createStatement();
        if(!(adate.isEmpty()||fname.isEmpty()||lname.isEmpty()||sex.isEmpty()||dob.isEmpty()||address.isEmpty()||(jTextField7.getText()).isEmpty()||state.isEmpty()||nationality.isEmpty()||father.isEmpty()||mother.isEmpty()))
        {
        st.executeUpdate("Insert into application values('"+adate+"', '"+fname+"', '"+lname+"', '"+sex+"', '"+dob+"', '"+address+"', "+contact+", '"+state+"', '"+nationality+"', '"+father+"', '"+mother+"')"); 
        JOptionPane.showMessageDialog(null, "Data Inserted");
        System.out.println("App");
        System.out.println("Data Inserted");
        }
      else{
            JOptionPane.showMessageDialog(null, "Fill in all the details");
        }
        }
    }catch(SQLException e2){e2.printStackTrace();}
     catch(Exception e1){e1.printStackTrace();}
    }

    /**
     * Creates new form Application
     */
    public Application1() {
        try{
            
            initComponents();
            setTitle("Passport Application");
            jButton1.setActionCommand("sub");
            jButton1.addActionListener(this);
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
        con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","vinitha");
        }catch(SQLException e){e.printStackTrace();}
        catch(Exception ex){ex.printStackTrace();}
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("APPLICATION TO PASSPORT");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Date of application");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("First Name");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Last Name");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Sex");

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jTextField4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Permanant Address");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Contact Information");

        jTextField6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("State");

        jTextField7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Nationality");

        jTextField8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("Father's Name");

        jTextField9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("Mother's Name");

        jTextField10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Date Of Birth");

        jTextField11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setText("Submit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(121, 121, 121)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5)
                            .addComponent(jTextField6)
                            .addComponent(jTextField7)
                            .addComponent(jTextField8)
                            .addComponent(jTextField9)
                            .addComponent(jTextField10)
                            .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>                        

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration                   
}

