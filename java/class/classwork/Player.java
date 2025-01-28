import java.util.Scanner;
import java.util.Arrays;

public class Player {

	private String name;
	private int Pid;
	
	private Player[] players;
	private int score[] = new int[5];
	private double avg;

	public int getPid() {
		return Pid;
	}

	public Player[] getTheArrayOfPlayers() {
		return players;
	}

	public Player() {
		System.out.println("Enter the name of the player");
		name = new Scanner(System.in).nextLine();
		System.out.println("Enter the id of the player");
		Pid = new Scanner(System.in).nextInt();
		for(int i = 0; i < 5; i++) {
			System.out.println("Enter score " + (i + 1));
			score[i] = new Scanner(System.in).nextInt();
		}
	}

	public Player(int numberOfPlayers) {
		players = new Player[numberOfPlayers];
		for(int i = 0; i < numberOfPlayers; i++){
			players[i] = new Player();
		}
	}
	
	public double getAverage() {
		int avg = 0;
		for(int i : score) {
			avg += i;
		}
		return avg / 5;
	}

	public void showScore() {
		for(int i : score) {
			System.out.println(i);
		}
	}

	public void displayPlayer() {
		System.out.println(Pid + " " + name + " " + avg);
	}
}
