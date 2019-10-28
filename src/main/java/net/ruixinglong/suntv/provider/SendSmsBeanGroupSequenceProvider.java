package net.ruixinglong.suntv.provider;

import net.ruixinglong.suntv.bean.SendSmsBean;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class SendSmsBeanGroupSequenceProvider implements DefaultGroupSequenceProvider<SendSmsBean> {

    @Override
    public List<Class<?>> getValidationGroups(SendSmsBean sendSmsBean) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(SendSmsBean.class);
//        System.out.println(sendSmsBean);
//        System.out.println(sendSmsBean.getCaptcha());
        if (sendSmsBean != null && sendSmsBean.getCaptcha().equals("123")) {
            defaultGroupSequence.add(SendSmsBean.cellphone.class);
        }
        return defaultGroupSequence;
    }
}
