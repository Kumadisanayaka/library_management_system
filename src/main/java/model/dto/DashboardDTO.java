package model;

public class DashboardDTO {

    private int totalBookCount;
    private int totalMemberCount;
    private int totalIssuedBookCount;
    private int totalOverdueBookCount;

    public DashboardDTO(int totalBookCount,
                        int totalMemberCount,
                        int totalIssuedBookCount,
                        int totalOverdueBookCount
    ) {

        this.totalBookCount = totalBookCount;
        this.totalMemberCount = totalMemberCount;
        this.totalIssuedBookCount = totalIssuedBookCount;
        this.totalOverdueBookCount = totalOverdueBookCount;
    }

    public DashboardDTO() {
    }

    public int getTotalBookCount() {
        return totalBookCount;
    }

    public void setTotalBookCount(int totalBookCount) {
        this.totalBookCount = totalBookCount;
    }

    public int getTotalMemberCount() {
        return totalMemberCount;
    }

    public void setTotalMemberCount(int totalMemberCount) {
        this.totalMemberCount = totalMemberCount;
    }

    public int getTotalIssuedBookCount() {
        return totalIssuedBookCount;
    }

    public void setTotalIssuedBookCount(int totalIssuedBookCount) {
        this.totalIssuedBookCount = totalIssuedBookCount;
    }

    public int getTotalOverdueBookCount() {
        return totalOverdueBookCount;
    }

    public void setTotalOverdueBookCount(int totalOverdueBookCount) {
        this.totalOverdueBookCount = totalOverdueBookCount;
    }
}
