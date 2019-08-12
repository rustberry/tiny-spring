# tiny-spring

Try to have a minimal implementation of Spring Framework.

## Change log

[v1.0](https://github.com/rustberry/tiny-spring/tree/v1.0) IoC (Inversion of Control) function complete.

[v2.0](https://github.com/rustberry/tiny-spring/tree/v2.0) AOP (Aspect Oriented Programming) complete.

[v3.0](https://github.com/rustberry/tiny-spring/tree/v3.0) The minimum but sufficient structrue of Spring's BeanFactory. See the clear [UML diagram](https://github.com/rustberry/tiny-spring/blob/master/BeanFactory-ApplicationContext.png) at the end.

use `git checkout <tag name>` to jump to specific version.

## Analysis

The basic structure of BeanFactory classes:

![BeanFactory Classes](https://github.com/rustberry/tiny-spring/raw/master/BeanFactory-ApplicationContext.png)




## TODO

- [ ] detect circular dependencies
- [ ] solve circular dependencies
- [ ] instantiation of Beans having not only nullary constructor
- [ ] documentation. Write about how IoC in this framework works.
