/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuyetnta.utils;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 *
 * @author tuyet
 */
public class Validator implements Serializable {

    public static final String EMAIL_PATTERN = "^([a-zA-Z0-9]{3,50})@([a-zA-Z]{3,10})((\\.([a-zA-Z]{2,5})){1,2})$";
    public static final String FULLNAME_PATTERN = "^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀẾỂưăạảấầẩẫậắằẳẵặẹẻẽềếểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s.]{3,50}$";
    public static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]{6,30}$";
    public static final int TITLE_MIN_LENGTH = 3;
    public static final int TITLE_MAX_LENGTH = 100;
    public static final int SHORTDES_MIN_LENGTH = 3;
    public static final int SHORTDES_MAX_LENGTH = 100;

    public static boolean check(String value, String spattern) {
        Pattern pattern = Pattern.compile(spattern);
        return pattern.matcher(value).matches();
    }

    public static boolean checkLenght(String value, int min, int max) {
        return value.length() >= min && value.length() <= max;
    }
}
