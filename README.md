### 主观题：
@Component 已经可以支持声明一个 bean 了，为何还要再弄个 @Bean 出来？
***
我参考了资料给的那个对比两者的链接，除了以下这段话，  
  > 1. @Component auto detects and configures the beans using classpath scanning whereas @Bean explicitly declares a single bean, rather than letting Spring do it automatically.  
  > 2. @Component does not decouple the declaration of the bean from the class definition where as @Bean decouples the declaration of the bean from the class definition.  
  > 3. @Component is a class level annotation whereas @Bean is a method level annotation and name of the method serves as the bean name.  
  > 4. @Component need not to be used with the @Configuration annotation where as @Bean annotation has to be used within the class which is annotated with @Configuration.  
  > 5. We cannot create a bean of a class using @Component, if the class is outside spring container whereas we can create a bean of a class using @Bean even if the class is present outside the spring container.  
  > 6. @Component has different specializations like @Controller, @Repository and @Service whereas @Bean has no specializations.

我的理解是`@Component`是作用于一个类的，当一个类用了这个注解的时候，就会将这个类给放进IoC的容器之中并且给实力化，而`@Bean`则是作用于方法，我们通
过自己定义一些方法来实例化我们想要的类(甚至是第三方类)，例如：
```
@Configuration
class MyConfiguration{
    @Bean
    public User getUser() {
        return new User();
    }
}

class User{
}    
        
// Getting Bean 
User user = applicationContext.getBean("getUser");
```
这个例子就是结合`@Configuration`，在需要的时候调用这个方法去实例化一个 User。并且也可以通过一些不同的参数来实例化不同的 type。
而`@Component`则是作用于一个类，通常结合`@Autowired`去直接实例化其作用的那个类。

***
### Problem2
GreetingService 现在必须为 prototype scope，如何保证每次 GreetingController 的 greet() 方法被调用时都会创建新的 greetingService bean？
至少2种解决方法且
GreetingController 仍为 singleton scope。
1. 第一种方法是利用 `@Scope` 中的 `proxyMode` 属性将 `Service` 设置为 `ScopedProxyMode.TARGET_CLASS`  
这其中有一点是：当我不设置这个属性的时候，在初始化的时候就会调用 `Service` 的构造器，并且只注入一次所以只有个实例；  
当我用这个属性之后，一开始初始化并不会调用这个 `Service` 的构造器，而是在我调用了 `Controller` 之后才会去构造出同次数个数的实例。  
（待查阅资料）
2. 第二种方式则是利用 `@Bean`