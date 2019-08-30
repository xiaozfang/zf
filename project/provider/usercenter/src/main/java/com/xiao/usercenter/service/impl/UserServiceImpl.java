package com.xiao.usercenter.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.common.constant.MqConfigConstant;
import com.xiao.common.constant.MsgLogStatusConstant;
import com.xiao.common.model.LoginUser;
import com.xiao.common.model.RoleBaseInfo;
import com.xiao.common.response.BaseDataResponse;
import com.xiao.common.response.BaseResponse;
import com.xiao.dao.entity.MsgLog;
import com.xiao.dao.entity.UserInfo;
import com.xiao.dao.mapper.MsgLogMapper;
import com.xiao.dao.mapper.UserInfoMapper;
import com.xiao.dao.mapper.UserRoleInfoMapper;
import com.xiao.usercenter.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements IUserService, RabbitTemplate.ConfirmCallback {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserRoleInfoMapper userRoleInfoMapper;
    @Autowired
    private MsgLogMapper msgLogMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public BaseDataResponse<UserInfo> getUserInfo(int userid) {
        BaseDataResponse<UserInfo> response = new BaseDataResponse<>();
        UserInfo user = userInfoMapper.selectUserInfoByUserId(userid);
        response.setData(user);
        response.setCode(1);
        return response;
    }

    @Override
    public BaseResponse addUser(UserInfo user) {
        userInfoMapper.insertSelective(user);
        return null;
    }

    @Override
    public BaseResponse editUser(UserInfo user) {
        userInfoMapper.updateByPrimaryKey(user);
        return null;
    }

    @Override
    public BaseResponse deleteUser(String userid) {
//        User user = userInfoMapper.deleteByPrimaryKey(userid);
        return null;
    }

    @Override
    public LoginUser login(String username, String password) {
        log.info("登录成功");
        LoginUser loginUser = new LoginUser();
        UserInfo user = userInfoMapper.login(username, password);
        if (user == null) {
            return null;
        }
        List<RoleBaseInfo> roles = userRoleInfoMapper.selectRolesByUserId(user.getUserid());
        loginUser.setUserid(user.getUserid());
        loginUser.setUsername(user.getLastname());
        loginUser.setRoles(roles);
        return loginUser;
    }

    @Override
    public BaseDataResponse<UserInfo> test() {

        String msgId = UUID.randomUUID().toString();
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setStatus(MsgLogStatusConstant.DELIVERING);
        msgLog.setExchange("test");
        msgLog.setRoutingKey("hello");
        msgLog.setMsg("confirm");
//        msgLogMapper.insertSelective(msgLog);
//        Message msg = MessageBuilder.withBody("消息1！！".getBytes()).setContentType(MessageProperties.DEFAULT_CONTENT_TYPE).build();
        Message msg1 = MessageBuilder.withBody(JSONObject.toJSONString(msgLog).getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON).build();
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setMandatory(true);
//        rabbitTemplate.convertAndSend(MqConfigConstant.TOPIC_EXCHANGE, MqConfigConstant.TOPIC_ROUTING_KEY_1, msg, new CorrelationData("1"+msgId));
        rabbitTemplate.convertAndSend(MqConfigConstant.TOPIC_EXCHANGE, MqConfigConstant.TOPIC_ROUTING_KEY_2, msg1, new CorrelationData("2"+msgId));
//        rabbitTemplate.convertAndSend(MqConfigConstant.FANOUT_EXCHANGE,"", "消息3！！", new CorrelationData("3"+msgId));
        return null;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        MsgLog msgLog = new MsgLog();
        String msgId = correlationData.getId();
//        msgLog.setMsgId(msgId);
        if (ack) {
            log.info("消息唯一标识:{}\t状态:{}", msgId, "SUCCESS");
//            msgLog.setStatus(MsgLogStatusConstant.DELIVER_SUCCESS);
//            msgLogMapper.updateByPrimaryKeySelective(msgLog);
        } else {
            log.info("消息唯一标识:{}\t状态:{}\t原因:{}", msgId, "FAIL", cause);
//            msgLog.setStatus(MsgLogStatusConstant.DELIVER_FAIL);
//            msgLogMapper.updateByPrimaryKeySelective(msgLog);
        }
    }
}
