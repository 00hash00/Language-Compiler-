package Compiler;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class UI extends JFrame {



    private JTextArea Chatarea = new JTextArea();
    JTextArea code = new JTextArea(10,15);
    JTextArea token = new JTextArea(10,15);
    JTextArea main = new JTextArea(10,15);
    JTextArea maint = new JTextArea(10,15);
    JTextArea body = new JTextArea(10,15);
    JTextArea func = new JTextArea(10,15);
    JTextArea bodyt = new JTextArea(10,15);
    JTextArea funct = new JTextArea(10,15);
    JTextArea sem = new JTextArea(10,15);
    JTextArea semt = new JTextArea(10,15);
    JScrollPane jb = new JScrollPane(code,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JScrollPane jb1 = new JScrollPane(token,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JScrollPane jbm = new JScrollPane(maint,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JScrollPane jbb = new JScrollPane(bodyt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JScrollPane jbf = new JScrollPane(funct,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JScrollPane jbs = new JScrollPane(semt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    //creating a public constructor
    public UI() {
        JFrame frame = new JFrame();
        JFrame stables = new JFrame();

        ImageIcon tokimg = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\img\\tok.png");
        ImageIcon codimg = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\img\\code.png");
        ImageIcon leximg = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\img\\lex.png");
        ImageIcon synimg = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\img\\syn.png");
        ImageIcon semimg = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\img\\sem.png");
        ImageIcon labimg = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\img\\ana.png");
        ImageIcon compimg = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\img\\comp.png");
        ImageIcon editimg = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\img\\edit.png");
        ImageIcon savimg = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\img\\sav.png");
        ImageIcon stimg = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\img\\sybt.png");
        ImageIcon mainimg = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\img\\main.png");
        ImageIcon bodyimg = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\img\\body.png");
        ImageIcon funcimg = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\img\\func.png");
        ImageIcon tabimg = new ImageIcon("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\img\\tab.png");

        JFrame codeframe =new JFrame();
        JFrame tokenframe = new JFrame();
        JFrame mainframe = new JFrame();
        JFrame bodyframe = new JFrame();
        JFrame funcframe = new JFrame();
        JFrame semerr = new JFrame();

        JPanel panel = new JPanel();
        JPanel panelleft = new JPanel();
        JPanel panelright = new JPanel();
        JPanel panelbottom = new JPanel();
        JPanel paneltop = new JPanel();
        JLabel label = new JLabel();
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel();
        JPanel panel2 = new JPanel();
        JLabel label2 = new JLabel();
        JPanel panel3 = new JPanel();
        JLabel label3 = new JLabel();

        JTextArea symb= new JTextArea(10,10);
        JTextArea symb1= new JTextArea(10,10);



        //Frame Work

        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);


        frame.setSize(680, 800);
        frame.setTitle("COMPILER CONSTRUCTION");
        frame.add(Chatarea);

        stables.setSize(750, 300);
        stables.setTitle("SYMBOL TABLES");
        stables.getContentPane().setBackground(Color.DARK_GRAY);

        stables.setResizable(false);
        stables.setLayout(null);



        Chatarea.setBackground(Color.black);
        Chatarea.setEditable(false);

        //Panels and Labels
        panel.setBounds(11, 11, 642, 45);
        panel.setBackground(Color.DARK_GRAY);
        label.setIcon(compimg);
        label.setBounds( 200, 0, 270, 45);
        panel1.setBounds(80, 520, 500, 50);
        panel1.setBackground(Color.DARK_GRAY);
        label1.setBounds(80, 520, 250, 50);
        label1.setIcon(labimg);
        label1.setFont(new Font("CASTELLAR", Font.BOLD, 15));
        label3.setIcon(tabimg);
        label3.setBounds( 250, 10, 270, 45);
        label.setFont(new Font("CASTELLAR", Font.BOLD, 20));
        paneltop.setBounds(0, 0, 700, 10);
        paneltop.setBackground(Color.blue);
        panelbottom.setBounds(0,751 , 700, 10);
        panelbottom.setBackground(Color.blue);
        panelleft.setBounds(0, 0, 10, 800);
        panelleft.setBackground(Color.blue);
        panelright.setBounds(654, 0, 13, 800);
        panelright.setBackground(Color.blue);







        //Send Button
        JButton b1 = new JButton(codimg);
        JButton b0 = new JButton(tokimg);
        JButton b2= new JButton(leximg);
        JButton b3= new JButton(synimg);
        JButton b4= new JButton(semimg);
        JButton b5= new JButton(editimg);
        JButton b6= new JButton(savimg);
        JButton b7= new JButton(stimg);
        JButton b8= new JButton(mainimg);
        JButton b9= new JButton(bodyimg);
        JButton b10= new JButton(funcimg);

        b0.setBounds(50, 670, 170, 50);
        b0.setBackground(Color.DARK_GRAY);
        b0.setBorder(BorderFactory.createEmptyBorder());
        b1.setBounds(240, 670, 170, 50);
        b1.setBackground(Color.DARK_GRAY);
        b7.setBounds(440, 670, 190, 50);
        b7.setBackground(Color.DARK_GRAY);
        b7.setBorder(BorderFactory.createEmptyBorder());
        b1.setBorder(BorderFactory.createEmptyBorder());
        b2.setBounds(440, 590, 160, 50);
        b2.setBackground(Color.DARK_GRAY);
        b2.setBorder(BorderFactory.createEmptyBorder());
        b3.setBounds(270, 590, 160, 50);
        b3.setBackground(Color.DARK_GRAY);
        b3.setBorder(BorderFactory.createEmptyBorder());
        b4.setBounds(80, 590, 160, 50);
        b4.setBackground(Color.DARK_GRAY);
        b4.setBorder(BorderFactory.createEmptyBorder());
        b5.setBounds(275,15,240,48);
        b5.setBackground(Color.BLACK);
        b5.setBorder(BorderFactory.createEmptyBorder());
        b6.setBounds(515,15,130,48);
        b6.setBackground(Color.BLACK);
        b6.setBorder(BorderFactory.createEmptyBorder());
        b8.setBounds(60, 100, 180, 50);
        b8.setBackground(Color.DARK_GRAY);
        b8.setBorder(BorderFactory.createEmptyBorder());
        b10.setBounds(460, 100, 200, 50);
        b10.setBackground(Color.DARK_GRAY);
        b10.setBorder(BorderFactory.createEmptyBorder());
        b9.setBounds(270, 100, 170, 50);
        b9.setBackground(Color.DARK_GRAY);
        b9.setBorder(BorderFactory.createEmptyBorder());




        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(b0);
        frame.add(b7);
        //Adding Panels and Labels On Frame
        frame.add(panel);
        frame.add(paneltop);
        frame.add(panelbottom);
        frame.add(panelleft);
        frame.add(panelright);
        panel.add(label);
        panel1.setVisible(true);
        frame.add(panel1);
        panel1.add(label1);
        frame.add(panel2);
        panel2.add(label2);

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\wel.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }

        //Text Area
        Chatarea.setSize(630, 450);
        Chatarea.setLocation(17, 60);
        Chatarea.setFont(new Font("CASTELLAR", Font.BOLD, 20));
        Chatarea.setForeground(Color.GREEN);

        // Text Field




        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                code.setBackground(Color.BLACK);
                code.setForeground(Color.GREEN);
                codeframe.setTitle("SOURCE CODE");
                codeframe.setResizable(false);
                codeframe.setSize(680, 800);
                codeframe.setVisible(true);
                code.setEditable(false);
                codeframe.add(jb);
                code.setVisible(true);
                code.add(b5);
                code.add(b6);
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\sc.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                FileInputStream fis = null;
                try {
                    String path = "C:\\Users\\admin\\IdeaProjects\\TVACompiler\\input.txt";
                    File file = new File(path);
                    FileReader fr = new FileReader(file);
                    while(fr.read()!= -1){
                        code.read(fr,null);
                    }
                fr.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                token.setBackground(Color.BLACK);
                token.setForeground(Color.GREEN);
                tokenframe.setResizable(false);
                tokenframe.setTitle("TOKEN FILE");
                tokenframe.setSize(642, 800);
                tokenframe.setVisible(true);
                tokenframe.add(jb1);
                token.setVisible(true);
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\tf.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                FileInputStream fis = null;
                try {
                    String path = "C:\\Users\\admin\\IdeaProjects\\TVACompiler\\output.txt";
                    File file = new File(path);
                    FileReader fr = new FileReader(file);
                    while(fr.read()!= -1){
                        token.read(fr,null);
                    }
                    fr.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Chatarea.setFont(new Font("Times New Roman", Font.BOLD, 15));
               Chatarea.append("\nProcessing Phase: Lexical Analyzer.....................");
               Lexical obj = new Lexical();
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\process.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"Processing Complete...................");

                Chatarea.append("\nTokens Generated.......................");
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\Token.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Chatarea.setFont(new Font("Times New Roman", Font.BOLD, 15));
                Chatarea.append("\nProcessing Phase: Syntax Analyzer.....................\n");
                Lexical obj = new Lexical();
                try {
                    Syntax ob = new Syntax();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\process.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(null,"Processing Complete.........................");


                FileInputStream fis = null;
                try {
                    String path = "C:\\Users\\admin\\IdeaProjects\\TVACompiler\\table.txt";
                    File file = new File(path);
                    FileReader fr = new FileReader(file);
                    while(fr.read()!= -1){
                        symb.read(fr,null);

                    }

                    Chatarea.append(symb.getText());
                    if(symb.getText().equals("Valid Syntax")){
                        try {
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\Valid.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                        } catch (Exception ex) {
                            System.out.println("Error with playing sound.");
                            ex.printStackTrace();
                        }
                    }else {
                        try {
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\Invalid.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                        } catch (Exception ex) {
                            System.out.println("Error with playing sound.");
                            ex.printStackTrace();
                        }
                    }
                    fr.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }




                //console.setVisible(true);

            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Chatarea.setFont(new Font("Times New Roman", Font.BOLD, 15));
                Chatarea.append("\nProcessing Phase: Semantic Analyzer.....................");
                Lexical obj = new Lexical();
                try {
                    Syntax ob = new Syntax();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\process.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(null,"Processing Complete.........................");
                semt.setBackground(Color.BLACK);
                semt.setForeground(Color.GREEN);
                semerr.setTitle("SEMANTIC ERRORS");
                semerr.setResizable(false);
                semerr.setSize(680, 800);
                semt.setEditable(false);
                semerr.add(jbs);
                semt.setVisible(true);
                try {
                    String path = "C:\\Users\\admin\\IdeaProjects\\TVACompiler\\Errortable.txt";
                    File file = new File(path);
                    FileReader fr = new FileReader(file);
                    while(fr.read()!= -1){
                        sem.read(fr,null);
                    }
                    semt.append("\n"+sem.getText());
                    fr.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }




                if(semt.getText().equals(" ")){
                    Chatarea.append("\nNo Semantic Errors Detected................");
                    try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\nserr.wav").getAbsoluteFile());
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                    } catch (Exception ex) {
                        System.out.println("Error with playing sound.");
                        ex.printStackTrace();
                    }
                }else{
                    Chatarea.append("\nSemantic Errors Detected................");
                    semerr.setVisible(true);
                    try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\serr.wav").getAbsoluteFile());
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                    } catch (Exception ex) {
                        System.out.println("Error with playing sound.");
                        ex.printStackTrace();
                    }
                    semt.setVisible(true);
                }
            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                try {
                    Desktop.getDesktop().open(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\input.txt"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                code.setBackground(Color.BLACK);
                code.setForeground(Color.GREEN);
                codeframe.setResizable(false);
                codeframe.setSize(680, 800);
                codeframe.setVisible(true);
                code.setEditable(false);
                codeframe.add(jb);
                code.setVisible(true);
                b5.setVerticalTextPosition(JButton.BOTTOM);
                code.add(b5);
                code.add(b6);
                FileInputStream fis = null;

                try {
                    String path = "C:\\Users\\admin\\IdeaProjects\\TVACompiler\\input.txt";
                    File file = new File(path);
                    FileReader fr = new FileReader(file);
                    while(fr.read()!= -1){
                        code.read(fr,null);
                    }
                    fr.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


            }
        });
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\st.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }

                stables.setVisible(true);
                stables.add(label3);
                stables.add(b8);
                b8.setVisible(true);
                stables.add(b9);
                b9.setVisible(true);
                stables.add(b10);
                b10.setVisible(true);



            }
        });

        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                maint.setBackground(Color.BLACK);
                maint.setForeground(Color.GREEN);
                maint.append("NAME \t TYPE \t ACC MOD\tCATEGORY \t PARENT \nA");
                mainframe.setTitle("MAIN TABLE");
                mainframe.setResizable(false);
                mainframe.setSize(680, 800);
                mainframe.setVisible(true);
                maint.setEditable(false);
                mainframe.add(jbm);
                maint.setVisible(true);
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\main.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                FileInputStream fis = null;
                try {
                    String path = "C:\\Users\\admin\\IdeaProjects\\TVACompiler\\Maintable.txt";
                    File file = new File(path);
                    FileReader fr = new FileReader(file);
                    while(fr.read()!= -1){
                        main.read(fr,null);
                    }
                    maint.append(main.getText());
                    fr.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                bodyt.setBackground(Color.BLACK);
                bodyt.setForeground(Color.GREEN);
                bodyframe.setTitle("BODY TABLE");
                bodyframe.setResizable(false);
                bodyframe.setSize(680, 800);
                bodyframe.setVisible(true);
                bodyt.setEditable(false);
                bodyframe.add(jbb);
                bodyt.setVisible(true);
                bodyt.append("N");
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\body.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                FileInputStream fis = null;
                try {
                    String path = "C:\\Users\\admin\\IdeaProjects\\TVACompiler\\Bodytable.txt";
                    File file = new File(path);
                    FileReader fr = new FileReader(file);
                    while(fr.read()!= -1){
                        body.read(fr,null);
                    }
                    bodyt.append(body.getText());
                    fr.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                funct.setBackground(Color.BLACK);
                funct.setForeground(Color.GREEN);
                funct.append("N");
                funcframe.setTitle("FUNCTION TABLE");
                funcframe.setResizable(false);
                funcframe.setSize(680, 800);
                funcframe.setVisible(true);
                funct.setEditable(false);
                funcframe.add(jbf);
                funct.setVisible(true);
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\admin\\IdeaProjects\\TVACompiler\\voice\\func.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                FileInputStream fis = null;
                try {
                    String path = "C:\\Users\\admin\\IdeaProjects\\TVACompiler\\Functiontable.txt";
                    File file = new File(path);
                    FileReader fr = new FileReader(file);
                    while(fr.read()!= -1){
                        func.read(fr,null);
                    }
                    funct.append(func.getText());
                    fr.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

    }



}









