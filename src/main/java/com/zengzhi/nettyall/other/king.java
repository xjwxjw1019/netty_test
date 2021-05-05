package com.zengzhi.nettyall.other;

/**
 * @author xiejiawei
 * @Date 2021-04-28
 * @Time 21:58
 */
public class king extends Character {
    /**
     * 谁打架
     */
    @Override
    void fight(Weapon weapon) {
        System.out.println("king用"+weapon.userWeapon()+"打架");
    }
}
