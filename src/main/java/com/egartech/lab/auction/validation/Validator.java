package com.egartech.lab.auction.validation;

import com.egartech.lab.auction.data.User;
import com.egartech.lab.auction.service.UserService;
import com.egartech.lab.auction.service.LotService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class Validator contains all methods of checking for the application.
 *
 * @author Max Bondarev.
 */
public class Validator {
    public static HttpServletRequest request;

    final static String LOG_ERROR = "loginError";
    final static String PAS_ERROR = "pasError";
    final static String ERROR = "error";
    final static String TEXT_LOG_ERROR_LETTERS = "Login should not " +
            "contain spaces and must contain letters!";
    final static String TEXT_PAS_ERROR_SPACES = "Password should not " +
            "contain spaces and must contain letters!";
    private static String texLogErrorExist = "Error! Login %s already exist!";
    final static String TEXT_L_P_ERROR_INCORRECT = "Login or password " +
            "incorrect!";
    final static String TEXT_LOT_ERROR_LETTERS = "Lot name should " +
            "contain single spaces and must contain letters!";
    private static String textLotErrorExist = "Error! Lot name %s " +
            "already exist!";
    final static String TEXT_AUTH_ERROR = "To get to this page the " +
            "user must be authorized!";
    final static String TEXT_BET_ERROR = "Error! The bet must be greater " +
            "than zero!";
    final static String TEXT_NULL = "";

    /**
     * Checks authorisation data.
     * @param login
     * @param password
     * @param request
     * @return boolean
     */
    public static boolean checkAuth(String login,
                                    String password,
                                    HttpServletRequest request) {
        Validator.request = request;
        if (isLoginCorrect(login) & isAuthLoginExist(login)) {
            if (isPasCorrect(password) & isPasEquals(login, password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean isLoginCorrect(String login) {
        if (isStringCorrect(login)) {
            return true;
        } else {
            request.setAttribute(LOG_ERROR, TEXT_LOG_ERROR_LETTERS);
            return false;
        }
    }

    private static boolean isStringCorrect(String str) {
        if (str == null || str.matches("") || str.matches("[/\\s/]+")) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isLoginExist(String login) {
        if (new UserService().findByLogin(login) != null) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isAuthLoginExist(String login) {
        if (isLoginExist(login)) {
            return true;
        } else {
            request.setAttribute(LOG_ERROR, TEXT_L_P_ERROR_INCORRECT);
            return false;
        }
    }

    /**
     * Checks registration data.
     * @param login
     * @param password
     * @param request
     * @return boolean
     */
    public static boolean checkReg(String login,
                                   String password,
                                   HttpServletRequest request) {
        Validator.request = request;
        if (isLoginCorrect(login) & isLoginUniq(login) & isPasCorrect(password)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isLoginUniq(String login) {
        if (isLoginExist(login)) {
            String msg  = String.format(texLogErrorExist, login);
            request.setAttribute(LOG_ERROR, msg);
            return false;
        } else {
            return true;
        }
    }

    private static boolean isPasCorrect(String password) {
        if (isStringCorrect(password)) {
            return true;
        } else {
            request.setAttribute(PAS_ERROR, TEXT_PAS_ERROR_SPACES);
            return false;
        }
    }

    private static boolean isPasEquals(String login, String password) {
        User user = new UserService().findByLogin(login);
        if (user.getPassword().equals(password)) {
            return true;
        } else {
            request.setAttribute(PAS_ERROR, TEXT_L_P_ERROR_INCORRECT);
            return false;
        }

    }

    /**
     * Validation of lotname.
     * @param lotName
     * @param request
     * @return boolean
     */
    public static boolean checkNewLot(String lotName,
                                      HttpServletRequest request) {
        Validator.request = null;
        Validator.request = request;
        if(isLotNameCorrect(lotName) & isLotNameUniq(lotName)){
            return true;
        } else {
            return false;
        }
    }

    private static boolean isLotNameCorrect(String lotName) {
        if (lotName == null || lotName.matches("") || lotName
                .matches("[/\\s\\s/]+")) {
            request.setAttribute(ERROR, TEXT_LOT_ERROR_LETTERS);
            return false;
        } else {
            return true;
        }
    }

    private static boolean isLotNameUniq(String lotname) {
        if (new LotService().isNameUnique(lotname)) {
            return true;
        } else {
            String msg = String.format(textLotErrorExist, lotname);
            request.setAttribute(ERROR, msg);
            return false;
        }
    }

    /**
     * Checks if the user is in session
     * @param request
     * @return boolean
     */
    public static boolean isUserInSession(HttpServletRequest request) {
        Validator.request = request;
        HttpSession hSession = request.getSession();
        if (hSession.getAttribute("user") == null) {
            request.setAttribute(ERROR, TEXT_AUTH_ERROR);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks whether the bet data are correct
     * @param request
     * @return boolean
     */
    public static boolean checkBet(HttpServletRequest request) {
        Validator.request = request;
        if (request.getParameter("bet_price") == "") {
            request.setAttribute(ERROR, TEXT_BET_ERROR);
            return false;
        } else {
            Double betPrice = Double.parseDouble(
                    request.getParameter("bet_price"));
            if(betPrice <= 0){
                request.setAttribute(ERROR, TEXT_BET_ERROR);
                return false;
            } else {
                request.setAttribute(ERROR, TEXT_NULL);
                return true;
            }
        }
    }
}