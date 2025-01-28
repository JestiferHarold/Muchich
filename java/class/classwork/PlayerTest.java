import java.util.Scanner;

public class PlayerTest {

	public static Boolean searchPlayer(Player[] p,int target ) {
		for(Player pa : p) {
			if (pa.getPid() == target) {
				return true;
			}
		}
		return false;
	}

	public static void sortPlayer(Player[] p) {
		Player temp;
		for(int i = 0; i < p.size(); i++) {
			for(int j = 0; j < i.p.size() - i - 1; j++) {
				if(p[j].getAverage() > p[j + 1].getAverage()) {
					temp = p[j];
					p[j] = p[j + 1];
					p[j + 1] = temp;
				}
			}
		}

		for(Player p1 : p) {
			System.out.println(p1.displayPlayer());
		}
	}

	public static void main(String[] args) {
		Player set1 = new Player(3);
		System.out.println("Enter the pid of the player you want to search");
		if (searchPlayer(set1.getTheArrayOfPlayers(), new Scanner(System.in).nextInt())) {
			System.out.println("The Player exists");
		} else {
		System.out.println("The player does not exists");
		}
		sortPlayer(set1.getTheArrayOfPlayers());
	}
}
