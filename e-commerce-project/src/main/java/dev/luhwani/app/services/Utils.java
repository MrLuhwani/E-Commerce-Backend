package dev.luhwani.app.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.regex.Pattern;

import dev.luhwani.app.models.userModels.UserModel;

public class Utils {

    static Scanner scanner = new Scanner(System.in);

    public static String changePassword(UserModel userModel) {
        String oldPassword = userModel.getPassword();
        while (true) {
            System.out.print("Enter your old password: ");
            String input = scanner.nextLine();
            if (input.equals(oldPassword)) {
                break;
            }
            System.out.println("Invalid Password");
        }
        String newPassword;
        while (true) {
            System.out.println("""
                    Set new Password:
                    Password requirements:
                    1.Between 7 - 20 characters long
                    2.No space
                    3.Contains both letters and numbers
                    4.Contains at least one symbol""");
            System.out.print("Response: ");
            newPassword = scanner.nextLine();
            if (validPassword(newPassword)) {
                break;
            }
            System.out.println("Invalid Password");
        }
        String confirmPassword;
        while (true) {
            System.out.println("Confirm new password: ");
            confirmPassword = scanner.nextLine();
            if (newPassword.equals(confirmPassword)) {
                break;
            }
            System.out.println("Password doesnt match");
        }
        return newPassword;
    }
    
    public static boolean validName(String name) {
        Pattern namePattern = Pattern.compile("^[A-Za-z]{2,}$");
        return namePattern.matcher(name).matches();
    }

    public static boolean validPassword(String password) {
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[^A-Za-z0-9\\s])[^\\s]{7,20}$");
        return passwordPattern.matcher(password).matches();
    }

    public static boolean validEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9]+@[A-za-z-]+\\.[A-Za-z]{2,}$");
        return emailPattern.matcher(email).matches();
    }

    public static long nairaStringToKobo(String nairaString) {
        BigDecimal naira = new BigDecimal(nairaString);
        BigDecimal kobo = naira.multiply(BigDecimal.valueOf(100));
        if (kobo.scale() > 0) {
            kobo = kobo.setScale(0,RoundingMode.UNNECESSARY);
        }
        return kobo.longValueExact();
    }

    public static boolean validChoice(String choice, int size) {
        int choiceInt = Integer.parseInt(choice);
        try {
            if (choiceInt > size || choiceInt < 1) {
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
