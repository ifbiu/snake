package com.ifbiu;

import com.ifbiu.obj.HeadObj;
import com.ifbiu.utils.GameUtils;

import javax.swing.*;
import java.awt.*;

/**
 * @author Candide
 * @ClassName GameWin
 * @createTime 2022年04月12日 20:23:00
 */
public class GameWin extends JFrame {
    HeadObj headObj = new HeadObj(GameUtils.rightImg,30,570,this);

    public void launch(){
        // 设置窗口可见
        this.setVisible(true);
        // 设置窗口大小
        this.setSize(600,600);
        // 设置窗口位置在屏幕上居中
        this.setLocationRelativeTo(null);
        // 设置窗口的标题
        this.setTitle("`别指望我们组`版贪吃蛇");
    }

    @Override
    public void paint(Graphics g) {
        // 灰色背景
        g.setColor(Color.pink);
        g.fillRect(0,0,600,600);
        // 网格线
        g.setColor(Color.black);
        g.drawLine(0,60,600,60);

        for (int i = 0; i < 20; i++) {
            // 横线
            g.drawLine(0,i * 30,600,i * 30);
            // 竖线
            g.drawLine(i * 30,0,i * 30,600);
        }

        // 绘制蛇头
        headObj.paintSelf(g);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
