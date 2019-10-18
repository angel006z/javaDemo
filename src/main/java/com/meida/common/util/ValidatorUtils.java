package com.meida.common.util;

import com.meida.base.vo.ResultMessage;
import com.meida.common.constant.EErrorCode;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

public class ValidatorUtils {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> ResultMessage validateEntity(T obj) {
        ResultMessage result = new ResultMessage();
        if(obj==null){
            result.setCode(EErrorCode.Error);
            result.setMessage("验证对象为null");
            return result;
        }
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && set.size() != 0) {
            result.setCode(EErrorCode.Error);
//            Map<String, String> errorMsg = new HashMap<String, String>();
//            for (ConstraintViolation<T> cv : set) {
//                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
//            }
            for (ConstraintViolation<T> cv : set) {
                result.setMessage(cv.getMessage());
                break;
            }
        }else{
            result.setCode(EErrorCode.Success);
            result.setMessage("验证合法");
        }
        return result;
    }

}
