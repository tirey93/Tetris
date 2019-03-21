public class Player {
	private int place;
	private String name;
	private int result;
	private int level;
	
	Player(int p, String n, int r, int l) {
		place = p;
		name = n;
		result = r;
		level = l;
	}
	public String toString() {
		return place + "\t" + name + "\t" + result + "\t" + level;
	}
	public int getResult() {
		return result;
	}
	public void setPlace(int p) {
		place = p;
	}
}
