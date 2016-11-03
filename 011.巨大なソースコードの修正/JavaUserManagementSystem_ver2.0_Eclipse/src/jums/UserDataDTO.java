package jums;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ユーザー情報を持ちまわるJavaBeans
 * データベースのカラムと型に対応させている(DTO)。データの挿入、取り出しどちらにも便利
 * @version 1.00
 * @author hayashi-s
 */
public class UserDataDTO {
    private int userID;
    private String name;
    private Date birthday;
    private String tell;
    private int type;
    private String comment;
    private Timestamp newDate;


    public int getUserID() {
        return userID;
    }

    public String toString_UserID () {
    	return String.valueOf(userID);
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Date getBirthday(){
        return birthday;
    }
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }

    public String getTell(){
        return tell;
    }
    public void setTell(String tell){
        this.tell = tell;
    }

    public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }

    public String getComment(){
        return comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }

    public Timestamp getNewDate() {
        return newDate;
    }
    public void setNewDate(Timestamp newDate) {
        this.newDate = newDate;
    }

    public void DTO2UDMapping (UserDataBeans ud) {
    	ud.setName(this.name);
    	ud.setYear(new SimpleDateFormat("yyyy").format(this.birthday));
    	ud.setMonth(new SimpleDateFormat("MM").format(this.birthday));
    	ud.setDay(new SimpleDateFormat("dd").format(this.birthday));
    	ud.setTell(this.tell);
        ud.setType(this.type);
        ud.setComment(this.comment);
    }

    /**
	 * 無効な値が含まれているか。
	 * @return
	 */
	public boolean existInvalidParam () {
		// 名前が無効な値の場合
		if (checkInvalidText(name)) { return true; }
		// 誕生日が無効な値の場合
		if (birthday == null) { return true; }
		// 電話番号が無効な値の場合
		if (checkInvalidText(tell)) { return true; }
		// 種別が無効な値の場合
		if (type < 1 || type > 3) { return true; }
		// 趣味が無効な値の場合
		if (checkInvalidText(comment)) { return true; }

		return false;
	}

	/**
	 * 無効な文字列かを判別
	 * @param text
	 * @return
	 */
	private boolean checkInvalidText (String text) {
		// nullチェック
		if (text == null) { return true; }
		// 文字列が空のとき
		if (text.isEmpty()) { return true; }

		return false;
	}
}
