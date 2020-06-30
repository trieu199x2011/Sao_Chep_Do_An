import java.util.LinkedList;
import java.util.Queue;

public class SoSanh_Text {
	private String NameOfFileA;
	private String NameOfFileB;
	private String kq;
	private String NameOfCoppy;
	
	private double rate;
	private int numberSentence;
	
	private String[] A;
	private String[] B;
	
	private Queue<String> Clone = new LinkedList<String>();
	
	public String getNameOfFileA() {
		return NameOfFileA;
	}

	public void setNameOfFileA(String nameOfFileA) {
		NameOfFileA = nameOfFileA;
	}

	public String getNameOfFileB() {
		return NameOfFileB;
	}

	public void setNameOfFileB(String nameOfFileB) {
		NameOfFileB = nameOfFileB;
	}

	public String getKq() {
		return kq;
	}

	public void setKq(String kq) {
		this.kq = kq;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getNumberSentence() {
		return numberSentence;
	}

	public void setNumberSentence(int numberSentence) {
		this.numberSentence = numberSentence;
	}

	public String[] getA() {
		return A;
	}

	public void setA(String[] a) {
		A = a;
	}

	public String[] getB() {
		return B;
	}

	public void setB(String[] b) {
		B = b;
	}

	public Queue<String> getClone() {
		return Clone;
	}

	public void setClone(Queue<String> clone) {
		Clone = clone;
	}
	
	public String getNameOfCoppy() {
		return this.NameOfCoppy;
	}

	public SoSanh_Text(String NameOfFileA, String NameOfFileB, double rate, int numberSentence)
	{
		this.NameOfFileA = NameOfFileA;
		this.NameOfFileB = NameOfFileB;
		this.NameOfCoppy = NameOfFileA + " và " + NameOfFileB;
		this.kq = "...\n";
		this.rate = rate;
		this.numberSentence = numberSentence;
		RAW_File TextA = new RAW_File(NameOfFileA);
		RAW_File TextB = new RAW_File(NameOfFileB);
		
		this.A = TextA.Tach_cau();
		this.B = TextB.Tach_cau();
		
		if(A.length>B.length)
		{
			String[] C = this.A;
			this.A = this.B;
			this.B= C;
		}
	}

	public SoSanh_Text(RAW_File A, RAW_File B, double rate, int numberSentence)
	{
		this.NameOfFileA = A.getNameOfFile();
		this.NameOfFileB = B.getNameOfFile();
		this.NameOfCoppy = NameOfFileA + " và " + NameOfFileB;
		this.kq = "...\n";
		this.rate = rate;
		this.numberSentence = numberSentence;
		
		this.A = A.Tach_cau();
		this.B = B.Tach_cau();
		
		if(this.A.length>this.B.length)
		{
			String[] C = this.A;
			this.A = this.B;
			this.B = C;
		}
	}
	public String Test()
	{
		boolean check = false;
		
		for(int i=0; i<=A.length-1;)
		{
			for(int j=0;j<=B.length-1;j++)
			{
				if(A[i].equalsIgnoreCase(B[i]))
				{
					Clone.add(A[i]);
					i++;
					check = true;
				}
				else if(Clone.size()>=numberSentence)
				{
					while(!Clone.isEmpty())
					{
						kq+=Clone.remove()+".";
					}
					kq+="\n...\n";
				}
				else
				{
					Clone.clear();
				}
			}
		if(Clone.size()>=numberSentence) {
			while(!Clone.isEmpty())
			{
				kq+=Clone.remove()+".";
			}
			kq+="\n...\n";
		}
		Clone.clear();
		if(!check) i++;
		check = false;
		}
		return kq;
	}
	
	public boolean isCoppy()
	{
		double Rate = rate * Math.min(A.length, B.length);
		if(kq!="\n...\n" && kq.length()>=Rate) return true;
		return false;
	}
	
	public static void main(String[] args)
	{
		SoSanh_Text DoAn = new SoSanh_Text("DoAn.txt", "DeCuong.docx", 0.2, 5);
		String s = DoAn.Test();
		System.out.print(s);
		
	}
	
}
