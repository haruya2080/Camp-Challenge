package jums;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sun.jmx.snmp.Timestamp;

public class UserDataBeans {
	private final SimpleDateFormat dateFormat
		= new SimpleDateFormat("yyyy年MM月dd日");
	private final String[] typeArray = { "エンジニア", "営業", "その他" };

	private int userID;
	private String name;
	private int year;
	private int month;
	private int day;
	private String tell;
	private int type;
	private String comment;
	private Timestamp newDate;

	private boolean isEmptyUser;

	public UserDataBeans () {
		this(false);
	}

	public UserDataBeans (boolean isEmptyUser) {
		name = "";
		year = -1;
		month = -1;
		day = -1;
		tell = "";
		type = 1;
		comment = "";
		newDate = null;

		this.isEmptyUser = isEmptyUser;
	}

	/**
	 * @return userID
	 */
	public int getUserID() {
		return userID;
	}
	/**
	 * @param userID セットする userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return (name != null) ? name : "";
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return birthday
	 */
	public Date getBirthday () {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		return cal.getTime();
	}

	/**
	 * 指定されたフォーマット形式で誕生日を出力する
	 * @return
	 */
	public String getBirthdayText () {
		return dateFormat.format(getBirthday());
	}

	/**
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * 年のデフォルト値を決定するselectedを出力する
	 * @return
	 */
	public String getSelectTag4Year (int num) {
		return (year == num) ? "selected" : "";
	}

	/**
	 * @param year セットする year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 *
	 * @param yearText
	 */
	public void setYear (String yearText) {
		try {
			year = Integer.parseInt(yearText);
		} catch (NumberFormatException nfex) {
			System.out.print(nfex);
		}
	}

	/**
	 * @return month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * 月のデフォルト値を決定するselectedタグを出力する
	 * @return
	 */
	public String getSelectTag4Month (int num) {
		return (month == num) ? "selected" : "";
	}

	/**
	 * @param month セットする month
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @param monthText
	 */
	public void setMonth (String monthText) {
		try {
			month = Integer.parseInt(monthText);
		} catch (NumberFormatException nfex) {
			System.out.print(nfex);
		}
	}

	/**
	 * @return day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * 日のデフォルト値を決定するselectedタグを出力する
	 * @return
	 */
	public String getSelectTag4Day (int num) {
		return (day == num) ? "selected" : "";
	}

	/**
	 * @param day セットする day
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @param dayText
	 */
	public void setDay (String dayText) {
		try {
			day = Integer.parseInt(dayText);
		} catch (NumberFormatException nfex) {
			System.out.print(nfex);
		}
	}

	/**
	 * @return tell
	 */
	public String getTell() {
		return tell;
	}
	/**
	 * @param tell セットする tell
	 */
	public void setTell(String tell) {
		this.tell = tell;
	}
	/**
	 * @return type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type セットする type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 種別を文字列で取得
	 * @param num
	 * @return
	 */
	public String getTypeText () {
		return typeArray[type-1];
	}

	/**
	 * typeのデフォルト値を決定するcheckedタグの出力
	 * @param num
	 * @return
	 */
	public String getCheckTag4Type (int num) {
		return (type == num) ? "checked" : "";
	}

	/**
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment セットする comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return newDate
	 */
	public Timestamp getNewDate() {
		return newDate;
	}
	/**
	 * @param newDate セットする newDate
	 */
	public void setNewDate(Timestamp newDate) {
		this.newDate = newDate;
	}

	/**
	 * 無効な値が含まれているか。
	 * @return
	 */
	public boolean existInvalidParam () {
		// 名前が無効な値の場合
		if (checkInvalidText(name)) { return true; }
		// 誕生日が無効な値の場合
		if (year < 1 || month < 1 || day < 1) { return true; }
		// 電話番号が無効な値の場合
		if (checkInvalidText(tell)) { return true; }
		// 種別が無効な値の場合
		if (type < 0) { return true; }
		// 趣味が無効な値の場合
		if (checkInvalidText(comment)) { return true; }

		return false;
	}

	/**
	 * 無効な名前が存在するとき、メッセージを取得
	 * @return
	 */
	public String getMessage_InvalidName () {
		// 空のユーザーだった場合、エラーメッセージは出さない。
		if (isEmptyUser) { return ""; }

		return (checkInvalidText(name)) ? "名前が入力されていません。<br>" : "";
	}

	/**
	 * 無効な誕生日が存在するとき、メッセージを取得
	 * @return
	 */
	public String getMessage_InvalidBirthday () {
		// 空のユーザーだった場合、エラーメッセージは出さない。
		if (isEmptyUser) { return ""; }

		// 年月日に存在しない数値の場合、正しい数値ではない
		if (year < 1 || month < 1 || day < 1) {
			return "誕生日が正しい値ではありません。<br>";
		}

		return "";
	}

	/**
	 * 無効な電話番号が存在するとき、メッセージを取得
	 * @return
	 */
	public String getMessage_InvalidTell () {
		// 空のユーザーだった場合、エラーメッセージは出さない。
		if (isEmptyUser) { return ""; }

		return (checkInvalidText(tell)) ? "電話番号が入力されていません。<br>" : "";
	}

	/**
	 * 無効な種別タイプのとき、メッセージを取得
	 * @return
	 */
	public String getMessage_InvalidType () {
		// 空のユーザーだった場合、エラーメッセージは出さない。
		if (isEmptyUser) { return ""; }
		return (type < 0) ? "種別が入力されていません。<br>" : "";
	}

	/**
	 * 無効な自己紹介文が存在するとき、メッセージを取得
	 * @return
	 */
	public String getMessage_InvalidComment () {
		// 空のユーザーだった場合、エラーメッセージは出さない。
		if (isEmptyUser) { return ""; }
		return (checkInvalidText(comment)) ? "自己紹介文が入力されていません。<br>" : "";
	}

	private boolean checkInvalidText (String text) {
		// nullチェック
		if (text == null) { return true; }
		// 文字列が空のとき
		if (text.isEmpty()) { return true; }

		return false;
	}
}
