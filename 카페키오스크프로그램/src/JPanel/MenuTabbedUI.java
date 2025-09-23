package JPanel;

import javax.swing.*;
import Controller.Controller;
import VO.Menu;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MenuTabbedUI extends JFrame {

	public JPanel createMenuPanel(List<Menu> menus, String category, JPanel parentPanel, Controller c) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2, 20, 20));

		for (Menu m : menus) {
			if (category.equals(m.getCategory())) { 
				JButton btnMenu = new JButton(m.toString());

				btnMenu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						QuantityDialog qDialog = new QuantityDialog(parentPanel, m.getName());
						qDialog.setVisible(true);

						int quantity = qDialog.getQuantity();
						if (quantity > 0) {
							c.cart.addItem(m, quantity);
							JOptionPane.showMessageDialog(parentPanel, m.getName() + " x " + quantity + " 담았습니다!");
						} else {
							JOptionPane.showMessageDialog(parentPanel, "수량 선택이 취소되었습니다.");
						}
					}
				});

				panel.add(btnMenu);
			}
		}

		return panel;
	}
}
