package logger;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FirstLogger {
	Logger logger = Logger.getLogger("firstLooger");
	
	private static FirstLogger instnce = new FirstLogger();
	
	private FileHandler logFile = null;
	
	public FirstLogger() {
		
		try {
			//log_20211112_0.log
			String errorLog = "C:/dev/log/log_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "_%g.log";
			
			logFile = new FileHandler(errorLog, 1024*1024, 10, true);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logFile.setFormatter(new SimpleFormatter());
		logFile.setLevel(Level.ALL);
		logger.addHandler(logFile);
	}
	
	public static FirstLogger getLogger() {
		return instnce;
	}
	
	public void log(String msg) {
		logger.log(Level.INFO, "레벨 INFO 로그");
		logger.log(Level.ALL, "전체");
	}
}
