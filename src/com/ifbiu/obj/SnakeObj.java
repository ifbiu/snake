package com.ifbiu.obj;

import com.ifbiu.GameWin;

import java.awt.*;

/**
 * @author Candide
 * @ClassName Snake
 * @createTime 2022年04月18日 09:19:00
 */
public class SnakeObj extends GameObj{
    //宽高
    int width = 100;
    int height = 100;

    public SnakeObj() {
        super();
    }

    public SnakeObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
    }

    public SnakeObj(Image img, int x, int y, int width, int height, GameWin frame) {
        super(img, x, y, width, height, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
    }
}
