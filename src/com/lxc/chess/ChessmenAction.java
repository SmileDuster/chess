package com.lxc.chess;

interface ChessmenAction {
	void putchessmen(Board target, int x, int y, int state);
	void removechessmen(Board target, int x, int y, int state);
}
