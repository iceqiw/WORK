package com.cwgj.bigdata.api.data_collecter.vo;


import io.swagger.annotations.ApiModelProperty;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

@Data
public class BaseVO implements IBaseVO {

  private final static Logger logger = LoggerFactory.getLogger("BaseDataVO");

  @NotNull(message = "时间戳错误")
  @ApiModelProperty(required = true, value = "时间戳 必填")
  protected Long timestamp;
  @NotNull(message = "签名不能为空")
  @ApiModelProperty(required = true, value = "签名 必填")
  protected String sign;

  @Override
  public boolean isMD5Vaild(String key) {
    try {
      Long s = (System.currentTimeMillis() - timestamp) / 1000;
      if (s.compareTo(1200L) > 0) {
        return false;
      }
      String str = timestamp + key;
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      BASE64Encoder base64en = new BASE64Encoder();
      //加密后的字符串
      String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
      logger.info(newstr);
      if (sign.equalsIgnoreCase(newstr)) {
        return true;
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return false;
  }
}
