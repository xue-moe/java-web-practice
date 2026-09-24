# Java Web 项目练习册

三个适合大学阶段练习的小型管理应用：员工档案、图书借阅和课程成绩。每个项目都有可操作的浏览器演示、需求说明和扩展题；员工档案还提供 JSP、Servlet、JDBC 与 MySQL 的完整后端示例。

## 在线演示

打开 [项目演示页](https://xue-moe.github.io/java-web-practice/)。无需安装环境，可以直接新增、编辑、删除、搜索数据并尝试业务规则。

演示使用浏览器 localStorage 保存虚构样例，只保存在当前浏览器中，不连接真实数据库。请勿输入真实的员工、读者或学生信息。

GitHub Pages 只发布这套静态浏览器演示，不会运行 Java Servlet 或 MySQL。Java Web 后端可用仓库中的 Docker Compose 启动；如果要让后端长期公开访问，还需要一台独立的容器主机。

## 三个项目

| 项目 | 已实现的演示 | 学习重点 |
| --- | --- | --- |
| [01 员工档案管理](assignments/01-employee-management.md) | 浏览器演示 + 完整 Java Web 后端 | Servlet、JSP、JDBC、增删改查、参数校验 |
| [02 图书借阅管理](assignments/02-library-management.md) | 图书档案、库存编辑、借出与归还 | 外键、库存约束、事务、多表查询 |
| [03 课程成绩管理](assignments/03-grade-management.md) | 成绩录入、编辑、删除、统计 | 分数校验、聚合查询、空状态处理 |

图书和成绩项目的演示已经可以实际操作；练习说明和 SQL 表结构引导你把它们从浏览器存储改造成 Servlet/JDBC/MySQL 应用。

## 直接打开演示

可以从 GitHub Pages 访问，也可以下载仓库后用浏览器打开 demo/index.html。页面不需要第三方服务。若浏览器限制本地文件存储，可从 demo/ 目录启动一个静态文件服务器。

## 运行员工档案 Java Web 后端

需要 JDK 8 或更高版本、Maven 3.8+、Tomcat 9 和 MySQL 8。也可以用 Docker Compose 一键启动。

~~~sh
docker compose up --build
~~~

打开 http://localhost:8080。首次启动会创建数据库并加入三条虚构员工记录。停止服务：

~~~sh
docker compose down
~~~

不使用 Docker 时：

1. 执行 db/init.sql。
2. 设置 DB_URL、DB_USER、DB_PASSWORD 数据库环境变量。
3. 在仓库根目录运行 mvn package。
4. 把 target/hr-management.war 放入 Tomcat webapps 目录并启动 Tomcat。
5. 打开 http://localhost:8080/hr-management/。

## 从一次请求读懂代码

~~~mermaid
flowchart LR
    A[浏览器] --> B[EncodingFilter]
    B --> C[StaffInfoListServlet]
    C --> D[StaffDao]
    D --> E[(MySQL)]
    C --> F[staff_list.jsp]
    F --> A
~~~

Servlet 读取请求并组织页面数据，DAO 执行 SQL，JSP 渲染结果。代码使用 PreparedStatement 绑定查询参数，JDBC 资源通过 try-with-resources 关闭，JSP 用 JSTL 输出用户输入。

## 目录

~~~text
demo/                   三个项目的交互式浏览器演示
src/main/java/          员工项目的 Servlet、DAO、JavaBean 和校验
src/main/webapp/        JSP 页面、样式和交互脚本
assignments/            需求、实现路线、SQL 表结构和扩展题
db/init.sql             员工项目数据库初始化脚本
.github/workflows/      GitHub Pages 自动发布配置
~~~

这是用于学习的示例，不包含登录、角色权限、审计和备份。公开演示只使用虚构数据。
