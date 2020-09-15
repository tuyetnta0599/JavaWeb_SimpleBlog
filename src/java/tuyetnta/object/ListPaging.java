/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuyetnta.object;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author tuyet
 */
public class ListPaging<T> extends ArrayList<T> implements Serializable {

    private Integer totalRow = null;
    public static final int ROW_PER_PAGE = 5;

    public Integer getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }
    public double getTotalPage(){
        if(totalRow == null){
            return 0;
        }
        return Math.ceil((double)totalRow / ROW_PER_PAGE);
    }
}
