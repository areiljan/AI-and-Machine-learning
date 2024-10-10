package main.java.ee.taltech.iti0210;

public class 											GameMain
{
	public static void main(String[] args) {
		Player player1 = new IntelligentPlayer(1000); // 1000 simulations per move
		Player player2 = new HumanPlayer();

		Game g = new Game(player1, player2);

		Cell winner = g.play();
		System.out.println(Cell.playerCellString(winner) + " won");
		System.out.println(g.getBoard());
	}
};
