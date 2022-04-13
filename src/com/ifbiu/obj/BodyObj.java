package com.ifbiu.obj;

import com.ifbiu.GameWin;

import java.awt.*;

/**
 * @author Candide
 * @ClassName BodyObj
 * @createTime 2022年04月13日 16:51:00
 */
public class BodyObj extends GameObj{
    public BodyObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
    }
}
