package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import utils.DBUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("漢字勉強ｖ1.0");
		setFont(new Font("MS Gothic", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_Title = new JLabel("\u6F22\u5B57\u52C9\u5F37");
		lbl_Title.setFont(new Font("MS Gothic", Font.PLAIN, 60));
		lbl_Title.setBounds(91, 29, 240, 114);
		contentPane.add(lbl_Title);
		
		JLabel lblChooseLevel = new JLabel("Choose level");
		lblChooseLevel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChooseLevel.setBounds(30, 174, 144, 44);
		contentPane.add(lblChooseLevel);
		
		JComboBox cb_level = new JComboBox();
		cb_level.setBounds(186, 190, 103, 22);
		contentPane.add(cb_level);
		loadLevelBox(cb_level);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnStart.setBounds(313, 189, 97, 25);
		contentPane.add(btnStart);
	}
	
	public void loadLevelBox(JComboBox<String> cb) {
		con = DBUtils.makeConnection();
		if(con != null) {
			String sql = "SELECT * FROM tbl_Class";
			try {
				stm = con.prepareStatement(sql);
				rs = stm.executeQuery();
				
				//Load result to combo box
				while(rs.next()) {
					cb.addItem(rs.getString(2));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				
			}
			
		}
	}
}
