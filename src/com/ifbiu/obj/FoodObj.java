package com.ifbiu.obj;

import com.ifbiu.GameWin;
import com.ifbiu.utils.GameUtils;

import java.awt.*;
import java.util.Random;

/**
 * @author Candide
 * @ClassName FoodObj
 * @createTime 2022年04月14日 08:12:00
 */
public class FoodObj extends GameObj {

    //随机
    Random r = new Random();

    public FoodObj() {
        super();
    }

    public FoodObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
    }

    //获取食物
    public FoodObj getFood(){
        return new FoodObj(GameUtils.foodImg,r.nextInt(20) * 30,(r.nextInt(19) + 1) * 30,this.frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
    }
}
