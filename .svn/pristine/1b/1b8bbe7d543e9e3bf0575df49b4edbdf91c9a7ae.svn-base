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
public class NullObjectException extends InvalidJSONFileException {

    private final String fieldType;
    private final int xPos;
    private final int yPos;
    private final String fileType;

    public NullObjectException(String fieldType, int xPos, int yPos, String fileType) {
        this.fieldType = fieldType;
        this.xPos = xPos;
        this.yPos = yPos;
        this.fileType = fileType;
    }

    public String getErrorMessage() {
        return fieldType + " at position [" + xPos + " ,"
                + yPos + "] in the " + fileType + " is null";
    }

}
