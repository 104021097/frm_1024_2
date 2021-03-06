import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Main_frm extends JFrame {
    private int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private JMenuBar jmb = new JMenuBar();
    private JMenu jmF = new JMenu("File");
    private JMenu jmS = new JMenu("Set");
    private JMenu jmG = new JMenu("Game");
    private JMenu jmA = new JMenu("About");

    private JPanel jpnCal = new JPanel(new GridLayout(4,4));
    private JTextField jtf = new JTextField();
    private  JButton jbtnCal[] = new JButton[10];
    private int data_cal[] = new int[10];
    private Random rnd_Cal = new Random(System.currentTimeMillis());

    private JDesktopPane jdp = new JDesktopPane();
    private JMenuItem jMenuItemFExit = new JMenuItem("Exit");
    private JMenuItem jMenuItemFLotto = new JMenuItem("Lotto");
    private JMenuItem jMenuItemCalculator = new JMenuItem("Calculator");
    private JInternalFrame jInternalFrame = new JInternalFrame();
    private JInternalFrame jInternalFrame_cal = new JInternalFrame();
    private Container jifCP;
    private JPanel jpn = new JPanel(new GridLayout(1,6,5,5));
    private JPanel jpn1 = new JPanel(new GridLayout(1,2,5,5));

    private JLabel jlbs[] = new JLabel[6];
    private int data[] = new int[6];
    private Random rnd = new Random(System.currentTimeMillis());


    private JButton jbtnClose = new JButton("Close");
    private JButton jbtnRegen = new JButton("Generate");
    private JButton jbtnClose_Cal = new JButton("Close");
    private JButton jbtnRegen_Cal = new JButton("Generate");


    private int frmW = 500, frmH = 450;
    private Login_frm loginFrm;
    public Main_frm(Login_frm frm){
        loginFrm = frm;
        initComp();
    }
    private void initComp(){
        this.setBounds(screenW/2-frmW/2,screenH/2-frmH/2,frmW,frmH);
        jInternalFrame.setBounds(0,0,200,100);
        jInternalFrame_cal.setBounds(0,0,400,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setJMenuBar(jmb);
        this.setContentPane(jdp);

        jmF.add(jMenuItemFExit);
        jmG.add(jMenuItemFLotto);
        jmG.add(jMenuItemCalculator);
        jmb.add(jmF);
        jmb.add(jmS);
        jmb.add(jmG);
        jmb.add(jmA);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loginFrm.reset();
                loginFrm.setVisible(true);
            }
        });

        jpn1.add(jbtnClose);
        jpn1.add(jbtnRegen);
        jpnCal.add(jbtnClose_Cal);
        jpnCal.add(jbtnRegen_Cal);
        jpnCal.add(jtf);
        jtf.setEditable(false);

        jMenuItemFExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        jMenuItemFLotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jInternalFrame);
                jInternalFrame.setVisible(true);
            }
        });
        jMenuItemCalculator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jInternalFrame_cal);
                jInternalFrame_cal.setVisible(true);
            }
        });


        jbtnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jInternalFrame.dispose();
            }
        });
        jbtnRegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lottoGenerate();
            }
        });
        jbtnClose_Cal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jInternalFrame_cal.dispose();
            }
        });

        jifCP = jInternalFrame.getContentPane();
        jifCP.setLayout(new BorderLayout(5,5));
        jifCP.add(jpn, BorderLayout.CENTER);
        jifCP.add(jpn1, BorderLayout.SOUTH);

        for(int i=0;i<6;i++){
            jlbs[i] = new JLabel();
            jlbs[i].setFont(new Font(null, Font.PLAIN,22));
            jlbs[i].setOpaque(true);
            jlbs[i].setBackground(new Color(186,85,211));
            jlbs[i].setHorizontalAlignment(JLabel.CENTER);
            jpn.add(jlbs[i]);

        }
        lottoGenerate();
        jMenuItemFLotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lottoGenerate();

            }
        });
        for(int i=0;i<10;i++){
            jbtnCal[i] = new JButton();
            jbtnCal[i].setFont(new Font(null,Font.PLAIN,18));
            jbtnCal[i].setOpaque(true);
            jbtnCal[i].setBackground(new Color(2,202,119));
            jbtnCal[i].setHorizontalAlignment(JButton.CENTER);
            jpnCal.add(jbtnCal[i]);
            jbtnCal[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton tmpButton = (JButton) e.getSource();
                    jtf.setText(jtf.getText()+tmpButton.getText());
                }
            });
        }

        calculatorGenerate();
        jMenuItemCalculator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatorGenerate();
            }
        });

        jMenuItemFExit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    }


    private  void lottoGenerate(){
        int i =0;
        while(i<6){
            data[i] = rnd.nextInt(42)+1;
            int j =0;
            boolean flag = true;
            while (j<i && flag){
                if(data[i] == data[j]){
                    flag = false;
                }
                j++;
            }
            if(flag){
                jlbs[i].setText(Integer.toString(data[i]));
                i++;
            }
        }
    }

    private void calculatorGenerate(){
        int i =0;
        while (i<10){
            data_cal[i] = rnd_Cal.nextInt(10)+1;
            int j =0;
            boolean flag = true;
            while (j<i && flag){
                flag = false;
            }
            j++;
            if (flag){
                jbtnCal[i].setText(Integer.toString(data_cal[i]));
                i++;
        }

        }
    }

}
