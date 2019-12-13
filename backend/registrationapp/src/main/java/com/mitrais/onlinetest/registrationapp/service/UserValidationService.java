package com.mitrais.onlinetest.registrationapp.service;
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

import com.mitrais.onlinetest.registrationapp.validation.UniqueEmailValidation;
import com.mitrais.onlinetest.registrationapp.validation.UniqueMobileNumberValidation;

public interface UserValidationService extends UniqueMobileNumberValidation, UniqueEmailValidation {
}
