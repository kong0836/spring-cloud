package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author konglinghui
 * @description
 * @date 2021/5/6 14:52
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseInfo implements Serializable {

    private Long field1;
    private String field2;
    private String field3;
    private String field4;

}
