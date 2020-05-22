package testcases;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

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
	private JLabel result;
	private String[] db = { "Oracle", "MySql", "Snowflake" };

	public DCToolFrame() {

		setTitle("SDC Automation Tool");
		setBounds(200, 90, 900, 500);
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
		source_sql.setLocation(100, 125);
		c.add(source_sql);

		t_source_sql = new JTextField();
		t_source_sql.setFont(new Font("Arial", Font.PLAIN, 15));
		t_source_sql.setSize(500, 20);
		t_source_sql.setLocation(200, 125);
		c.add(t_source_sql);

		target_db = new JLabel("Target DB : ");
		target_db.setFont(new Font("Arial", Font.PLAIN, 15));
		target_db.setSize(100, 20);
		target_db.setLocation(100, 190);
		c.add(target_db);

		c_target_db = new JComboBox<String>(db);
		c_target_db.setFont(new Font("Arial", Font.PLAIN, 15));
		c_target_db.setSize(100, 20);
		c_target_db.setLocation(200, 190);
		c.add(c_target_db);

		target_sql = new JLabel("Target SQL : ");
		target_sql.setFont(new Font("Arial", Font.PLAIN, 15));
		target_sql.setSize(100, 20);
		target_sql.setLocation(100, 225);
		c.add(target_sql);

		t_target_sql = new JTextField();
		t_target_sql.setFont(new Font("Arial", Font.PLAIN, 15));
		t_target_sql.setSize(500, 20);
		t_target_sql.setLocation(200, 225);
		c.add(t_target_sql);

		submit = new JButton("Submit");
		submit.setFont(new Font("Arial", Font.PLAIN, 15));
		submit.setSize(100, 20);
		submit.setLocation(300, 300);
		submit.addActionListener(this);
		c.add(submit);

		reset = new JButton("Reset");
		reset.setFont(new Font("Arial", Font.PLAIN, 15));
		reset.setSize(100, 20);
		reset.setLocation(450, 300);
		reset.addActionListener(this);
		c.add(reset);

		result = new JLabel("");
		result.setFont(new Font("Arial", Font.BOLD, 20));
		result.setSize(700, 30);
		result.setLocation(100, 400);
		c.add(result);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submit) {

			try {
				DbDecide.dbSelect((String) c_source_db.getSelectedItem(), (String) c_target_db.getSelectedItem());
				result.setText(DbManager.dataCompare(t_source_sql.getText(), t_target_sql.getText()));
			} catch (ClassNotFoundException e1) {

				e1.printStackTrace();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		} else if (e.getSource() == reset) {

			String def = "";
			t_source_sql.setText(def);
			t_target_sql.setText(def);
		}

	}

}
