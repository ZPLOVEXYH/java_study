package com.java.oracle.study.java_study.lamdbas.main;

import com.java.oracle.study.java_study.lamdbas.bean.Car;

import java.util.Arrays;
import java.util.List;

/**
 * java 1.8方法引用参考
 *
 * @link https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
 */
public class FuncationalMain {

    /**
     * 类	例
     * 参考静态方法	ContainingClass::staticMethodName
     * 引用特定对象的实例方法	containingObject::instanceMethodName
     * 引用特定类型的任意对象的实例方法	ContainingType::methodName
     * 引用构造函数	ClassName::new
     *
     * @param args
     */
    public static void main(String[] args) {
        // 第一种方法引用的类型是构造器引用，语法是Class::new，或者更一般的形式：Class<T>::new。注意：这个构造器没有参数。
        final Car car = Car.create(Car::new);
        final Car car2 = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car, car2);
        // 第二种方法引用的类型是静态方法引用，语法是Class::static_method。注意：这个方法接受一个Car类型的参数。
        cars.forEach(Car::collide);
        // 第三种方法引用的类型是某个类的成员方法的引用，语法是Class::method，注意，这个方法没有定义入参：
        cars.forEach(Car::repair);
        // 第四种方法引用的类型是某个实例对象的成员方法的引用，语法是instance::method。注意：这个方法接受一个Car类型的参数：
        cars.forEach(car::follow);
    }
}
