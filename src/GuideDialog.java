import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuideDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuideDialog dialog = new GuideDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuideDialog() {
		setBackground(Color.LIGHT_GRAY);
		setModal(true);
		setBounds(100, 100, 804, 425);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JTextPane txtpnGiiThiuChng = new JTextPane();
		txtpnGiiThiuChng.setBackground(SystemColor.control);
		txtpnGiiThiuChng.setEditable(false);
		txtpnGiiThiuChng.setText("Giới thiệu chương trình phát hiện sao chép đồ án:\r\n\r\nChương trình có khả năng phát hiện sự giống nhau của các đồ án, với tỷ lệ phần trăm giống nhau và số câu giống nhau liên tiếp để được coi là 1 đoạn sao chép do người dùng cài đặt trước.\r\n\r\nChọn: File -> Open để chọn các đồ án cần kiểm tra\r\nChọn: Help -> Setting để cài đặt các tiêu chí khi kiểm tra\r\n\r\nNút: Test: để kiểm trả toàn bộ các đồ án đã thêm\r\nNút: Delete: để xóa đồ án đã chọn khỏi danh sách\r\nNút: Clear: để làm trắng danh sách đồ án\r\n\r\nDouble click vào 1 kết quả để xem phần giống nhau và có thể mở các đồ án bị nghi ngờ sao chép\r\nNhập tên vào thanh tìm kiếm Search và bấm Enter để tìm kiếm 1 đồ án trong danh sách");
		txtpnGiiThiuChng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnGiiThiuChng, GroupLayout.PREFERRED_SIZE, 772, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnGiiThiuChng, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
