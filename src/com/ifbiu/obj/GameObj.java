package com.ifbiu.obj;

import com.ifbiu.GameWin;

import java.awt.*;

/**
 * @author Candide
 * @ClassName Game
 * @createTime 2022年04月12日 21:03:00
 */
public class GameObj {
    // 图片
    Image img;
    // 坐标
    int x;
    int y;
    // 宽高
    int width = 30;
    int height = 30;
    // 窗口类的引用
    GameWin frame;

    public GameObj() {
    }

    public GameObj(Image img, int x, int y, int width, int height, GameWin frame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frame = frame;
    }

    public GameObj(Image img, int x, int y, GameWin frame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.frame = frame;
    }

    // 绘制自身
    public void paintSelf(Graphics g){
        g.drawImage(img,x,y,null);
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GameWin getFrame() {
        return frame;
    }

    public void setFrame(GameWin frame) {
        this.frame = frame;
    }
}
