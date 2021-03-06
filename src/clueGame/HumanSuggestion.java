package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HumanSuggestion extends JDialog{
	JComboBox<String> personBox;
	JComboBox<String> weaponBox;
	String room;
	int playerIndex = 0;
	
	public HumanSuggestion(String room, int playerIndex){
		this.room = room;
		this.playerIndex = playerIndex;
		setTitle("Make a Guess");
		setSize(300,400);
		setLayout(new GridLayout(4,1));
		
		JPanel roomSuggestion = createRoomSuggestion(room);
		add(roomSuggestion);
		
		JPanel personPanel = new JPanel();
		personPanel.setLayout(new GridLayout(1,2));
		JPanel personSuggestion = createPersonSuggestion();
		JPanel personOptions = createPersonBox();
		personPanel.add(personSuggestion);
		personPanel.add(personOptions);
		add(personPanel);
		
		JPanel weaponPanel = new JPanel();
		weaponPanel.setLayout(new GridLayout(1,2));
		JPanel weaponSuggestion = createWeaponSuggestion();
		JPanel weaponOptions = createWeaponBox();
		weaponPanel.add(weaponSuggestion);
		weaponPanel.add(weaponOptions);
		add(weaponPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		JPanel submitButton = createSubmitButton(room);
		JPanel cancelButton = createCancelButton();
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel);
		
	}
	
	private JPanel createPersonSuggestion() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		JLabel person = new JLabel("Person");
		panel.add(person);
		return panel;
	}
	
	private JPanel createPersonBox() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		personBox = new JComboBox<String>();
		for( Player p: Board.getInstance().getPlayers()) {
			personBox.addItem(p.getPlayerName());
		}
		
		panel.add(personBox);
		return panel;
	}
	
	private JPanel createWeaponSuggestion() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		JLabel weapon = new JLabel("Weapon");
		panel.add(weapon);
		return panel;
	}
	
	private JPanel createWeaponBox() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		weaponBox = new JComboBox<String>();
		for( Card c : Board.getInstance().getDeck()) {
			if(c.getCardType() == CardType.WEAPON) {
				weaponBox.addItem(c.getCardName());
			}
		}
		
		panel.add(weaponBox);
		return panel;
	}
	
	private JPanel createRoomSuggestion(String roomName) {
		JPanel roomChoice = new JPanel();
		roomChoice.setLayout(new GridLayout(1,2));
		JLabel roomLeftLabel = new JLabel("Room");
		JLabel roomRightLabel = new JLabel(roomName);
		roomChoice.add(roomLeftLabel);
		roomChoice.add(roomRightLabel);
		return roomChoice;
	}
	
	private JPanel createSubmitButton(String roomName) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Solution solution = new Solution((String)personBox.getSelectedItem(), roomName, (String)weaponBox.getSelectedItem());
				Player p = Board.getInstance().getPlayers().get(playerIndex);
				Card c = Board.getInstance().handleSuggestion(solution,p);
				if (c != null) {
					ClueGame.updateSuggestion(solution.toString(), c.getCardName());
				} else {
					ClueGame.updateSuggestion(solution.toString(), "No new clue");
				}
			//	Board.getInstance().nextPlayer();
				dispose();
			}
		});
		panel.add(submitButton);
		return panel;
	}
	
	private JPanel createCancelButton() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Board.getInstance().submitSuggestion = false;
				dispose();
			}
		});
		panel.add(cancelButton);
		return panel;
	}
	
	
}
