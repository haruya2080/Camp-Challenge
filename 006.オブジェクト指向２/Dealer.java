/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author niwayama
 */
public class Dealer extends Human {
    private ArrayList<Integer> cards;
    
    // コンストラクタ
    public Dealer() {
        // 手持ちカードの初期化
        myCards = new ArrayList<Integer>();
        
        // リストの初期化
        cards = new ArrayList<Integer>();
        
        // トランプのマークが4種類あるので4回ループする
        for (int i=0; i<4; i++) {
            // それぞれのマークで1~13までリストに追加
            for (int cardNum = 1; cardNum <= 13; cardNum++) {
                // カードをリストに追加
                cards.add(cardNum);
            }
        }
        
        // シャッフル
        Collections.shuffle(cards);
    }
    
    /**
     * ブラックジャックでディーラーが、
     * プレーヤーに2枚カードを配る動作
     * @return 
     */
    public ArrayList<Integer> deal () {
        // 候補カードのリストを作成
        ArrayList<Integer> outputCards = new ArrayList<Integer>();
        
        // 2回ループ
        for (int i=0; i<2; i++) {
            outputCards.add(cards.get(0));
            cards.remove(0);
        }
        
        // カードを返す
        return outputCards;
    }
    
    /**
     * ブラックジャックのヒット
     * @return 
     */
    public ArrayList<Integer> hit () {
        ArrayList outputCards = new ArrayList<Integer>();
        
        Random rand = new Random();
        
        // 1枚のランダムなカードを取得
        int index = rand.nextInt(cards.size());
        outputCards.add(cards.get(index));
        // 山札を減らす
        cards.remove(index);
        
        return outputCards;
    }
    
    @Override
    public int open() {
        int total = 0;
        int countA = 0;
        
        // すべてのカードを見ていく
        for (Integer card : myCards) {
            // 10, J, Q, Kは10で数えるため
            if (card > 10) {
                total += 10;
            }
            
            // カードが1の場合
            else if (card == 1) {
                countA++;
            }
            
            // それ以外のカードはその数字を代入
            else {
                total += card;
            }
        }
        
        // Aの枚数だけループ
        for (int i=0; i<countA; i++) {
            // 合計値が21を超える場合 1 として数え、それ以外は11として数える
            total += ((total+11) > 21) ? 1 : 11;
        }
        
        // 合計値を返す
        return total;
    }
    
    @Override
    public void setCard (ArrayList<Integer> cards) {
        myCards.addAll(cards);
    }
    
    @Override
    public boolean checkSum () {
        // 合計値が17より少ない場合、引ける
        return (open() < 17);
    }
    
    /**
     * 自分のカードを文字列で返す
     * (課題対象外)
     * @return 
     */
    public String ToStringMyCards () {
        String output = "";
        for (Integer card : myCards) {
            output += card + ", ";
        }
        
        int total = open();
        String userGameStatus = "";
        if (total > 21) {
            userGameStatus = "「バスト」";
        } else if (total == 21) {
            userGameStatus = "「ブラックジャック」";
        }
        
        output = "Dealer : " + open() + "(" + output + ")" + userGameStatus + "<br>";
        
        return output;
    }
}
