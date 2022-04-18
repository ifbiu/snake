package com.ifbiu;

import com.ifbiu.obj.BodyObj;
import com.ifbiu.obj.FoodObj;
import com.ifbiu.obj.HeadObj;
import com.ifbiu.obj.SnakeObj;
import com.ifbiu.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Candide
 * @ClassName GameWin
 * @createTime 2022年04月12日 20:23:00
 */
public class GameWin extends JFrame {


    //游戏状态 0.未开始  1.游戏中  2.暂停  3.失败  4.通关 5.失败后重新开始 6.下一关
    public static int state = 0;
    //分数
    public int score = 0;
    //定义双缓存图片
    Image offScreenImage = null;

    // 定义背景图
//    Image background = Toolkit.getDefaultToolkit().getImage("img/background.png");
    //窗口宽高
    int winWidth = 800;
    int winHeight = 600;
    //蛇头对象
    public HeadObj headObj = new HeadObj(GameUtils.rightImg,60,570,this);

    //蛇的身体集合
    public List<BodyObj> bodyObjList = new ArrayList<>();

    // 食物
    public FoodObj foodObj = new FoodObj().getFood();

    // 右侧蛇图
    public SnakeObj snakeObj = new SnakeObj(GameUtils.snakeImg,640,240,this);

    // 启动
    public void launch(){
        //设置窗口是否可见
        this.setVisible(true);
        //设置窗口的大小
        this.setSize(winWidth,winHeight);
        //设置窗口的位置在屏幕上居中
        this.setLocationRelativeTo(null);
        //设置窗口的标题
        this.setTitle("别指望我们组版贪吃蛇");

        //蛇身体的初始化
        bodyObjList.add(new BodyObj(GameUtils.bodyImg,30,570,this));
        bodyObjList.add(new BodyObj(GameUtils.bodyImg,0,570,this));

        //键盘事件
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    switch (state){
                        case 0:
                            //未开始
                            state = 1;
                            break;
                        case 1:
                            //游戏中
                            state = 2;
                            repaint();
                            break;
                        case 2:
                            //游戏暂停
                            state = 1;
                            break;
                        case 3:
                            //失败后重新开始
                            state = 5;
                            break;
                        case 4:
                            //下一关
                            state = 6;
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        while (true){
            if (state == 1){
                //游戏中才调用
                repaint();
            }
            //失败重启
            if (state == 5){
                state = 0;
                resetGame();
            }
            //通关下一关
            if (state == 6 && GameUtils.level != 3){
                state = 1;
                GameUtils.level++;
                resetGame();
            }

            int time = 450;
            if (GameUtils.level==1){
                time = 400;
            }
            if (GameUtils.level==2){
                time = 300;
            }
            if (GameUtils.level==3){
                time = 200;
            }

            try {
                //1秒1000毫秒
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        //初始化双缓存图片
        if (offScreenImage == null){
            offScreenImage = this.createImage(winWidth,winHeight);
        }
        //获取图片对应的graphics对象
        Graphics gImage = offScreenImage.getGraphics();
        //灰色背景
        gImage.setColor(new Color(238, 236, 249));
//        gImage.drawImage(background,0,0,winWidth,winHeight,this);
        gImage.fillRect(0,0,winWidth,winHeight);
        //网格线
        gImage.setColor(new Color(214, 210, 238));
        //横线
        for (int i = 0; i <= 20 ; i++) {
            //横线
            gImage.drawLine(0,i * 30,600,i * 30);
            //竖线
            gImage.drawLine(i * 30,0,i * 30,600);
        }
        //绘制蛇身体
        for (int i = bodyObjList.size() - 1; i >= 0; i--) {
            bodyObjList.get(i).paintSelf(gImage);
        }
        //绘制蛇头
        try {
            headObj.paintSelf(gImage);
        }catch (IndexOutOfBoundsException e){
            return;
        }
        //食物绘制
        foodObj.paintSelf(gImage);
        //关卡
        GameUtils.drawWord(gImage,"第"+GameUtils.level+"关",Color.orange,40,650,100);
        //分数绘制
        GameUtils.drawWord(gImage,score + " 分",Color.BLUE,50,650,200);
        //右侧蛇图
        snakeObj.paintSelf(gImage);
        GameUtils.drawWord(gImage,"by 王昊 田永杰 柳杨 武安祺 王晓云",Color.red,10,620,580);
        //绘制提示语
        gImage.setColor(Color.gray);
        prompt(gImage);
        //将双缓存图片绘制到窗口中
        g.drawImage(offScreenImage,0,0,null);
    }

    // 绘制提示语
    void prompt(Graphics g){
        //未开始
        if (state == 0){
            g.fillRect(120,240,400,70);
            GameUtils.drawWord(g,"按下空格键开始游戏",Color.yellow,35,150,290);
        }
        //暂停
        if (state == 2){
            g.fillRect(120,240,400,70);
            GameUtils.drawWord(g,"按下空格键继续游戏",Color.yellow,35,150,290);
        }
        //失败
        if (state == 3){
            g.fillRect(120,240,400,70);
            GameUtils.drawWord(g,"呜呜,按空格重新开始",Color.red,35,150,290);
        }
        //通关
        if (state == 4){
            g.fillRect(120,240,400,70);
            if (GameUtils.level == 3){
                GameUtils.drawWord(g,"   ❀恭喜游戏通关❀",Color.green,35,150,290);
            } else {
                GameUtils.drawWord(g,"真棒,空格进入下一关",Color.green,35,150,290);
            }

        }

    }


    // 游戏重置
    void resetGame(){
        //关闭当前窗口
        this.dispose();
        //开启新窗口
        String[] args = {};
        main(args);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}