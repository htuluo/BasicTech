package basic.tech.xstream;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ： luoleiming
 * @date ：Created in 2020/10/29
 * @description： //TODO
 */
@Data
public class Computer implements Serializable {
    private static final long serialVersionUID = 567119860357020081L;

    private String name;
    private String brand;
    private List<CPU> processors;
}
