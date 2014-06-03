package es.unileon.ulebank.account.history;

/**
 * 
 * @author runix
 */
public class DetailedInformation {

	private final StringBuffer info;
	private boolean nonEditable;

	public DetailedInformation(String information) {
		this.info = new StringBuffer(information);
		this.nonEditable = false;
	}

	public DetailedInformation() {
		this("");
	}

	public void appendInformation(String information) {
		if (!this.nonEditable) {
			this.info.append(information);
			if (information.charAt(information.length() - 1) != '\n') {
				this.info.append("\n");
			}
		}
	}

	public void appendInformaton(StringBuffer information) {
		this.appendInformation(information.toString());
	}

	public void doFinal() {
		this.nonEditable = true;
	}

	@Override
	public String toString() {
		return this.info.toString();
	}
}