/**
 * Created by ulongx on 2017/2/21.
 */

jQuery(function() {
    //提示信息fn()
    function messages(inner){
        var $model = jQuery(".bs-example-modal-sm");
        $model.find('.modal-content').text(inner);
        $model.modal('show');
    }
    jQuery("#reqbtn").unbind(); //解绑之前的监听事件
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
            var name="姓名不能为空!";
            //调用提示信息函数
            messages(name);
            /*var $model = jQuery(".bs-example-modal-sm");
            $model.find('.modal-content').text("姓名不能为空!");
            $model.modal('show');*/
        }else if(emailAddress=='') {
            //邮箱地址不能为空
            var mail="邮箱地址不能为空!";
            messages(mail);
        }else if(!regmail.test(emailAddress)){
            //邮箱不符合规则
            var rightmail="请输入合适的邮箱地址!";
            messages(rightmail);
        }else if(state=='') {
           //所在地区(省份)
            var state="所在地区不能为空!";
           messages(state);
        }else if(category=='') {
            //行业分类
            var cate="分类不能为空!";
            messages(cate);
        }else if(phoneNumber=='') {
            //电话号码
            var phone="电话号码不能为空!";
            messages(phone);
        }else if(!reg.test(phoneNumber)){
            var rightphone="请输入正确的电话号码!";
            messages(rightphone);
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
            var name="姓名不能为空!";
            //调用提示信息函数
            messages(name);
            /*var $model = jQuery(".bs-example-modal-sm");
             $model.find('.modal-content').text("姓名不能为空!");
             $model.modal('show');*/
        }else if(emailAddress=='') {
            //邮箱地址不能为空
            var mail="邮箱地址不能为空!";
            messages(mail);
        }else if(!regmail.test(emailAddress)){
            //邮箱不符合规则
            var rightmail="请输入合适的邮箱地址!";
            messages(rightmail);
        }else if(state=='') {
            //所在地区(省份)
            var state="所在地区不能为空!";
            messages(state);
        }else if(category=='') {
            //行业分类
            var cate="分类不能为空!";
            messages(cate);
        }else if(phoneNumber=='') {
            //电话号码
            var phone="电话号码不能为空!";
            messages(phone);
        }else if(!reg.test(phoneNumber)){
            var rightphone="请输入正确的电话号码!";
            messages(rightphone);
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
