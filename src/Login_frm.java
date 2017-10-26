import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_frm extends JFrame {
    private JLabel jLabelID = new JLabel("ID");
    private JLabel jLabelPW = new JLabel("Password");
    private JTextField jtfID = new JTextField();
    private JPasswordField jPw = new JPasswordField();
    private JButton jbExit = new JButton("EXIT");
    private JButton jbLogin = new JButton("LOGIN");
    private Container cp;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int frmW = 300, frmH = 150, screenW, screenH;

    public Login_frm(){

        initComp();
    }
    private void initComp(){
        screenW = dim.width;
        screenH = dim.height;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(screenW/2-frmW/2,screenH/2-frmH/2,frmW,frmH);
        cp =this.getContentPane();
        cp.setLayout(new GridLayout(3,2,3,3));
        jLabelID.setHorizontalAlignment(JLabel.RIGHT);
        jLabelPW.setHorizontalAlignment(JLabel.RIGHT);
        jbLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtfID.getText().equals("h304") && (new String(jPw.getPassword())).equals("23323456")){
                    Main_frm M_frm = new Main_frm(Login_frm.this);
                    M_frm.setVisible(true);
                    Login_frm.this.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(Login_frm.this,"ERROR");
                }
            }
        });

        jbExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        cp.add(jLabelID);
        cp.add(jtfID);
        cp.add(jLabelPW);
        cp.add(jPw);
        cp.add(jbLogin);
        cp.add(jbExit);
    }
    public void reset(){
        jtfID.setText("");
        jLabelPW.setText("");
    }
}