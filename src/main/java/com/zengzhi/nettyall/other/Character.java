package com.zengzhi.nettyall.other;

/**
 * @author xiejiawei
 * @Date 2021-04-28
 * @Time 21:53
 */
public abstract class Character {

    Weapon Weapon;

    public void setWeapon(Weapon weapon) {
        this.Weapon = weapon;
    }

    /**
     * 打架的方法
     */
    void fight(Weapon weapon){

    };
}
