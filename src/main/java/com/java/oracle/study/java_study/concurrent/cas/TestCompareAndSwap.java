package com.java.oracle.study.java_study.concurrent.cas;

public class TestCompareAndSwap {

    public static void main(String[] args) {
        CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int value = cas.get();
                boolean bool = cas.compareAndSet(value, (int) (Math.random() * 101));
                System.out.println(bool);
            }).start();
        }
    }
}

class CompareAndSwap {
    public int value;

    // 内存值V
    public synchronized int get() {
        return value;
    }

    // 比较方法
    public synchronized int compareAndSwap(int expecteValue, int newValue) {
        int oldValue = value;
        if (oldValue == expecteValue) {
            this.value = newValue;
        }

        return oldValue;
    }

    // 设置方法，当且仅当 V 的值等于 A 时，CAS 通过原子方式用新值 B 来更新 V 的 值，否则不会执行任何操作
    public synchronized boolean compareAndSet(int expecteValue, int newValue) {
        return expecteValue == compareAndSwap(expecteValue, newValue);
    }
}
