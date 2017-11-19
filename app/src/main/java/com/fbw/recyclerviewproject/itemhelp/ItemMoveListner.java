package com.fbw.recyclerviewproject.itemhelp;

public interface ItemMoveListner {
    /**
     * 拖拽的过程中调用
     * @param from
     * @param to
     */
     void OnItemMove(int from,int to);

    /**
     * 当item左右侧滑的时候调用
     * @param position 当前view的位置
     * @param derection 滑动方向
     */
    void OnItemswape(int position,int derection);
}
