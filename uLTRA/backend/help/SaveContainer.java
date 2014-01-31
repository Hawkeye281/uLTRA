package help;

import gamegrid.GameGrid;
import gamegrid.Validator;

import java.io.Serializable;

public class SaveContainer implements Serializable {

	private GameGrid _gameGridRef = null;
	private Validator _validatorRef = null;
	
	public SaveContainer (GameGrid pGameGrid, Validator pValidator) {
		_gameGridRef = pGameGrid;
		_validatorRef = pValidator;
	}
	
	public void setGameGrid (GameGrid pGameGrid) {
		_gameGridRef = pGameGrid;
	}
	
	public void setValidator (Validator pValidator) {
		_validatorRef = pValidator;
	}
	
	public GameGrid getGameGrid () {
		return _gameGridRef;
	}
	
	public Validator getValidator() {
		return _validatorRef;
	}
	
}
