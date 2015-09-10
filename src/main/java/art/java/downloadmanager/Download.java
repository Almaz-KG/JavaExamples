package art.java.downloadmanager;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;

public class Download extends Observable implements Runnable {
	private static final int MAX_BUFFER_SIZE = 1024;
	public static final String[] STATUSES = {
		"Downloading",
		"Paused",
		"Complete",
		"Cancelled",
		"Error"
	};
	
	public static final int DOWNLOADING = 0;
	public static final int PAUSED = 1;
	public static final int COMPLETED = 2;
	public static final int CANCELED = 3;
	public static final int ERROR = 4;
	private int size;
	private int downloaded;
	private int status;
	private URL url;
	private String fileSavePath;
	
	public Download(URL url){
		this.url = url;
		this.size = -1;
		this.downloaded = 0;
		this.status = DOWNLOADING;
		download();
	}
	public Download(URL url, String fileSavePath){
		this.url = url;
		this.size = -1;
		this.downloaded = 0;
		this.status = DOWNLOADING;
		this.fileSavePath = fileSavePath;
		download();
	}
	public String getUrl() {
		return url.toString();
	}
	public int getSize() {
		return size;
	}
	public double getProgress(){
		return (1.0 * this.downloaded / this.size) * 100;
	}
	public int getStatus() {
		return status;
	}

	public void pause(){
		this.status = PAUSED;
		stateChanged();
	}
	public void resume(){
		this.status = DOWNLOADING;
		stateChanged();
		download();
	}
	public void cancel(){
		this.status = CANCELED;
		stateChanged();
	}
	private void error(){
		this.status = ERROR;
		this.stateChanged();
	}
	private void download() {
		Thread thread = new Thread(this);
		thread.start();
	}
	private String getFileName(){
		if(this.fileSavePath != null)
			return fileSavePath;
		
		String fileName = url.getFile();
		return fileName.substring(fileName.lastIndexOf('/') + 1);
	}
	private void stateChanged() {
		setChanged();
		notifyObservers();
	}

	
	@Override
	public void run() {
		RandomAccessFile file = null;
		InputStream stream = null;
		
		try{
			HttpURLConnection connection = 
					(HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Range", "bytes=" +
					downloaded + "-");
			connection.connect();
			if(connection.getResponseCode() / 100 != 2)
				error(); 
			int contentLength = connection.getContentLength();
			if(contentLength < 1)
				error();
			
			if(size == -1){
				size = contentLength;
				stateChanged();
			}
			
			file = new RandomAccessFile(getFileName(), "rw");
			file.seek(downloaded);
			stream = connection.getInputStream();
			while(status == DOWNLOADING){
				byte buffer[];
				if(size - downloaded >= MAX_BUFFER_SIZE){
					buffer = new byte[MAX_BUFFER_SIZE];
				} else{
					buffer = new byte[size - downloaded];
				}
				
				int read = stream.read(buffer);
				if(read == -1)
					break;
				
				file.write(buffer, 0, read);
			
				downloaded += read;
				stateChanged();
			}
			if(status == DOWNLOADING){
				status = COMPLETED;
				stateChanged();
			}
		} catch(IOException e){
			error();
		} finally{
			if(file != null)
				try {
					file.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			if(stream != null)
				try {
					stream.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
		}
	}

}



























