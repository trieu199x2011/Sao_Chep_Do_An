import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Giao_dien extends JFrame {

	private JPanel contentPane;
	private JTextField textSearch;
	private Vector<RAW_File> Files = new Vector<RAW_File>();
	private double rate = 0.2;
	private int numberSentence = 5;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private DefaultListModel<String> model2 = new DefaultListModel<String>();
	private Vector<SoSanh_Text> Coppy = new Vector<SoSanh_Text>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Giao_dien frame = new Giao_dien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Giao_dien() {
		setTitle("Chương trình phát hiện sao chép đồ án");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1171, 618);
		
		GuideDialog Guide = new GuideDialog();
		Guide.setVisible(false);
		SettingDialog setting = new SettingDialog();
		setting.setVisible(false);
		ViewDialog View = new ViewDialog();
		View.setVisible(false);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JList<String> list_Project = new JList<String>();
		JList<String> list_Coppy = new JList<String>();
		list_Coppy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					String a = list_Coppy.getSelectedValue();
					for(SoSanh_Text t : Coppy) {
						if(t.getNameOfCoppy()==a) {
							View.setTextA(t.getNameOfFileA());
							View.setTextB(t.getNameOfFileB());
							View.setTextCoppy(t.getKq());
							View.setVisible(true);
							return;
						}
					}
				}
			}
		});
		list_Coppy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("c:\\Do_An\\"));
				fs.setMultiSelectionEnabled(true);
				
				fs.setDialogTitle("Open project");
				fs.setFileFilter(new FileTypeFilter(".txt", "Text File"));
				fs.setFileFilter(new FileTypeFilter(".doc", "Word File"));
				fs.setFileFilter(new FileTypeFilter(".docx", "Word File"));
				
				int result = fs.showOpenDialog(null);
				
				if(result==JFileChooser.APPROVE_OPTION) {
					if(!Files.isEmpty()) {
						int kt = JOptionPane.showConfirmDialog(mntmOpen,"Bạn có muốn xóa dữ liệu cũ không?");
						if(kt==JOptionPane.YES_OPTION) {
							Files.clear();
							model.clear();
						}
						else if(kt==JOptionPane.CANCEL_OPTION)
							{
								actionPerformed(e);
								return;
							}
					}
					File[] f = fs.getSelectedFiles();
					RAW_File g;
					for(File i : f) {
						g = new RAW_File(i.getAbsolutePath());
						if(g.getFormat().contains("doc") || g.getFormat().contains("txt")) {
						Files.add(g);
						//System.out.println(i.getAbsolutePath());
						
						
						model.addElement(i.getAbsolutePath());
						list_Project.setModel(model);
						}
					}
				}
				
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmSetting = new JMenuItem("Setting");
		mntmSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setting.setChecked(false);
				setting.setTextRare(String.valueOf(rate*100));
				setting.setTextNumberSentence(String.valueOf(numberSentence));
				setting.setVisible(true);
				
				if(setting.isChecked()) {
					rate = Double.parseDouble(setting.getTextRare())/100;
					numberSentence = Integer.parseInt(setting.getTextNumberSentence());
				}
				System.out.println(rate+" "+numberSentence);
			}
		});
		mnHelp.add(mntmSetting);
		
		JMenuItem mntmGuide = new JMenuItem("Guide");
		mntmGuide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guide.setVisible(true);
			}
		});
		mnHelp.add(mntmGuide);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_Project = new JPanel();
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textSearch = new JTextField();
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				DefaultListModel<String> model_clone = new DefaultListModel<String>();
				String text = textSearch.getText();
				for(RAW_File f : Files) {
					if(f.getNameOfFile().contains(text))
					model_clone.addElement(f.getNameOfFile());
				}
				list_Project.setModel(model_clone);
			}
		});
		textSearch.setColumns(20);
		
		
		
		JLabel lblDanhSch = new JLabel("List project");
		lblDanhSch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_panel_Project = new GroupLayout(panel_Project);
		gl_panel_Project.setHorizontalGroup(
			gl_panel_Project.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Project.createSequentialGroup()
					.addGroup(gl_panel_Project.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Project.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSearch)
							.addGap(5)
							.addComponent(textSearch, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
						.addGroup(gl_panel_Project.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDanhSch, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
						.addComponent(list_Project, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_Project.setVerticalGroup(
			gl_panel_Project.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Project.createSequentialGroup()
					.addGroup(gl_panel_Project.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Project.createSequentialGroup()
							.addGap(6)
							.addComponent(lblSearch))
						.addGroup(gl_panel_Project.createSequentialGroup()
							.addGap(5)
							.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(34)
					.addComponent(lblDanhSch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(list_Project, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
		);
		panel_Project.setLayout(gl_panel_Project);
		
		JPanel panel_Coppy = new JPanel();
		
		JLabel lblDanSch = new JLabel("Danh sách các đồ án bị nghi ngờ sao chép");
		lblDanSch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDanSch.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_Button = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_Project, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_Button, GroupLayout.PREFERRED_SIZE, 812, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_Coppy, GroupLayout.PREFERRED_SIZE, 812, GroupLayout.PREFERRED_SIZE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_Project, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(panel_Coppy, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_Button, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Files.size()<2) {
					JOptionPane.showMessageDialog(btnTest, "Hãy thêm đồ án vào danh sách", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					model2.clear();
					int d = Files.size();
					for(int i=0;i<d;i++)
					{
						for(int j=i+1;j<d;j++) {
							SoSanh_Text SS = new SoSanh_Text(Files.elementAt(i).getNameOfFile(), Files.elementAt(j).getNameOfFile(), rate, numberSentence);
							SS.Test();
							if(SS.isCoppy()) {
								Coppy.add(SS);
								model2.addElement(SS.getNameOfCoppy());
							}
						}
					}
					if(model2.isEmpty()) model2.addElement("(Không phát hiện sự sao chép đồ án)");
					list_Coppy.setModel(model2);
				}
			}
		});
		btnTest.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Files.isEmpty()) {
					return;
				}
				else
				{
					int x = JOptionPane.showConfirmDialog(btnClear, "Xóa toàn bộ danh sách và dữ liệu?", "Thông báo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
					if(x==JOptionPane.OK_OPTION) {
						Files.clear();
						model.clear();
						model2.clear();
						list_Project.setModel(model);
						list_Coppy.setModel(model2);
					}
				}
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_panel_Button = new GroupLayout(panel_Button);
		gl_panel_Button.setHorizontalGroup(
			gl_panel_Button.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_Button.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnTest, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addGap(201)
					.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addGap(66))
		);
		gl_panel_Button.setVerticalGroup(
			gl_panel_Button.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_Button.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addGroup(gl_panel_Button.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTest, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_Button.setLayout(gl_panel_Button);
		
		
		GroupLayout gl_panel_Coppy = new GroupLayout(panel_Coppy);
		gl_panel_Coppy.setHorizontalGroup(
			gl_panel_Coppy.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Coppy.createSequentialGroup()
					.addGroup(gl_panel_Coppy.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblDanSch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(list_Coppy, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE))
					.addGap(1))
		);
		gl_panel_Coppy.setVerticalGroup(
			gl_panel_Coppy.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_Coppy.createSequentialGroup()
					.addComponent(lblDanSch)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(list_Coppy, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE))
		);
		panel_Coppy.setLayout(gl_panel_Coppy);
		contentPane.setLayout(gl_contentPane);
	}
}
