package basic.tech.xstream;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ： luoleiming
 * @date ：Created in 2020/10/29
 * @description： //TODO
 */
@Data
public class CPU implements Serializable {
    private static final long serialVersionUID = -718438777162957779L;

    private String name;
    private String generation;
}
