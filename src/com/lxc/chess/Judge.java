package com.lxc.chess;

public abstract class Judge {
	public int whoseturn;
	public int playernumber;
	public Judge() {
		// TODO 自动生成的构造函数存根
		whoseturn = 1;
	}
	public abstract void changeturn();
	public abstract int checking(int x,int y,int state);
	public abstract int judging(int x,int y,int state,int extra);
}
