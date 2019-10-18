package template.quiz2D;

import framework.RWT.RWTContainer;
import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.audio.Sound3D;
import framework.gameMain.SimpleScenarioGame;
import framework.model3D.Universe;
import framework.scenario.Event;
import framework.scenario.ScenarioState;
import framework.view3D.Camera3D;

public class TemplateQuizGame extends SimpleScenarioGame {
	private RWTFrame3D frame;

	//SE�̓���
	private Sound3D correct = new Sound3D("data//TemplateQuiz//Quiz-Buzzer02-1.wav");
	private Sound3D incorrect = new Sound3D("data//TemplateQuiz//Quiz-Wrong_Buzzer01-1.wav");

	@Override
	public void init(Universe universe, Camera3D camera) {
		// �V�i���I�̐ݒ�
		setScenario("data\\TemplateQuiz\\scenario.xml");
		container.setScenario(scenario);
		scenario.fire("�J�n");

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

	//����
	int count = 0;

	@Override
	public void action(String action, Event event, ScenarioState nextState) {
		// �V�i���I�i�s�ɂ�鐢�E�ւ̍�p�������ɏ���
		if (action.equals("right")) {

			//��������SE
			correct.play();

			//���𐔂̃J�E���g
			count ++;

			System.out.println(count);

		} else if (action.equals("wrong")) {
			//�s��������SE
			incorrect.play();
		} else if(action.equals("lastright")) {
			count ++;

			if(count > 2) {
				scenario.go("�S�␳��");
			} else {
				scenario.go("�I��");
			}
		} else if (action.equals("lastwrong")) {
			if(count > 2) {
				scenario.go("�S�␳��");
			} else {
				scenario.go("�I��");
			}
		}
	}

	/**
	 * �Q�[���̃��C��
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		TemplateQuizGame game = new TemplateQuizGame();
		game.setFramePolicy(5, 33, false);
		game.start();
	}

}
