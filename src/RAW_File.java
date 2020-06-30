import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
 
public class RAW_File{
	private String NameOfFile;
	private String Format;
	private String Text;
	
	private String Link;
	public RAW_File()
	{
		
	}
	public RAW_File(String NameOfFile)
	{
		this.NameOfFile = NameOfFile;
		String[] s = NameOfFile.split("[.]");
		for(String w : s)
		{
			this.Format = w;
		}
		this.Text = Read_File();
	}
	public String Read_File()
	{
		String str = "";
		try {
			if(Format.contains("txt")) {
				File fileDir = new File(NameOfFile);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(
	                      new FileInputStream(fileDir), "UTF8"));
				String strx;
				while ((strx = in.readLine()) != null) {
					str+=strx+"\n";
				}
	            in.close();
			}
			if(Format.contains("doc"))
			{
				FileInputStream iStream = new FileInputStream(new File(NameOfFile));
				XWPFDocument doc = new XWPFDocument(iStream);
				XWPFWordExtractor wx = new XWPFWordExtractor(doc);
				str = wx.getText();
				//iStream.close();
			}
			}
		    catch (UnsupportedEncodingException e)
		    {
				System.out.println(e.getMessage());
		    }
		    catch (IOException e)
		    {
				System.out.println(e.getMessage());
		    }
		    catch (Exception e)
		    {
				System.out.println(e.getMessage());
		    }
		return str;
		
	}
	
	public String[] Tach_cau()
	{
		String[] str = Text.split("[.]");
		return str;
	}
	public String getNameOfFile() {
		return NameOfFile;
	}
	public void setNameOfFile(String nameOfFile) {
		NameOfFile = nameOfFile;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public String getLink() {
		return Link;
	}
	public void setLink(String link) {
		Link = link;
	}
	
	public static void main(String[] args){
		RAW_File x = new RAW_File("C:\\Users\\ASUS\\eclipse-workspace\\Sao_Chep_Do_An\\DeCuong.docx");
		String[] s = x.Tach_cau();
		for(String w : s)
		{
			System.out.print(w+".");
		}
	}
	
}