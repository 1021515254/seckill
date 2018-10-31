//存放主要交互逻辑代码
// 模块化，java里采用分包
var seckill = {
    //封装秒杀相关ajax代码
    url: {
        now: function () {
            return "/seckill/time/now";
        },
        exposer: function (seckillId) {
            return "/seckill/" + seckillId + "/exposer";
        },
        killURL:function (seckillId,md5) {
            return '/seckill/'+seckillId+'/'+md5+'/execute';
        }
    },
    //处理秒杀逻辑
    handleSecKillKill: function (seckillId, node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.ajax({
            url: seckill.url.exposer(seckillId),
            type: 'POST',
            success: function (result) {
                if (result && result['success']) {
                    var exposer = result["data"];
                    //是否开启秒杀
                    if (exposer["exposer"]) {
                        var md5 = exposer["md5"];
                        //获取秒杀地址
                        var killURL = seckill.url.killURL(seckillId,md5);
                        console.log(killURL);
                        //绑定一次点击事件
                        $("#killBtn").one("click",function () {
                            $(this).addClass('disabled');//禁用按钮
                            //发送请求
                            $.ajax({
                                url:killURL,
                                type:'POST',
                                success:function (result) {
                                    if(result && result['success']){
                                        var killResult = result['data'];
                                        var state = killResult['state'];
                                        var stateInfo = killResult['stateInfo'];
                                        //显示秒杀结果
                                        node.html('<span class="label label-success">' + stateInfo + '</span>');
                                    }
                                }
                            })
                        })
                        node.show();
                    }else{
                        //未开启秒杀
                        var start = exposer['start'];
                        var end = exposer['end'];
                        var now = exposer['now'];
                        //重新判断秒杀时间
                        seckill.countdown(seckillId,start,now,end);
                    }
                } else {
                    console.log("result:" + result);
                }
            }
        })
    },
    //秒杀时间判断
    countdown: function (seckillId, startTime, nowTime, endTime) {
        var seckillBox = $("#seckill-box");
        if (nowTime > endTime) {
            //秒杀结束
            seckillBox.html("秒杀结束");
        } else if (nowTime < startTime) {
            //秒杀未开始
            //计时事件绑定
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime("秒杀计时：%D天 %H时 %M分 %S秒");
                seckillBox.html(format);
            }).on('finish', function () {//倒计时结束，回调时间
                //处理秒杀逻辑
                seckill.handleSecKillKill(seckillId, seckillBox);
            });
        } else {
            //处理秒杀逻辑
            seckill.handleSecKillKill(seckillId, seckillBox);
        }
    },
    //验证手机号
    validatePhone: function (phone) {
        //直接判断对象会看对象是否为空,空就是undefine就是false; isNaN 非数字返回true
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    //详情页面秒杀逻辑
    detail: {
        //详情页初始化
        inti: function (params) {
            //用户和手机验证，计时交互
            //1.规划交互流程
            var killPhone = $.cookie('killphone');
            //验证手机号
            if (!seckill.validatePhone(killPhone)) {
                //绑定手机号
                var killPhoneModal = $("#killPhoneModal");
                killPhoneModal.modal({
                    show: true,//显示弹出层
                    backdrop: 'static',//禁止位置关闭
                    keyboard: false//关闭键盘事件
                });
                $("#killPhoneBtn").click(function () {
                    var inputPhone = $("#killPhoneKey").val();//输入手机号
                    if (seckill.validatePhone(inputPhone)) {
                        $.cookie("killphone", inputPhone, {expires: 7, path: '/seckill'});//在那个地址下生效。有效期。存入cookie
                        window.location.reload();//刷新页面
                    } else {
                        $("#killPhoneMessage").hide().html('<label class="label label-danger">手机号错误</label>').show(300);
                    }
                });
            }
            //已经登录
            //计时交互
            var seckillId = params['seckillId'];
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            $.ajax({
                url: seckill.url.now(),
                success: function (result) {
                    if (result && result['success']) {
                        var nowTime = result['data'];
                        //时间判断
                        seckill.countdown(seckillId, startTime, nowTime, endTime);
                    } else {

                    }
                }

            })
        },

    }
}