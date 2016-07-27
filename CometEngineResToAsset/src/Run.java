import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Run {

	public static void copy(File sourceF, File targetF) {

		File[] ff = sourceF.listFiles();
		for (File file : ff) {
			File temp = new File(targetF.getAbsolutePath() + File.separator + file.getName());
			if (file.isDirectory()) {
				temp.mkdir();
				copy(file, temp);
			} else {
				FileInputStream fis = null;
				FileOutputStream fos = null;
				try {
					fis = new FileInputStream(file);
					fos = new FileOutputStream(temp);
					byte[] b = new byte[4096];
					int cnt = 0;
					while ((cnt = fis.read(b)) != -1) {
						fos.write(b, 0, cnt);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						fis.close();
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
	}

	public static void main(String[] args) {
		File file = new File("AssetDirectory.txt");
		if (file.isFile() == false) {
			System.err.println("AssetDirectory.txt is not founded");
			return;
		}
		String s;
		String data = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			while ((s = in.readLine()) != null) {
				data += s;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		File dir = new File(data);
		if (dir.isDirectory() == false) {
			System.err.println("Android Asset Directory Didn't Found");
			return;
		}

		File Res = new File("../Resource");
		
	}

}
