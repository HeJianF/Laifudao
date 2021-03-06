package com.example.hjf.mylaifudao.been;

/**
 * @author heJianfeng
 * @date 2018/12/14
 */
public class LfdInfo {

    /**
     * Adapter中ViewHolder的type
     * getItemViewType
     */
    private int type;

    /**
     * 对应type的数据object
     */
    private Object object;

    public LfdInfo(int type, Object object) {
        this.type = type;
        this.object = object;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
