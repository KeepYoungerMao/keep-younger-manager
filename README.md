### KeepYounger manager system
* spring security的登入登出

    * 默认自带csrf防护。官方指定需要post表单（form）提交登入登出
    * 当你使用thymeleaf时，使用`<form th:action="@{/login}" method="post">`时，
    * spring security会在表单中添加：
    * `<input type="hidden" name="_csrf" value="c431ac97-7350-452a-83fe-a9b6272a6ca2"/>`
    * 如果直接写：`<form action="/login" method="post">`好像不会添加_csrf参数
    * 系统便报405过不去。
    * 暂时不知道在不使用模板的时候如何处理。
* spring security的登录登出拦截
    ```
    .and()
    .formLogin()
    .loginPage("/login")
    .failureUrl("/login-error")
    .defaultSuccessUrl("/",true)
    ```
    * 以上代码可以实现登录的流程
    * 如果需要在登入成功后做额外的事情。需要使用：
    ```
    .successHandler(myHandler)
    ```
    * myHandler 实现 AuthenticationSuccessHandler
    * 一旦使用`.successHandler()`，`defaultSuccessUrl("/",true)`便会失效
    * 登出拦截同样如此