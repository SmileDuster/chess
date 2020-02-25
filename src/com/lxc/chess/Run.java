package com.lxc.chess;

import javax.swing.*;

public class Run {

	public static final String PROGRAM_NAME = "棋类对战";
	public static Mainwindow mainwindow = new Mainwindow(Run.PROGRAM_NAME);
	
	public Run() {

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				mainwindow.Initialization();
				mainwindow.runinfo();
			}
		});
	}

}
