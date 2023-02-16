package org.example.mongodb.pojo;

/**
 * @Author: zbl
 * @Date: Created in 2023/2/7 16:46
 * @Description:
 * @Version: $
 */


public class Test {

    private String id;

    public Test(String id) {
        this.id = id;
    }

    public Test() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                '}';
    }

}
