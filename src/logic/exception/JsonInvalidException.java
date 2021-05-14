/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.exception;

import com.google.gson.JsonParseException;

/**
 *
 * @author SHWETHA
 */
public class JsonInvalidException extends InvalidJSONFileException {

    private final JsonParseException ex;

    public JsonInvalidException(JsonParseException ex) {
        this.ex = ex;
    }

    public JsonParseException getEx() {
        return this.ex;
    }

}
