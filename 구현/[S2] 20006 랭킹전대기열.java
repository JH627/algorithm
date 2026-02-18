import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_20006_랭킹전대기열 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Player implements Comparable<Player> {
		int level;
		String id;
		boolean grouped;

		public Player(int level, String id) {
			this.level = level;
			this.id = id;
			grouped = false;
		}

		@Override
		public int compareTo(Player o) {
			return this.id.compareTo(o.id);
		}
	}

	static int playerCount, roomLimit;
	static Player[] players;

	public static void main(String[] args) throws IOException {
		init();
		printStatus();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		playerCount = Integer.parseInt(st.nextToken());
		roomLimit = Integer.parseInt(st.nextToken());

		players = new Player[playerCount];
		for (int player = 0; player < playerCount; player++) {
			st = new StringTokenizer(br.readLine());

			int lever = Integer.parseInt(st.nextToken());
			String id = st.nextToken();
			players[player] = new Player(lever, id);
		}
	}

	static void printStatus() {
		sb = new StringBuilder();

		for (int player = 0; player < playerCount; player++) {
			if (players[player].grouped) {
				continue;
			}

			ArrayList<Player> room = new ArrayList<>();
			for (int other = player; other < playerCount; other++) {
				if (!players[other].grouped &&
					players[player].level - 10 <= players[other].level &&
					players[player].level + 10 >= players[other].level) {

					players[other].grouped = true;
					room.add(players[other]);

					if (room.size() == roomLimit) {
						break;
					}
				}
			}

			Collections.sort(room);

			if (room.size() == roomLimit) {
				sb.append("Started!\n");
			} else {
				sb.append("Waiting!\n");
			}

			for (Player p : room) {
				sb.append(p.level).append(" ").append(p.id).append("\n");
			}

		}

		System.out.print(sb);
	}
}
