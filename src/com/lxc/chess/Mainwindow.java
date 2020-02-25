package com.lxc.chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Mainwindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int line = 19+2;

	public JLabel test = new JLabel();
	
	public JPanel left = new JPanel();
	public JPanel right = new JPanel();
	
	public static GobangBoard gobangboard = new GobangBoard();
	public static GobangJudge gobangjudge = new GobangJudge();
	public static GobangAI gobangai = new GobangAI(); 
	public static JLabel jlplayername[] = new JLabel[2];
	public static JLabel jlgame = new JLabel("开始或载入一个对局");
	
	public Mainwindow(String programname) {
		// TODO 自动生成的构造函数存根
		super(programname);
	}

	public void Initialization() {
		this.setVisible(true);
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		JMenuBar jmb = new JMenuBar();
		
		JMenu jmstart = new JMenu("新对局(N)");
		jmstart.setMnemonic(KeyEvent.VK_N);
		JMenu jmigobang = new JMenu("五子棋");
		JMenuItem jmigobang1 = new JMenuItem("单人对局");
		jmigobang1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_DOWN_MASK));
		JMenuItem jmigobang2 = new JMenuItem("双人对局");
		JMenuItem jmixiangqi = new JMenuItem("象棋(X)",KeyEvent.VK_X);
		JMenuItem jmichess = new JMenuItem("国际象棋(G)",KeyEvent.VK_G);
		JMenuItem jmijunqi = new JMenuItem("军棋(J)",KeyEvent.VK_J);
		JMenuItem jmigo = new JMenuItem("围棋(W)",KeyEvent.VK_W);
		jmigobang.add(jmigobang1);
		jmigobang.add(jmigobang2);
		jmstart.add(jmigobang);
		jmstart.add(jmixiangqi);
		jmstart.add(jmichess);
		jmstart.add(jmijunqi);
		jmstart.add(jmigo);
		jmb.add(jmstart);
		
		JMenu jmchange = new JMenu("变更对局(B)");
		jmchange.setMnemonic(KeyEvent.VK_B);
		JMenuItem jmiload = new JMenuItem("载入(Z)",KeyEvent.VK_Z);
		jmiload.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				InputEvent.CTRL_DOWN_MASK));
		JMenuItem jmisave = new JMenuItem("保存(A)",KeyEvent.VK_A);
		jmisave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_DOWN_MASK));
		JMenuItem jmirestart = new JMenuItem("重新开始(C)",KeyEvent.VK_C);
		JMenuItem jmigiveup = new JMenuItem("放弃(F)",KeyEvent.VK_F);
		jmchange.add(jmiload);
		jmchange.add(jmisave);
		jmchange.addSeparator();
		jmchange.add(jmirestart);
		jmchange.add(jmigiveup);
		jmb.add(jmchange);
		
		JMenu jmsetting = new JMenu("设置(S)");
		jmsetting.setMnemonic(KeyEvent.VK_S);
		JMenuItem jmiplay = new JMenuItem("游戏设置(G)",KeyEvent.VK_G);
		JMenuItem jmimusic = new JMenuItem("声音设置(S)",KeyEvent.VK_S);
		JMenuItem jmiother = new JMenuItem("其他设置(Q)",KeyEvent.VK_Q);
		jmsetting.add(jmiplay);
		jmsetting.add(jmimusic);
		jmsetting.add(jmiother);
		jmb.add(jmsetting);
		
		JMenu jmcount = new JMenu("统计(T)");
		jmcount.setMnemonic(KeyEvent.VK_T);
		JMenuItem jmigame = new JMenuItem("游戏统计(Y)",KeyEvent.VK_Y);
		jmcount.add(jmigame);
		jmb.add(jmcount);
		
		JMenu jmhelp = new JMenu("帮助(Z)");
		jmhelp.setMnemonic(KeyEvent.VK_Z);
		JMenuItem jmirule = new JMenuItem("规则(G)",KeyEvent.VK_G);
		JMenuItem jmiabout = new JMenuItem("关于(U)",KeyEvent.VK_U);
		jmhelp.add(jmirule);
		jmhelp.add(jmiabout);
		jmb.add(jmhelp);
		
		jmigo.addActionListener(this);
		jmigobang1.setActionCommand("五子棋单人对局");
		jmigobang2.setActionCommand("五子棋双人对局");
		jmigobang1.addActionListener(this);
		jmigobang2.addActionListener(this);
		jmijunqi.addActionListener(this);
		jmixiangqi.addActionListener(this);
		jmichess.addActionListener(this);
		jmiabout.addActionListener(this);
		jmirule.addActionListener(this);
		jmiload.addActionListener(this);
		jmisave.addActionListener(this);
		jmirestart.addActionListener(this);
		jmigiveup.addActionListener(this);
		jmiplay.addActionListener(this);
		jmimusic.addActionListener(this);
		jmiother.addActionListener(this);
		jmigame.addActionListener(this);
		
		this.setJMenuBar(jmb);
		this.add(test);
	}
	
	public void runinfo() {
		JPanel top = new JPanel();
		JPanel button = new JPanel();
		
		left.setBackground(new Color(0,0,255));
		right.setBackground(new Color(0,255,0));
		top.setBackground(new Color(255,0,0));
		button.setBackground(new Color(255,255,0));
		
		for(int i = 0 ; i < 2 ; i++) {
			jlplayername[i] = new JLabel("未知的选手");
		}
		
		JLabel jl1 = new JLabel("玩家1");
		JLabel jl2 = new JLabel("玩家2");
		left.add(jl1);
		left.add(jlplayername[0]);
		right.add(jl2);
		right.add(jlplayername[1]);
		top.add(jlgame);
		
		
		this.add(button,BorderLayout.SOUTH);
		this.add(top,BorderLayout.NORTH);
		this.add(right,BorderLayout.EAST);
		this.add(left,BorderLayout.WEST);
		
	}
	
	public void runGobang(int mode) {
		this.add(gobangboard,BorderLayout.CENTER);
		gobangboard.removeAll();
		gobangboard.creat(mode);
		gobangboard.revalidate();
		this.setVisible(true);
		gobangjudge.whoseturn = 1;
		Mainwindow.gobangai.refresh();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		String command = e.getActionCommand();
		switch(command) {
		case "五子棋单人对局":
			gobangai.runflag = true;
			left.removeAll();
			right.removeAll();
			left.revalidate();
			right.revalidate();
			JButton jb1 = new JButton("我先手");
			JButton jb2 = new JButton("电脑先手");
			jb1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					left.removeAll();
					right.removeAll();
					left.revalidate();
					right.revalidate();
					JLabel jl1 = new JLabel("玩家1");
					JLabel jl2 = new JLabel("玩家2");
					left.add(jl1);
					left.add(jlplayername[0]);
					right.add(jl2);
					right.add(jlplayername[1]);
					runGobang(0);
					Mainwindow.gobangai.firstflag = false;
				}
			});
			jb2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					left.removeAll();
					right.removeAll();
					left.revalidate();
					right.revalidate();
					JLabel jl1 = new JLabel("玩家1");
					JLabel jl2 = new JLabel("玩家2");
					left.add(jl1);
					left.add(jlplayername[0]);
					right.add(jl2);
					right.add(jlplayername[1]);
					runGobang(1);
					Mainwindow.gobangai.firstflag = true;
				}
			});
			left.add(jb1);
			right.add(jb2);
			break;
		case "五子棋双人对局":
			gobangai.runflag = false;
			runGobang(0);
			break;
		case "象棋(X)":
			break;
		case "国际象棋(G)":
			break;
		case "军棋(J)":
			break;
		case "围棋(W)":
			break;
		case "关于(U)":
			break;
		case "规则(G)":
			break;
		case "载入(Z)":
			break;
		case "保存(A)":
			break;
		case "重新开始(C)":
			break;
		case "放弃(F)":
			break;
		case "游戏统计(Y)":
			break;
		case "游戏设置(G)":
			break;
		case "声音设置(S)":
			break;
		case "其他设置(Q)":
			break;
		}
	}
}
