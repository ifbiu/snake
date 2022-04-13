package com.ifbiu.obj;

import com.ifbiu.GameWin;
import com.ifbiu.utils.GameUtils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Candide
 * @ClassName HeadObj
 * @createTime 2022年04月13日 08:01:00
 */
public class HeadObj extends GameObj{
    private String direction = "right";

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public HeadObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
        // 键盘监听事件
        this.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                changeDirection(e);
            }
        });
    }

    // 控制移动方向
    public void changeDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                if (!"right".equals(direction)){
                    direction = "left";
                    img = GameUtils.leftImg;
                }
                break;
            case KeyEvent.VK_D:
                if (!"left".equals(direction)){
                    direction = "right";
                    img = GameUtils.rightImg;
                }
                break;
            case KeyEvent.VK_W:
                if (!"down".equals(direction)){
                    direction = "up";
                    img = GameUtils.upImg;
                }
                break;
            case KeyEvent.VK_S:
                if (!"up".equals(direction)){
                    direction = "down";
                    img = GameUtils.downImg;
                }
                break;
            default:
                break;
        }
    }

    public void move(){
        // 蛇身体的移动
        java.util.List<BodyObj> bodyObjList = this.frame.bodyObjList;
        for (int i = bodyObjList.size() - 1; i >= 1; i--) {
            bodyObjList.get(i).x = bodyObjList.get(i-1).x;
            bodyObjList.get(i).y = bodyObjList.get(i-1).y;
        }
        bodyObjList.get(0).x = this.x;
        bodyObjList.get(0).y = this.y;
        // 蛇头的移动
        switch (direction){
            case "up":
                y -= height;
                break;
            case "down":
                y += height;
                break;
            case "left":
                x-= width;
                break;
            case "right":
                x += width;
                break;
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        move();
        // 越界处理
        if (x < 0){
            x = 570;
        }else if (x > 570){
            x = 0;
        }else if (y < 30){
            y = 570;
        }else if (y > 570){
            y = 30;
        }
    }
}
