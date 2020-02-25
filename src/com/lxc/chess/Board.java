package com.lxc.chess;

import javax.swing.*;

public abstract class Board extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Point point[][];
	
	public abstract void win(int player);
		
	public abstract void creat(int mode);
}
