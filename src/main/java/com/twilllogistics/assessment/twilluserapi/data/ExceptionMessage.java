package com.twilllogistics.assessment.twilluserapi.data;

/**
 * Created by turgay on 31/08/17.
 *
 * Class for returning error messages over REST API
 */
public class ExceptionMessage {

    /**
     * The error/exception message
     */
    private String message;

    /**
     * URL address of reference documentation
     */
    private String documentation_url;

    public ExceptionMessage(String message, String documentation_url) {
        this.message = message;
        this.documentation_url = documentation_url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentation_url() {
        return documentation_url;
    }

    public void setDocumentation_url(String documentation_url) {
        this.documentation_url = documentation_url;
    }
}
