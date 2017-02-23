/**
 * Created by ulongx on 2017/2/21.
 */

jQuery(function() {
    jQuery("#reqbtn").unbind();
    jQuery("#reqbtn").click(function(){

        var url="/reqsimple";
        var name=jQuery("#name").val();
        var companyName=jQuery("#company").val();
        var emailAddress=jQuery("#emailAddress").val();
        var state=jQuery("#state").val();
        var category=jQuery("#category").val();
        var phoneNumber=jQuery("#phoneNumber").val();
        var projectContext=jQuery("#projectContext").val();
        //有效性验证(电话号码验证)
        var reg=/^1[34578]\d{9}$/;
        //邮箱有效性验证
        var regmail=/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;

        if(name=='') {
            //姓名不能为空
            //name="姓名不能为空";
            alert("姓名不能为空!");
        }else if(emailAddress=='') {
            //邮箱地址不能为空
            alert("邮箱地址不能为空!");
        }else if(!regmail.test(emailAddress)){
            //邮箱不符合规则
            alert("请输入合适的邮箱地址!")
        }else if(state=='') {
           //所在地区(省份)
           alert("所在地区不能为空!");
        }else if(category=='') {
            //行业分类
            alert("分类不能为空!");
        }else if(phoneNumber=='') {
            //电话号码
            alert("电话号码不能为空!");
        }else if(!reg.test(phoneNumber)){
            alert("请输入正确的电话号码!")
        }else{
            jQuery.post(url,{name:name,companyName:companyName,emailAddress:emailAddress,state:state,category:category
                         ,phoneNumber:phoneNumber,projectContext:projectContext},function(result){
                if(result.code=="0"){
                alert(result.message)
                }
            });
        }
    });
    /*导航栏的索取样品*/
    jQuery("#reqbttn").unbind(); //解绑之前的监听事件
    jQuery("#reqbttn").click(function(){
        console.log('1');

        var url="/reqsimple";
        var name=jQuery("#name1").val();
        var companyName=jQuery("#company1").val();
        var emailAddress=jQuery("#emailAddress1").val();
        var state=jQuery("#state1").val();
        var category=jQuery("#category1").val();
        var phoneNumber=jQuery("#phoneNumber1").val();
        var projectContext=jQuery("#projectContext1").val();
        //有效性验证(电话号码验证)
        var reg=/^1[34578]\d{9}$/;
        //邮箱有效性验证
        var regmail=/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
        if(name=='') {
            //姓名不能为空
            //name="姓名不能为空";
            alert("姓名不能为空!");
        }else if(emailAddress=='') {
            //邮箱地址不能为空
            alert("邮箱地址不能为空!");
        }else if(!regmail.test(emailAddress)){
            //邮箱不符合规则
            alert("请输入合适的邮箱地址!")
        }else if(state=='') {
            //所在地区(省份)
            alert("所在地区不能为空!");
        }else if(category=='') {
            //行业分类
            alert("分类不能为空!");
        }else if(phoneNumber=='') {
            //电话号码
            alert("电话号码不能为空!");
        }else if(!reg.test(phoneNumber)){
            alert("请输入正确的电话号码!")
        }else{
            jQuery.post(url,{name:name,companyName:companyName,emailAddress:emailAddress,state:state,category:category
                ,phoneNumber:phoneNumber,projectContext:projectContext},function(result){
                if(result.code=="0"){
                    alert(result.message)
                }
            });
        }
    });
});
