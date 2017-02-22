<!DOCTYPE html>
<html>
<head>
    <title >索取样品</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <body>
        <p><span>昵称：${name!}</span></p>
        <p><span>email：${emialAddress!}</span> </p>
        <p><span>所在省份：${state!}</span></p>
        <p><span>分类：${category!}</span></p>
        <p><span>联系电话：${phoneNumber!}</span></p>
        <p><span>项目描述：${projectContext!}</span></p>
        <br/>
        <br/>
        <br/>
        <p> 发送时间：${subscriptionDate?string('yyyy-MM-dd')}</p>

    </body>
</html>
