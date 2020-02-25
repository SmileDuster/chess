package com.lxc.chess;

public class GobangAI {
	public boolean runflag = false;
	public boolean firstflag = false;
	
	public int lastx = -1,lasty = -1;
	public int enemylastx = -1,enemylasty = -1;
	
	public int[][] score;
	public int[][] enemyscore;
	public int maxscore = -1;
	public int omaxscore = -1;
	public int maxenemyscore = -1;
	public int omaxenemyscore = -1;
	
	public void refresh() {
		score = new int[Mainwindow.line][Mainwindow.line];
		enemyscore = new int[Mainwindow.line][Mainwindow.line];
		lastx = -1;
		lasty = -1;
		enemylastx = -1;
		enemylasty = -1;
		maxscore = -1;
		maxenemyscore = -1;
		omaxscore = -1;
		omaxenemyscore = -1;
		for(int a[] : score) {
			for(int aa : a) {
				aa = 0;
			}
		}
		for(int a[] : enemyscore) {
			for(int aa : a) {
				aa = 0;
			}
		}
	}
	
	public int running() {
		maxscore = -1;
		maxenemyscore = -1;
		omaxscore = -1;
		omaxenemyscore = -1;
		if(lastx == -1 && Mainwindow.gobangboard.point[10][10].state == 2) {
			lastx = 10;
			lasty = 10;
		}
//		if(lastx == -1) {
//			if(enemylastx == 10 && enemylasty == 10) {
//				return 1011;
//			}
//			else {
//				return 1010;
//			}
//		}
		
		int x1,x2,y1,y2;
		int ex1,ex2,ey1,ey2;
		int maxx = 1,maxy = 1;
		int emaxx = 1,emaxy = 1;
		
		x1 = lastx - 4;
		x2 = lastx + 4;
		y1 = lasty - 4;
		y2 = lasty + 4;
		if(x1 < 1) {x1 = 1;}
		if(x2 > Mainwindow.line - 2) {x2 = Mainwindow.line - 2;}
		if(y1 < 1) {y1 = 1;}
		if(y2 > Mainwindow.line - 2) {y2 = Mainwindow.line - 2;}
		ex1 = enemylastx - 4;
		ex2 = enemylastx + 4;
		ey1 = enemylasty - 4;
		ey2 = enemylasty + 4;
		if(ex1 < 1) {ex1 = 1;}
		if(ex2 > Mainwindow.line - 2) {ex2 = Mainwindow.line - 2;}
		if(ey1 < 1) {ey1 = 1;}
		if(ey2 > Mainwindow.line - 2) {ey2 = Mainwindow.line - 2;}
		
		for(int i = x1;i <= x2;i++) {
			for(int o = y1;o <= y2;o++) {
				score[i][o] = thinking(i,o,2,true);
			}
		}
		for(int i = ex1;i <= ex2;i++) {
			for(int o = ey1;o <= ey2;o++) {
				enemyscore[i][o] = enemythinking(i,o,1,true);
			}
		}
		for(int i = 1;i <= 19;i++) {
			for(int o = 1;o <= 19;o++) {
				if(score[i][o] >= maxscore && Mainwindow.gobangboard.point[i][o].state == 0) {
					if(score[i][o] > maxscore) {
						maxscore = score[i][o];
						maxx = i;
						maxy = o;
					}
					else if(score[i][o] == maxscore) {
						if(enemyscore[i][o] > omaxenemyscore) {
							omaxenemyscore = enemyscore[i][o];
							maxx = i;
							maxy = o;
						}
					}
					
				}
				if(enemyscore[i][o] >= maxenemyscore && Mainwindow.gobangboard.point[i][o].state == 0) {
					if(enemyscore[i][o] > maxenemyscore) {
						maxenemyscore = enemyscore[i][o];
						emaxx = i;
						emaxy = o;
					}
					else if(enemyscore[i][o] == maxenemyscore) {
						if(score[i][o] > omaxscore) {
							omaxscore = score[i][o];
							emaxx = i;
							emaxy = o;
						}
					}
				}
			}
		}
		
		int message = 0;
		if(maxscore >= maxenemyscore) {
			System.out.println("ai:"+maxscore);
			message = maxy + 100 * maxx;
		}
//		else if(maxscore == maxenemyscore) {
//			if(score[emaxx][emaxy] >= enemyscore[maxx][maxy]) {
//				message = maxy + 100 * maxx;
//			}
//			else {
//				message = emaxy + 100 * emaxx;
//			}
//		}
		else {
			System.out.println("enemy:"+maxenemyscore);
			message = emaxy + 100 * emaxx;
		}
//		System.out.println("my:"+maxscore+" enemy:"+maxenemyscore);
//		System.out.println(message);
		return message;
	}
	private int thinking(int x,int y,int who,boolean nextflag) {
		if(Mainwindow.gobangboard.point[x][y].state != 0) {
			return 0;
		}
		else {
			int five = 0;
			int highfour = 0;
			int lowfour = 0;
			int highthree = 0;
			int jumpthree = 0;
			int lowthree = 0;
			int hightwo = 0;
			int jumptwo = 0;
			int lowtwo = 0;
			
			int[][] temp = new int[4][9+1];
			
			for(int i = 1;i <= 4;i++) {
				temp[0][5+i] = Mainwindow.gobangboard.point[x+i][y].state;
				if(Mainwindow.gobangboard.point[x+i][y].state == -1) {
					break;
				}
			}
			for(int i = 1;i <= 4;i++) {
				temp[0][5-i] = Mainwindow.gobangboard.point[x-i][y].state;
				if(Mainwindow.gobangboard.point[x-i][y].state == -1) {
					break;
				}
			}
			
			for(int i = 1;i <= 4;i++) {
				temp[1][5-i] = Mainwindow.gobangboard.point[x][y-i].state;
				if(Mainwindow.gobangboard.point[x][y-i].state == -1) {
					break;
				}
			}
			for(int i = 1;i <= 4;i++) {
				temp[1][5+i] = Mainwindow.gobangboard.point[x][y+i].state;
				if(Mainwindow.gobangboard.point[x][y+i].state == -1) {
					break;
				}
			}
			
			for(int i = 1;i <= 4;i++) {
				temp[2][5+i] = Mainwindow.gobangboard.point[x+i][y+i].state;
				if(Mainwindow.gobangboard.point[x+i][y+i].state == -1) {
					break;
				}
			}
			for(int i = 1;i <= 4;i++) {
				temp[2][5-i] = Mainwindow.gobangboard.point[x-i][y-i].state;
				if(Mainwindow.gobangboard.point[x-i][y-i].state == -1) {
					break;
				}
			}
			
			for(int i = 1;i <= 4;i++) {
				temp[3][5+i] = Mainwindow.gobangboard.point[x+i][y-i].state;
				if(Mainwindow.gobangboard.point[x+i][y-i].state == -1) {
					break;
				}
			}
			for(int i = 1;i <= 4;i++) {
				temp[3][5-i] = Mainwindow.gobangboard.point[x-i][y+i].state;
				if(Mainwindow.gobangboard.point[x-i][y+i].state == -1) {
					break;
				}
			}
			temp[0][5] = who;
			temp[1][5] = who;
			temp[2][5] = who;
			temp[3][5] = who;

			
			
			for(int i = 0; i <= 3; i++) {
				int count = 1;
				int jumpcount1 = 0;
				int jumpcount2 = 0;
				int maxcount = 1;
				boolean lowflag1 = false;
				boolean lowflag2 = false;
				boolean blankfirstflag1 = false;
				boolean blankfirstflag2 = false;
				boolean blanksecondflag1 = false;
				boolean blanksecondflag2 = false;
				for(int o = 4;o >= 1;o--) {
					if(temp[i][o] == who) {
						if(!blankfirstflag1) {
							count++;
						}
						else {
							if(!blanksecondflag1) {
								jumpcount1++;
							}
						}
						maxcount++;
					}
					else if(temp[i][o] == 0) {
						maxcount++;
						if(!blankfirstflag1) {
							blankfirstflag1 = true;
						}
						else {
							if(!blanksecondflag1) {
								blanksecondflag1 = true;
							}
						}
					}
					else {
						if(temp[i][o+1] == who) {
							lowflag1 = true;
						}
						break;
					}
				}
				for(int o = 6;o <= 9;o++) {
					if(temp[i][o] == who) {
						if(!blankfirstflag2) {
							count++;
						}
						else {
							if(!blanksecondflag2) {
								jumpcount2++;
							}
						}
						maxcount++;
					}
					else if(temp[i][o] == 0) {
						maxcount++;
						if(!blankfirstflag2) {
							blankfirstflag2 = true;
						}
						else {
							if(!blanksecondflag2) {
								blanksecondflag2 = true;
							}
						}
					}
					else {
						if(temp[i][o-1] == who) {
							lowflag2 = true;
						}
						break;
					}
				}
				int jumpcount;
				boolean lowflag;
				if(jumpcount1 > jumpcount2) {
					jumpcount = jumpcount1;
					lowflag = lowflag1;
				}
				else if(jumpcount1 < jumpcount2) {
					jumpcount = jumpcount2;
					lowflag = lowflag2;
				}
				else {
					if(!lowflag1) {
						jumpcount = jumpcount1;
						lowflag = lowflag1;
					}
					else {
						jumpcount = jumpcount2;
						lowflag = lowflag2;
					}
				}
				if(count >= 5) {
					five++;
				}
				else if(count == 4 && maxcount >= 5) {
					if(lowflag1 || lowflag2) {
						lowfour++;
					}
					else {
						highfour++;
					}
				}
				else if(count + jumpcount >= 4) {
					lowfour++;
				}
				else if(count == 3) {
					if(maxcount == 5) {
						lowthree++;
					}
					else if(maxcount > 5) {
						if(lowflag1 || lowflag2) {
							lowthree++;
						}
						else {
							highthree++;
						}
					}
				}
				else if(count + jumpcount == 3) {
					if(maxcount == 5) {
						lowthree++;
					}
					else if(maxcount >5) {
						if(lowflag) {
							lowthree++;
						}
						else {
							jumpthree++;
						}
					}
				}
				else if(count == 2) {
					if(maxcount == 5) {
						lowtwo++;
					}
					else if(maxcount > 5) {
						if(lowflag1 || lowflag2) {
							lowtwo++;
						}
						else {
							hightwo++;
						}
					}
				}
				else if(count + jumpcount == 2) {
					if(maxcount == 5) {
						lowtwo++;
					}
					else if(maxcount >5) {
						if(lowflag) {
							lowtwo++;
						}
						else {
							jumptwo++;
						}
					}
				}
			}
			int pointscore = 0;
			if(five != 0) {
				pointscore = 100;
			}
			else if(highfour != 0 && lowfour != 0) {
				pointscore = 98;
			}
			else if(highfour != 0 && highthree != 0) {
				pointscore = 96;
			}
			else if(highfour != 0 && lowthree != 0) {
				pointscore = 94;
			}
			else if(highfour != 0 && hightwo != 0) {
				pointscore = 92;
			}
			else if(highfour != 0 && lowtwo != 0) {
				pointscore = 90;
			}
			else if(highfour != 0) {
				pointscore = 88;
			}
			else if(lowfour >= 2) {
				pointscore = 86;
			}
			else if(lowfour == 1 && highthree + jumpthree != 0) {
				pointscore = 84;
			}
			else if(highthree + jumpthree >= 2 && !firstflag) {
				pointscore = 82;
			}
			else if(lowfour == 1 && hightwo + jumptwo != 0) {
				pointscore = 81;
			}
			else if(highthree + jumpthree == 1 && hightwo + jumptwo >= 2) {
				pointscore = 80;
			}
			else if(highthree + jumpthree == 1 && hightwo + jumptwo == 1) {
				pointscore = 79;
			}
			else if(highthree + jumpthree == 1 && lowthree >= 1) {
				pointscore = 78;
			}
			else if(lowthree >= 1 && hightwo + jumptwo >= 1) {
				pointscore = 76;
			}
			else if(highthree == 1 || jumpthree == 1) {
				pointscore = 74;
			}
			else if(hightwo + jumptwo >= 2) {
				pointscore = 70;
			}
			else if(hightwo == 1) {
				pointscore = 68;
			}
			else if(jumptwo == 1) {
				pointscore = 66;
			}
			else if(lowthree == 1) {
				pointscore = 64;
			}
			else if(lowtwo >= 1) {
				pointscore = 62;
			}
			else {
				pointscore = 1;
			}
			return pointscore;
		}
	}
	
	private int enemythinking(int x,int y,int who,boolean nextflag) {
		if(Mainwindow.gobangboard.point[x][y].state != 0) {
			return 0;
		}
		else {
			int five = 0;
			int highfour = 0;
			int lowfour = 0;
			int highthree = 0;
			int jumpthree = 0;
			int lowthree = 0;
			int hightwo = 0;
			int jumptwo = 0;
			int lowtwo = 0;
			
			int[][] temp = new int[4][9+1];
			
			for(int i = 1;i <= 4;i++) {
				temp[0][5+i] = Mainwindow.gobangboard.point[x+i][y].state;
				if(Mainwindow.gobangboard.point[x+i][y].state == -1) {
					break;
				}
			}
			for(int i = 1;i <= 4;i++) {
				temp[0][5-i] = Mainwindow.gobangboard.point[x-i][y].state;
				if(Mainwindow.gobangboard.point[x-i][y].state == -1) {
					break;
				}
			}
			
			for(int i = 1;i <= 4;i++) {
				temp[1][5-i] = Mainwindow.gobangboard.point[x][y-i].state;
				if(Mainwindow.gobangboard.point[x][y-i].state == -1) {
					break;
				}
			}
			for(int i = 1;i <= 4;i++) {
				temp[1][5+i] = Mainwindow.gobangboard.point[x][y+i].state;
				if(Mainwindow.gobangboard.point[x][y+i].state == -1) {
					break;
				}
			}
			
			for(int i = 1;i <= 4;i++) {
				temp[2][5+i] = Mainwindow.gobangboard.point[x+i][y+i].state;
				if(Mainwindow.gobangboard.point[x+i][y+i].state == -1) {
					break;
				}
			}
			for(int i = 1;i <= 4;i++) {
				temp[2][5-i] = Mainwindow.gobangboard.point[x-i][y-i].state;
				if(Mainwindow.gobangboard.point[x-i][y-i].state == -1) {
					break;
				}
			}
			
			for(int i = 1;i <= 4;i++) {
				temp[3][5+i] = Mainwindow.gobangboard.point[x+i][y-i].state;
				if(Mainwindow.gobangboard.point[x+i][y-i].state == -1) {
					break;
				}
			}
			for(int i = 1;i <= 4;i++) {
				temp[3][5-i] = Mainwindow.gobangboard.point[x-i][y+i].state;
				if(Mainwindow.gobangboard.point[x-i][y+i].state == -1) {
					break;
				}
			}
			temp[0][5] = who;
			temp[1][5] = who;
			temp[2][5] = who;
			temp[3][5] = who;

			
			
			for(int i = 0; i <= 3; i++) {
				int count = 1;
				int jumpcount1 = 0;
				int jumpcount2 = 0;
				int maxcount = 1;
				boolean lowflag1 = false;
				boolean lowflag2 = false;
				boolean blankfirstflag1 = false;
				boolean blankfirstflag2 = false;
				boolean blanksecondflag1 = false;
				boolean blanksecondflag2 = false;
				for(int o = 4;o >= 1;o--) {
					if(temp[i][o] == who) {
						if(!blankfirstflag1) {
							count++;
						}
						else {
							if(!blanksecondflag1) {
								jumpcount1++;
							}
						}
						maxcount++;
					}
					else if(temp[i][o] == 0) {
						maxcount++;
						if(!blankfirstflag1) {
							blankfirstflag1 = true;
						}
						else {
							if(!blanksecondflag1) {
								blanksecondflag1 = true;
							}
						}
					}
					else {
						if(temp[i][o+1] == who) {
							lowflag1 = true;
						}
						break;
					}
				}
				for(int o = 6;o <= 9;o++) {
					if(temp[i][o] == who) {
						if(!blankfirstflag2) {
							count++;
						}
						else {
							if(!blanksecondflag2) {
								jumpcount2++;
							}
						}
						maxcount++;
					}
					else if(temp[i][o] == 0) {
						maxcount++;
						if(!blankfirstflag2) {
							blankfirstflag2 = true;
						}
						else {
							if(!blanksecondflag2) {
								blanksecondflag2 = true;
							}
						}
					}
					else {
						if(temp[i][o-1] == who) {
							lowflag2 = true;
						}
						break;
					}
				}
				int jumpcount;
				boolean lowflag;
				if(jumpcount1 > jumpcount2) {
					jumpcount = jumpcount1;
					lowflag = lowflag1;
				}
				else if(jumpcount1 < jumpcount2) {
					jumpcount = jumpcount2;
					lowflag = lowflag2;
				}
				else {
					if(!lowflag1) {
						jumpcount = jumpcount1;
						lowflag = lowflag1;
					}
					else {
						jumpcount = jumpcount2;
						lowflag = lowflag2;
					}
				}
				if(count >= 5) {
					five++;
				}
				else if(count == 4 && maxcount >= 5) {
					if(lowflag1 || lowflag2) {
						lowfour++;
					}
					else {
						highfour++;
					}
				}
				else if(count + jumpcount >= 4) {
					lowfour++;
				}
				else if(count == 3) {
					if(maxcount == 5) {
						lowthree++;
					}
					else if(maxcount > 5) {
						if(lowflag1 || lowflag2) {
							lowthree++;
						}
						else {
							highthree++;
						}
					}
				}
				else if(count + jumpcount == 3) {
					if(maxcount == 5) {
						lowthree++;
					}
					else if(maxcount >5) {
						if(lowflag) {
							lowthree++;
						}
						else {
							jumpthree++;
						}
					}
				}
				else if(count == 2) {
					if(maxcount == 5) {
						lowtwo++;
					}
					else if(maxcount > 5) {
						if(lowflag1 || lowflag2) {
							lowtwo++;
						}
						else {
							hightwo++;
						}
					}
				}
				else if(count + jumpcount == 2) {
					if(maxcount == 5) {
						lowtwo++;
					}
					else if(maxcount >5) {
						if(lowflag) {
							lowtwo++;
						}
						else {
							jumptwo++;
						}
					}
				}
			}
			int pointscore = 0;
			if(five != 0) {
				pointscore = 100;
			}
			else if(highfour != 0 && lowfour != 0) {
				pointscore = 98;
			}
			else if(highfour != 0 && highthree != 0) {
				pointscore = 96;
			}
			else if(highfour != 0 && lowthree != 0) {
				pointscore = 94;
			}
			else if(highfour != 0 && hightwo != 0) {
				pointscore = 92;
			}
			else if(highfour != 0 && lowtwo != 0) {
				pointscore = 90;
			}
			else if(highfour != 0) {
				pointscore = 88;
			}
			else if(lowfour >= 2) {
				pointscore = 86;
			}
			else if(lowfour == 1 && highthree + jumpthree != 0) {
				pointscore = 84;
			}
			else if(highthree + jumpthree >= 2 && !firstflag) {
				pointscore = 82;
			}
			else if(lowfour == 1 && hightwo + jumptwo != 0) {
				pointscore = 81;
			}
			else if(highthree + jumpthree == 1 && hightwo + jumptwo >= 2) {
				pointscore = 80;
			}
			else if(highthree + jumpthree == 1 && hightwo + jumptwo == 1) {
				pointscore = 79;
			}
			else if(highthree + jumpthree == 1 && lowthree >= 1) {
				pointscore = 78;
			}
			else if(lowthree >= 1 && hightwo + jumptwo >= 1) {
				pointscore = 76;
			}
			else if(hightwo + jumptwo >= 2) {
				pointscore = 74;
			}
			else if(hightwo == 1 && lastx == -1) {
				pointscore = 2;
			}
			else {
				pointscore = 1;
			}
			return pointscore;
		}
	}
}
