package data.trees;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 * Created by Mohamed Mahmoud on 5/7/2017.
 */
class Main extends JFrame implements ActionListener {
    /**
     *
     */
    static Dictionary<String> B;

    public static void main(String[] args){
        B=new Dictionary<>();
        new Main();
    }

    private static final long serialVersionUID = -2829448395694197965L;

    public Main(){
        this.setSize(500,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("AVL Tree");
        panel1 = new JPanel();
        /*************buttons******/
        button0 = new JButton("Insert");
        button1 = new JButton("Find");
        button2 = new JButton("Delete");
        button3 = new JButton("Show Tree");
        button0.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        panel1.add(button0);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);

        Border b = BorderFactory.createEmptyBorder(45,0,0, 0);
        panel1.setBorder(b);
        this.add(panel1);
        this.setVisible(true);
    }
    JPanel panel1;
    private JButton button0,button1,button2,button3;

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == button0){
            String s=JOptionPane.showInputDialog("Enter the integer value");
            B.tree.insert(s);
        }else if(e.getSource() == button1){
            String s=JOptionPane.showInputDialog("Enter the integer value");
            if(B.tree.find(s)==null)
                JOptionPane.showMessageDialog(null,"Not Found");
            else
                JOptionPane.showMessageDialog(null,"Found");
        }else if(e.getSource() == button2){
            String s=JOptionPane.showInputDialog("Enter the integer value");
            TreeNode<String> temp=B.tree.find(s);
            if(temp==null)
                JOptionPane.showMessageDialog(null,"Not Found");
            else{
                B.tree.delete(s);
                JOptionPane.showMessageDialog(null,"Removed");
            }
        }else if(e.getSource() == button3){
            JFrame f = new JFrame("AVL Tree");
            f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) { }
            });
            Drawtree applet = new Drawtree();
            f.getContentPane().add("Center", applet);
            Toolkit tk = Toolkit.getDefaultToolkit();
            int xSize = ((int) tk.getScreenSize().getWidth());
            applet.init(B.tree.root,xSize-50);
            f.pack();
            f.setSize(new Dimension(xSize,500));
            f.setVisible(true);
        }
    }
}
class Drawtree extends JApplet {
    /**
     *
     */
    private static final long serialVersionUID = -7654352523443329890L;
    final  Color bg = Color.white;
    final  Color fg = Color.black;
    final  Color red = Color.red;
    final  Color white = Color.white;
    final  BasicStroke stroke = new BasicStroke(2.0f);
    final  BasicStroke wideStroke = new BasicStroke(8.0f);

    Dimension totalSize;
    int height,width;
    TreeNode r=null;
    public void init(TreeNode N,int x) {
        //Initialize drawing colors
        setBackground(bg);
        setForeground(fg);
        r=N;
        width=x;
    }
    Graphics2D g2;


    public void paint(Graphics g) {
        g2=  (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        getSize();

    }

    public void draw(int x1,int x2,int y,String n,int d){
        g2.setStroke(stroke);

        g2.setPaint(Color.black);
        int x=(x1+x2)/2;
        if(d==1)
            g2.draw(new Line2D.Double(x2, y-30, x+15, y));
        else if(d==2)
            g2.draw(new Line2D.Double(x+15, y,x1+30 , y-30));
        g2.setPaint(Color.blue);
        Shape circle=new Ellipse2D.Double((x1+x2)/2,y, 30, 30);
        g2.draw(circle);
        g2.fill(circle);
        g2.setPaint(Color.white);
        g2.drawString(n, x+10, y+18);
    }

    int x1=500,y1=30;

    void inorder(TreeNode r,int x1,int x2,int y){
        if(r==null) return;

        inorder(r.left,x1,(x1+x2)/2,y+40);
        if(r.parent==null) draw(x1,x2,y,r.content+"",0);
        else{
            if(r.parent.content.compareTo(r.content)<0)	draw(x1,x2,y,r.content+"",2);
            else			draw(x1,x2,y,r.content+"",1);
        }
        inorder(r.right,(x1+x2)/2,x2,y+40);
    }
}
