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
public class User extends Human {
    private int stopTotal = 17;
    
    
    // コンストラクタ
    public User () {
        // 手持ちカードの初期化
        myCards = new ArrayList<Integer>();
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
    public void setCard(ArrayList<Integer> cards) {
        myCards.addAll(cards);
    }

    @Override
    public boolean checkSum() {
        // 17までは勝負するユーザーとして考える
        return (open() < 17);
    }
    
    /**
     * 自分のカードを文字列で返す
     * (課題対象外)
     * @return 
     */
    public String ToStringMyCards (int num) {
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
        
        output = "User" + num + " : " + open() + "(" + output + ")" + userGameStatus;
        
        return output;
    }
}
