import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1941_소문난칠공주 {

	static BufferedReader br;

	static final int MAP_SIZE = 5;
	static final int SELECT_USER_COUNT = 7;

	static class User {
		int row, col;
		char flag;

		public User(int row, int col, char flag) {
			this.row = row;
			this.col = col;
			this.flag = flag;
		}
	}

	static User[] users;
	static int[] currentUsers;
	static int count;

	public static void main(String[] args) throws IOException {
		init();
		findCount(0, 0, 0, 0);
		System.out.print(count);
	}

	static void findCount(int userIndex, int selectedCount, int somTeamCount, int yoonTeamCount) {
		if (selectedCount == SELECT_USER_COUNT) {
			if (isPossibleLine()) {
				count++;
			}
			return;
		}

		if (userIndex == MAP_SIZE * MAP_SIZE) {
			return;
		}

		currentUsers[selectedCount] = userIndex;
		if (users[userIndex].flag == 'Y') {
			if (yoonTeamCount < 3) {
				findCount(userIndex + 1, selectedCount + 1, somTeamCount, yoonTeamCount + 1);
			}
		}
		else {
			findCount(userIndex + 1, selectedCount + 1, somTeamCount + 1, yoonTeamCount);
		}

		findCount(userIndex + 1, selectedCount, somTeamCount, yoonTeamCount);
	}


	static boolean isPossibleLine() {
		Queue<User> nearUsers = new LinkedList<>();
		boolean[] checked = new boolean[SELECT_USER_COUNT];
		int checkedCount = 1;

		checked[0] = true;
		nearUsers.add(users[currentUsers[0]]);

		while (!nearUsers.isEmpty()) {
			User now = nearUsers.poll();

			for (int userIndex = 0; userIndex < SELECT_USER_COUNT; userIndex++) {
				if (checked[userIndex]) {
					continue;
				}

				if (isNearUser(now, users[currentUsers[userIndex]])) {
					checked[userIndex] = true;
					if (++checkedCount == SELECT_USER_COUNT) {
						return true;
					}
					nearUsers.add(users[currentUsers[userIndex]]);
				}
			}
		}

		return false;
	}

	static boolean isNearUser(User now, User currentUser) {
		return (Math.abs(now.row - currentUser.row) + Math.abs(now.col - currentUser.col)) == 1;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		users = new User[MAP_SIZE * MAP_SIZE];

		for (int row = 0; row < MAP_SIZE; row++) {
			char[] line = br.readLine().toCharArray();
			for (int col = 0; col < MAP_SIZE; col++) {
				users[row * MAP_SIZE + col] = new User(row, col, line[col]);
			}
		}

		currentUsers = new int[SELECT_USER_COUNT];
		count = 0;
	}
}
