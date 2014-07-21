package util;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.wc.ISVNAnnotateHandler;

public class DefaultSVNAnnotateHandler implements ISVNAnnotateHandler {

	private Map<Integer,String> lineAuthorMap = new HashMap<Integer,String>();
	
	public void handleEOF() throws SVNException {
		// TODO Auto-generated method stub

	}

	public void handleLine(Date date, long revision, String author, String line)
			throws SVNException {

	}

	public void handleLine(Date date, long revision, String author,
			String line, Date mergedDate, long mergedRevision,
			String mergedAuthor, String mergedPath, int lineNumber)
			throws SVNException {
		lineAuthorMap.put(lineNumber, author);

	}

	public boolean handleRevision(Date date, long revision, String author,
			File contents) throws SVNException {
		// TODO Auto-generated method stub
		return false;
	}

	public Map<Integer, String> getLineAuthorMap() {
		return lineAuthorMap;
	}
	
	

}
