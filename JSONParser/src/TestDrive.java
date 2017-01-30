import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class TestDrive {
	public static void main(String[] args) {
		Gson gson = new Gson();
		BufferedReader br = null; 

		try {
			br = new BufferedReader(new FileReader("F:/EclipseWorkspace/CodingPractice/JSONParser/src/hello.json"));
			FileInfo fInfo = gson.fromJson(br, FileInfo.class);

			if (fInfo != null) {
				for (LibraryItems lItems : fInfo.getLibraryItems()) {
					System.out.println("Item name: " + lItems.getItemName() + ". Item type: " + lItems.getItemType()
							+ ". Item id: " + lItems.getItemId() + ". Item artist: " + lItems.getItemArtist()
							+ ". Item author: " + lItems.getItemAuthor());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
