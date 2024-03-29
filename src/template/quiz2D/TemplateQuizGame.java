package template.quiz2D;

import framework.RWT.RWTContainer;
import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.audio.BGM3D;
import framework.audio.Sound3D;
import framework.gameMain.SimpleScenarioGame;
import framework.model3D.Universe;
import framework.scenario.Event;
import framework.scenario.ScenarioState;
import framework.view3D.Camera3D;

public class TemplateQuizGame extends SimpleScenarioGame {
	private RWTFrame3D frame;

	//SEの導入
	private Sound3D correct = new Sound3D("data//TemplateQuiz//Quiz-Buzzer02-1.wav");
	private Sound3D incorrect = new Sound3D("data//TemplateQuiz//Quiz-Wrong_Buzzer01-1.wav");
	//BGM
	private Sound3D iBGM = BGM3D.registerBGM("data//TemplateQuiz//naked.wav");
	@Override
	public void init(Universe universe, Camera3D camera) {
		// シナリオの設定
		setScenario("data\\TemplateQuiz\\scenario.xml");
		container.setScenario(scenario);
		scenario.fire("開始");
		BGM3D.playBGM(iBGM);
	}

	@Override
	public RWTFrame3D createFrame3D() {
		frame = new RWTFrame3D();
		frame.setSize(1000, 800);
		frame.setTitle("Template for 2D Quiz Game");
		return frame;
	}

	@Override
	protected RWTContainer createRWTContainer() {
		container = new QuizGameContainer();
		return container;
	}

	@Override
	public void progress(RWTVirtualController virtualController, long interval) {
	}

	@Override
	public void showOption(int n, String option) {
		((QuizGameContainer)container).showOption(n, option);
	}

	//正解数
	int count = 0;

	@Override
	public void action(String action, Event event, ScenarioState nextState) {
		// シナリオ進行による世界への作用をここに書く
		if (action.equals("right")) {

			//正解時のSE
			correct.play(2);

			//正解数のカウント
			count ++;

			System.out.println(count);

		} else if (action.equals("wrong")) {
			//不正解時のSE
			incorrect.play(2);

		//最終結果
		} else if(action.equals("lastright")) {
			//正解時のSE
			correct.play(2);

			count ++;

			System.out.println(count);

			//画像の変更
			if(count > 2) {

				((QuizGameContainer) container).setClear();

			} else {

				((QuizGameContainer) container).setGameover();

			}
		} else if (action.equals("lastwrong")) {
			//不正解時のSE
			incorrect.play(2);

			//画像の変更
			if(count > 2)
			{
				((QuizGameContainer) container).setClear();

			} else {

				((QuizGameContainer) container).setGameover();
			}
		}
	}

	/**
	 * ゲームのメイン
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		TemplateQuizGame game = new TemplateQuizGame();
		game.setFramePolicy(5, 33, false);
		game.start();
	}

}
