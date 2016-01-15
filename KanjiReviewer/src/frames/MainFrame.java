package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import tbl_class.ClassObject;
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;

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
		
		JLabel lbl_Title = new JLabel("\u6F22\u5B57\u52C9\u5F37");
		lbl_Title.setFont(new Font("MS Gothic", Font.PLAIN, 60));
		
		JLabel lblChooseLevel = new JLabel("Choose level");
		lblChooseLevel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JComboBox cb_level = new JComboBox();
		cb_level.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loadLevelBox(cb_level);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, cb_level.getSelectedItem().toString());
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(lblChooseLevel, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(cb_level, 0, 103, Short.MAX_VALUE)
					.addGap(37)
					.addComponent(btnStart, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addGap(33))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(105)
					.addComponent(lbl_Title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(112))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl_Title, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cb_level, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnStart)
						.addComponent(lblChooseLevel, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
					.addGap(97))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void loadLevelBox(JComboBox<ClassObject> cb) {
		con = DBUtils.makeConnection();
		if(con != null) {
			String sql = "SELECT * FROM tbl_Class";
			try {
				stm = con.prepareStatement(sql);
				rs = stm.executeQuery();
				
				//Add item to combo box
				while(rs.next()) {
					ClassObject obj = new ClassObject();
					obj.setId(rs.getString(1));
					obj.setLevel(rs.getString(2));
					
					cb.addItem(obj);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					if(rs!=null)
						rs.close();
					if(stm!=null)
						stm.close();
					if(con!=null)
						con.close();
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			
		}
	}
}
