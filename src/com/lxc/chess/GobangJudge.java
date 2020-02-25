package com.lxc.chess;

public class GobangJudge extends Judge {
	public GobangJudge() {
		// TODO 自动生成的构造函数存根
		super();
		playernumber = 2;
	}

	@Override
	public void changeturn() {
		// TODO 自动生成的方法存根
		if(++whoseturn>playernumber) {
			whoseturn = 1;
		}
	}

	@Override
	public int checking(int x, int y, int state) {
		// TODO 自动生成的方法存根
		if(Mainwindow.gobangboard.point[x][y].state != 0) {
			return 0;
		}
		else {
			return 1;
		}
	}

	@Override
	public int judging(int x, int y, int state, int extra) {
		// TODO 自动生成的方法存根
		if(find(x,y,state) >= 5) {
			Mainwindow.gobangboard.win(whoseturn);
		}
		return 0;
	}
	private int find(int x,int y,int state) {
		int auto[][] = {{-1,-1,1,1}, //左斜
						{-1,1,1,-1}, //右斜
						{1,0,-1,0}, //竖
						{0,1,0,-1}};//横
		int max = 1;
		for(int i = 0; i < 4; i++) {
			int now = 1;
			int i0 = auto[i][0];
			int i1 = auto[i][1];
			int i2 = auto[i][2];
			int i3 = auto[i][3];
			while(Mainwindow.gobangboard.point[x+i0]
					[y+i1].state==state) {
				now++;
				if(i0 > 0) {
					i0++;
					if(x+i0 > Mainwindow.line) {
						break;
					}
				}
				if(i0 < 0) {
					i0--;
					if(x+i0 < 0) {
						break;
					}
				}
				if(i1 > 0) {
					i1++;
					if(y+i1 > Mainwindow.line) {
						break;
					}
				}
				if(i1 < 0) {
					i1--;
					if(y+i1 < 0) {
						break;
					}
				}
			}
			while(Mainwindow.gobangboard.point[x+i2]
					[y+i3].state==state) {
				now++;
				if(i2 > 0) {
					i2++;
					if(x+i2 > Mainwindow.line) {
						break;
					}
				}
				if(i2 < 0) {
					i2--;
					if(x+i2 < 0) {
						break;
					}
				}
				if(i3 > 0) {
					i3++;
					if(y+i3 > Mainwindow.line) {
						break;
					}
				}
				if(i3 < 0) {
					i3--;
					if(y+i3 < 0) {
						break;
					}
				}
			}
			if(now > max) {
				max = now;
			}
		}
		return max;
	}
}