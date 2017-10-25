package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.media.Media;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Point;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
//import com.mycompany.a2.commands.*;
//import com.mycompany.a2
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.a3.commands.*;
import com.mycompany.a3.sound.Sound;

public class Game extends Form implements Runnable {
  private GameWorld gw;
  private MapView mv;
  private ScoreView sv;
  private boolean playMode; 
//  private Media m;
//  public Sound bgSound = new Sound("background.WAV", gw);
  private int duration = 1000;
  private Toolbar myToolbar = new Toolbar();
  //private TextField myTF = new TextField(); 
  //private Form topContainer = new Form(new BoxLayout(BoxLayout.X_AXIS));
  private Container topContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//  private Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
//  private FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
  private Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
  private Container bottomContainer = new Container(new FlowLayout(Component.CENTER));
  //private Container centerContainer = new Container();
  
  private Button leftButton = new Button();
  private Button rightButton = new Button();
  private Button exitButton = new Button();
  private Button brakeButton = new Button();
  private Button accelerateButton = new Button();
  private Button switchStrategyButton = new Button();
  private Button soundButton = new Button();
  private Button aboutButton = new Button();
  private Button helpButton = new Button();
  private Button pauseButton = new Button();
  private Button positionButton = new Button();
  
  //private SideCommand sideMenuCmd;
  private AccelerateCommand accelerateCmd;
  private SoundCommand soundCmd;
  private AboutCommand aboutCmd;
  private ExitCommand exitCmd;
  private HelpCommand helpCmd;
  private LeftCommand leftCmd;
  private RightCommand rightCmd;
  private BrakeCommand brakeCmd;
  private PositionCommand positionCmd;
  private PauseCommand pauseCmd;
  private FuelCanCommand fuelCmd;
  private SwitchStrategyCommand swStCmd;
  
  public Game() {
	playMode = true;
    gw = new GameWorld();
    mv = new MapView(gw);
    sv = new ScoreView(gw);
    Point pCmpRelPrnt;
    
    this.setLayout(new BorderLayout());
    //layout of containers in the form
    this.add(BorderLayout.WEST,leftContainer).add(BorderLayout.NORTH,topContainer).add(BorderLayout.SOUTH,bottomContainer).add(BorderLayout.CENTER,mv); 
    /******************************************/
    //tool bar with title
    setToolbar(myToolbar); 
    myToolbar.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.WHITE));
    myToolbar.setTitle("Car Racing Game");
    myToolbar.setTitleCentered(true);
    /******************************************/
    //top container with score view
    topContainer.add(sv);
    /******************************************/
    //left container with buttons
    leftContainer.getAllStyles().setPadding(Component.TOP, 50);
    leftContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
    leftContainer.add(switchStrategyButton).add(accelerateButton).add(brakeButton).add(rightButton).add(leftButton).add(exitButton);
    
    /* Buttons: missing setFgTransparency */
    switchStrategyButton.getAllStyles().setBgColor(ColorUtil.BLUE);
    switchStrategyButton.getAllStyles().setBgTransparency(200);
    accelerateButton.getAllStyles().setBgColor(ColorUtil.BLUE);
    accelerateButton.getAllStyles().setBgTransparency(200);
    brakeButton.getAllStyles().setBgColor(ColorUtil.BLUE);
    brakeButton.getAllStyles().setBgTransparency(200);
    rightButton.getAllStyles().setBgColor(ColorUtil.BLUE);
    rightButton.getAllStyles().setBgTransparency(200);
    leftButton.getAllStyles().setBgColor(ColorUtil.BLUE);
    leftButton.getAllStyles().setBgTransparency(200);
    exitButton.getAllStyles().setBgColor(ColorUtil.BLUE);
    exitButton.getAllStyles().setBgTransparency(200);
    positionButton.getAllStyles().setBgColor(ColorUtil.BLUE);
    positionButton.getAllStyles().setBgTransparency(200);
    pauseButton.getAllStyles().setBgColor(ColorUtil.BLUE);
    pauseButton.getAllStyles().setBgTransparency(200);
    /******************************************/
    //bottom container with buttons
    bottomContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
    bottomContainer.add(pauseButton).add(positionButton);
    /******************************************/
    //centerContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
    //centerContainer.add(mv);
    /******************************************/
    
//    x = x - getParent().getAbsoluteX();
//    y = y - getParent().getAbsoluteY();
//    Point pPtrRelPrnt = new Point(x, y);
//    Point pCmpRelPrnt = new Point(getX(), getY());
    //Commands
    accelerateCmd = new AccelerateCommand("Accelerate", gw);
    soundCmd = new SoundCommand("Sound ON/OFF", gw);
    aboutCmd = new AboutCommand("About", gw);
    exitCmd = new ExitCommand("Exit", gw);
    helpCmd = new HelpCommand("Help", gw);
    leftCmd = new LeftCommand("Left", gw);
    rightCmd = new RightCommand("Right", gw);
    brakeCmd = new BrakeCommand("Brake", gw);
    positionCmd = new PositionCommand("Position", gw, mv);
    pauseCmd = new PauseCommand("Pause", gw, this);
    fuelCmd = new FuelCanCommand("Pickup Fuel Can", gw);
    swStCmd = new SwitchStrategyCommand("Switch Strategy", gw);
    
    //Functionality of the Toolbar
    myToolbar.addCommandToSideMenu(accelerateCmd);
    myToolbar.addCommandToSideMenu(soundCmd);
    myToolbar.addCommandToSideMenu(aboutCmd);
    myToolbar.addCommandToSideMenu(exitCmd);
    myToolbar.addCommandToRightBar(helpCmd);
    
    //Functionality of the Buttons
    accelerateButton.setCommand(accelerateCmd);
    soundButton.setCommand(soundCmd);
    aboutButton.setCommand(aboutCmd);
    exitButton.setCommand(exitCmd);
    helpButton.setCommand(helpCmd);
    leftButton.setCommand(leftCmd);
    switchStrategyButton.setCommand(swStCmd);
    accelerateButton.setCommand(accelerateCmd);
    brakeButton.setCommand(brakeCmd);
    rightButton.setCommand(rightCmd);
    leftButton.setCommand(leftCmd);
    pauseButton.setCommand(pauseCmd);
    positionButton.setCommand(positionCmd);
    
    //Functionality of the Key Binding
    this.addKeyListener('a', accelerateCmd);
    this.addKeyListener('x', exitCmd);
    this.addKeyListener('b', brakeCmd);
    this.addKeyListener('l', leftCmd);
    this.addKeyListener('r', rightCmd);
    this.addKeyListener('f', fuelCmd);
    
//    gw.setHeight(mv.getHeight());
//    gw.setWidth(mv.getWidth());
//    System.out.println("height: " + (mv.getHeight()));
//    System.out.println("width: " + (mv.getWidth()));
    pCmpRelPrnt = new Point(mv.getWidth(), mv.getHeight());
    gw.init(mv.getWidth(), mv.getHeight());
    gw.addObserver(mv);
    gw.addObserver(sv);
    gw.notifyObservers();
    this.setVisible(true);
    this.show();
    
    UITimer timer = new UITimer(this);
    timer.schedule(duration, true, this);
  }

  public boolean changeMode(String text) {
	  gw.pauseSound();
	  //PauseCommand.setText(text);
	  return true;
  }
  public boolean getPlayMode() {
	  return this.playMode;
  }
  public void setPlayMode() {
	  this.playMode = !this.playMode;
  }
  public boolean changePauseStatus(String text) {
	  gw.pauseSound();
	  PauseCommand.setText(text);
	  return true;
  }
  public void run() {
	  if (playMode) {
		  gw.isPlaying = true;
		  gw.update(duration);
		  if (gw.getSoundMenu())
		  		gw.bgSound.play();
		  mv.repaint();
	  } else {
		  PointerListener myPL = new PointerListener();
		  mv.addPointerPressedListener(myPL);
		  Point pointerLoc = myPL.getpPtrRelPrnt();
		  if (pointerLoc != null) {
			  mv.setSelected(myPL.getpPtrRelPrnt());
			  mv.repaint();
		  }
	  }
	  
  }
  public void switchPlayMode(boolean mode) {
	  if (mode) {
//		  pauseCmd = new PauseCommand("Pause", gw, this);
		  
		  accelerateCmd.setEnabled(true);
		  soundCmd.setEnabled(true);
		  exitCmd.setEnabled(true);
		  leftCmd.setEnabled(true);
		  rightCmd.setEnabled(true);
		  brakeCmd.setEnabled(true);
		  positionCmd.setEnabled(false);
		  fuelCmd.setEnabled(true);
		  swStCmd.setEnabled(true);
		  
		  leftButton.setEnabled(true);
		  rightButton.setEnabled(true);
		  exitButton.setEnabled(true);
		  brakeButton.setEnabled(true);
		  accelerateButton.setEnabled(true);
		  switchStrategyButton.setEnabled(true);
		  soundButton.setEnabled(true);
		  positionButton.setEnabled(false);
		  
		  this.addKeyListener('a', accelerateCmd);
		  this.addKeyListener('x', exitCmd);
		  this.addKeyListener('b', brakeCmd);
		  this.addKeyListener('l', leftCmd);
		  this.addKeyListener('r', rightCmd);
		  this.addKeyListener('f', fuelCmd);
	  } else {
//		  pauseCmd = new PauseCommand("Play", gw, this);
		  
		  accelerateCmd.setEnabled(false);
		  soundCmd.setEnabled(false);
		  exitCmd.setEnabled(false);
		  leftCmd.setEnabled(false);
		  rightCmd.setEnabled(false);
		  brakeCmd.setEnabled(false);
		  positionCmd.setEnabled(true);
		  fuelCmd.setEnabled(false);
		  swStCmd.setEnabled(false);
		  
		  leftButton.setEnabled(false);
		  rightButton.setEnabled(false);
		  exitButton.setEnabled(false);
		  brakeButton.setEnabled(false);
		  accelerateButton.setEnabled(false);
		  switchStrategyButton.setEnabled(false);
		  soundButton.setEnabled(false);
		  positionButton.setEnabled(true);
		  
		  this.removeKeyListener('a', accelerateCmd);
		  this.removeKeyListener('x', exitCmd);
		  this.removeKeyListener('b', brakeCmd);
		  this.removeKeyListener('l', leftCmd);
		  this.removeKeyListener('r', rightCmd);
		  this.removeKeyListener('f', fuelCmd);
	  }
  }
}
  /*public void play() {
	  Label myLabel=new Label("Enter a Command:");
	  this.addComponent(myLabel);
	  final TextField myTextField=new TextField();
	  this.addComponent(myTextField);
	  this.show();
	  
	  myTextField.addActionListener(new ActionListener(){
		  int pylonNum;
		  char cmd;
		  public void actionPerformed(ActionEvent evt) {
			  String sCommand=myTextField.getText().toString();
			  myTextField.clear();
			  if 	(sCommand.charAt(0) == '1' || sCommand.charAt(0) == '2'|| 
					 sCommand.charAt(0) == '3' || sCommand.charAt(0) == '4'|| 
					 sCommand.charAt(0) == '5' || sCommand.charAt(0) == '6'|| 
					 sCommand.charAt(0) == '7' || sCommand.charAt(0) == '8'|| 
					 sCommand.charAt(0) == '9') {
				  		pylonNum = sCommand.charAt(0); 
				  		cmd = '#';
			  		}
			  else cmd = sCommand.charAt(0);
			  switch(cmd) {
			  	case 'a': 
			  		System.out.println("Accelerate!!");
			  		gw.accelerate();
			  		break;
			  	case 'b': 
			  		System.out.println("Brake!!");
			  		gw.brake();
			  		break;
			  	case 'l': 
			  		System.out.println("Left!");
			  		gw.steerLeft();
			  		break;
			  	case 'r': 
			  		System.out.println("Right!");
			  		gw.steerRight();
			  		break;
			  	case 'c': 
			  		System.out.println("We hit a car!");
			  		gw.carCollideCar();
			  		break;
			  	case '#': 
			  		System.out.println("We hit a pylon!");
			  		gw.carCollidePylon(pylonNum);
			  		break;
			  	case 'f': 
			  		System.out.println("We got more fuel!");
			  		gw.carCollideFuelCan();
			  		break;
			  	case 'g': 
			  		System.out.println("We hit a bird!");
			  		gw.birdCollideCar();
			  		break;
			  	case 't': 
			  		System.out.println("Game Clock is Ticking!");
			  		gw.update();
			  		break;
			  	case 'd': 
			  		System.out.println("Our Status:" + gw.toString());
			  		break;
			  	case 'm':
			  		System.out.println("Here's the map!");
			  		gw.displayMap();
			  		break;
			  	case 'x': 
			  		System.out.println("Would you like to exit? Y/N");
			  		break;
			  	case 'y':
			  		System.out.println("System exiting!");
			  		System.exit(0);
			  	case 'n':
			  		System.out.println("Let's continue the game!");
			  		break;
			  } //switch
		  } //actionPerformed
	  } //new ActionListener()
	); //addActionListener
  } //play */