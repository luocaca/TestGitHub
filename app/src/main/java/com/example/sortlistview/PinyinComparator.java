package com.example.sortlistview;

import java.util.Comparator;

import com.hldj.hmyg.bean.XiaoQuYu;

/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<XiaoQuYu> {

	public int compare(XiaoQuYu o1, XiaoQuYu o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}


}
