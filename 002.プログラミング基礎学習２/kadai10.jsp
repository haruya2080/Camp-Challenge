<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
	String param = request.getParameter("number");
	int originalNum = Integer.parseInt(param);	// 元の数
	
	// 負の値を考えない
	if (originalNum <= 0) {
		out.print("正の整数のみ入力可能です。");
		return;
	}
	
	// 1 に対する素因数分解は 1 と定義されているため
	if (originalNum == 1) {
		out.print("元の値 : 1<br>");
		out.print("1ケタの素因数 : 1<br>");
		out.print("その他 : なし<br>");
		return;
	}
	
	// １桁の素数のセット
	int primeArray[] = { 2, 3, 5, 7 };
	
	// 1桁の素因数を保存しておくリスト
	ArrayList<Integer> primeFactors = new ArrayList<Integer>();
	
	// 割り切れない場合の真偽を扱うboolean変数
	boolean isIndivisible = false;
	
	// 一時保存用
	int num = originalNum;
	
	// -1を空数値として代入
	int other = -1;
	
	// 割れなくなるまで繰り返す
	while (!isIndivisible) {
		isIndivisible = true;
		// 全１桁の素数で割っていく
		for (int prime : primeArray) {
			// 割り切れる場合
			if (num % prime == 0) {
				num = num / prime;
				
				// 登録済みか確認
				if (!primeFactors.contains(prime)) {
					primeFactors.add(prime);
				}
				
				// 割れたのでfalse
				isIndivisible = false;
				break;
			}
		}
	}
	
	// 数値が10以上（2桁の場合）その他に追加する
	if (num >= 10) {
		other = num;
	} else {
		// 含まれていない場合、追加
		if (!primeFactors.contains(num) && num != 1) {
			primeFactors.add(num);
		}
	}
	
	// 元の値の出力
	out.print("元の値：" + originalNum + "<br>");
	
	// １桁の素因数を全て出力
	out.print("1ケタの素因数：");
	
	// 1桁の素因数の要素があるか
	if (primeFactors.size() > 0) {
		for (int primeFactor : primeFactors) {
			out.print(primeFactor + ", ");
		}
	} else {
		out.print("なし");
	}
	
	out.print("<br>");
	
	if (other > 0) {
		// その他の値を出力
		out.print("その他：" + other);
	} else {
		// その他なし
		out.print("その他：なし");
	}
%>