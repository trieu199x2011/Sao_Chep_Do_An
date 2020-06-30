import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SettingDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textRare;
	private JTextField textNumberSentence;
	private boolean checked = false;
	
	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public String getTextRare() {
		return textRare.getText();
	}

	public void setTextRare(String Text) {
		this.textRare.setText(Text);
	}
	
	public String getTextNumberSentence() {
		return textNumberSentence.getText();
	}
	
	public void setTextNumberSentence(String Text) {
		this.textNumberSentence.setText(Text);
	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			SettingDialog dialog = new SettingDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public SettingDialog() {
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 598, 313);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblTiuChunSao = new JLabel("Tiêu chuẩn sao chép:");
		lblTiuChunSao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textRare = new JTextField();
		textRare.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9'))
                        && (caracter != '\b') && caracter != '.') {
                    e.consume();
                }
			}
		});
		textRare.setColumns(10);
		
		JLabel label = new JLabel("%");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblSCuGing = new JLabel("Số câu giống nhau liên tiếp để được coi là 1 đoạn sao chép:");
		lblSCuGing.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textNumberSentence = new JTextField();
		textNumberSentence.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9'))
                        && (caracter != '\b') /*&& caracter !='.'*/) {
                    e.consume();
                }
			}
		});
		textNumberSentence.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(textRare, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTiuChunSao, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSCuGing, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addComponent(textNumberSentence, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(214, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTiuChunSao)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textRare, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(18)
					.addComponent(lblSCuGing, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textNumberSentence, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(125, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Integer.parseInt(textNumberSentence.getText().trim())==0) {
							JOptionPane.showMessageDialog(okButton, "Số câu phải lớn hơn 0");
							return;
						}
						else if(Double.parseDouble(textRare.getText())==0) {
							JOptionPane.showMessageDialog(okButton, "Phần trăm giống nhau không được là 0");
						}
						else if(Double.parseDouble(textRare.getText())>100) {
							JOptionPane.showMessageDialog(okButton, "Phần trăm giống nhau không được vượt quá 100");
						}
						else if(Double.parseDouble(textRare.getText())<10) {
							int x = JOptionPane.showConfirmDialog(okButton, "Phần trăm giống nhau quá nhỏ, bạn vẫn muốn tiếp tục?", "Thông báo",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
							if(x==JOptionPane.NO_OPTION) return;
						}
						else if(Integer.parseInt(textNumberSentence.getText().trim())<5) {
							int x = JOptionPane.showConfirmDialog(okButton, "Số câu quá nhỏ, bạn vẫn muốn tiếp tục?", "Thông báo",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
							if(x==JOptionPane.NO_OPTION) return;
						}
						JOptionPane.showMessageDialog(okButton, "Đã lưu thay đổi","Thông báo", JOptionPane.INFORMATION_MESSAGE);
						checked = true;
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						checked = false;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
