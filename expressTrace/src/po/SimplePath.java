package po;

import java.util.ArrayList;

public class SimplePath {
	private ArrayList<String> lats;
	private ArrayList<String> lngs;
	private SimpleStyle simpleStyle;

	public ArrayList<String> getLats() {
		return lats;
	}

	public void setLats(ArrayList<String> lats) {
		this.lats = lats;
	}

	public ArrayList<String> getLngs() {
		return lngs;
	}

	public void setLngs(ArrayList<String> lngs) {
		this.lngs = lngs;
	}

	public SimpleStyle getSimpleStyle() {
		return simpleStyle;
	}

	public void setSimpleStyle(SimpleStyle simpleStyle) {
		this.simpleStyle = simpleStyle;
	}
}
