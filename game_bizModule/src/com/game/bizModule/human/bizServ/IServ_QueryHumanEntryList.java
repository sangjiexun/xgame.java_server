package com.game.bizModule.human.bizServ;

import com.game.bizModule.human.io.IoOper_QueryHumanEntryList;
import com.game.bizModule.login.LoginStateTable;
import com.game.gameServer.framework.Player;

/**
 * 查询玩家入口列表
 *
 * @author hjj2019
 * @since 2015/7/11
 *
 */
interface IServ_QueryHumanEntryList {
    /**
     * 异步方式查询玩家入口列表
     *
     * @param p
     *
     */
    default void asyncQueryHumanEntryList(Player p) {
        if (p == null) {
            // 如果参数对象为空,
            // 则直接退出!
            return;
        }

        // 获取登陆状态表
        LoginStateTable stateTbl = p.getPropValOrCreate(LoginStateTable.class);

        if (stateTbl._platformUIdOk == false ||
            stateTbl._authOk == false) {
            // 如果登陆验证都没成功,
            // 那还是退出吧!
            return;
        }

        // 创建异步操作对象
        IoOper_QueryHumanEntryList op = new IoOper_QueryHumanEntryList();
        op._p = p;
        // 执行异步操作!
        HumanServ.OBJ.execute(op);
    }
}
