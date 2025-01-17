package com.ifbiu.obj;

import com.ifbiu.GameWin;
import com.ifbiu.utils.GameUtils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Candide
 * @ClassName HeadObj
 * @createTime 2022年04月13日 08:01:00
 */
public class HeadObj extends GameObj {
    //方向 up down left right
    private String direction = "right";
    private final ArrayList<Integer> keyEventArr = new ArrayList<>();;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public HeadObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
        //键盘监听事件
        this.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                changeDirection(e);
            }
        });
    }

    //控制移动方向  w -up  a - left   d -right  s-down
    public void changeDirection(KeyEvent e){
        int node = 0;
        if (keyEventArr.size()>=2){
            node = keyEventArr.get(keyEventArr.size()-2);
        }
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                if (!"right".equals(direction)){
                    try {
                        if (node == KeyEvent.VK_D){
                            TimeUnit.SECONDS.sleep(1);
                        }
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    direction = "left";
                    img = GameUtils.leftImg;
                    keyEventArr.add(KeyEvent.VK_A);
                }
                break;
            case KeyEvent.VK_D:
                if (!"left".equals(direction)){
                    try {
                        if (node == KeyEvent.VK_A){
                            TimeUnit.SECONDS.sleep(1);
                        }
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    direction = "right";
                    img = GameUtils.rightImg;
                    keyEventArr.add(KeyEvent.VK_D);
                }
                break;
            case KeyEvent.VK_W:
                if (!"down".equals(direction)){
                    try {
                        if (node == KeyEvent.VK_S){
                            TimeUnit.MILLISECONDS.sleep(50);
                        }
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    direction = "up";
                    img = GameUtils.upImg;
                    keyEventArr.add(KeyEvent.VK_W);
                }
                break;
            case KeyEvent.VK_S:
                if (!"up".equals(direction)){
                    try {
                        if (node == KeyEvent.VK_W){
                            TimeUnit.MILLISECONDS.sleep(50);
                        }
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    direction = "down";
                    img = GameUtils.downImg;
                    keyEventArr.add(KeyEvent.VK_S);
                }
                break;
            default:
                break;
        }

    }

    //蛇的移动
    public void move(){
        //蛇身体的移动
        java.util.List<BodyObj> bodyObjList = this.frame.bodyObjList;
        for (int i = bodyObjList.size() - 1; i >= 1; i--) {
            bodyObjList.get(i).x = bodyObjList.get(i - 1).x;
            bodyObjList.get(i).y = bodyObjList.get(i - 1).y;
            //蛇头与身体的碰撞判断
            if (this.x == bodyObjList.get(i).x && this.y == bodyObjList.get(i).y){
                //失败
                GameWin.state = 3;
            }
        }
        bodyObjList.get(0).x = this.x;
        bodyObjList.get(0).y = this.y;
        //蛇头的移动
        switch (direction){
            case "up":
                y -= height;
                break;
            case "down":
                y += height;
                break;
            case "left":
                x -= width;
                break;
            case "right":
                x += width;
            default:
                break;
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        //蛇吃食物
        FoodObj food = this.frame.foodObj;
        //身体最后一节的坐标
        Integer newX = null;
        Integer newY = null;
        if (this.x == food.x && this.y == food.y){
            this.frame.foodObj = food.getFood();
            //获取蛇身的最后一个元素
            BodyObj lastBody = this.frame.bodyObjList.get(this.frame.bodyObjList.size() - 1);
            newX = lastBody.x;
            newY = lastBody.y;
            //分数+1
            this.frame.score++;
        }
        //通关条件设置
        if (GameUtils.level==1 && this.frame.score >= 3){
            GameWin.state = 4;
        }
        if (GameUtils.level==2 && this.frame.score >= 4){
            GameWin.state = 4;
        }
        if (GameUtils.level==3 && this.frame.score >= 5){
            GameWin.state = 4;
        }
        move();
        //move结束后,新的bodyObj对象添加到bodyObjList
        if (newX != null && newY != null){
            this.frame.bodyObjList.add(new BodyObj(GameUtils.bodyImg,newX,newY,this.frame));
        }



        //越界处理
        if (x < 0){
            x = 570;
        } else if (x > 570){
            x = 0;
        } else if (y < 30){
            y = 570;
        }else if (y > 570){
            y = 30;
        }
    }

    @Override
    public String toString() {
        return "HeadObj{" +
                "img=" + img +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
