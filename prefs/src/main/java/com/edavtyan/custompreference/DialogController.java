package com.edavtyan.custompreference;

public abstract class DialogController<TPreference extends DialogPreference>
		extends BaseController<TPreference> {

	public DialogController(TPreference prefView) {
		super(prefView);
	}


	public void closeDialog() {
		prefView.closeDialog();
	}
}
