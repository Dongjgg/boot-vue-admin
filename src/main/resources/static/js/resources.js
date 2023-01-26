//把所有js和css放到一起
document.write(
   `
    <link rel="stylesheet" href="css/bootstrap.min.css">   <!--样式库-->
    <link rel="stylesheet" href="css/bootstrap-icons.css"> <!--图标库-->
    <link rel="stylesheet" href="css/global.css">  <!--全局css-->
    <link rel="stylesheet" href="css/message.css"> <!--消息组件-->
    
    <script src="js/jquery-3.6.0.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/vue.min.js"></script>
    <script src="js/message.min.js"></script>
    `
)