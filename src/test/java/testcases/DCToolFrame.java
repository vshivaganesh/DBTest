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
	private JLabel source_sql;
	private JLabel target_sql;
	private JTextField t_source_sql;
	private JTextField t_target_sql;
	private JButton submit;
	private JButton reset;
	private JLabel result;

	public DCToolFrame() {

		setTitle("Data Comparison Tool");
		setBounds(200, 90, 900, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		c = getContentPane();
		c.setLayout(null);

		title = new JLabel("Data Comparison Tool");
		title.setFont(new Font("Arial", Font.PLAIN, 30));
		title.setSize(300, 30);
		title.setLocation(300, 30);
		c.add(title);

		source_sql = new JLabel("Source SQL : ");
		source_sql.setFont(new Font("Arial", Font.PLAIN, 15));
		source_sql.setSize(150, 40);
		source_sql.setLocation(100, 100);
		c.add(source_sql);

		t_source_sql = new JTextField();
		t_source_sql.setFont(new Font("Arial", Font.PLAIN, 15));
		t_source_sql.setSize(500, 40);
		t_source_sql.setLocation(200, 100);
		c.add(t_source_sql);

		target_sql = new JLabel("Target SQL : ");
		target_sql.setFont(new Font("Arial", Font.PLAIN, 15));
		target_sql.setSize(150, 40);
		target_sql.setLocation(100, 200);
		c.add(target_sql);

		t_target_sql = new JTextField();
		t_target_sql.setFont(new Font("Arial", Font.PLAIN, 15));
		t_target_sql.setSize(500, 40);
		t_target_sql.setLocation(200, 200);
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
				result.setText(DbManager.getOracleQuery(t_source_sql.getText(), t_target_sql.getText()));
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
