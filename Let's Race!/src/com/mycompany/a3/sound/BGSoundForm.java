package com.mycompany.a3.sound;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class BGSoundForm extends Form implements ActionListener {
	private BGSound bgSound;
	private boolean bPause = false;
	
	public BGSoundForm() {
		Button bButton = new Button("Pause/Play");
		//style and add bButton to the form
		bButton.addActionListener(this);
//		bgSound = new BGSound("background.wav", this.gw);
//		bgSound.play();
	}
	
	public void actionPerformed(ActionEvent e) {
		bPause = !bPause;
		if 	(bPause)	bgSound.pause();
		else 			bgSound.play();
	}
}
