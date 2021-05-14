/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.exception;

import logic.SpecialFields;

/**
 *
 * @author SHWETHA
 */
public class IllegalPointsException extends InvalidJSONFileException {

    private final int points;
    private final SpecialFields fieldType;

    public IllegalPointsException(int points, SpecialFields fieldType) {
        this.fieldType = fieldType;
        this.points = points;
    }

    public String getErrorMessage() {
        return "Points " + points + " assigned to the " + fieldType + " is invalid";
    }
}
