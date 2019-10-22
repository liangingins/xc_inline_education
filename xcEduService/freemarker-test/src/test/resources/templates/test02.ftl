<!DOCTYPE html>
<html>
<head>
    <meta charset="utf‐8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name}!
<table>
    <tr>
        <td>编号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>生日</td>
        <td>金钱</td>
    </tr>
    <#--遍历list-->
    <#--判断是否为空-->
    <#if stus??>
    <#list stus as stu >
        <tr>
            <td>${stu_index+1}</td>
            <td <#if stu.name=='小明'>style="background-color: red"</#if>>${stu.name}</td>
            <td>${stu.age}</td>
            <#--日期处理 为空时的默认值处理-->
            <td>${(stu.birthday?datetime)!''}</td>
            <td>${stu.money}</td>
        </tr>
    </#list>
    </#if>
</table>
    <#--遍历map 方式一-->
    <br><br>
    <#list stuMap?keys as key>
        <tr>
            <td>${key_index}</td>
            <td>${stuMap[key].name}</td>
            <td>${stuMap[key].age}</td>
            <td>${stuMap[key].money}</td>
            <#--<td>${key_has_next}</td>-->
        </tr>
    </#list>
    <#--遍历map 方式二-->
    <br><br>
    <tr>
        <td>${stuMap['stu1'].name}</td>
        <td>${stuMap['stu1'].age}</td>
        <td>${stuMap['stu1'].money}</td>
    </tr>
    <tr>
        <td>${stuMap['stu2'].name}</td>
        <td>${stuMap['stu2'].age}</td>
        <td>${stuMap['stu2'].money}</td>
    <#--<td>${key_has_next}</td>-->
    </tr>
<br>
    <#--map遍历方式三-->
    <#--!''默认值处理 避免为空报错-->
    ${(stuMap.stu1.name)!''}<br>
    ${(stuMap.stu1.age)!''}
    ${stuMap.stu1.money!''}

    <#--转成字符串输出-->
    <br>
    ${point?c}
<#--将json转为对象-->
<#assign text="{'bank':'工商银行','account':'123424234434'}" />
<#assign data=text?eval />
开户行：${data.bank} 账号：${data.account}


</body>
</html>
