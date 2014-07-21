package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;


public class SVNUtil {
	
	private static Logger logger = Logger.getLogger(SVNUtil.class.getName());
	
	private static SVNClientManager getSVNClientManager(){
		SVNClientManager svnClientManager = SVNClientManager.newInstance();
		
		ISVNAuthenticationManager authManager = new BasicAuthenticationManager("xiao", "xiao");
		
		svnClientManager.setAuthenticationManager(authManager );
		
		return svnClientManager;
	}
	
	
	public static File getFileFromSVN(String filePath,  
            long revision,String destFilePath) {  
		File file = new File(destFilePath);
		SVNURL reponsitoryURL = null;
		FileOutputStream fos = null;
		try {
			reponsitoryURL = SVNURL.parseURIEncoded(filePath);
			
			fos = new FileOutputStream(file);
			
			SVNRepository  reponsitory = getSVNClientManager().createRepository(reponsitoryURL, false);

			reponsitory.getFile("", revision, null, fos);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					logger.warning(e.getMessage());
				}
			}
		}
		return file;
    }  
	
	
	public static Map<Integer,String> getFileInfo(String filePath,long revision) throws SVNException{
		SVNURL url = SVNURL.parseURIEncoded(filePath);
		SVNRevision pegRevision = SVNRevision.create(revision);
		SVNRevision startRevision = SVNRevision.create(0);
		SVNRevision endRevision = SVNRevision.create(revision);
		DefaultSVNAnnotateHandler handler = new DefaultSVNAnnotateHandler();
		getSVNClientManager().getLogClient().doAnnotate(url, pegRevision, startRevision, endRevision, handler);
		
		return handler.getLineAuthorMap();
	}
}



