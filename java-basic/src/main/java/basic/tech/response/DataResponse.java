package basic.tech.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ： luoleiming
 * @date ：Created in 2020/9/22 17:45
 * @description： 后台查询需要返回列表的响应
 */
@Data
public class DataResponse implements Serializable {
    private static final long serialVersionUID = -6416977681493190814L;

    private String errorcode;
    private String msg;
    private Object data;
}
