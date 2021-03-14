package com.zbl.wwj.concurrent.step2.p53;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/14
 * @Description:
 * @Version: $
 */

/**
 * SharedResource
 * 临界值
 */
public class Gate {

    private Integer counter = 0;

    private String name = "Nobody";

    private String address = "NoWhere";

    /**
     * 临界值
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address) {
        this.counter = counter++;
        //race
        this.name = name;
        this.address = address;
        verify();
    }

    private void verify() {
        if (this.name.charAt(0) != this.address.charAt(0)) {
            System.out.println("*******BROKEN******" + toString());
        }
    }

    public String toString() {
        return "No." + counter + ":" + name + ":" + address;
    }
}
