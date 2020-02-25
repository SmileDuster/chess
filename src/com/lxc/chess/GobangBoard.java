package com.lxc.chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GobangBoard extends Board implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ImageIcon outside;
	public static ImageIcon inside;
	public static ImageIcon edge[] = new ImageIcon[4];
	public static ImageIcon horn[] = new ImageIcon[4];
	
	static {
		outside = new ImageIcon("outside.png");
		inside = new ImageIcon("inside.png");
		edge[0] = new ImageIcon("edge0.png");
		edge[1] = new ImageIcon("edge1.png");
		edge[2] = new ImageIcon("edge2.png");
		edge[3] = new ImageIcon("edge3.png");
		horn[0] = new ImageIcon("horn0.png");
		horn[1] = new ImageIcon("horn1.png");
		horn[2] = new ImageIcon("horn2.png");
		horn[3] = new ImageIcon("horn3.png");
	}
	
	public void creat(int mode) {
		this.setLayout(new GridLayout(Mainwindow.line,Mainwindow.line,0,0));
		point = new Point[Mainwindow.line][Mainwindow.line];
		int b=0;
		for(int x = 0 ; x < Mainwindow.line ; x++) {
			for(int y = 0 ; y < Mainwindow.line ; y++) {
				point[x][y] = new Point();
				if(x == 0 || x == Mainwindow.line-1 || y == 0 || y == Mainwindow.line-1) {
					point[x][y].setEnabled(false);
					point[x][y].setBorderPainted(false);
					point[x][y].setIcon(outside);
					point[x][y].state = -1;
				}
				else {
					point[x][y].setActionCommand(String.valueOf(b));
					point[x][y].addActionListener(this);
					point[x][y].setBorderPainted(false);
					point[x][y].setIcon(inside);
					if(x == 1) {
						point[x][y].setIcon(edge[0]);
						if(y == 1) point[x][y].setIcon(horn[0]);
						else if(y == Mainwindow.line-2) point[x][y].setIcon(horn[1]);
					}
					else if(x == Mainwindow.line-2) {
						point[x][y].setIcon(edge[2]);
						if(y == 1) point[x][y].setIcon(horn[2]);
						else if(y == Mainwindow.line-2) point[x][y].setIcon(horn[3]);
					}
					else if(y == 1) point[x][y].setIcon(edge[3]);
					else if(y == Mainwindow.line-2) point[x][y].setIcon(edge[1]);
					if(x == 10 && y == 10 && mode == 1) {
						point[x][y].setIcon(null);
						point[x][y].setBackground(new Color(255,255,255));
						point[x][y].state = 2;
						point[x][y].setEnabled(false);
						Mainwindow.gobangai.lastx = 10;
						Mainwindow.gobangai.lasty = 10;
					}
				}
				this.add(point[x][y]);
				b++;
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		int target = Integer.parseInt(e.getActionCommand());
		int y = target%Mainwindow.line;
		int x = target/Mainwindow.line;
		if(Mainwindow.gobangjudge.checking(x, y, Mainwindow.gobangjudge.whoseturn)==0) {
			return;
		}
		point[x][y].state = Mainwindow.gobangjudge.whoseturn;
		point[x][y].setIcon(null);
		if(point[x][y].state==1) {
			point[x][y].setBackground(new Color(0,0,0));
			point[x][y].setEnabled(false);
		}
		else {
			point[x][y].setBackground(new Color(255,255,255));
			point[x][y].setEnabled(false);
		}
		Mainwindow.gobangjudge.judging(x, y, point[x][y].state, -1);
		Mainwindow.gobangjudge.changeturn();
		if(Mainwindow.gobangai.runflag) {
			Mainwindow.gobangai.enemylastx = x;
			Mainwindow.gobangai.enemylasty = y;
			int aitarget = Mainwindow.gobangai.running();
			int aiy = aitarget%100;
			int aix = aitarget/100;
			if(Mainwindow.gobangjudge.checking(aix, aiy, Mainwindow.gobangjudge.whoseturn)==0) {
				return;
			}
			point[aix][aiy].state = Mainwindow.gobangjudge.whoseturn;
			point[aix][aiy].setIcon(null);
			if(point[aix][aiy].state==1) {
				point[aix][aiy].setBackground(new Color(0,0,0));
				point[aix][aiy].setEnabled(false);
			}
			else {
				point[aix][aiy].setBackground(new Color(255,255,255));
				point[aix][aiy].setEnabled(false);
			}
			Mainwindow.gobangjudge.judging(aix, aiy, point[aix][aiy].state, -1);
			Mainwindow.gobangjudge.changeturn();
			
			Mainwindow.gobangai.lasty = aiy;
			Mainwindow.gobangai.lastx = aix;
		}
	}
	public void win(int player) {
		for(int i = 0 ; i <Mainwindow.line ; i++) {
			for(int o = 0; o < Mainwindow.line ; o++) {
				if(point[i][o].state == 0) {
					point[i][o].setEnabled(false);
				}
			}
		}
		JDialog jdwin = new JDialog(Run.mainwindow,"游戏结束");
		jdwin.setSize(300, 200);
		jdwin.setVisible(true);
		jdwin.setLocationRelativeTo(null);
		jdwin.setResizable(false);
		
		JLabel jlwin = new JLabel();
		jlwin.setText("玩家"+Mainwindow.gobangjudge.whoseturn
				+"获胜！");
		
		JPanel jpall = new JPanel();
		
		JButton jbrestart1 = new JButton();
		JButton jbrestart2 = new JButton();
		JButton jbend = new JButton();
		jbrestart1.setText("mode1");
		jbrestart2.setText("ai first");
		jbend.setText("结束");
		jpall.add(jbrestart1);
		jpall.add(jbrestart2);
		jpall.add(jbend);
		jpall.add(jlwin);
		
		jdwin.add(jpall);
		
		jbend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Mainwindow.gobangboard.removeAll();
				Mainwindow.gobangboard.setLayout(new GridLayout());
				JPanel a = new JPanel();
				a.setBackground(new Color(255,255,255));
				Mainwindow.gobangboard.add(a);
				Mainwindow.gobangboard.revalidate();
				Mainwindow.gobangjudge.whoseturn = 1;
				jdwin.dispose();
			}
		});
		
		jbrestart1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Mainwindow.gobangboard.removeAll();
				Mainwindow.gobangboard.creat(0);
				Mainwindow.gobangai.firstflag = false;
				Mainwindow.gobangboard.revalidate();
				Mainwindow.gobangjudge.whoseturn = 1;
				Mainwindow.gobangai.refresh();
				jdwin.dispose();
			}
		});
		
		jbrestart2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Mainwindow.gobangboard.removeAll();
				Mainwindow.gobangboard.creat(1);
				Mainwindow.gobangai.firstflag = true;
				Mainwindow.gobangboard.revalidate();
				Mainwindow.gobangjudge.whoseturn = 1;
				Mainwindow.gobangai.refresh();
				jdwin.dispose();
			}
		});
	}
}
