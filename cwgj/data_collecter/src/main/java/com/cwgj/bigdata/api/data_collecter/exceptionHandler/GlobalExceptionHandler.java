package com.cwgj.bigdata.api.data_collecter.exceptionHandler;


import com.cwgj.bigdata.api.data_collecter.model.ApiResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  private final static Logger logger = LoggerFactory.getLogger("GlobalExceptionHandler");


  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ApiResponse allExceptionHandler(Exception exception) throws Exception {
    logger.error(exception.getCause().getMessage());
    return new ApiResponse(exception.getCause().getMessage(), 500);
  }

  /**
   * 处理实体字段校验不通过异常
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ApiResponse validationError(MethodArgumentNotValidException ex) {
    logger.error("raised MethodArgumentNotValidException : " + ex.getMessage());
    BindingResult result = ex.getBindingResult();
    final List<FieldError> fieldErrors = result.getFieldErrors();
    StringBuilder builder = new StringBuilder();
    for (FieldError error : fieldErrors) {
      builder.append(error.getDefaultMessage() + "\n");
    }

    return new ApiResponse(builder.toString(), 501);
  }
}
