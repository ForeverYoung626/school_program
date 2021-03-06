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
    String current;

    String searchMonth;
    String searchDay;

    //位置樣板
    JPanel pnlMenu = new JPanel();
    JPanel pnlNorth = new JPanel();
    JPanel pnlNW = new JPanel();
    JPanel pnlNE = new JPanel();
    JPanel pnlNE1 = new JPanel();
    JPanel pnlDate = new JPanel();
    JPanel pnlFunction = new JPanel();
    JPanel pnlCenter = new JPanel();
    //    JPanel pnlClass = new JPanel();
    JPanel pnlSouth = new JPanel();
    JPanel pnlSouth1 = new JPanel();
    JPanel pnlSouth2 = new JPanel();

    //內容樣板
    JPanel pnlInti = new JPanel();//初始設定（第一次使用）
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
    JLabel lblSearch = new JLabel("查看: ");
    JLabel lblTime = new JLabel();
    JLabel lblSenior = new JLabel("高中");
    JLabel lblJunior = new JLabel("國中");
    JPanel lbl[][];

    JButton btnHome = new JButton("主頁面");
    JButton btnHistory = new JButton("歷史紀錄");
    JButton btnClass = new JButton("班級");
    //    JButton btnClass = new JButton("點數");
    JButton btnTimeTable = new JButton("時間表");
    JButton btnSetting = new JButton("設定");
    JButton btnOK = new JButton("確定");
    JButton btnBack = new JButton("返回");
    JButton btnNext =new JButton("下一頁");
    JButton btnAnnuallyUpdate = new JButton("自動更新新學年");
    JButton btnHisSearch = new  JButton("搜尋");
    JButton btnHisBack = new JButton("上一次");
    JButton btnHisNext = new JButton("下一次");
    JButton[] btnRemoveClass;
    JButton[] btnRemoveBall;



    JTextField[] classField;
    JTextField[] gPointField;
    JTextField[] bPointField;
    JTextField[] banField;
    JButton[] btnUnban;

    JTextField[] ballNameField;
    JTextField[] courtField;
    JTextField[] ballField;
    JTextField[] batField;
    JTextField[] damagedField;
    JTextField[][] timeTablet = new JTextField[7][9];

    String [] ballName;

//    String [][] juniorData={
//            {"國三忠","1","2","3"},
//            {"國三孝","1","2","3"},
//            {"國二忠","1","2","3"},
//            {"國二孝","1","2","3"},
//            {"國一忠","1","2","3"},
//            {"國一孝","1","2","3"}
//    };
//
//    String [][] seniorData={
//            {"高三忠","1","2","3"},
//            {"高三孝","1","2","3"},
//            {"高二忠","1","2","3"},
//            {"高二孝","1","2","3"},
//            {"高一忠","1","2","3"},
//            {"高一孝","1","2","3"},
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

    String[] stringHisYear={"前年","去年","今年"};
    String[] stringHisMon={"1","2","3","4","5","6","7","8","9","10","11","12"};
    String[] stringsHisDay={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    JComboBox boxHisYear = new JComboBox(stringHisYear);
    JComboBox boxHisMon = new JComboBox(stringHisMon);
    JComboBox boxHisDay = new JComboBox(stringsHisDay);

    Color themeColor = Color.cyan;

    Main() throws FileNotFoundException {
        //建立系統
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //視窗
        this.setSize(width,height);
        this.setLocation(x,y);
        this.setTitle("學務處借球登記　超OP Fy(By) 兩本書&一只羊");
        BorderLayout thisLayout = new BorderLayout(50,60);
        this.setLayout(thisLayout);

        //主樣板
        this.add(pnlMenu,BorderLayout.CENTER);
        pnlMenu.setSize(600,1000);
//        GridLayout layMenu = new GridLayout(5,10);
//        pnlMenu.setLayout(layMenu);
        pnlMenu.setLayout(new BorderLayout());
//        pnlMenu.setSize(1400,800);
        pnlMenu.setOpaque(true);
        pnlMenu.setBackground(themeColor);

        //上半
        pnlMenu.add(pnlNorth,BorderLayout.NORTH);
        pnlNorth.setAlignmentY(40);
        pnlNorth.setLayout(new GridLayout());
        pnlNorth.setOpaque(false);
//        pnlNorth.setBackground(themeColor);

        pnlNorth.add(pnlNW);
        pnlNW.setLayout(new FlowLayout());
        pnlNW.setOpaque(true);
        pnlNW.setBackground(themeColor);
        pnlNW.add(lblTitle);
        pnlNW.setOpaque(true);
        pnlNW.setBackground(themeColor);
        lblTitle.setOpaque(false);
        lblTitle.setBackground(themeColor);
        lblTitle.setFont(new Font("Dialog",Font.BOLD,55));
        pnlNorth.add(pnlNE);
        pnlNE.setLayout(new GridLayout(2,1));
        pnlNE.setOpaque(true);
        pnlNE.setBackground(themeColor);
        pnlNE.add(pnlNE1);
        pnlNE1.setOpaque(true);
        pnlNE1.setBackground(themeColor);
        pnlNE1.setLayout(new GridLayout());
        pnlNE1.setOpaque(false);

        //日期
        lblTime.setOpaque(false);
        lblTime.setText(school.getTime());
        lblTime.setFont(new Font("Dialog", Font.BOLD, 16));
        pnlDate.setOpaque(false);
        pnlDate.add(lblTime);
        pnlNE1.add(pnlDate);

        //功能表
        btnHome.setFont(new Font("Dialog", Font.BOLD, 16));
        lblSearch.setFont(new Font("Dialog", Font.BOLD, 16));
        btnHistory.setFont(new Font("Dialog", Font.BOLD, 16));
        btnClass.setFont(new Font("Dialog", Font.BOLD, 16));
//        btnClass.setFont(new Font("Dialog", Font.BOLD, 16));
        btnTimeTable.setFont(new Font("Dialog", Font.BOLD, 16));

        pnlFunction.setLayout(new FlowLayout());
        pnlFunction.setOpaque(false);
//        pnlFunction.setBackground(themeColor);
        pnlFunction.add(btnHome);
        pnlFunction.add(lblSearch);
        pnlFunction.add(btnHistory);
        pnlFunction.add(btnClass);
//        pnlFunction.add(btnClass);
        pnlFunction.add(btnTimeTable);
        pnlNE.add(pnlFunction);

        //內容

        pnlMenu.add(pnlCenter,BorderLayout.CENTER);
//        pnlCenter.setLayout(new GridLayout());
        pnlCenter.setLayout(card);
        pnlCenter.setOpaque(true);
        pnlCenter.setBackground(themeColor);

        //班級介面
//        pnlCenter.add(pnlClass,"class");
//        pnlClass.add(new LabelNotDone());

        //歷史紀錄介面
        pnlCenter.add(pnlHistory,"history");
        BorderLayout borderLayout = new BorderLayout();
        pnlHistory.setLayout(borderLayout);
        pnlHistory.setOpaque(false);
        pnlHistory.setBackground(themeColor);
        JPanel pnlHisUp = new JPanel();
        searchDay = school.getDate();
        searchMonth = school.getMonth();
        pnlHisUp.setLayout(new GridLayout(1,2));
        pnlHisUp.setOpaque(false);
//        pnlHisUp.setBackground(themeColor);
        JLabel labHisDays = new JLabel(searchMonth+ " 月 "+searchDay+" 日");
        labHisDays.setFont(new Font("Dialog", Font.BOLD, 55));
//        labHisDays.setHorizontalTextPosition(Label.CENTER);
        JPanel pnlSearch = new JPanel();
        pnlSearch.setLayout(new GridLayout(1,8));
        pnlSearch.setOpaque(false);
        pnlHistory.add(pnlHisUp, BorderLayout.NORTH);
        pnlHisUp.add(labHisDays);
        pnlHisUp.add(pnlSearch);
        JLabel labHisMon = new JLabel("月");
        JLabel labHisDay = new  JLabel("日");

        pnlSearch.add(boxHisYear);
        pnlSearch.add(boxHisMon);
        pnlSearch.add(labHisMon);
        pnlSearch.add(boxHisDay);
        pnlSearch.add(labHisDay);
        pnlSearch.add(btnHisSearch);
        pnlSearch.add(btnHisBack);
        pnlSearch.add(btnHisNext);

        //歷史記錄下半面
        JPanel pnlHisDown = new JPanel();
        pnlHisDown.setLayout(new GridLayout(school.getClassNum()/2,4));
        pnlHisDown.setOpaque(false);
        JLabel[][] labHisResult = new JLabel[school.getClassNum()/2][4];
        for (int i=0;i<school.getClassNum()/2;i++){
            labHisResult[i][0] = new JLabel(school.getClass(i).getName());
            labHisResult[i][1] = new JLabel(""+"");
            labHisResult[i][2] = new JLabel(school.getClass(i+1).getName());
            labHisResult[i][3] = new JLabel(""+"");
            labHisResult[i][0].setFont(new Font("微軟正黑體",Font.BOLD,50));
            labHisResult[i][1].setFont(new Font("微軟正黑體",Font.BOLD,50));
            labHisResult[i][2].setFont(new Font("微軟正黑體",Font.BOLD,50));
            labHisResult[i][3].setFont(new Font("微軟正黑體",Font.BOLD,50));
            pnlHisDown.add(labHisResult[i][0]);
            pnlHisDown.add(labHisResult[i][1]);
            pnlHisDown.add(labHisResult[i][2]);
            pnlHisDown.add(labHisResult[i][3]);

        }
        pnlHistory.add(pnlHisDown);


        //點數介面

        //設定介面
        resetSetting();

        //時間表界面
        pnlCenter.add(pnlTimeTable,"timeTable");
        //pnlTimeTable.add(new LabelNotDone());
        pnlTimeTable.setLayout(new GridLayout(10,8, 5, 10));
        pnlTimeTable.setOpaque(false);
        pnlTimeTable.add(new JLabel(""));
        JLabel[] num=new JLabel[9];
        for(int j=1;j<=7;j++) {
            String str = "";
            switch (j){
                case 7->{str="星期日";}
                case 1->{str="星期一";}
                case 2->{str="星期二";}
                case 3->{str="星期三";}
                case 4->{str="星期四";}
                case 5->{str="星期五";}
                case 6->{str="星期六";}
            }
            JLabel lbl = new JLabel(str, JLabel.CENTER);
            lbl.setFont(new Font("Dialog",Font.BOLD,20));
            pnlTimeTable.add(lbl);
        }
        for(int i=0; i<9; i++) {
            num[i]=new JLabel("第 "+String.valueOf(i+1)+" 節", JLabel.CENTER);
//            System.out.println(num[i].getFont());
            num[i].setFont(new Font( "Dialog",Font.BOLD,20));
            pnlTimeTable.add(num[i]);
            for(int j=0; j<7; j++) {
                timeTablet[j][i] = new JTextField(school.getTablet(j, i));
                timeTablet[j][i].setFont(new Font("Dialog",Font.BOLD,20));
                pnlTimeTable.add(timeTablet[j][i]);
            }
        }

        //初始界面 (已并入?定界面)
//        pnlCenter.add(pnlInti,"Init");
//        pnlInti.add(new LabelNotDone());

//        pnlCenter.add(lblNotDone,"repaired");

        //周末借球填寫順序介面
        pnlCenter.add(pnlWeekend,"weekend");
        pnlWeekend.setLayout(new GridLayout(2,1));
        pnlWeekend.setOpaque(true);
        pnlWeekend.add(pnlSenior);
        pnlWeekend.add(pnlJunior);

        ballName = new String[school.getBallKinds()+1];
        ballName[0] = "班級";
        for (int i=0; i< school.getBallKinds(); i++)
            ballName[i+1] = school.getBall(i).getName();

        String[][] sSenior = new String[school.getSenior().length][school.getBallKinds()+1];
        for (int i=0; i<school.getSenior().length; i++) {
            sSenior[i][0] = school.getSenior()[i].getName();
            for (int j=1; j<=school.getBallKinds(); j++)
                sSenior[i][j] = String.valueOf(j);
        }
        tabSenior = new JTable( sSenior, ballName);
        tabSenior.setOpaque(true);
        tabSenior.setBackground(themeColor);
        scrollPane1 = new JScrollPane(tabSenior);
        scrollPane1.setOpaque(true);
        pnlSenior.add(scrollPane1);

        String[][] sJunior = new String[school.getJunior().length][4];
        for (int i=0; i<school.getJunior().length; i++) {
            sJunior[i][0] = school.getJunior()[i].getName();
            for (int j=1; j<=3; j++)
                sJunior[i][j] = String.valueOf(j);
        }
        tabJunior = new JTable( sJunior, ballName);
        tabJunior.setOpaque(true);
        tabJunior.setBackground(themeColor);
        scrollPane2 = new JScrollPane(tabJunior);
        scrollPane2.setOpaque(true);
        pnlJunior.add(scrollPane2);

        pnlSenior.setLayout(new GridLayout());
        pnlSenior.setOpaque(true);
        tabSenior.getTableHeader().setReorderingAllowed(false);
        tabSenior.setUpdateSelectionOnSort(false);
        tabSenior.setFont(new Font("Dialog", Font.BOLD, 16));
        tabSenior.setRowMargin(10);
        tabSenior.setRowHeight(40);
        tabSenior.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 16));

        pnlJunior.setLayout(new GridLayout());
        pnlJunior.setOpaque(true);
        tabJunior.getTableHeader().setReorderingAllowed(false);
        tabJunior.setUpdateSelectionOnSort(false);
        tabJunior.setFont(new Font("Dialog", Font.BOLD, 16));
        tabJunior.setOpaque(true);
        tabJunior.setRowMargin(10);
        tabJunior.setRowHeight(40);
        tabJunior.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 16));


        //周末分配結果介面
//        resetResult();

        //下半
        pnlMenu.add(pnlSouth,BorderLayout.SOUTH);
        pnlSouth.setLayout(new GridLayout());
        pnlSouth.setOpaque(false);
        FlowLayout flowLayout1 = new FlowLayout();
        flowLayout1.setAlignment(FlowLayout.LEFT);
        pnlSouth1.setLayout(flowLayout1);
        pnlSouth1.setOpaque(false);
        btnSetting.setFont(new Font("Dialog", Font.BOLD, 16));
        pnlSouth1.add(btnSetting);
        pnlSouth.add(pnlSouth1);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        pnlSouth2.setLayout(flowLayout);
        pnlSouth2.setOpaque(false);
        btnOK.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnNext.setFont(new Font("Dialog", Font.BOLD, 16));
        pnlSouth2.add(btnOK);
        pnlSouth2.add(btnBack);
        pnlSouth2.add(btnNext);
        pnlSouth.add(pnlSouth2);

//        JPasswordField pass = new JPasswordField(10);
//        pnlSouth2.add(pass);
//        JButton btn = new JButton("OK");
//        btn.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(String.valueOf(pass.getPassword()));
//            }
//        });
//        pnlSouth2.add(btn);

        //按紐
        btnHome.addActionListener(new action());
        btnClass.addActionListener(new action());
        btnHistory.addActionListener(new action());
//        btnClass.addActionListener(new action());
        btnTimeTable.addActionListener(new action());
        btnBack.addActionListener(new action());
        btnNext.addActionListener(new action());
        btnSetting.addActionListener(new action());
        btnOK.addActionListener(new action());

        //初始化
        current = "weekend";
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

    Map<String, ArrayList<String>> distribute(Map<String, Map<String, Integer>> form) {// form 的型態為 <班級名稱 <球類名稱, 填選志願>>
        Map<String, ArrayList<String>> court = new HashMap<>(); // court 的型態為 <球類名稱, 班級[]>

        ArrayList<String> line = new ArrayList<String>(); // 儲存填寫志願順序相同的班級
//        for( Object s : form.keySet()) {
//            if ()
//        }

        //OP 分類
        for (int i=0; i<school.getBallKinds(); i++) {
            ArrayList<String> temp = new ArrayList<>();
            for (int j=0; j<school.getBall(i).getCourtNum(); j++) {
                again:

                if (school.turn()) { //輪到高中
                    int n = school.getSeniorTurn();
                    if (school.getSenior()[n].isBanned()) {
                        j--;
                        break again;
                    } else if (form.get(school.getSenior()[n]).get(school.getBall(i).getName()) == 1)
                        temp.add(school.getSenior()[n].getName());
                } else { //輪到國中
                    int n = school.getJuniorTurn();
                    if (school.getJunior()[n].isBanned()) {
                        j--;
                        break again;
                    } else {
                        temp.add(school.getJunior()[n].getName());
                    }
                }

//                System.out.println(temp.size());
                court.put(school.getBall(i).getName(), temp);
            }
        }

        return court;
    }

    public void saveData() {
        Map<String, Map<String, Integer>> form = new HashMap<>(); // form 的型態為 <班級名稱 <球類名稱, 填選志願>>
        for (int i=0; i<school.getSenior().length; i++) { // 將高中資料加入form
            Map<String, Integer> temp = new HashMap<>();
            for (int j=0; j<school.getBallKinds(); j++) {
                temp.put(school.getBall(j).getName(), Integer.parseInt((String) tabSenior.getValueAt( i, j+1)));
            }
            form.put(school.getClass(i).getName(), temp);
        }
        for (int i=0; i<school.getJunior().length; i++) { // 將國中資料加入form
            Map<String, Integer> temp = new HashMap<>();
            for (int j=0; j<school.getBallKinds(); j++) {
                temp.put(school.getBall(j).getName(), Integer.parseInt((String) tabJunior.getValueAt( i, j+1)));
            }
            form.put(school.getClass(i + school.getSenior().length).getName(), temp);
        }

        school.pushHis(form, distribute(form));
    }

    void resetScore() {
        pnlClass.removeAll();

        pnlCenter.add(pnlClass,"class");
//        pnlClass.add(new LabelNotDone());

        ArrayList<String> cList = new ArrayList<>();
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        cList.add("班級");
        for (int i=0; i<school.getClassNum(); i++) {
            cList.add(school.getClass(i).getName());
        }
//        String[] s = cList.toArray(new String[cList.size()]);
//        for (String s1 : s)
//            System.out.println(s1);

        ArrayList<String> gList = new ArrayList<String>();
        gList.add("優良點數");
        for (int i=0; i<school.getClassNum(); i++) {
            gList.add(String.valueOf(school.getClass(i).getGoodPoint()));
        }
        list.add(gList);

        ArrayList<String> bList = new ArrayList<String>();
        bList.add("違規點數");
        for (int i=0; i<school.getClassNum(); i++) {
            bList.add(String.valueOf(school.getClass(i).getBadPoint()));
        }
        list.add(bList);

        ArrayList<String> banList = new ArrayList<String>();
        banList.add("禁球狀態");
        for (int i=0; i<school.getClassNum(); i++) {
            banList.add(school.getClass(i).isBanned()? "禁球至"+school.getDate(school.getClass(i).getBanTime()) : "開放借球" );
        }
        list.add(banList);

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

        pnlClass.setLayout(new GridLayout(2,1,0,10));
        pnlClass.setOpaque(false);
        // JLabel[] pnlFst=new JLabel[12];
        for(int i=0;i<2;i++)
        {
            pac[i]=new JPanel();
            pnlClass.add(pac[i]);
            pac[i].setLayout(new GridLayout(i+1,school.getClassNum()+1,10,0));
            pac[i].setOpaque(false);
            //pnlFst[i]=new JLabel("       "+juniorData[i][0]);
            // pnlFst[i+6]=new JLabel("       "+seniorData[i][0]);
            pan[i]=new JPanel();
            pan[i].setLayout(new GridLayout(0,school.getClassNum()+1,0,10));
            pan[i].setOpaque(false);
        }
        pac[0].add(scrollPane);
        pac[1].add(pan[0]);
        pac[1].add(pan[1]);
        JLabel lblAdd = new JLabel("增加優良點數");
        JLabel lblSub = new JLabel("增加違規點數");
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
        pnlSetting.setOpaque(false);
        pnlSetClass = new JPanel();
        pnlSetBall = new JPanel();
        pnlSetting.add(pnlSetClass);
        pnlSetClass.setLayout(new GridLayout(6,school.getClassNum()+1, 5, 25));
        pnlSetClass.setOpaque(false);
        pnlSetting.add(pnlSetBall);
        pnlSetBall.setLayout(new GridLayout(6, school.getBallKinds()+1, 50, 15));
        pnlSetBall.setOpaque(false);

        classField = new JTextField[school.getClassNum()];
        gPointField = new JTextField[school.getClassNum()];
        bPointField = new JTextField[school.getClassNum()];
        banField = new JTextField[school.getClassNum()];
        btnUnban = new JButton[school.getClassNum()];
        btnRemoveClass = new JButton[school.getClassNum()];
        btnRemoveBall = new JButton[school.getBallKinds()];

        String[]sBall={"球類名稱","球數量","球場數量","球拍數量","損毀球拍數"};
        JLabel classTitle=new JLabel("班級名稱", JLabel.CENTER);
        classTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel gPointTitle=new JLabel("優良點數", JLabel.CENTER);
        gPointTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel bPointTitle=new JLabel("違規點數", JLabel.CENTER);
        bPointTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel banTitle=new JLabel("禁球狀態", JLabel.CENTER);
        banTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel unbanTitle = new JLabel("禁 / 解禁", JLabel.CENTER);
        unbanTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JButton btnAddClass=new JButton("新增班級");
        btnAddClass.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel ballNameTitle=new JLabel("球類名稱", JLabel.CENTER);
        ballNameTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel courtTitle=new JLabel("球場數量", JLabel.CENTER);
        courtTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel ballTitle=new JLabel("球的數量", JLabel.CENTER);
        ballTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel batTitle=new JLabel("球拍數量", JLabel.CENTER);
        batTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JLabel damagedTitle=new JLabel("已損球具數量", JLabel.CENTER);
        damagedTitle.setFont(new Font("Dialog", Font.BOLD, 16));
        JButton btnAddBall=new JButton("新增球類");
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
        pnlSetClass.add(unbanTitle);
        for(int i=0;i<school.getClassNum();i++)
        {
            String str;
            btnUnban[i]=new JButton(school.getClass(i).isBanned()? "解除禁球" : "禁球" );
            btnUnban[i].setFont(new Font("Dialog", Font.BOLD, 16));
            int finalI = i;
            btnUnban[i].addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (school.getClass(finalI).isBanned()){
                        int option = JOptionPane.showConfirmDialog(null,
                                "確定要解除 "+ school.getClass(finalI).getName() +" 的禁球狀態嗎?",
                                "確認通知",
                                JOptionPane.YES_OPTION
                        );

                        if(option == JOptionPane.YES_OPTION) {
                            school.getClass(finalI).unban();
                        }
                    } else {
                        int option = JOptionPane.showConfirmDialog(null,
                                "確定要將 " + school.getClass(finalI).getName() + " 禁球嗎?",
                                "確認通知",
                                JOptionPane.YES_OPTION
                        );

                        if (option == JOptionPane.YES_OPTION) {
                            school.getClass(finalI).ban(7);
                        }
                    }
                    resetSetting();
                }
            });
            pnlSetClass.add(btnUnban[i]);
        }
        //新增班級按紐
        btnAddClass.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel pane  = new JPanel();
                pane.setLayout(new GridLayout(0,2,2,2));
                pane.setOpaque(true);
                pane.setBackground(themeColor);

                JTextField fieldName = new JTextField("高三忠");
                JTextField fieldGood = new JTextField("0");
                JTextField fieldBad = new JTextField("0");

                pane.add(new JLabel("班級名稱"));
                pane.add(fieldName);
                pane.add(new JLabel("優良點數"));
                pane.add(fieldGood);
                pane.add(new JLabel("違規點數"));
                pane.add(fieldBad);

                int option = JOptionPane.showConfirmDialog(null,
                        pane,
                        "建立班級",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    String name = fieldName.getText();
                    if (school.existClass(name)){
                        JOptionPane.showMessageDialog(null, "此班級已存在，無法建立已存在的班級!", "通知", JOptionPane.WARNING_MESSAGE);
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

                        JOptionPane.showMessageDialog(null, "已成功建立班級 "+name+" !", "通知", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        pnlSetClass.add(btnAddClass);
        for(int i=0;i<school.getClassNum(); i++) {
            btnRemoveClass[i] = new JButton("移除班級");
            btnRemoveClass[i].setFont(new Font("Dialog", Font.BOLD, 16));
            int index = i;
            btnRemoveClass[i].addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result=JOptionPane.showConfirmDialog(null,
                            "確定要移除班級 "+ school.getClass(index).getName() +" 嗎?",
                            "確認訊息",
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

        //String[][]sSetBall={{"球類名稱","0","0","0","0"}};
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
                pane.setOpaque(true);
                pane.setBackground(themeColor);

                JTextField fieldName = new JTextField("籃球");
                JTextField fieldCourt = new JTextField("2");
                JTextField fieldBall = new JTextField("10");
                JTextField fieldBat = new JTextField("0");

                pane.add(new JLabel("球類名稱", JLabel.CENTER));
                pane.add(fieldName);
                pane.add(new JLabel("球場數量", JLabel.CENTER));
                pane.add(fieldCourt);
                pane.add(new JLabel("球數量", JLabel.CENTER));
                pane.add(fieldBall);
                pane.add(new JLabel("球拍數量", JLabel.CENTER));
                pane.add(fieldBat);

                int option = JOptionPane.showConfirmDialog(null,
                        pane,
                        "新增球類",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    String name = fieldName.getText();
                    if (school.existBall(name)){
                        JOptionPane.showMessageDialog(null, "此球類已存在，無法新增已存在的球類!", "通知", JOptionPane.WARNING_MESSAGE);
                    } else {
                        school.addBall( new Ball(name, Integer.parseInt(fieldCourt.getText()), Integer.parseInt(fieldBall.getText()), Integer.parseInt(fieldBat.getText())));
                        resetSetting();

                        JOptionPane.showMessageDialog(null, "已成功新增球類 "+name+" !", "通知", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        pnlSetBall.add(btnAddBall);
        for(int i=0;i<school.getBallKinds(); i++) {
            btnRemoveBall[i] = new JButton("移除球類");
            btnRemoveBall[i].setFont(new Font("Dialog", Font.BOLD, 16));
            int index = i;
            btnRemoveBall[i].addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result=JOptionPane.showConfirmDialog(null,
                            "確定要移除 "+ school.getBall(index).getName() +" 嗎?",
                            "確認訊息",
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
        if(f.exists()) {//不是第一次登入
//            System.out.println(gson.toJson(school));
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String s1 = "";
            String s2 = "";
            while ((s2 = reader.readLine()) != null) s1 += s2;
            reader.close();
            current = "weekend";

            school = new Gson().fromJson(s1, School.class);
//            school = new Gson().fromJson(new FileReader(f), School.class);
        } else{//第一次登入
//            new Gson().toJson(new School(), new FileWriter(f));
            school=new School();
            String pass = JOptionPane.showInputDialog(null, "這是您第一次登入，請設定管理員密碼 :", JOptionPane.PLAIN_MESSAGE);
            school.setPassword(pass);
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
        btnOK.setEnabled(Objects.equals(current, "weekend") || Objects.equals(current, "setting") || current == "timeTable");
//        showStack();//用以顯示堆積結果
    }

    public void resetResult() {
        pnlWeekendResult.removeAll();

        pnlCenter.add(pnlWeekendResult,"weekendResult");
        pnlWeekendResult.setLayout(new GridLayout(0,2));
        pnlWeekendResult.setOpaque(false);

        pnl = new JPanel[2][Math.max(school.getSenior().length, school.getJunior().length)];
        for (int i=0; i<school.getSenior().length; i++) {
            pnl[0][i] = new JPanel();
            pnl[0][i].setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
            pnl[0][i].setOpaque(false);
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
            pnl[1][i].setOpaque(false);
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
//                System.out.println("i = "+i+", j = "+j+", ballName = "+school.getBall(i).getName());
                String cls = school.getHisCourt(school.getBall(i).getName(),j);
                int grade = school.getClass(cls).isJunior() ? 1:0;
                JLabel lbl  = new JLabel(school.getBall(i).getName()+String.valueOf(j+1));
                lbl.setFont(new Font("Dialog",Font.BOLD,25));
                if (grade == 0) {
                    pnl[grade][school.getSeniorIndex(cls)].add(lbl);
                } else {
                    pnl[grade][school.getJuniorIndex(cls)].add(lbl);
                }
            }
        }


    }

    String getTitle(String str) {
        switch (str) {
            case "weekend" -> { return "星期日各班球場填寫志願"; }
            case "weekendResult" -> { return "星期日球場分配結果"; }
            case "class" -> { return "各班資料"; }
            case "history" -> { return "歷史紀錄"; }
            case "score" -> { return "各班優缺點數"; }
            case "setting" -> { return "設定"; }
            case "timeTable" -> { return "時間表"; }

            default -> throw new IllegalStateException("Unexpected value: " + str);
        }
    }

    void updateSetting() {
        //班級資料更新
        for(int i=0; i< school.getClassNum(); i++)
            school.getClass(i).setClassName(classField[i].getText());
        for(int i=0; i< school.getClassNum(); i++)
            school.getClass(i).setGoodPoint(Integer.parseInt(gPointField[i].getText()));
        for(int i=0; i< school.getClassNum(); i++)
            school.getClass(i).setBadPoint(Integer.parseInt(bPointField[i].getText()));
//                        for(int i=0; i< school.getClassNum(); i++)
//                            school.getClass(i)(classField[i].getText());
        //球類資料更新
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
                "資料已更新，上傳至資料庫",
                "溫馨小通知",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateTable() {
        for (int i=0; i<7; i++) {
            for (int j=0; j<9; j++) {
                school.updateTablet( i, j, timeTablet[i][j].getText());
            }
        }

        JOptionPane.showMessageDialog(null,
                "資料已更新，上傳至資料庫",
                "溫馨小通知",
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
                        resetSetting();
                    } else if (current.equals("timeTable")) {
                        int option = JOptionPane.showConfirmDialog(null,
                                "確定要更新資料嗎? 舊資料將被覆蓋哦!",
                                "溫馨小提醒",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.WARNING_MESSAGE
                        );

                        if (option == JOptionPane.YES_OPTION)
                            updateTable();

                        resetTime();
                    }
                } else if(e.getSource()==btnBack){
                    next.push(current);
                    current = previous.pop();
                    if (current == "setting") {
                        ClickSetting();
                    } else {
                        card.show(pnlCenter, current);
                    }
                    lblTitle.setText(getTitle(current));
                    resetButton();
                } else if (e.getSource()==btnNext) {
                    previous.push(current);
                    current = next.pop();
                    if (current == "setting") {
                        ClickSetting();
                    } else {
                        card.show(pnlCenter, current);
                    }
                    lblTitle.setText(getTitle(current));
                    resetButton();
                } else if(e.getSource()==btnClass){
                    resetScore();
                    click("class");
                } else if(e.getSource()==btnHistory){
                    click("history");
//                } else if(e.getSource()==btnClass){
//                    click("score");
                } else if(e.getSource()==btnSetting){
                    ClickSetting();
                } else if(e.getSource()==btnTimeTable){
                    click("timeTable");
                } else {
                    throw new IllegalStateException("Unexpected value: " + e.getSource());
                }
            }

        }

        private void ClickSetting() {
            JPasswordField passwd = new JPasswordField();
            JOptionPane localJOptionPane = new JOptionPane(
                    "請輸入管理員密碼 : ",
                    JOptionPane.QUESTION_MESSAGE);
            localJOptionPane.add(passwd);
            passwd.setEchoChar('*');
            JDialog localJDialog = localJOptionPane.createDialog(localJOptionPane,
                    "管理員密碼");
            localJDialog.setVisible(true);
            String input = "";
            input = String.valueOf(passwd.getPassword());
//                    System.out.println(passwd.getPassword());
//                    localJDialog.dispose();

            if (school.checkPassword(input)) {
                resetSetting();
                click("setting");
            } else if (!input.equals("")){
                JOptionPane.showMessageDialog(null, "密碼錯誤", "錯誤錯誤", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    private void resetTime() {
        for (int i=0; i<7; i++) {
            for (int j=0; j<9; j++) {
                timeTablet[i][j].setText(school.getTablet(i, j));
            }
        }
    }

    void showStack() { //檢視用，顯示頁面堆積
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
            this.setText(" 此頁面尚未完成！ 請見諒");

        }
    }
}
