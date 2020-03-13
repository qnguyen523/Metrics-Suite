import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/*
 * This class is to save the object saveObject
 */
public class SaveItemListener implements ActionListener{
	SaveModel saveObject;
	ProjectInfoModel projectInfo;
	JFrame frame;
	ArrayList<SaveModel> saveObjectArray;
	public void setFields(ArrayList<SaveModel> saveObjectArray,ProjectInfoModel projectInfo, JFrame frame) {
//		this.saveObject=saveObject;
		this.projectInfo=projectInfo;
		this.frame=frame;
		this.saveObjectArray=saveObjectArray;
	}
	// when the save button is clicked
	public void actionPerformed(ActionEvent e) {
		// save file based on projectName and creatorName
		String projectName = projectInfo.newProjectText.getText();
		String creatorName = projectInfo.creatorText.getText();
		creatorName = creatorName.equals("") ? "" : "_"+ creatorName;
		String fileName = projectName + creatorName+".ms";
		if (fileName.equals("Project Name cannot be empty.ms")) {
			JOptionPane.showMessageDialog(frame, "Nothing to be saved", "Alert", JOptionPane.ERROR_MESSAGE);
			return;
		}
//		if (saveObject.CodeSizeField.getText().equals("") || saveObject.CodeSizeField.getText().equals("0")) {
//			JOptionPane.showMessageDialog(null, "Fields cannot be empty before saving", "Alert", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
		// save a file
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(saveObjectArray);
			out.close();
			fileOut.close();
			JOptionPane.showMessageDialog(frame, "Saved!","Save", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Serialized data is saved");
			System.out.println(saveObjectArray);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
