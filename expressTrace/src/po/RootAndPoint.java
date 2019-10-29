package po;

import java.util.ArrayList;

public class RootAndPoint {
	private Root root;
	private ArrayList<String> lats;
	private ArrayList<String> lngs;
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

	

	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}

	
}
