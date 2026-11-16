package model;

public class MemberCountDTO {

    private int totalMembers;
    private int studentMembers;
    private int otherMembers;

    public MemberCountDTO(int totalMembers, int studentMembers, int otherMembers) {
        this.totalMembers = totalMembers;
        this.studentMembers = studentMembers;
        this.otherMembers = otherMembers;
    }

    public MemberCountDTO() {
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    public int getStudentMembers() {
        return studentMembers;
    }

    public void setStudentMembers(int studentMembers) {
        this.studentMembers = studentMembers;
    }

    public int getOtherMembers() {
        return otherMembers;
    }

    public void setOtherMembers(int otherMembers) {
        this.otherMembers = otherMembers;
    }
}
