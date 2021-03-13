package com.zbl.wwj.concurrent.step2.p51;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/11 13:51
 * @Description:
 * @Version: $
 */
public class Subject {

    List<ObServer> obServers = new ArrayList<>();

    int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void attach(ObServer obServer) {
        obServers.add(obServer);
    }

    public void update(int state) {
        if (this.state == state) {
            return;
        }
        this.state = state;
        this.notifyAllObs();
    }

    public void notifyAllObs() {
        obServers.forEach(ObServer::update);
    }
}
