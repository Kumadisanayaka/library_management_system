package controller.addBookController;

public class Add_book_controller {

    public boolean isEmpty(String iD) {

        return iD == null || iD.trim().isEmpty();
    }

    public boolean isNumber(String id) {

        return id.matches("\\d+");
    }
}
