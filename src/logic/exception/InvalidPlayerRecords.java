/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.exception;

/**
 *
 * @author SHWETHA
 */
public class InvalidPlayerRecords extends InvalidJSONFileException {

    private final String errorMessage;

    public InvalidPlayerRecords(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
