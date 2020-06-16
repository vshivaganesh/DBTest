package testcases;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DCToolFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Container c;
	private JLabel title;
	private JLabel source_db;
	private JComboBox<String> c_source_db;
	private JLabel source_sql;
	private JTextField t_source_sql;
	private JLabel target_db;
	private JComboBox<String> c_target_db;
	private JLabel target_sql;
	private JTextField t_target_sql;
	private JButton submit;
	private JButton reset;
	private JLabel result_label;
	private JTextArea result_actual;
	private String[] db = { "Snowflake", "Oracle", "MySql" };
	private JLabel s_snowflakeUrl;
	private JTextField t_s_snowflakeUrl;
	private JLabel s_snowflakeUser;
	private JTextField t_s_snowflakeUser;
	private JLabel s_snowflakePwd;
	private JTextField t_s_snowflakePwd;

	public DCToolFrame() {

		setTitle("SDC Automation Tool");
		setBounds(150, 100, 1000, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		c = getContentPane();
		c.setLayout(null);

		title = new JLabel("SDC Automation Tool");
		title.setFont(new Font("Arial", Font.PLAIN, 30));
		title.setSize(300, 30);
		title.setLocation(300, 30);
		c.add(title);

		source_db = new JLabel("Source DB : ");
		source_db.setFont(new Font("Arial", Font.PLAIN, 15));
		source_db.setSize(100, 20);
		source_db.setLocation(100, 90);
		c.add(source_db);

		c_source_db = new JComboBox<String>(db);
		c_source_db.setFont(new Font("Arial", Font.PLAIN, 15));
		c_source_db.setSize(100, 20);
		c_source_db.setLocation(200, 90);
		c.add(c_source_db);

		source_sql = new JLabel("Source SQL : ");
		source_sql.setFont(new Font("Arial", Font.PLAIN, 15));
		source_sql.setSize(100, 20);
		source_sql.setLocation(100, 120);
		c.add(source_sql);

		t_source_sql = new JTextField();
		t_source_sql.setFont(new Font("Arial", Font.PLAIN, 15));
		t_source_sql.setSize(500, 20);
		t_source_sql.setLocation(200, 120);
		c.add(t_source_sql);

		target_db = new JLabel("Target DB : ");
		target_db.setFont(new Font("Arial", Font.PLAIN, 15));
		target_db.setSize(100, 20);
		target_db.setLocation(100, 160);
		c.add(target_db);

		c_target_db = new JComboBox<String>(db);
		c_target_db.setFont(new Font("Arial", Font.PLAIN, 15));
		c_target_db.setSize(100, 20);
		c_target_db.setLocation(200, 160);
		c.add(c_target_db);

		target_sql = new JLabel("Target SQL : ");
		target_sql.setFont(new Font("Arial", Font.PLAIN, 15));
		target_sql.setSize(100, 20);
		target_sql.setLocation(100, 190);
		c.add(target_sql);

		t_target_sql = new JTextField();
		t_target_sql.setFont(new Font("Arial", Font.PLAIN, 15));
		t_target_sql.setSize(500, 20);
		t_target_sql.setLocation(200, 190);
		c.add(t_target_sql);

		s_snowflakeUrl = new JLabel("URL : ");
		s_snowflakeUrl.setFont(new Font("Arial", Font.PLAIN, 15));
		s_snowflakeUrl.setSize(100, 20);
		s_snowflakeUrl.setLocation(350, 90);
		c.add(s_snowflakeUrl);

		t_s_snowflakeUrl = new JTextField();
		t_s_snowflakeUrl.setFont(new Font("Arial", Font.PLAIN, 15));
		t_s_snowflakeUrl.setSize(100, 20);
		t_s_snowflakeUrl.setLocation(400, 90);
		c.add(t_s_snowflakeUrl);

		s_snowflakeUser = new JLabel("User Name : ");
		s_snowflakeUser.setFont(new Font("Arial", Font.PLAIN, 15));
		s_snowflakeUser.setSize(100, 20);
		s_snowflakeUser.setLocation(510, 90);
		c.add(s_snowflakeUser);

		t_s_snowflakeUser = new JTextField();
		t_s_snowflakeUser.setFont(new Font("Arial", Font.PLAIN, 15));
		t_s_snowflakeUser.setSize(100, 20);
		t_s_snowflakeUser.setLocation(600, 90);
		c.add(t_s_snowflakeUser);

		s_snowflakePwd = new JLabel("Password : ");
		s_snowflakePwd.setFont(new Font("Arial", Font.PLAIN, 15));
		s_snowflakePwd.setSize(100, 20);
		s_snowflakePwd.setLocation(720, 90);
		c.add(s_snowflakePwd);

		t_s_snowflakePwd = new JTextField();
		t_s_snowflakePwd.setFont(new Font("Arial", Font.PLAIN, 15));
		t_s_snowflakePwd.setSize(100, 20);
		t_s_snowflakePwd.setLocation(800, 90);
		c.add(t_s_snowflakePwd);
		

		submit = new JButton("Submit");
		submit.setFont(new Font("Arial", Font.PLAIN, 15));
		submit.setSize(100, 20);
		submit.setLocation(300, 240);
		submit.addActionListener(this);
		c.add(submit);

		reset = new JButton("Reset");
		reset.setFont(new Font("Arial", Font.PLAIN, 15));
		reset.setSize(100, 20);
		reset.setLocation(450, 240);
		reset.addActionListener(this);
		c.add(reset);

		result_label = new JLabel("Result : ");
		result_label.setFont(new Font("Arial", Font.BOLD, 20));
		result_label.setSize(100, 20);
		result_label.setLocation(100, 290);
		c.add(result_label);

		result_actual = new JTextArea("");
		result_actual.setFont(new Font("Calibri", Font.PLAIN, 15));
		result_actual.setSize(500, 40);
		result_actual.setLocation(100, 340);
		result_actual.setEditable(false);
		c.add(result_actual);

		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submit) {

			try {
				DbDecide.dbSelect((String) c_source_db.getSelectedItem(), (String) c_target_db.getSelectedItem(),
						t_s_snowflakeUrl.getText(), t_s_snowflakeUser.getText(), t_s_snowflakePwd.getText());
				result_actual.setText(DbManager.dataCompare(t_source_sql.getText(), t_target_sql.getText()));
			}

			catch (ClassNotFoundException e1) {

				e1.printStackTrace();
			}

			catch (SQLException e1) {

				e1.printStackTrace();
			}

		} else if (e.getSource() == reset) {

			String def = "";
			t_source_sql.setText(def);
			t_target_sql.setText(def);
		}

	}

}
