package memo.model.dto;

public class MemoDTO {
	private int no;
	private String writer;
	private String content;
	private String regiDate;
	
	public MemoDTO() {
		
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(String regiDate) {
		this.regiDate = regiDate;
	}

	@Override
	public String toString() {
		return "MemoDTO [no=" + no + ", writer=" + writer + ", content=" + content
				+ ", regiDate=" + regiDate + "]";
	}
}