import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

import javafx.scene.control.TextInputDialog;

public class WinnerList extends Physics{
	private static ArrayList<Player> recordList = new ArrayList<>();
	public static void createRecordList(){
		for(int i = 0; i < 10; i++) {
			Player p = new Player(i+1, "Empty", 0, 1);
			recordList.add(p);
		}
	}
	public static String getString() {
		String out = "";
		for(int i = 0; i < 10; i++) {
			out += recordList.get(i).toString() + "\n";
		}
		return out;
	}
	public static int getPlace(int res) {
		int position = 1;
		for(int i = 0; i < 10; i++) {
			Player p = recordList.get(i);
			if(res >= p.getResult()){
				break;
			}
			position++;
		}
		return position;
	}
	public static void addNewResult(String n, int r, int l) {
		int i = 0;
		int j = 1;
		while(recordList.get(i).getResult() > r) {
			i++;
		}
		Player p = new Player(getPlace(r), n, r, l);
		recordList.add(i, p);	
		for(i++; i < 10; i++) {
			recordList.get(i).setPlace(getPlace(r) + j);
			j++;
		}
	}
	public static void storeToFile(){
		ArrayList<String> out = new ArrayList<String>();
		for(int i = 0; i < 10; i++) {
			out.add(recordList.get(i).toString());
		}
		try {
			Path path = Paths.get("record");
			Files.write(path, out);
			
		}
		catch( IOException a) {
			System.out.print("Can't write to file.");
		}
	}
	public static void readFromFile() {
		try {
			Path path = Paths.get("record");
			ArrayList<String> in = (ArrayList<String>) Files.readAllLines(path);
			for(int i = 0; i < 10; i++) {
				recordList.add(setObject(in.get(i)));
			}
		}
		catch(IOException ex) {
			createRecordList();
			storeToFile();
		}
	}
	private static Player setObject(String in){
		String[] val = in.split("\t");
		Player out = new Player(
				Integer.parseInt(val[0]),
				val[1],Integer.parseInt(val[2]),
				Integer.parseInt(val[3]));
		return out;
	}
	public static int getRecord() {
		return recordList.get(0).getResult();
	}
}
