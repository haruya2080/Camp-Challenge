package jums;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 画面系の処理や表示を簡略化するためのヘルパークラス。定数なども保存されます
 * @author hayashi-s
 */
public class JumsHelper {
    //トップへのリンクを定数として設定
    private final String homeURL = "index.jsp";

    public static JumsHelper getInstance(){
        return new JumsHelper();
    }

    //トップへのリンクを返却
    public String home(){
        return "<a href=\""+homeURL+"\">トップへ戻る</a>";
    }

    /**
     * 入力されたデータのうち未入力項目がある場合、チェックリストにしたがいどの項目が
     * 未入力なのかのhtml文を返却する
     * @param chkList　UserDataBeansで生成されるリスト。未入力要素の名前が格納されている
     * @return 未入力の項目に対応する文字列
     */
    public String chkinput(ArrayList<String> chkList){
        String output = "";
        for(String val : chkList){
                if(val.equals("name")){
                    output += "名前";
                }
                if(val.equals("year")){
                    output +="年";
                }
                if(val.equals("month")){
                    output +="月";
                }
                if(val.equals("day")){
                    output +="日";
                }
                if(val.equals("type")){
                    output +="種別";
                }
                if(val.equals("tell")){
                    output +="電話番号";
                }
                if(val.equals("comment")){
                    output +="自己紹介";
                }
                output +="が未記入です<br>";
            }
        return output;
    }

    /**
     * 名前が未入力かチェック
     * @param chkList
     * @return
     */
    public String chkinputName(ArrayList<String> chkList) {
    	return (chkList.contains("name")) ? "※名前が未入力です。" : "";
    }

    /**
     * 年が未入力かチェック
     * @param chkList
     * @return
     */
    public String chkinputYear(ArrayList<String> chkList) {
    	return (chkList.contains("year")) ? "※年が未入力です。" : "";
    }

    /**
     * 月が未入力かチェック
     * @param chkList
     * @return
     */
    public String chkinputMonth(ArrayList<String> chkList) {
    	return (chkList.contains("month")) ? "※月が未入力です。" : "";
    }

    /**
     * 日が未入力かチェック
     * @param chkList
     * @return
     */
    public String chkinputDay(ArrayList<String> chkList) {
    	return (chkList.contains("day")) ? "※日が未入力です。" : "";
    }

    /**
     * 種別が未入力かチェック
     * @param chkList
     * @return
     */
    public String chkinputType(ArrayList<String> chkList) {
    	return (chkList.contains("type")) ? "※種別が未入力です。" : "";
    }

    /**
     * 電話番号が未入力かチェック
     * @param chkList
     * @return
     */
    public String chkinputTell(ArrayList<String> chkList) {
    	return (chkList.contains("tell")) ? "※電話番号が未入力です。" : "";
    }

    /**
     * 自己紹介が未入力かチェック
     * @param chkList
     * @return
     */
    public String chkinputComment(ArrayList<String> chkList) {
    	return (chkList.contains("comment")) ? "※自己紹介が未入力です。" : "";
    }

    /**
     * 種別は数字で取り扱っているので画面に表示するときは日本語に変換
     * @param i
     * @return
     */
    public String exTypenum(int i){
        switch(i){
            case 1:
                return "営業";
            case 2:
                return "エンジニア";
            case 3:
                return "その他";
        }
        return "";
    }

    /**
     * 改行を<br>タグに変換する
     * @return 変換後の文字列を出力
     */
    public String exBrTagFromRN (String str) {
    	return str.replace("\r\n", "<br>");
    }

    public String chkSelected (int num1, int num2) {
    	return (num1 == num2) ? "selected" : "";
    }

    public String chkChecked (int num1, int num2) {
    	return (num1 == num2) ? "checked" : "";
    }

    /**
     * 誕生日を指定したフォーマットで文字列出力する
     * @param birthday
     * @return
     */
    public String birthdayFormat (Date birthday) {
    	return new SimpleDateFormat("yyyy-MM-dd").format(birthday);
    }
}
