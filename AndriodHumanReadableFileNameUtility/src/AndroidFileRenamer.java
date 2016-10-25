import java.io.File;

/**Android files are in a strange format. 20161015_125134
 * This format is hard to work with. 
 * THis class is a solution to fix Android files to a more human readable format
 * 
 * @author richardmpanga
 *
 */

public class AndroidFileRenamer {

	private final static String seperator = "_";
	private final static String space = " ";
	private final static String tab = "    ";
	private final static String mp4_ext = ".mp4";
	private int renameCount = 0;
	
	/**
	 *  Renames video file to a MM_DD_YYYY (hh-mm) format 
	 *  Time is military format
	 * @param videoFile
	 * @return
	 */
	public  boolean renameVideoFile(File videoFile)
	{
		System.out.println(tab+ "Renaming File process");
		
		String filename = videoFile.getName();
		String path = videoFile.getAbsolutePath();
		
		//File name attributes
		String year = filename.substring(0, 4);
		String month = filename.substring(4, 6);
		String day = filename.substring(6, 8);
		String hour = filename.substring(9, 11);
		String min = filename.substring(11, 13);
		
		String newFileName = month + seperator+ day + seperator + year + space + "(" + hour +"-" + min + ")"+mp4_ext;
		String newPath = path.replaceFirst(filename, newFileName);
		
		File tempFile = new File(newPath);
		
		//Renaming file 
		if (videoFile.renameTo(tempFile))
		{
			System.out.println(tab+tab+ "Success file renamed");	
			System.out.println(tab+tab + "Old Path: " + path);
			System.out.println(tab+tab+ "New Path " + newPath);
			renameCount++;
			return true;
		}
		else
		{
			System.err.println(tab+tab+ "Error renaming file");
			System.err.flush();
			return false;
		}
	}
	
	/**
	 *  Checks to if file is an mp4 file
	 * @param fileName
	 * @return
	 * boolean
	 */
	
	public  boolean isMP4File(String fileName)
	{
		System.out.println(tab +"Checking file extension");
		int dotIndex = fileName.indexOf(".");
		int lastChar = fileName.length();
		String ext = fileName.substring(dotIndex + 1, lastChar);
		if (ext.equals("mp4"))
		{
			System.out.println(tab+tab+"Success");
			return true;
		}
	
		System.err.println(tab+tab+"Failure: " +fileName +" Not an .mp4");
		System.err.flush();
		System.out.flush();
		return false;
	}
	
	/**
	 *  Checks to see if file is an AndroidVideoFile
	 * @param fileName
	 * @return
	 * boolean
	 */
	public  boolean isAndroidFile(String fileName)
	{
		System.out.println(tab+"Checking if file is Andriod File");
		int androidVideoFileLength = 19;
		if (fileName.length() == androidVideoFileLength)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 *  Checks to if array is not empty
	 * @param files
	 * @return
	 * boolean
	 */
	public boolean hasFiles(File[] files)
	{
		return files.length > 0;
	}
	
	/**
	 *  Returns video files in a specific directory
	 * @param fileDirectoryPath
	 * @return
	 * File[]
	 */
	
	public  File[] getFiles (String fileDirectoryPath)
	{	
		File [] videoFiles = new File[0];
		File videoFolder = new File (fileDirectoryPath);
		if (!videoFolder.exists() && !videoFolder.isDirectory())
		{
			System.err.println(tab+tab+"Folder is not a Directory or doesn't exist");
			System.err.flush();
			return videoFiles;
		}
			System.out.println(tab+tab+"Retrieved files from directory");
		videoFiles = videoFolder.listFiles();
		return videoFiles;
	}
	
	public int getRenameCount()
	{
		return renameCount;
	}

}
