/**
 * Created by ulongx on 2017/2/21.
 */

jQuery(function() {
    jQuery("#reqbtn").click(function(){
        console.log('1');

        var url="/reqsimple";
        var name=jQuery("#name").val();
        var companyName=jQuery("#company").val();
        var emailAddress=jQuery("#emailAddress").val();
        var state=jQuery("#state").val();
        var category=jQuery("#category").val();
        var phoneNumber=jQuery("#phoneNumber").val();
        var projectContext=jQuery("#projectContext").val();
        //有效性验证(电话号码验证)
        var reg=/^1[3|5][0-9]\d{4,8}$/;

        if(name=='') {
            //姓名不能为空
            //name="姓名不能为空";
            alert("姓名不能为空!");
        }else if(emailAddress=='') {
            //邮箱地址不能为空
            alert("邮箱地址不能为空!");
        }else if(state=='') {
           //所在地区(省份)
           alert("所在地区不能为空!");
        }else if(category=='') {
            //行业分类
            alert("分类不能为空!");
        }else if(phoneNumber=='') {
            //电话号码
            alert("电话号码不能为空!");
        }else if(!reg.test(phoneNumber.value)){
            alert("请输入正确的电话号码!")
        }else{
            jQuery.post(url,{name:name,companyName:companyName,emailAddress:emailAddress,state:state,category:category
                         ,phoneNumber:phoneNumber,projectContext:projectContext},function(result){
                //console.log(JSON.stringify(result));
                if(result.code=="0"){
                alert(result.message)
                }
            });
        }
    });
});
