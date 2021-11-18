# glinde-blog

一个基于Vue+SpringBoot的前后端分离个人博客项目

是在大一暑假末期参考网上的一些教程制作出来的

作为个人网站部署到了 <https://glinde.me>

因为是项目实践的初学者,所以在架构和代码上难免有不规范的地方

因为是给本人个人使用的项目,所以前端页面很多元素和数值都是写死的

## 前端

- Vue
- axios
- Element UI
- mavon-editor ( Markdown编辑器 )
- markdown-it ( Markdown解析器 )

## 后端

- SpringBoot
- MyBatis
- Redis
- JWT

## 目录

- glinde-blog-springboot    后端项目文件
- glinde-blog-vue               前端项目文件
- sql                                   表结构sql
- image                             文档使用的图片

## 页面展示

欢迎

![欢迎](https://github.com/GlindeAL/glinde-blog/blob/051cb42fb1b89a4b7b4cec64555bf52b0190e869/image/welcome.png)

博客展示

![博客展示](https://github.com/GlindeAL/glinde-blog/blob/051cb42fb1b89a4b7b4cec64555bf52b0190e869/image/home.png)

信箱展示

![信箱展示](https://github.com/GlindeAL/glinde-blog/blob/051cb42fb1b89a4b7b4cec64555bf52b0190e869/image/mailbox.png)

博客编辑

![博客编辑](https://github.com/GlindeAL/glinde-blog/blob/051cb42fb1b89a4b7b4cec64555bf52b0190e869/image/EditBlog.png)

博客管理

![博客管理](https://github.com/GlindeAL/glinde-blog/blob/051cb42fb1b89a4b7b4cec64555bf52b0190e869/image/BlogManagement.png)

信箱管理

![信箱管理](https://github.com/GlindeAL/glinde-blog/blob/051cb42fb1b89a4b7b4cec64555bf52b0190e869/image/MailboxManagement.png)

Tips :

​	需要根据实际情况修改后端配置类CorsConfig中的allowedOrigin

​	只提供了表结构的sql,用户需要手动添加,理论上因为是单人博客,所以只需要新建一个用户

​	
