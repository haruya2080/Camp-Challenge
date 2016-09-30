/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp.kadai3;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * プログラミング基礎学習3の課題クラス
 * @author niwayama
 */
public class Kadai3 {
    // 課題3の結果を表示するout
    private PrintWriter out;
    
    // コンストラクタ
    public Kadai3 (PrintWriter out) {
        this.out = out;
    }
    
    /**
     * 課題1,4　プロフィールの表示
     * @return 
     */
    public boolean viewProfile () {
        out.print("<b>～名前～</b><br>"
                + "庭山悠也" + "<br><br>");
        out.print("<b>～生年月日～</b><br>"
                + "1993年02月10日" + "<br><br>");
        out.print("<b>～自己紹介～</b><br>"
                + "私の名前は庭山悠也です。" + "<br>"
                + "趣味は映画観賞、音楽鑑賞です。" + "<br>"
                + "犬を飼っていて、犬種はコーギーです。" + "<br>");
        
        return true;
    }
    
    /**
     * 課題2　偶数と奇数の判別
     * @param num
     */
    public void discriminateNumberType (int num) {
        String result = (num % 2 == 0) ? "偶数" : "奇数";
        
        out.print("入力された値は：" + result + "です。");
    }
    
    /**
     * 課題3　条件分岐で計算結果を変える
     * @param num
     * @param num2
     * @param type 
     */
    public void kadai3_3 (int num, int num2, boolean type) {
        int result = num * num2;
        
        // type が正ならそのまま、偽なら2乗値を代入
        result = type ? result : (result * result);
        
        // 引数を見やすく文字列に格納
        String argumentText = "( " + num + ", " + num2 + ", " + type + " )";
        
        out.print("<b>～計算結果～</b><br>" + argumentText + " = " + result + "<br>");
    }
    
    public void kadai3_3 (int num, int num2) {
        kadai3_3(num, num2, false);
    }
    
    public void kadai3_3 (int num, boolean type) {
        kadai3_3(num, 5, type);
    }
    
    public void kadai3_3 (int num) {
        kadai3_3(num, 5, false);
    }
    
    /**
     * 課題5,6で使用するプロフィールクラス
     */
    public class ProfileData {
        public ProfileData(int id, String name, String birthDay, String address) {
            this.id = id;
            this.name = name;
            this.birthDay = birthDay;
            this.address = address;
        }
        
        private int id;
        private String name;
        private String birthDay;
        private String address;
        
        

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @return the birthDay
         */
        public String getBirthDay() {
            return birthDay;
        }

        /**
         * @return the address
         */
        public String getAddress() {
            return address;
        }
    }
    
    /**
     * 課題5　プロフィールデータの取得
     * @return 
     */
    public ProfileData getProfileData () {
        ProfileData data = new ProfileData(
                1, "太郎", "2000/01/01", "一県一市一区1-1-1");
        
        return data;
    }
    
    /**
     * 課題6　3人分のプロフィールからidで検索
     * @param id
     * @return 
     */
    public ProfileData findProfileData (int id) {
        ArrayList<ProfileData> profileDataList = new ArrayList<ProfileData>();
        
        // テスト用のデータを追加する
        profileDataList.add(new ProfileData(1, "太郎", "2001/01/01", "一県一市一区1-1-1"));
        profileDataList.add(new ProfileData(2, "次郎", "2002/02/02", "二県二市二区2-2-2"));
        profileDataList.add(new ProfileData(3, "三郎", "2003/03/03", "三県三市三区3-3-3"));
        
        // idと一致するデータを返す
        for (ProfileData data : profileDataList) {
            if (data.getId() == id) {
                return data;
            }
        }
        
        // 探そうとしたデータがない場合
        return null;
    }
    
    /**
     * 課題7,8　nullを除いた限界値までのプロフィールを表示
     */
    public void viewAllProfileData () {
        Integer limit = 2;
        
         ArrayList<ProfileData> profileDataList = new ArrayList<ProfileData>();
        
        // テスト用のデータを追加する
        profileDataList.add(new ProfileData(1, "太郎", "2001/01/01", "一県一市一区1-1-1"));
        profileDataList.add(new ProfileData(2, "次郎", "2002/02/02", null));
        profileDataList.add(new ProfileData(3, "三郎", "2003/03/03", "三県三市三区3-3-3"));
        
        int num = 0;
        
        // 住所がnull以外のデータをすべて表示する
        for (ProfileData data : profileDataList) {            
            // 限界値を超えてしまったため、終了
            if (num >= limit-1) {
                return;
            }
            
            // 住所がnullの場合、ループを戻す
            if (data.getAddress() == null) {
                num++;
                continue;
            }
            
            out.print("--------------------------<br>");
            out.print("名前：" + data.getName() + "<br>");
            out.print("生年月日：" + data.getBirthDay() + "<br>");
            out.print("住所：" + data.getAddress() + "<br>");
            num++;
        }
    }
}
