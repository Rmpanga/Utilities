import java.io.File;
import javax.swing.JOptionPane;
public class Runner {

	public static void main (String [] args)
	{
		AndroidFileRenamer androidFileRenamer = new AndroidFileRenamer();
		String directoryPath = JOptionPane.showInputDialog("Enter the directory path of the video folder");
		if (directoryPath == null)
		{
			JOptionPane.showMessageDialog(null, "No directory path entered " , "No information entered" , JOptionPane.INFORMATION_MESSAGE, null);
			return;
		}
		
		System.out.println("Getting files from directory");
		File[] files = androidFileRenamer.getFiles(directoryPath);
		
		if (androidFileRenamer.hasFiles(files))
		{		
			for (File file : files)
			{
				if (file.getName().equals(".DS_Store"))
				{
					continue;
				}
				System.out.println("Processing file name: " + file.getName());
				if (androidFileRenamer.isMP4File(file.getName()) && androidFileRenamer.isAndroidFile(file.getName()))
				{
					boolean renamed = androidFileRenamer.renameVideoFile(file);
					
					if (!renamed)
					{
						JOptionPane.showMessageDialog(null,"Unable to rename " + file.getName(), "Unable to rename file" , JOptionPane.ERROR_MESSAGE, null);
					}
					
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "No files were in the directory" , "No Files found" , JOptionPane.INFORMATION_MESSAGE, null);
		}
		//Presenting results to user
		JOptionPane.showMessageDialog(null, "Finised executing, Renamed: " + androidFileRenamer.getRenameCount() +" Out of " + files.length + " files" , "Done", JOptionPane.INFORMATION_MESSAGE, null);
	}
}
