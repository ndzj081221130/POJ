package p2632_robot_wa;import java.util.Scanner;public class Main {	public static void main(String[] args) {		Scanner scanner = new Scanner(System.in);		int caseNum = Integer.parseInt(scanner.nextLine().trim());		for (int i = 0; i < caseNum; i++) {			String line = scanner.nextLine().trim();			String arr[] = line.split(" ");			int A = Integer.parseInt(arr[0]);			int B = Integer.parseInt(arr[1]);			line = scanner.nextLine().trim();			arr = line.split(" ");			int N = Integer.parseInt(arr[0]);			int M = Integer.parseInt(arr[1]);			Robot[] robots = new Robot[N + 1];			for (int j = 0; j < N; j++) {				line = scanner.nextLine().trim();				arr = line.split(" ");				int x = Integer.parseInt(arr[0]);				int y = Integer.parseInt(arr[1]);				int dir = 0;				if (arr[2].equals("E"))					dir = 0;				else if (arr[2].equals("S")) {					dir = 1;				} else if (arr[2].equals("W")) {					dir = 2;				} else {					dir = 3;				}				robots[j + 1] = new Robot(x, y, dir, j + 1);			}			Instruction[] instructions = new Instruction[M];			for (int j = 0; j < instructions.length; j++) {				line = scanner.nextLine().trim();				arr = line.split(" ");				int id = Integer.parseInt(arr[0]);				int action = 0;				if (arr[1].equals("L")) {					action = 0;				} else if (arr[1].equals("R")) {					action = 1;				} else {					action = 2;				}				int repeat = Integer.parseInt(arr[2]);				instructions[j] = new Instruction(id, action, repeat);			}			isLegal(A, B, N, M, robots, instructions);		}	}	private static void isLegal(int a, int b, int n, int m, Robot[] robots,			Instruction[] instructions) {		// TODO Auto-generated method stub		Place[][] map = new Place[a + 1][b + 1];		for (int i = 0; i < a + 1; i++) {			for (int j = 0; j < b + 1; j++) {				if (i == 0 || i == a || j == 0 || j == b)					map[i][j] = new Place(null, i, j, true);				else					map[i][j] = new Place(null, i, j, false);			}		}		for (int ri = 1; ri < robots.length; ri++) {			Robot robot = robots[ri];			map[robot.getX()][robot.getY()].setRobot(robot);		}		for (int in = 0; in < instructions.length; in++) {			Instruction instruction = instructions[in];			int rid = instruction.getRobotId();			Robot cur = robots[instruction.getRobotId()];			int dir = cur.getDir();			int x = cur.getX();			int y = cur.getY();			int action = instruction.getAction();			int repeat = instruction.getRepeat();			if (action == 2) {// forward				map[x][y].setRobot(null);				if (dir == 0) {// East					int next_x = x;					for (int step = 0; step < repeat; step++) {						next_x++;						if (next_x > a || next_x <= 0) {							System.out.println("Robot " + rid									+ " crashes into the wall");							return;						}												Robot next_rRobot = map[next_x][y].getRobot();						if (next_rRobot != null) {							System.out.println("Robot " + rid									+ " crashes into robot "									+ next_rRobot.getId());							return;						}else{							cur.setX(next_x);							map[next_x][y].setRobot(cur);						}					}				} else if (dir == 1) {// south					int next_y = y;					for (int step = 0; step < repeat; step++) {						next_y--;						if (next_y > b || next_y <= 0) {							System.out.println("Robot " + rid									+ " crashes into the wall");							return;						}						Robot next_rRobot = map[x][next_y].getRobot();						if (next_rRobot != null) {							System.out.println("Robot " + rid									+ " crashes into robot "									+ next_rRobot.getId());							return;						}else{							cur.setY(next_y);							map[x][next_y].setRobot(cur);						}					}				} else if (dir == 2) {					int next_x = x;					for (int step = 0; step < repeat; step++) {						next_x--;						if (next_x > a || next_x <= 0) {							System.out.println("Robot " + rid									+ " crashes into the wall");							return;						}						Robot next_rRobot = map[next_x][y].getRobot();						if (next_rRobot != null) {							System.out.println("Robot " + rid									+ " crashes into robot "									+ next_rRobot.getId());							return;						}else{							cur.setX(next_x);							map[next_x][y].setRobot(cur);						}					}				} else {// dir == 3					int next_y = y;					for (int step = 0; step < repeat; step++) {						next_y++;						if (next_y > b || next_y <= 0) {							System.out.println("Robot " + rid									+ " crashes into the wall");							return;						}						Robot next_rRobot = map[x][next_y].getRobot();						if (next_rRobot != null) {							System.out.println("Robot " + rid									+ " crashes into robot "									+ next_rRobot.getId());							return;						}else{							cur.setY(next_y);							map[x][next_y].setRobot(cur);						}					}				}			} else if (action == 0) { // left				repeat = repeat % 4;				int des = dir;				for (int leftStep = 0; leftStep < repeat; leftStep++) {					des = (des - 1) % 4;				}				cur.setDir(des);				map[x][y].setRobot(cur);			} else { // right if (action == 1)				repeat = repeat % 4;				int des = dir;				for (int leftStep = 0; leftStep < repeat; leftStep++) {					des = (des + 1) % 4;				}				cur.setDir(des);				map[x][y].setRobot(cur);			}		}		System.out.println("OK");	}	public static class Instruction {		// Robot robot;		int robotId;		int action;// {0,1,2},L,R,F		int repeat;		public Instruction(int id, int ac, int r) {			robotId = id;			action = ac;			repeat = r;		}		public int getRobotId() {			return robotId;		}		public void setRobotId(int robotId) {			this.robotId = robotId;		}		public int getAction() {			return action;		}		public void setAction(int action) {			this.action = action;		}		public int getRepeat() {			return repeat;		}		public void setRepeat(int repeat) {			this.repeat = repeat;		}	}	public static class Robot {		int x;		int y;		int dir;// {0,1,2,3 } = {E,S,W,N}		int id;		public Robot(int x, int y, int dir, int i) {			this.x = x;			this.y = y;			this.dir = dir;			this.id = i;		}		public int getX() {			return x;		}		public void setX(int x) {			this.x = x;		}		public int getY() {			return y;		}		public void setY(int y) {			this.y = y;		}		public int getDir() {			return dir;		}		public void setDir(int dir) {			this.dir = dir;		}		public int getId() {			return id;		}		public void setId(int id) {			this.id = id;		}	}	public static class Place {		Robot robot;		int x;		int y;		boolean wall = false;		public Place(Robot r, int x, int y, boolean f) {			this.robot = r;			this.x = x;			this.y = y;			this.wall = f;		}		public Robot getRobot() {			return robot;		}		public void setRobot(Robot robot) {			this.robot = robot;		}		public int getX() {			return x;		}		public void setX(int x) {			this.x = x;		}		public int getY() {			return y;		}		public void setY(int y) {			this.y = y;		}		public boolean isWall() {			return wall;		}		public void setWall(boolean wall) {			this.wall = wall;		}	}}