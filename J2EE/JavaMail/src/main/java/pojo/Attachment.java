package pojo;

public class Attachment {
	private String attachmentName;
	private String attachmentLocation;

	public Attachment() {
	}

	public Attachment(String attachmentName, String attachmentLocation) {
		this.attachmentName = attachmentName;
		this.attachmentLocation = attachmentLocation;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentLocation() {
		return attachmentLocation;
	}

	public void setAttachmentLocation(String attachmentLocation) {
		this.attachmentLocation = attachmentLocation;
	}

}
