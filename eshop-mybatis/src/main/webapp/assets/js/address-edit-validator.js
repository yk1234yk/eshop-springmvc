/**
 * 收货地址修改时进行校验
 */
$(function (){
    $("#address-edit-form").bootstrapValidator({
        message:'收货信息验证失败',
        feedbackIcons:{
            valid:'glyphicon glyphicon-ok',
            invalid:'glyphicon glyphicon-remove',
            validating:'glyphicon glyphicon-refresh'
        },
        fields:{
            consigneeName:{
                message: '收货人名称验证失败',
                validators:{
                    notEmpty:{
                        message:'收货人名称不能为空'
                    },
                    stringLength:{
                        min:1,
                        max:20,
                        message:'收货人名称长度必须在1到20之间'
                    }
                }
            },
            consigneeAddress:{
                message:'收货人地址验证失败',
                validators: {
                    notEmpty: {
                        message:'收货人不能为空'
                    },
                    stringLength: {
                        min: 10,
                        max: 30,
                        message:'收货人名称长度必须在10到30之间'
                    }
                }
            },
            consigneePhone:{
                validators:{
                    notEmpty:{
                        message:'手机号不能为空'
                    },
                    regexp:{
                        regexp: /^1(3[0-9]|4[01456789]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\d{8}$/,
                        message:'手机号格式不正确'
                    }
                }
            },
            postcode:{
                validators:{
                    notEmpty:{
                        message:'邮编不能为空'
                    },
                    regexp:{
                        regexp:/^[1-9][0-9]{5}$/,
                        message:'邮编格式不正确'
                    }
                }
            }
        }
    });
});