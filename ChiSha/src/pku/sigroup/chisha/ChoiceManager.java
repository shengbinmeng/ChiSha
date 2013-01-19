package pku.sigroup.chisha;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;


public class ChoiceManager {

	public static Context appContext;
	public static ArrayList<String> choiceList;
	
	private static int saveList() {
		if (choiceList == null) {
			return -1;
		}
		ObjectOutputStream out = null;

		try {
	        FileOutputStream outStream = appContext.openFileOutput("choice.data", Context.MODE_PRIVATE);
			out = new ObjectOutputStream (
					outStream);
			for (int i = 0; i < choiceList.size(); i++) {
				out.writeObject(choiceList.get(i));
			}
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	private static int loadList() {
		choiceList.clear();
		String choice;
		try {
			FileInputStream inStream = appContext.openFileInput("choice.data");
			ObjectInputStream oin = new ObjectInputStream(inStream);
			while ((choice = (String)oin.readObject()) != null) {
				choiceList.add(choice);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static ArrayList<String> getChoiceList() {
		if (choiceList == null) {
			choiceList = new ArrayList<String>();
			// load all from file
			loadList();
		}
		return choiceList;
	}
	
	
	public static void saveChoiceList() {
		saveList();
	}

}
