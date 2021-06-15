package com.company;

import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;

public class Main extends Frame {
    static int x=20;
    static int y=10;
    static int width=1500;
    static int height=800;

    File f = new File("storage.txt");

    Stack<String> previous = new Stack<>();
    Stack<String> next = new Stack<>();

    School school;
    CardLayout card = new CardLayout();
    String current = "weekend";

    //��m�˪O
    JPanel pnlMenu = new JPanel();
    JPanel pnlNorth = new JPanel();
    JPanel pnlNW = new JPanel();
    JPanel pnlNE = new JPanel();
    JPanel pnlNE1 = new JPanel();
    JPanel pnlDate = new JPanel();
    JPanel pnlFunction = new JPanel();
    JPanel pnlCenter = new JPanel();
    JPanel pnlScore = new JPanel();
    JPanel pnlSouth = new JPanel();
    JPanel pnlSouth1 = new JPanel();
    JPanel pnlSouth2 = new JPanel();
    //    JPanel pnlSen31 = new JPanel();
    //    JPanel pnlSen21 = new JPanel();
    //    JPanel pnlSen22 = new JPanel();
    //    JPanel pnlSen11 = new JPanel();
    //    JPanel pnlSen12 = new JPanel();
    //    JPanel pnlJun31 = new JPanel();
    //    JPanel pnlJun21 = new JPanel();
    //    JPanel pnlJun22 = new JPanel();
    //    JPanel pnlJun11 = new JPanel();
    //    JPanel pnlJun12 = new JPanel();
    //���e�˪O
    JPanel pnlInti = new JPanel();//��l�]�w�]�Ĥ@���ϥΡ^
    JPanel pnlSenior = new JPanel();
    JPanel pnlJunior = new JPanel();
    JPanel pnlSetting = new JPanel();
    JPanel pnlClass = new JPanel();
    JPanel pnlHistory = new JPanel();
    JPanel pnlTimeTable = new JPanel();
    JPanel pnl[][];
    JPanel pnlSetClass;
    JPanel pnlSetBall;


    //
    JLabel lblTitle = new JLabel();
    JLabel lblSearch = new JLabel("�d��: ");
    JLabel lblTime = new JLabel();
    JLabel lblSenior = new JLabel("����");
    JLabel lblJunior = new JLabel("�ꤤ");
    JPanel lbl[][];

    JButton btnHome = new JButton("�D����");
    JButton btnHistory = new JButton("���v����");
    JButton btnClass = new JButton("�Z��");
    JButton btnScore = new JButton("�I��");
    JButton btnTimeTable = new JButton("�ɶ���");
    JButton btnSetting = new JButton("�]�w");
    JButton btnOK = new JButton("�T�w");
    JButton btnBack = new JButton("��^");
    JButton btnNext =new JButton("�U�@��");
    JButton[] btnRemoveClass;
    JButton[] btnRemoveBall;

    JTextField[] classField;
    JTextField[] gPointField;
    JTextField[] bPointField;
    JTextField[] banField;

    JTextField[] ballNameField;
    JTextField[] courtField;
    JTextField[] ballField;
    JTextField[] batField;
    JTextField[] damagedField;

    String [] ballName;

//    String [][] juniorData={
//            {"��T��","1","2","3"},
//            {"��T��","1","2","3"},
//            {"��G��","1","2","3"},
//            {"��G��","1","2","3"},
//            {"��@��","1","2","3"},
//            {"��@��","1","2","3"}
//    };
//
//    String [][] seniorData={
//            {"���T��","1","2","3"},
//            {"���T��","1","2","3"},
//            {"���G��","1","2","3"},
//            {"���G��","1","2","3"},
//            {"���@��","1","2","3"},
//            {"���@��","1","2","3"},
//    };


    JPanel pnlWeekend=new JPanel();
    JPanel pnlWeekendResult = new JPanel();
    //    JPanel pnlSenior=new JPanel();
//    JPanel pnlJunior=new JPanel();
//    JPanel[][] pnlSeniorOrder= new JPanel[5][3];
//    JPanel[][] PnlJuniorOrder=new JPanel[5][3];

    JTable tabSenior;
    JScrollPane scrollPane1;

    JTable tabJunior;
    JScrollPane scrollPane2;

    JTable table;

    Main() throws FileNotFoundException {
        //�إߨt��
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //����
        this.setSize(width,height);
        this.setLocation(x,y);
        this.setTitle("�ǰȳB�ɲy�n�O�@�WOP Fy(By) �⥻��&�@�u��");
        BorderLayout thisLayout = new BorderLayout(50,60);
        this.setLayout(thisLayout);

        //�D�˪O
        this.add(pnlMenu,BorderLayout.CENTER);
        pnlMenu.setSize(600,1000);
//        GridLayout layMenu = new GridLayout(5,10);
//        pnlMenu.setLayout(layMenu);
        pnlMenu.setLayout(new BorderLayout());
//        pnlMenu.setSize(1400,800);
//        pnlMenu.setOpaque(true);
//        pnlMenu.setBackground(Color.YELLOW);

        //�W�b
        pnlMenu.add(pnlNorth,BorderLayout.NORTH);
        pnlNorth.setAlignmentY(40);
        pnlNorth.setLayout(new GridLayout());
//        pnlNorth.setOpaque(true);
//        pnlNorth.setBackground(Color.YELLOW);

//        lblTitle.setBackground(Color.BLACK);
        pnlNorth.add(pnlNW);
        pnlNW.setLayout(new FlowLayout());
//        pnlNW.setOpaque(true);
//        pnlNW.setBackground(Color.YELLOW);
        pnlNW.add(lblTitle);
        lblTitle.setOpaque(true);
        lblTitle.setBackground(Color.cyan);
        lblTitle.setFont(new Font("Dialog",Font.BOLD,55));
        pnlNorth.add(pnlNE);
        pnlNE.setLayout(new GridLayout(2,1));
//        pnlNE.setOpaque(true);
//        pnlNE.setBackground(Color.YELLOW);
        pnlNE.add(pnlNE1);
//        pnlNE1.setOpaque(true);
//        pnlNE1.setBackground(Color.YELLOW);
        pnlNE1.setLayout(new GridLayout());

        //���
        lblTime.setText("����"+school.getYear()+"�~"+school.getMonth()+"��"+school.getDate()+"�� �P��"+school.getDay());
        lblTime.setFont(new Font("Dialog", Font.BOLD, 16));
        pnlDate.add(lblTime);
        pnlNE1.add(pnlDate);

        //�\���
        btnHome.setFont(new Font("Dialog", Font.BOLD, 16));
        lblSearch.setFont(new Font("Dialog", Font.BOLD, 16));
        btnHistory.setFont(new Font("Dialog", Font.BOLD, 16));
        btnClass.setFont(new Font("Dialog", Font.BOLD, 16));
        btnScore.setFont(new Font("Dialog", Font.BOLD, 16));
        btnTimeTable.setFont(new Font("Dialog", Font.BOLD, 16));

        pnlFunction.setLayout(new FlowLayout());
        pnlFunction.add(btnHome);
        pnlFunction.add(lblSearch);
        pnlFunction.add(btnHistory);
        pnlFunction.add(btnClass);
        pnlFunction.add(btnScore);
        pnlFunction.add(btnTimeTable);
        pnlNE.add(pnlFunction);

        //���e

        pnlMenu.add(pnlCenter,BorderLayout.CENTER);
//        pnlCenter.setLayout(new GridLayout());
        pnlCenter.setLayout(card);

        //�Z�Ť���
        pnlCenter.add(pnlClass,"class");
        pnlClass.add(new LabelNotDone());

        //���v��������
        pnlCenter.add(pnlHistory,"history");
        pnlHistory.add(new LabelNotDone());

        //�I�Ƥ���

        //�]�w����
        resetSetting();

       //�ɶ���ɭ�
        pnlCenter.add(pnlTimeTable,"timeTable");
        //pnlTimeTable.add(new LabelNotDone());
        pnlTimeTable.setLayout(new GridLayout(10,8, 5, 10));
        pnlTimeTable.add(new JLabel(""));
        JLabel[] num=new JLabel[9];
        for(int j=1;j<=7;j++) {
            String str = new String();
            switch (j){
                case 7->{str="�P����";}
                case 1->{str="�P���@";}
                case 2->{str="�P���G";}
                case 3->{str="�P���T";}
                case 4->{str="�P���|";}
                case 5->{str="�P����";}
                case 6->{str="�P����";}
            }
            JLabel lbl = new JLabel(str, JLabel.CENTER);
            lbl.setFont(new Font("Dialog",Font.BOLD,20));
            pnlTimeTable.add(lbl);
        }
        for(int i=0; i<9; i++) {
            num[i]=new JLabel("�� "+String.valueOf(i+1)+" �`", JLabel.CENTER);
//            System.out.println(num[i].getFont());
            num[i].setFont(new Font( "Dialog",Font.BOLD,20));
            pnlTimeTable.add(num[i]);
            for(int j=0; j<7; j++) {
                JTextField txt = new JTextField("" );
                txt.setFont(new Font("Dialog",Font.BOLD,20));
                pnlTimeTable.add(txt);
            }
        }

        //��l�ɭ� (�w�}�J?�w�ɭ�)
//        pnlCenter.add(pnlInti,"Init");
//        pnlInti.add(new LabelNotDone());

//        pnlCenter.add(lblNotDone,"repaired");

        //�P���ɲy��g���Ǥ���
        pnlCenter.add(pnlWeekend,"weekend");
        pnlWeekend.setLayout(new GridLayout(2,1));
        pnlWeekend.add(pnlSenior);
        pnlWeekend.add(pnlJunior);

        ballName = new String[school.getBallKinds()+1];
        ballName[0] = "�Z��";
        for (int i=0; i< school.getBallKinds(); i++)
            ballName[i+1] = school.getBall(i).getName();

        String[][] sSenior = new String[school.getSenior().length][school.getBallKinds()+1];
        for (int i=0; i<school.getSenior().length; i++) {
            sSenior[i][0] = school.getSenior()[i].getName();
            for (int j=1; j<=school.getBallKinds(); j++)
                sSenior[i][j] = String.valueOf(j);
        }
        tabSenior = new JTable( sSenior, ballName);
        scrollPane1 = new JScrollPane(tabSenior);
        pnlSenior.add(scrollPane1);

        String[][] sJunior = new String[school.getJunior().length][4];
        for (int i=0; i<school.getJunior().length; i++) {
            sJunior[i][0] = school.getJunior()[i].getName();
            for (int j=1; j<=3; j++)
                sJunior[i][j] = String.valueOf(j);
        }
        tabJunior = new JTable( sJunior, ballName);
        scrollPane2 = new JScrollPane(tabJunior);
        pnlJunior.add(scrollPane2);

        pnlSenior.setLayout(new GridLayout());
        tabSenior.getTableHeader().setReorderingAllowed(false);
        tabSenior.setUpdateSelectionOnSort(false);
        tabSenior.setFont(new Font("Dialog", Font.BOLD, 16));
        tabSenior.setRowMargin(10);
        tabSenior.setRowHeight(40);
        tabSenior.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 16));

        pnlJunior.setLayout(new GridLayout());
        tabJunior.getTableHeader().setReorderingAllowed(false);
        tabJunior.setUpdateSelectionOnSort(false);
        tabJunior.setFont(new Font("Dialog", Font.BOLD, 16));
        tabJunior.setRowMargin(10);
        tabJunior.setRowHeight(40);
        tabJunior.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 16));


        //�P�����t���G����
//        resetResult();

        //�U�b
        pnlMenu.add(pnlSouth,BorderLayout.SOUTH);
        pnlSouth.setLayout(new GridLayout());
        FlowLayout flowLayout1 = new FlowLayout();
        flowLayout1.setAlignment(FlowLayout.LEFT);
        pnlSouth1.setLayout(flowLayout1);
        pnlSouth1.add(btnSetting);
        pnlSouth.add(pnlSouth1);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        pnlSouth2.setLayout(flowLayout);
        btnOK.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnNext.setFont(new Font("Dialog", Font.BOLD, 16));
        pnlSouth2.add(btnOK);
        pnlSouth2.add(btnBack);
        pnlSouth2.add(btnNext);
        pnlSouth.add(pnlSouth2);

        //����
        btnHome.addActionListener(new action());
        btnClass.addActionListener(new action());
        btnHistory.addActionListener(new action());
        btnScore.addActionListener(new action());
        btnTimeTable.addActionListener(new action());
        btnBack.addActionListener(new action());
        btnNext.addActionListener(new action());
        btnSetting.addActionListener(new action());
        btnOK.addActionListener(new action());

        //��l��
        click(current);

    }

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        Main frame = new Main();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    frame.saveFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }

    Map<String, ArrayList<String>> distribute(Map<String, Map<String, Integer>> form) {
        Map<String, ArrayList<String>> court = new HashMap<>();

        //example
//        for (int i=0; i<school.getBallKinds(); i++) {
//            ArrayList<String> temp = new ArrayList<>();
//            for (int j=0; j<school.getBall(i).getCourtNum(); j++)
//                temp.add(school.getClass(i).getName());
//            court.put(school.getBall(i).getName(), temp);
//        }

        //OP ����
        for (int i=0; i<school.getBallKinds(); i++) {
            ArrayList<String> temp = new ArrayList<>();
            for (int j=0; j<school.getBall(i).getCourtNum(); j++) {
                if (school.turn()) {
                    temp.add(school.getSenior()[school.getSeniorTurn()].getName());
                } else {
                    temp.add(school.getJunior()[school.getJuniorTurn()].getName());
                }
                court.put(school.getBall(i).getName(), temp);
            }
        }

        return court;
    }

    public void saveData() {
        Map<String, Map<String, Integer>> form = new HashMap<>();
        for (int i=0; i<school.getSenior().length; i++) {
            Map<String, Integer> temp = new HashMap<>();
            for (int j=0; j<school.getBallKinds(); j++) {
                temp.put(school.getBall(j).getName(), Integer.parseInt((String) tabSenior.getValueAt( i, j+1)));
            }
            form.put(school.getClass(i).getName(), temp);
        }
        for (int i=0; i<school.getJunior().length; i++) {
            Map<String, Integer> temp = new HashMap<>();
            for (int j=0; j<school.getBallKinds(); j++) {
                temp.put(school.getBall(j).getName(), Integer.parseInt((String) tabJunior.getValueAt( i, j+1)));
            }
            form.put(school.getClass(i + school.getSenior().length).getName(), temp);
        }

        school.pushHis(form, distribute(form));
    }

    void resetScore() {
        pnlScore.removeAll();

        pnlCenter.add(pnlScore,"score");
//        pnlScore.add(new LabelNotDone());

        ArrayList<String> cList = new ArrayList<>();
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        ArrayList<String> gList = new ArrayList<String>();
        ArrayList<String> bList = new ArrayList<String>();
        cList.add("�Z��");
        for (int i=0; i<school.getClassNum(); i++) {
            cList.add(school.getClass(i).getName());
        }
//        String[] s = cList.toArray(new String[cList.size()]);
//        for (String s1 : s)
//            System.out.println(s1);

        gList.add("�u�}�I��");
        for (int i=0; i<school.getClassNum(); i++) {
            gList.add(String.valueOf(school.getClass(i).getGoodPoint()));
        }
        list.add(gList);

        bList.add("�H�W�I��");
        for (int i=0; i<school.getClassNum(); i++) {
            bList.add(String.valueOf(school.getClass(i).getBadPoint()));
        }
        list.add(bList);

        String[][] ss = new String[list.size()][];
        for (int i=0; i<list.size(); i++) {
            ss[i] = list.get(i).toArray(new String[list.get(i).size()]);
        }

        JPanel[] pac=new JPanel[2];
        DefaultTableModel tableModel = new DefaultTableModel(ss, cList.toArray(new String[cList.size()]));
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setRowMargin(10);
        table.setRowHeight(50);
        table.getTableHeader().setFont(new Font("Dialog",Font.BOLD,16));
        table.setFont(new Font("Dialog",Font.BOLD,16));
        for (int i=0; i<school.getClassNum(); i++)
            table.setValueAt(String.valueOf(school.getClass(i).getGoodPoint()),0,i+1);
        for (int i=0; i<school.getClassNum(); i++)
            table.setValueAt(String.valueOf(school.getClass(i).getBadPoint()),1,i+1);


        JScrollPane scrollPane=new JScrollPane(table);
        JPanel[] pan=new JPanel[2];
        JButton[][] bb = new JButton[2][school.getClassNum()];

        pnlScore.setLayout(new GridLayout(2,1,0,10));
        // JLabel[] pnlFst=new JLabel[12];
        for(int i=0;i<2;i++)
        {
            pac[i]=new JPanel();
            pnlScore.add(pac[i]);
            pac[i].setLayout(new GridLayout(i+1,school.getClassNum()+1,10,0));
            //pnlFst[i]=new JLabel("       "+juniorData[i][0]);
            // pnlFst[i+6]=new JLabel("       "+seniorData[i][0]);
            pan[i]=new JPanel();
            pan[i].setLayout(new GridLayout(0,school.getClassNum()+1,0,10));
        }
        pac[0].add(scrollPane);
        pac[1].add(pan[0]);
        pac[1].add(pan[1]);
        JLabel lblAdd = new JLabel("�W�[�u�}�I��");
        JLabel lblSub = new JLabel("�W�[�H�W�I��");
        lblAdd.setFont(new Font("Dialog", Font.BOLD, 16));
        lblSub.setFont(new Font("Dialog", Font.BOLD, 16));
        pan[0].add(lblAdd);
        pan[1].add(lblSub);
        for(int i=0;i< school.getClassNum();i++)
        {
            // pac[0].add(pnlFst[i]);
            bb[0][i]=new JButton("+1");
            bb[1][i]=new JButton("-1");
            bb[0][i].setFont(new Font("Dialog",Font.BOLD,16));
            bb[1][i].setFont(new Font("Dialog",Font.BOLD,16));
//            for (int j=0; j<2; j++ ) {
//                int finalI = i;
//                int finalJ = j;
//                bb[j][i].addActionListener( new action());
//            }
            pan[0].add(bb[0][i]);
            pan[1].add(bb[1][i]);
        }

        for (int i=0; i<school.getClassNum(); i++) {
            int index = i;
            bb[0][i].addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    school.getClass(index).addGoodPoint();
                    table.setValueAt(school.getClass(index).getGoodPoint(),0, index+1);
                    click("score");
//                    System.out.println(school.getClass(i).getGoodPoint());
                }
            });
            bb[1][i].addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    school.getClass(index).addBadPoint();
                    table.setValueAt(school.getClass(index).getBadPoint(),1, index+1);
                    click("score");
                }
            });
        }

    }

    void resetSetting() {
        pnlSetting.removeAll();

        pnlCenter.add(pnlSetting,"setting");
        pnlSetting.setLayout(new GridLayout(2,1,70,10));
        pnlSetClass = new JPanel();
        pnlSetBall = new JPanel();
        pnlSetting.add(pnlSetClass);
        pnlSetClass.setLayout(new GridLayout(5,school.getClassNum()+1, 5, 25));
        pnlSetting.add(pnlSetBall);
        pnlSetBall.setLayout(new GridLayout(6, school.getBallKinds()+1, 50, 15));

        classField = new JTextField[school.getClassNum()];
        gPointField = new JTextField[school.getClassNum()];
        bPointField = new JTextField[school.getClassNum()];
        banField = new JTextField[school.getClassNum()];
        btnRemoveClass = new JButton[school.getClassNum()];
        btnRemoveBall = new JButton[school.getBallKinds()];

        String[]sBall={"�y���W��","�y�ƶq","�y���ƶq","�y��ƶq","�l���y���"};
        JLabel classTitle=new JLabel("�Z�ŦW��", JLabel.CENTER);
        classTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel gPointTitle=new JLabel("�u�}�I��", JLabel.CENTER);
        gPointTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel bPointTitle=new JLabel("�H�W�I��", JLabel.CENTER);
        bPointTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel banTitle=new JLabel("�O�_�T�y", JLabel.CENTER);
        banTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JButton btnAddClass=new JButton("�s�W�Z��");
        btnAddClass.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel ballNameTitle=new JLabel("�y���W��", JLabel.CENTER);
        ballNameTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel courtTitle=new JLabel("�y���ƶq", JLabel.CENTER);
        courtTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel ballTitle=new JLabel("�y���ƶq", JLabel.CENTER);
        ballTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel batTitle=new JLabel("�y��ƶq", JLabel.CENTER);
        batTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel damagedTitle=new JLabel("�w�l�y��ƶq", JLabel.CENTER);
        damagedTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JButton btnAddBall=new JButton("�s�W�y��");
        btnAddBall.setFont(new Font("Dialog", Font.BOLD, 16));
        pnlSetClass.add(classTitle);
        for(int i=0;i<school.getClassNum();i++)
        {
            classField[i]=new JTextField(school.getClass(i).getName());
            classField[i].setFont(new Font("Dialog", Font.BOLD, 16));
            pnlSetClass.add(classField[i]);
        }
        pnlSetClass.add(gPointTitle);
        for(int i=0;i<school.getClassNum();i++)
        {
            gPointField[i]=new JTextField(String.valueOf(school.getClass(i).getGoodPoint()));
            gPointField[i].setFont(new Font("Dialog", Font.BOLD, 16));
            pnlSetClass.add(gPointField[i]);
        }
        pnlSetClass.add(bPointTitle);
        for(int i=0;i<school.getClassNum();i++)
        {
            bPointField[i]=new JTextField(String.valueOf(school.getClass(i).getBadPoint()));
            bPointField[i].setFont(new Font("Dialog", Font.BOLD, 16));
            pnlSetClass.add(bPointField[i]);
        }
        pnlSetClass.add(banTitle);
        for(int i=0;i<school.getClassNum();i++)
        {
            banField[i]=new JTextField(school.getClass(i).getBanned());
            banField[i].setFont(new Font("Dialog", Font.BOLD, 16));
            banField[i].setEditable(false);
            pnlSetClass.add(banField[i]);
        }
        //�s�W�Z�ū���
        btnAddClass.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel pane  = new JPanel();
                pane.setLayout(new GridLayout(0,2,2,2));

                JTextField fieldName = new JTextField("���T��");
                JTextField fieldGood = new JTextField("0");
                JTextField fieldBad = new JTextField("0");

                pane.add(new JLabel("�Z�ŦW��"));
                pane.add(fieldName);
                pane.add(new JLabel("�u�}�I��"));
                pane.add(fieldGood);
                pane.add(new JLabel("�H�W�I��"));
                pane.add(fieldBad);

                int option = JOptionPane.showConfirmDialog(null,
                                                pane,
                                                "�إ߯Z��",
                                                JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    String name = fieldName.getText();
                    if (school.existClass(name)){
                        JOptionPane.showMessageDialog(null, "���Z�Ťw�s�b�A�L�k�إߤw�s�b���Z��!", "�q��", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int index = -1;
                        for (int i=school.getClassNum()-1; i>=0; i--) {
                            if (school.getClass(i).getGrade() == school.getGrade(name))
                                index = i;
                        }
                        if(index == -1)
                            school.addClass(new Class(name, Integer.parseInt(fieldGood.getText()), Integer.parseInt(fieldBad.getText())));
                        else
                            school.addClass(index, new Class(name, Integer.parseInt(fieldGood.getText()), Integer.parseInt(fieldBad.getText())));
                        resetSetting();

                        JOptionPane.showMessageDialog(null, "�w���\�إ߯Z�� "+name+" !", "�q��", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        pnlSetClass.add(btnAddClass);
        for(int i=0;i<school.getClassNum(); i++) {
            btnRemoveClass[i] = new JButton("�����Z��");
            btnRemoveClass[i].setFont(new Font("Dialog", Font.BOLD, 16));
            int index = i;
            btnRemoveClass[i].addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result=JOptionPane.showConfirmDialog(null,
                            "�T�w�n�����Z�� "+ school.getClass(index).getName() +" ��?",
                            "�T�{�T��",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (result==JOptionPane.YES_OPTION) {
                        school.removeClass(index);
                        resetSetting();
                    }

                }
            });
            pnlSetClass.add(btnRemoveClass[i]);
        }

        //String[][]sSetBall={{"�y���W��","0","0","0","0"}};
        ballNameField = new JTextField[school.getBallKinds()];
        courtField = new JTextField[school.getBallKinds()];
        ballField = new JTextField[school.getBallKinds()];
        batField = new JTextField[school.getBallKinds()];
        damagedField = new JTextField[school.getBallKinds()];
        pnlSetBall.add(ballNameTitle);
        for(int i=0; i<school.getBallKinds(); i++)
        {
            ballNameField[i]=new JTextField(school.getBall(i).getName());
            ballNameField[i].setFont(new Font("Dialog", Font.BOLD, 16));
            pnlSetBall.add(ballNameField[i]);
        }
        pnlSetBall.add(courtTitle);
        for(int i=0; i<school.getBallKinds(); i++)
        {
            courtField[i]=new JTextField(String.valueOf(school.getBall(i).getCourtNum()));
            courtField[i].setFont(new Font("Dialog", Font.BOLD, 16));
            pnlSetBall.add(courtField[i]);
        }
        pnlSetBall.add(ballTitle);
        for(int i=0; i<school.getBallKinds(); i++)
        {
            ballField[i]=new JTextField(String.valueOf(school.getBall(i).getBallNum()));
            ballField[i].setFont(new Font("Dialog", Font.BOLD, 16));
            pnlSetBall.add(ballField[i]);
        }
        pnlSetBall.add(batTitle);
        for(int i=0;i<school.getBallKinds();i++)
        {
            batField[i]=new JTextField(String.valueOf(school.getBall(i).getBatNum()));
            batField[i].setFont(new Font("Dialog", Font.BOLD, 16));
            pnlSetBall.add(batField[i]);
        }
        pnlSetBall.add(damagedTitle);
        for(int i=0;i<school.getBallKinds();i++)
        {
            damagedField[i]=new JTextField(String.valueOf(school.getBall(i).getBallDamaged()));
            damagedField[i].setFont(new Font("Dialog", Font.BOLD, 16));
            pnlSetBall.add(damagedField[i]);
        }
        btnAddBall.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel pane  = new JPanel();
                pane.setLayout(new GridLayout(0,2,2,2));

                JTextField fieldName = new JTextField("�x�y");
                JTextField fieldCourt = new JTextField("2");
                JTextField fieldBall = new JTextField("10");
                JTextField fieldBat = new JTextField("0");

                pane.add(new JLabel("�y���W��", JLabel.CENTER));
                pane.add(fieldName);
                pane.add(new JLabel("�y���ƶq", JLabel.CENTER));
                pane.add(fieldCourt);
                pane.add(new JLabel("�y�ƶq", JLabel.CENTER));
                pane.add(fieldBall);
                pane.add(new JLabel("�y��ƶq", JLabel.CENTER));
                pane.add(fieldBat);

                int option = JOptionPane.showConfirmDialog(null,
                        pane,
                        "�s�W�y��",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    String name = fieldName.getText();
                    if (school.existBall(name)){
                        JOptionPane.showMessageDialog(null, "���y���w�s�b�A�L�k�s�W�w�s�b���y��!", "�q��", JOptionPane.WARNING_MESSAGE);
                    } else {
                        school.addBall( new Ball(name, Integer.parseInt(fieldCourt.getText()), Integer.parseInt(fieldBall.getText()), Integer.parseInt(fieldBat.getText())));
                        resetSetting();

                        JOptionPane.showMessageDialog(null, "�w���\�s�W�y�� "+name+" !", "�q��", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        pnlSetBall.add(btnAddBall);
        for(int i=0;i<school.getBallKinds(); i++) {
            btnRemoveBall[i] = new JButton("�����y��");
            btnRemoveBall[i].setFont(new Font("Dialog", Font.BOLD, 16));
            int index = i;
            btnRemoveBall[i].addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result=JOptionPane.showConfirmDialog(null,
                            "�T�w�n���� "+ school.getBall(index).getName() +" ��?",
                            "�T�{�T��",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (result==JOptionPane.YES_OPTION) {
                        school.removeBall(index);
                        resetSetting();
                    }

                }
            });
            pnlSetBall.add(btnRemoveBall[i]);
        }

        click("setting");

    }

    public void init() throws IOException {
        if(f.exists()) {//���O�Ĥ@���n�J
//            System.out.println(gson.toJson(school));
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String s1 = "";
            String s2 = "";
            while ((s2 = reader.readLine()) != null) s1 += s2;
            reader.close();

            school = new Gson().fromJson(s1, School.class);
//            school = new Gson().fromJson(new FileReader(f), School.class);
        } else{//�Ĥ@���n�J
//            new Gson().toJson(new School(), new FileWriter(f));
            school=new School();
            saveFile();
            current = "setting";
        }

    }

    public void saveFile() throws IOException {
        String s = new Gson().toJson(school);
        OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(f));
        output.write(s);
        output.close();
    }

    public void resetButton () {
        btnNext.setEnabled(!next.empty());
        btnBack.setEnabled(!previous.empty());
        btnOK.setEnabled(Objects.equals(current, "weekend") || Objects.equals(current, "setting"));
//        showStack();//�ΥH��ܰ�n���G
    }

    public void resetResult() {
        pnlWeekendResult.removeAll();

        pnlCenter.add(pnlWeekendResult,"weekendResult");
        pnlWeekendResult.setLayout(new GridLayout(0,2));

        pnl = new JPanel[2][Math.max(school.getSenior().length, school.getJunior().length)];
        for (int i=0; i<school.getSenior().length; i++) {
            pnl[0][i] = new JPanel();
            pnl[0][i].setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
            JLabel lbl = new JLabel(school.getSenior()[i].getName());
            lbl.setOpaque(true);
            lbl.setBackground(Color.BLACK);
            lbl.setForeground(Color.yellow);
            lbl.setFont(new Font("Dialog",Font.BOLD,25));
            pnl[0][i].add(lbl);
            pnlWeekendResult.add(pnl[0][i]);
        }

        for (int i=0; i<school.getJunior().length; i++) {
            pnl[1][i] = new JPanel();
            pnl[1][i].setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
            JLabel lbl = new JLabel(school.getJunior()[i].getName());
            lbl.setOpaque(true);
            lbl.setBackground(Color.BLACK);
            lbl.setForeground(Color.YELLOW);
            lbl.setFont(new Font("Dialog",Font.BOLD,25));
            pnl[1][i].add(lbl);
            pnlWeekendResult.add(pnl[1][i]);
        }

        for (int i=0; i<school.getBallKinds();i++) {
            for (int j=0; j<school.getBall(i).getCourtNum(); j++) {
                String cls = school.getHisCourt(school.getBall(i).getName(),j);
                int grade = school.getClass(cls).isJunior() ? 1:0;
                if (grade == 0) {
                    JLabel lbl  = new JLabel(school.getBall(i).getName()+String.valueOf(j+1));
                    lbl.setFont(new Font("Dialog",Font.BOLD,25));
                    pnl[grade][school.getSeniorIndex(cls)].add(lbl);
                } else {
                    JLabel lbl = new JLabel(school.getBall(i).getName()+String.valueOf(j+1));
                    lbl.setFont(new Font("Dialog",Font.BOLD,25));
                    pnl[grade][school.getJuniorIndex(cls)].add(lbl);
                }
            }
        }


    }

    String getTitle(String str) {
        switch (str) {
            case "weekend" -> { return "�P����U�Z�y����g���@"; }
            case "weekendResult" -> { return "�P����y�����t���G"; }
            case "class" -> { return "�U�Z���"; }
            case "history" -> { return "���v����"; }
            case "score" -> { return "�U�Z�u���I��"; }
            case "setting" -> { return "�]�w"; }
            case "timeTable" -> { return "�ɶ���"; }

            default -> throw new IllegalStateException("Unexpected value: " + str);
        }
    }

    void updateSetting() {
        //�Z�Ÿ�Ƨ�s
        for(int i=0; i< school.getClassNum(); i++)
            school.getClass(i).setClassName(classField[i].getText());
        for(int i=0; i< school.getClassNum(); i++)
            school.getClass(i).setGoodPoint(Integer.parseInt(gPointField[i].getText()));
        for(int i=0; i< school.getClassNum(); i++)
            school.getClass(i).setBadPoint(Integer.parseInt(bPointField[i].getText()));
//                        for(int i=0; i< school.getClassNum(); i++)
//                            school.getClass(i)(classField[i].getText());
        //�y����Ƨ�s
        int num = school.getBallKinds();
        school.clearBall();
        for (int i=0; i<num; i++) {
            school.addBall(new Ball(
                    ballNameField[i].getText(),
                    Integer.parseInt(courtField[i].getText()),
                    Integer.parseInt(ballField[i].getText()),
                    Integer.parseInt(batField[i].getText()),
                    Integer.parseInt(damagedField[i].getText())));
        }
        try {
            saveFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        JOptionPane.showMessageDialog(null,
                "��Ƥw��s�A�W�Ǧܸ�Ʈw",
                "���ɤp�q��",
                JOptionPane.INFORMATION_MESSAGE);
    }

    void click(String str) {
        previous.push(current);
        current = str;
        next.clear();
        card.show(pnlCenter,current);
        lblTitle.setText(getTitle(current));
//        lblTitle.setSize(400, 60);
        resetButton();
    }

    class action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean b = true;
            if(b) {
                if (btnHome==e.getSource()) {
                    click("weekend");
                } else if(e.getSource()==btnOK){
                    if(Objects.equals(current, "weekend")) {
                        saveData();
//                        distribute();
                        resetResult();
                        click("weekendResult");
                    } else if(current.equals("setting")) {
                        updateSetting();
                    }
                } else if(e.getSource()==btnBack){
                    next.push(current);
                    current = previous.pop();
                    card.show(pnlCenter,current);
                    lblTitle.setText(getTitle(current));
                    resetButton();
                } else if (e.getSource()==btnNext) {
                    previous.push(current);
                    current = next.pop();
                    card.show(pnlCenter,current);
                    lblTitle.setText(getTitle(current));
                    resetButton();
                } else if(e.getSource()==btnClass){
                    click("class");
                } else if(e.getSource()==btnHistory){
                    click("history");
                } else if(e.getSource()==btnScore){
                    resetScore();
                    click("score");
                } else if(e.getSource()==btnSetting){
                    resetSetting();
                    click("setting");
                } else if(e.getSource()==btnTimeTable){
                    click("timeTable");
//            } else if() {
//
                } else {
                    throw new IllegalStateException("Unexpected value: " + e.getSource());
                }
            }

        }
    }

    void showStack() { //�˵��ΡA��ܭ�����n
        System.out.println("next:");
        for (String s : next) System.out.println(s);
        System.out.println("previous:");
        for (String s : previous) System.out.println(s);
        System.out.println();
    }

    static class LabelNotDone extends JLabel{
        LabelNotDone( ){
            this.setBackground(Color.RED);
            this.setFont(new Font("Dialog",Font.BOLD,100));
            this.setText(" �������|�������I �Ш���");

        }
    }
}
