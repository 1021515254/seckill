package com.rk.web;

import com.rk.dto.Exposer;
import com.rk.dto.SecKillExecution;
import com.rk.dto.SecKillResult;
import com.rk.entity.Seckill;
import com.rk.enums.SecKillStatEnum;
import com.rk.exception.RepeatKillException;
import com.rk.exception.SecKillCloseException;
import com.rk.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seckill")
public class SecKillController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SecKillService secKillService;

    /**
     * 获取秒杀列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //获取秒杀列表
        List<Seckill> list = secKillService.getSecKillList();
        model.addAttribute("list", list);
        //list.jsp + model = modelAndView
        return "list";//WEB-INF/JSP/list.jsp
    }

    /**
     * 获取单个秒杀商品
     * @param seckillId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = secKillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    /**
     * 暴露秒杀地址
     * @param seckillId
     * @return
     */
    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SecKillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SecKillResult<Exposer> result;
        try {
            Exposer exposer = secKillService.exportSecKillUrl(seckillId);
            result = new SecKillResult<Exposer>(true, exposer);//正确返回数据信息
        } catch (Exception e) {
            result = new SecKillResult<Exposer>(false, e.getMessage());//错误返回异常信息
        }
        return result;
    }

    /**
     * 执行秒杀
     * @param seckillId
     * @param md5
     * @param phone
     * @return
     */
    @RequestMapping(value = "/{seckillId}/{md5}/execute",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SecKillResult<SecKillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killphone", required = false) Long phone) {
        if(phone == null){
            return new SecKillResult<SecKillExecution>(false,"未注册");
        }
        SecKillResult<SecKillExecution> result;
        try {
            SecKillExecution secKillExecution = secKillService.executeSecKill(seckillId, phone, md5);
            result = new SecKillResult<SecKillExecution>(true, secKillExecution);
            return result;
        } catch (SecKillCloseException e1) {//接收secKillService.executeSecKill(seckillId, phone, md5);抛出的异常，
            SecKillExecution secKillExecution = new SecKillExecution(seckillId, SecKillStatEnum.END);
            return  new SecKillResult<SecKillExecution>(true, secKillExecution);
        } catch (RepeatKillException e2) {
            SecKillExecution secKillExecution = new SecKillExecution(seckillId, SecKillStatEnum.REPEAT_KILL);
            return  new SecKillResult<SecKillExecution>(true, secKillExecution);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            SecKillExecution secKillExecution = new SecKillExecution(seckillId, SecKillStatEnum.INNER_ERROR);
            return  new SecKillResult<SecKillExecution>(true, secKillExecution);
        }
    }

    /**
     * 获取当前系统时间
     * @return
     */
    @RequestMapping(value = "time/now",method = RequestMethod.GET)
    @ResponseBody
    public SecKillResult<Long> time(){
        Date date = new Date();
        return new SecKillResult<Long>(true,date.getTime());
    }
}
