package controller;

public class Add_member_form_validation_Controller {

    public static boolean isEmpty(String value){

        return value == null || value.trim().isEmpty();
    }

    public static boolean isNumber(String value){

        return value.matches("\\d+");

    }

    public static boolean isValidEmail(String email){

        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    }

    public static boolean isValidPhoneNumber(String phoneNumber){

        return phoneNumber.matches("\\d{10}");

    }
}
