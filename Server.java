import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net. *;
import java.io.*;


public class Server  implements ActionListener{

    static JFrame frame = new JFrame();

    JTextField text;
    JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dout;
    private int mouseX, mouseY;
    Server(){

        frame.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94 , 84));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        frame.add(p1);


        ImageIcon backIcon = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image backImage = backIcon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon backScaledIcon = new ImageIcon(backImage);
        JLabel back = new JLabel(backScaledIcon);
        back.setBounds(5, 20, 25, 25);
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                System.exit(0);
            }
        });
        p1.add(back);

        ImageIcon profileIcon = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
        Image profileImage = profileIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon profileScaledIcon = new ImageIcon(profileImage);
        JLabel  profile = new JLabel(profileScaledIcon);
        profile.setBounds(40, 10, 50, 50);
        profile.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                System.exit(0);
            }
        });
        p1.add(profile);

        ImageIcon videoIcon = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image videoImage = videoIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon videoScaledIcon = new ImageIcon(videoImage);      
        JLabel video = new JLabel(videoScaledIcon);
        video.setBounds(300, 20, 30, 30);
        p1.add(video);

        ImageIcon phoneIcon = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image phoneImage = phoneIcon.getImage().getScaledInstance(35, 30,  Image.SCALE_DEFAULT);
        ImageIcon phoneScaledIcon = new ImageIcon(phoneImage);
        JLabel phone = new JLabel(phoneScaledIcon);
        phone.setBounds(360, 20, 35, 30);
        p1.add(phone);

        JLabel name = new JLabel("Gaitonde");
        name.setBounds(100, 20, 100, 25);
        name.setForeground(Color.WHITE);
        name.setFont(name.getFont().deriveFont(20.0f));
        p1.add(name);

        JLabel status = new JLabel("Active Now");
        status.setBounds(100, 45, 100, 15);
        status.setForeground(Color.WHITE);
        status.setFont(status.getFont().deriveFont(14.0f));
        p1.add(status);

        a1 = new JPanel();
        a1.setBounds(5, 75, 420, 570);
        a1.setLayout(new BorderLayout());
        a1.add(vertical, BorderLayout.PAGE_START);
        frame.add(a1);

        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        frame.add(text);
         
        JButton send = new JButton("Send");
        send.setBounds(320, 655, 120, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("SANS_SERIF",  Font.BOLD, 16));
        send.addActionListener(this);
        frame.add(send);

        ImageIcon moreIcon = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image moreImage = moreIcon.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon moreScaledIcon = new ImageIcon(moreImage);
        JLabel more = new JLabel(moreScaledIcon);
        more.setBounds(410, 20, 10, 25);
        p1.add(more);

        p1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        p1.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - mouseX, e.getYOnScreen() - mouseY);
            }
        });

        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setSize(450, 700);
        frame.setTitle("Chatting Application");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLocation(200 , 200 );
    }

    

    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String out = text.getText().trim();
        if (out.isEmpty()) {
            return;
        }
        JPanel p2 = formatLabel(out);

        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        try{
            dout.writeUTF(out);
        }

        catch (Exception exception){
            exception.printStackTrace();
        }

        text.setText("");

        frame.repaint();
        frame.invalidate();
        frame.validate();
        
    }
    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style = \"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("SANS_SERIF", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15 , 15 ,15 ,50));
        panel.add(output);
        
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel(sdf.format(calendar.getTime()));
        time.setFont(new Font("SANS_SERIF", Font.PLAIN, 12));
        panel.add(time);    

        return panel;
    }
    public static void main(String[] args) {
        new Server();

        try (ServerSocket serverSocket = new ServerSocket(6001)) {
            while (true) {
                try (Socket s = serverSocket.accept();
                     DataInputStream din = new DataInputStream(s.getInputStream());
                     DataOutputStream doutStream = new DataOutputStream(s.getOutputStream())) {

                    dout = doutStream;
                    while (true) {
                        String msg = din.readUTF();
                        JPanel panel = formatLabel(msg);
                        
                        JPanel left = new JPanel(new BorderLayout());
                        left.add(panel, BorderLayout.LINE_START);
                        vertical.add(left);
                        vertical.add(Box.createVerticalStrut(15));
                        frame.validate();
                        frame.repaint();
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}