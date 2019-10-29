package prepro;

import java.util.List;

import po.Trace;

public interface IFile {
	public boolean WriteJson(String filename, String contents);
	public String readJson(String name); 
}
