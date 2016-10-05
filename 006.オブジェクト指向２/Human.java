/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp.blackjack;

import java.util.ArrayList;

/**
 *
 * @author niwayama
 */
public abstract class Human {
    protected ArrayList<Integer> myCards;
    
    // オープンする抽象メソッド
    abstract public int open ();
    
    // カードをセットする抽象メソッド
    abstract public void setCard (ArrayList<Integer> cards);
    
    // 合計を出す抽象メソッド
    abstract public boolean checkSum ();
}
