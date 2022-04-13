package com.ifbiu.obj;

import com.ifbiu.GameWin;

import java.awt.*;

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
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
    }
}
