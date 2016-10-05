/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp.standardclass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author niwayama
 */
public class Kadai {
    // コンストラクタ
    public Kadai () {}
    
    /**
     * 課題1
     * @return 
     */
    public String Kadai1 () {
        String output = "<h2>課題1</h2>";
        
        // カレンダーのインスタンスを取得
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 1, 1, 0, 0, 0);
        Date date = cal.getTime();
        
        output += date.getTime() + "<br>";
        
        return output;
    }
    
    /**
     * 課題2
     * @return 
     */
    public String Kadai2 () {
        String output = "<h2>課題2</h2>";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        // 現在の時間を表示
        Date date = new Date();
        
        output += sdf.format(date) + "<br>";
        
        return output;
    }
    
    /**
     * 課題3
     * @return 
     */
    public String Kadai3 () {
        String output = "<h2>課題3</h2>";
        
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 11, 4, 10, 0, 0);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Date date = cal.getTime();
        
        output += sdf.format(date);
        
        return output;
    }
    
    /**
     * 課題4
     * @return 
     */
    public String Kadai4 () {
        String output = "<h2>課題4</h2>";
        
        // インスタンスを取得
        Calendar cal = Calendar.getInstance();
        
        // 2015/01/01 00:00:00
        cal.set(2015, 1, 1, 0, 0, 0);
        Date date = cal.getTime();
        
        // 2015/12/31 23:59:59
        cal.set(2015, 12, 31, 23, 59, 59);
        Date date2 = cal.getTime();
        
        // タイムスタンプ
        long timeStamp = date.getTime();
        long timeStamp2 = date2.getTime();
        
        output += (timeStamp2 - timeStamp) + "<br>";
        
        return output;
    }
    
    /**
     * 課題5
     * @return 
     */
    public String Kadai5 () {
        String output = "<h2>課題5</h2>";
        
        String name = "庭山悠也";
        output += "バイト数：" + name.getBytes(Charset.forName("UTF-8")).length;
        output += "文字列の長さ：" + name.length();
        output += "<br>";
        
        return output;
    }
    
    /**
     * 課題6
     * @return 
     */
    public String Kadai6 () {
        String output = "<h2>課題6</h2>";
        
        String mailAddress = "haruya2080@gmail.com";
        String[] array = mailAddress.split("@", 0);
        
        output += array[1] + "<br>";
        
        return output;
    }
    
    /**
     * 課題7
     * @return 
     */
    public String Kadai7 () {
        String output = "<h2>課題7</h2>";
        
        String text = "きょUはぴIえIちぴIのくみこみかんすUのがくしゅUをしてIます";
        
        text = text.replaceAll("I", "い");
        text = text.replaceAll("U", "う");
        
        output += text + "<br>";
        
        return output;
    }
    
    /**
     * 課題8
     */
    public void Kadai8 (String path) {        
        File file = new File(path + "/kadai8.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("私の名前は庭山悠也です。\r\n"
                    + "趣味は映画鑑賞と音楽鑑賞です。\r\n"
                    + "犬を飼っていて犬種はコーギーです。\r\n");
            fileWriter.close();
        } catch (IOException ex) {
            // エラーの場合
            Logger.getLogger(Kadai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 課題9
     * @param path 
     * @return  
     */
    public String Kadai9 (String path) {
        String output = "<h2>課題9</h2>";
        
        // 前の課題で作成したtxtファイルを読み込む
        File file = new File(path + "/kadai8.txt");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(fileReader);
            
            String str = "";
            while ((str = buffer.readLine()) != null) {
                output += str + "<br>";
            }
            
            buffer.close();
        } catch (IOException ex) {
            // エラーの場合
            Logger.getLogger(Kadai.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }
    /**
     * 課題10
     * @param path
     * @return 
     */
    public String Kadai10 (String path) {
        String output = "<h2>課題10</h2>";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        
        Date startDate = new Date();
        
        long startTime = System.nanoTime();
        
        output += "使用クラス：Math" + "<br>";
        output += "円周率：" + Math.PI + "<br>";
        output += "ネイピア数：" + Math.E + "<br>";
        output += "円周率の3乗：" + Math.pow(Math.PI, 3) + "<br>";
        
        double result = Math.E;
        for (int i=0; i<100; i++) {
            result = Math.sqrt(result);
        }
        output += "ネイピア数の100重平方根：" + result + "<br>";
        
        long endTime = System.nanoTime();
        
        Date endDate = new Date();
        
        // 実行時間をmsに直す(元はns)
        float runTime = ((float)(endTime - startTime) / 1000000f);
        output += "<FONT COLOR=\"#FF0000\">実行時間：" + runTime + " ms" + "</FONT><br>";
        output += "------------------------------------------------<br>";
        
        File file = new File(path + "/kadai10_log.txt");
        try {
            // ログファイルに書き込み
            FileWriter writer = new FileWriter(file);
            writer.write(sdf.format(startDate) + "(0 ms)" + " 処理が開始されました。" + "\r\n");
            writer.write(sdf.format(endDate) + "(" + runTime + " ms)" + " 処理が終了しました。" + "\r\n");
            writer.close();
            
            // ログファイルを読み込み
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String str = "";
            while ((str = buffer.readLine()) != null) {
                output += str + "<br>";
            }
        } catch (IOException ex) {
            // エラーになった場合
            Logger.getLogger(Kadai.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        output += "------------------------------------------------<br>";
        
        return output;
    }
}
