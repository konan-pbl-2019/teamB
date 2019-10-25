package template.quiz2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import framework.RWT.RWTButton;
import framework.RWT.RWTImage;
import framework.RWT.RWTVirtualController;
import framework.RWT.RWTVirtualKey;
import framework.RWT.RWTWidget;
import framework.gameMain.BaseScenarioGameContainer;

/**
 * クイズゲーム用画面
 * @author Nitta
 *
 */
public class QuizGameContainer extends BaseScenarioGameContainer {

	private RWTButton[] optionButtons = new RWTButton[4];

	private RWTImage gameover;
	private RWTImage clear;

	public QuizGameContainer() {
		super();
	}

	@Override
	public void build(GraphicsConfiguration gc) {
		super.build(gc);
		canvas.setRelativePosition(0.0f, 0.0f);		// 3D表示部の左上端
		canvas.setRelativeSize(0.0f, 1.0f);		// 3D表示部のサイズ
		addCanvas(canvas);


		dialog.setRelativePosition(0.2f, 0.28f);	// ダイアログの左上端
		dialog.setFont(new Font("", Font.PLAIN, 12));	// 文字のフォント
		dialog.setColor(Color.YELLOW);				// 文字の色
		addWidget(dialog);



		Font f = new Font("", Font.PLAIN, 40);

		optionButtons[0] = new RWTButton("1");
		optionButtons[0].setFont(f);
		optionButtons[0].setColor(Color.BLACK);
		optionButtons[0].setRelativePosition(0.2f, 0.466f);
		optionButtons[0].setRelativeHeight(0.163f);
		optionButtons[0].setRelativeWidth(0.25f);
		addSelectableWidget(optionButtons[0], 0, 0);

		optionButtons[1] = new RWTButton("2");
		optionButtons[1].setFont(f);
		optionButtons[1].setRelativePosition(0.56f, 0.466f);
		optionButtons[1].setRelativeHeight(0.165f);
		optionButtons[1].setRelativeWidth(0.25f);
		addSelectableWidget(optionButtons[1], 1, 0);

		optionButtons[2] = new RWTButton("3");
		optionButtons[2].setFont(f);
		optionButtons[2].setRelativePosition(0.2f, 0.672f);
		optionButtons[2].setRelativeHeight(0.165f);
		optionButtons[2].setRelativeWidth(0.25f);
		addSelectableWidget(optionButtons[2], 0, 1);

		optionButtons[3] = new RWTButton("4");
		optionButtons[3].setFont(f);
		optionButtons[3].setRelativePosition(0.562f, 0.672f);
		optionButtons[3].setRelativeHeight(0.165f);
		optionButtons[3].setRelativeWidth(0.252f);
		addSelectableWidget(optionButtons[3], 1, 1);

		RWTImage background = new RWTImage("documents\\quizu.jpg");
		background.setRelativePosition(0.1f, -0.05f);
		background.setSize(800, 800);
		addWidgetOnBack(background);

		gameover = new RWTImage("documents\\罰ゲーム.jpg");
		gameover.setRelativePosition(0.1f, -0.05f);
		gameover.setSize(800, 800);
		gameover.setVisible(false);
		addWidget(gameover);

		clear = new RWTImage("documents\\女.jpg");
		clear.setRelativePosition(0.1f, -0.05f);
		clear.setSize(800, 800);
		clear.setVisible(false);
		addWidget(clear);



		repaint();
	}

	public void showOption(int n, String option) {
		optionButtons[n].setLabel(option);
	}

	public void setClear() {
			clear.setVisible(true);
	}
	
	public void setGameover() {
			gameover.setVisible(true);
	}
		
	@Override
	public void keyPressed(RWTVirtualKey key) {
		if (key.getVirtualKey() == RWTVirtualController.RIGHT) {
			cursorMoveRight();
		} else if (key.getVirtualKey() == RWTVirtualController.LEFT) {
			cursorMoveLeft();
		} else if (key.getVirtualKey() == RWTVirtualController.UP) {
			cursorMoveUp();
		} else if (key.getVirtualKey() == RWTVirtualController.DOWN) {
			cursorMoveDown();
		}
	}

	@Override
	public void keyReleased(RWTVirtualKey key) {
		if (key.getPlayer() == 0 && key.getVirtualKey() == RWTVirtualController.BUTTON_A) {
			RWTWidget selected = getSelectedWidget();
			for (int i = 0; i < 4; i++) {
				if (selected == optionButtons[i]) {
					scenario.fire(optionButtons[i].getLabel());
				}
			}
		}
	}

	@Override
	public void keyTyped(RWTVirtualKey key) {
	}
}
