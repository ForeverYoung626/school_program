package com.company;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

public class School {
    private ArrayList<Class> classes= new ArrayList<Class>();
    private ArrayList<Ball> balls = new ArrayList<Ball>();
    private ArrayList<His> history = new ArrayList<His>();
    private boolean turn;

    Calendar now = Calendar.getInstance();

    School() {//??
        String []schoolDefault = new String[]{"���T��", "���T��", "���G��", "���G��", "���@��", "���@��", "��T��", "��T��", "��G��", "��G��", "��@��", "��@��"};
        for (int i=0; i<12; i++) {
            classes.add(new Class(schoolDefault[i], 0, 0));
        }
        String []ballDefault = new String[] { "�Ʋy", "��y", "�вy"};
        for (int i=0; i<ballDefault.length;i++) {
            balls.add(new Ball(ballDefault[i], 6, 20, 20));
        }
        turn = true;
    }

    void pushHis(Map<String, Map<String, Integer>> form, Map<String, ArrayList<String>> court) {
        now = Calendar.getInstance();
        history.add(0,new His( this, form, court));
    }

    His getHis(int i) {
        return history.get(i);
    }

    void removeClass(int i) {
        this.classes.remove(i);
    }
    void removeBall(int i) {
        this.balls.remove(i);
    }

    void clearBall() {balls.clear();}

    boolean existClass(String name) {
        boolean b = false;
        for (int i=0; i<classes.size(); i++) {
            if (classes.get(i).getName().equals(name))
                b = true;
        }
        return b;
    }

    boolean existBall(String name) {
        boolean b = false;
        for (int i=0; i<balls.size(); i++) {
            if (balls.get(i).getName().equals(name))
                b = true;
        }
        return b;
    }

    void addClass (Class cls){
        classes.add(cls);
    }
    void addClass ( int i, Class cls){
        classes.add(i, cls);
    }
    void addBall(Ball bal){ balls.add(bal); }
    void addBall(int i , Ball bal){ balls.add(i, bal); }
    void subClass (Class cls){
        classes.remove(classes.indexOf(cls));
    }
    void subBall(Ball bal){ balls.remove(balls.indexOf(bal)); }
    int getBallKinds(){return balls.size();}
    int getClassNum(){return classes.size();}
    Class getClass(int i){return classes.get(i);}
    Class getClass(String str) {
        for (Class cls : classes) {
            if (Objects.equals(cls.getName(), str))
                return cls;
        }
        return null;
    }
    Ball getBall(int i ) { return balls.get(i);}
    Ball getBall(String ballName) {
        for (Ball ball : balls) {
            if (Objects.equals(ball.getName(), ballName))
                return ball;
        }
        return null;
    }

    Class[] getJunior() {
        ArrayList<Class> cls= new ArrayList<Class>();
        for (Class c : classes) {
            if ( String.valueOf(c.getName().toCharArray()[0]).equals("��") || String.valueOf(c.getName().toCharArray()[0]).equals("?"))
                cls.add(c);
        }
        return cls.toArray(new Class[cls.size()]);
    }

    Class[] getSenior() {
        ArrayList<Class> cls= new ArrayList<Class>();
        for (Class c : classes) {
            if ( String.valueOf(c.getName().toCharArray()[0]).equals("��"))
                cls.add(c);
        }
        return cls.toArray(Class[]::new);
    }
    String getHisCourt(String crt, int i) {
        return this.history.get(0).getCourt(this, crt, i);
    }
    int getClassIndex(String name) {
        for (int i=0; i<this.classes.size(); i++)
            if(classes.get(i).getName() == name)
                return i;
        return -1;
    }
    int getSeniorIndex(String name) {
        for (int i=0; i<this.getSenior().length; i++){
            if (this.getSenior()[i].getName().equals(name))
                return i;
//            System.out.println(this.getSenior()[i].getName());
//            System.out.println(name);
        }
        return -1;
    }
    int getJuniorIndex(String name) {
        for (int i=0; i<this.getJunior().length; i++){
            if (this.getJunior()[i].getName() == name)
                return i;
        }
        return -1;
    }

    public boolean isSenior(String name) {
        return name.contains("��");
    }

    public boolean isJunior(String name) {
        return name.contains("��");
    }

    public int getGrade(String name) {
        if(name.contains("�@"))
            return this.isJunior(name)? 1:4;
        if(name.contains("�G"))
            return this.isJunior(name)? 2:5;
        if(name.contains("�T"))
            return this.isJunior(name)? 3:6;
        return 0;
    }

    boolean turn () {
        turn = !turn;
        return !turn;
    }

    Calendar getNow() {
        now = Calendar.getInstance();
        return now;
    }

    String getYear(){return String.valueOf(now.get(Calendar.YEAR)-1911);} //����
    String getMonth(){return String.valueOf(now.get(Calendar.MONTH)+1);}//��
    String getDate(){return String.valueOf(now.get(Calendar.DATE));}//��
    String getDay(){
        String str = new String();
        switch (now.get(Calendar.DAY_OF_WEEK)){
            case 1->{str="��";}
            case 2->{str="�@";}
            case 3->{str="�G";}
            case 4->{str="�T";}
            case 5->{str="�|";}
            case 6->{str="��";}
            case 7->{str="��";}
        }
        return str;
    }

}
