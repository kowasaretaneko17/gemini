/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.utils;

import com.hekiraku.gemini.entity.dto.MailDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

import static com.hekiraku.gemini.common.enums.MailEnums.M_HEKIRAKU_SOURCE;

/**
 * 邮件发送工具
 *
 * @author bytedance<bytedance @ bytedance.com>
 * @date 03/24/2020 4:06 下午
 */
@Slf4j
public class MailUtils {
    public static void sendMail(MailDto mailDto) {
        // 1. 创建一封邮件
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", "smtp.qq.com");   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        // 设置通过ssl协议使用465端口发送、使用默认端口（25）时下面三行不需要
        // 然后linux服务器上默认的25端口被封了。所以改一下。
        props.setProperty("mail.smtp.socketFactory.port", "465");// 设置ssl端口
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // 用于连接邮件服务器的参数配置（发送邮件时才需要用到）
        Session session= Session.getDefaultInstance(props);        // 根据参数配置，创建会话对象（为了发送邮件准备的）
        session.setDebug(true);//查看log
        MimeMessage message = new MimeMessage(session);     // 创建邮件对象
        OutputStream out = null;
        /*
         * 也可以根据已有的eml邮件文件创建 MimeMessage 对象
         * MimeMessage message = new MimeMessage(session, new FileInputStream("MyEmail.eml"));
         */

        // 2. From: 发件人
        //    其中 InternetAddress 的三个参数分别为: 邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
        //    真正要发送时, 邮箱必须是真实有效的邮箱。

        try {
            message.setFrom(new InternetAddress(mailDto.getMailSource(), mailDto.getMailSourceNickName(), "UTF-8"));

            // 3. To: 收件人
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailDto.getMailTarget(), mailDto.getMailTargetNickName(), "UTF-8"));
            //    To: 增加收件人（可选）
            //message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("dd@receive.com", "USER_DD", "UTF-8"));
            //    Cc: 抄送（可选）
            //message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));
            //    Bcc: 密送（可选）
            //message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));

            // 4. Subject: 邮件主题
            message.setSubject(mailDto.getSubject(), "UTF-8");

            // 5. Content: 邮件正文（可以使用html标签）
            message.setContent(mailDto.getContent(), "text/html;charset=UTF-8");

            // 6. 设置显示的发件时间
            message.setSentDate(Optional.ofNullable(mailDto.getSentDate()).orElseGet(()->new Date()));

            // 7. 保存前面的设置
            message.saveChanges();
            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();

            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            //
            //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
            //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
            //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
            //
            //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
            //           (1) 邮箱没有开启 SMTP 服务;
            //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
            //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
            //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
            //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
            //
            //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
            transport.connect(M_HEKIRAKU_SOURCE.getCode(), M_HEKIRAKU_SOURCE.getDesc());

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

            // 7. 关闭连接
            transport.close();

            // 8. 将该邮件保存到本地
            out = new FileOutputStream("to"+mailDto.getMailTargetNickName()+ LocalDate.now());
            message.writeTo(out);
            out.flush();
        }catch (UnsupportedEncodingException e) {
            log.info("邮件发送：不支持的编码",e);
        } catch (FileNotFoundException e) {
            log.info("邮件发送：找不到文件",e);
        } catch (IOException e) {
            log.info("邮件发送：io错误",e);
        } catch (MessagingException e) {
            log.info("邮件发送：发送错误",e);
        }finally {
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    log.info("邮件发送：关闭流错误",e);
                }
            }
        }
    }
    public static void main(String[] args){
        MailDto mailDto = new MailDto();
        mailDto.setMailSource("1239407570@qq.com");
        mailDto.setMailTarget("1510381250@qq.com");
        mailDto.setSubject("test-gemini");
        mailDto.setContent("验证码：123456");
        sendMail(mailDto);
    }
}
