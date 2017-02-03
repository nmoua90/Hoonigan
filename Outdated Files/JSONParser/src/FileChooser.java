
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

	public String getJsonFile() {
		String filePath;
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files & Text Files", "json", "txt");
		fc.setFileFilter(filter);

		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + fc.getSelectedFile().getName());
			filePath = fc.getSelectedFile().getAbsolutePath();
			return filePath;
		}
		return null;
	}// end of getFile()
}// end of main