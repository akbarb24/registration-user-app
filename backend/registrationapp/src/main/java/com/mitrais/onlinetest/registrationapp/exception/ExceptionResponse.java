package com.mitrais.onlinetest.registrationapp.exception;
/*
 * Dear Maintainer,
 *
 * When I wrote this code, only I and God knew what it was. Now, only God knows!
 *
 * So, If you're done, trying to 'optimize' this routine (and failed).
 * Please, increment the following counter as a warning to the next guy:
 * total_hours_wasted_here: 999;
 *
 * Sincerely Yours, Hooman
 */

import lombok.Data;

import java.util.Date;
import java.util.HashMap;

@Data
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private HashMap<String, String> errorDetails;

    public ExceptionResponse(Date timestamp, String message, HashMap<String, String> errorDetails) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.errorDetails = errorDetails;
    }
}
