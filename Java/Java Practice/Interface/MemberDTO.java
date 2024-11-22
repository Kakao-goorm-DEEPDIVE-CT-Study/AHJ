package interfaceEx;

public class MemberDTO {
	
	private Long memId;
	private String memName;
	
	public MemberDTO(Long memId, String memName) {
		this.memId = memId;
		this.memName = memName;
	}

	public Long getMemId() {
		return memId;
	}

	public void setMemId(Long memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}
	@Override
	public String toString() {
		return "MemberDTO [memId=" + memId + ", memName = " + memName + "]";
	}
}
